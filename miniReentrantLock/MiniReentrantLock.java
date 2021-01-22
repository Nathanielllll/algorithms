
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

public class MiniReentrantLock implements Lock {

    /**
     * 资源 -> state
     * 0 表示未加锁状态
     * >0 表示当前lock是加锁状态
     */
    private volatile int state;

    /**
     * 独占模式
     * 同一时刻，只有一个线程可以持有锁，其他线程，在未获取到锁时，会被阻塞
     */
    // 当前独占锁的线程
    private Thread exclusiveOwnerThread;

    /**
     * 需要有两个引用去维护 阻塞队列
     * 1. head 指向队列的头节点
     * 2. tail 指向队列的尾节点
     */
    // head节点对应的线程，就是当前占用锁的线程
    private Node head;
    private Node tail;

    /**
     * 阻塞的线程被封装成什么？
     * Node节点，然后放入到FIFO队列
     */
    static final class Node {
        // 前置节点引用
        Node prev;
        // 后置节点引用
        Node next;
        // 封装的线程本身
        Thread thread;

        public Node(Thread thread) {
            this.thread = thread;
        }

        public Node() {
        }
    }


    /**
     * 模拟公平锁
     * lock的过程
     * 1. 线程进来后发现，当前state == 0，直接抢锁
     * 2. 线程进来后发现，当前state > 0，需要将当前线程入队
     */
    @Override
    public void lock() {
        // 第一次获取到锁时，将state设置为1
        // 第n次重入时，将state设置为n
        acquire(1);
    }

    /**
     * 竞争资源
     * 1. 尝试获取锁，成功则占用锁且返回
     * 2. 抢占锁失败，阻塞当前线程
     *
     * @param arg
     */
    private void acquire(int arg) {
        // 先去尝试获取锁
        if (!tryAcquire(arg)) {
            // 如果获取锁失败了，才会走到这里的逻辑
            Node node = addWaiter();
            acquireQueued(node, arg);
        }
    }

    /**
     * 抢占锁失败，怎么办？
     * 1. 需要封装成node，并加入等待队列
     * 2. 需要将当前队列park掉，处于挂起状态
     *
     * 唤醒后呢？
     * 1. 检查当前node节点是否为head.next节点
     *      head.next节点是拥有抢占权限的线程，其他的都没有
     * 2. 抢占
     *      成功：1. 将当前node设置为head，将老的head出队操作，返回业务层
     *      失败：2. 继续park，等待被唤醒
     *
     * 1. 增加到阻塞队列 addWaiter()
     * 2. 竞争资源 acquireQueued()
     */
    private void acquireQueued(Node node, int arg){
        // 只有当前node成功获取到锁，才会跳出自旋。
        for(;;){
            // 什么情况下，当前node被唤醒之后可以尝试去获取锁呢？
            // 只有一种情况，当前node是head的后继节点，才有这个权限

            // head 节点就是持锁节点
            Node pred = node.prev;
            if(pred == head && tryAcquire(arg)){
                // 说明当前线程竞争锁成功了
                // 1. 设置当前head为当前线程的node
                // 2. 协助原始head出队
                setHead(node);
                pred.next = null; // help GC
                return;
            }

            System.out.println("线程：" + Thread.currentThread().getName() + "。挂起");
            // 将当前线程挂起
            LockSupport.park();

            System.out.println("线程：" + Thread.currentThread().getName() + "。唤醒");
            // 说明时候唤醒park的线程呢？
            // unlock的过程
        }
    }

    /**
     * 当前线程入队
     * @return 当前线程对应的Node节点
     *
     * addWaiter方法执行完毕后，保证当前线程已经入队成功
     */
    private Node addWaiter(){
        Node newNode = new Node(Thread.currentThread());

        // 1. 找到newNode的前置节点pred；
        // 2. 更新newNode.prev = pred
        // 3. CAS 更新tail为newNode
        // 4. 更新pred.next = newNode

        // 前置条件：队列已经有等待着node了，当前node不是第一个入队的node
        Node pred = tail;
        if (pred != null) {
            newNode.prev = pred;
            // 条件成立：说明当前线程成功入队
            if(compareAndSetTail(pred, newNode)){
                pred.next = newNode;
                return newNode;
            }
        }

        // 执行到这里有几种情况？
        // 1. tail == null ---> 队列是空队列
        // 2. cas 设置当前newNode 为tail时，失败了，说明被其他线程抢先一步了
        enq(newNode);
        return newNode;
    }

    /**
     * 自旋入队，只有成功后才返回
     * 1. tail == null 队列是空队列
     * 2. cas 设置当前newNode 为tail时，失败了，说明被其他线程抢先一步了
     */
    private void enq(Node node){
        for(;;){
            // 第一种情况：队列是空
            // ---> 当前线程是第一个抢占锁失败的线程
            // 当前持有锁的线程，并没有设置过任何node，所以作为该线程的第一个后驱节点
            // 给当前持有锁的线程，补充一个node作为head节点。head节点任务时候，都代表当前占用锁的线程
            if (tail == null) {
                // 条件成立：说明当前线程 给 当前持有锁的线程 补充 head操作成功了
                if(compareAndSetHead(new Node())){
                    tail = head;
                    // 并没有直接返回，还是会继续自旋
                }

                // 说明当前队列中已经有node了，这里是一个追加node过程
            }else {
                // 1. 找到newNode的前置节点pred；
                // 2. 更新newNode.prev = pred
                // 3. CAS 更新tail为newNode
                // 4. 更新pred.next = newNode

                // 前置条件：队列已经有等待着node了，当前node不是第一个入队的node
                Node pred = tail;
                if (pred != null) {
                    node.prev = pred;
                    // 条件成立：说明当前线程成功入队
                    if(compareAndSetTail(pred, node)){
                        pred.next = node;
                        return;
                    }
                }
            }
        }
    }

    /**
     * 尝试获取锁，不会阻塞线程
     * true -> 抢占成功
     * false -> 抢占失败
     *
     * @param arg
     * @return
     */
    private boolean tryAcquire(int arg) {
        if (state == 0) {
            // 模拟公平锁，不可以直接抢锁
            // 条件一：当前线程前面没有等待者线程
            // 条件二：使用CAS，因为lock方法可能有多线程调用的情况
            if (!hasQueuedPredecessor() && compareAndSetState(0, arg)) {
                // 抢锁成功
                // 1. 需要将exclusiveOwnerThread设置为当前进入if块中的线程
                exclusiveOwnerThread = Thread.currentThread();
                return true;
            }
        }else if(Thread.currentThread() == this.exclusiveOwnerThread){
            // 锁重入。这里存在并发吗？不存在。因为只有当前加锁的线程才能进来，去修改state
            int c = getState();
            c = c + arg;
            // 越界判断...

            this.state = c;
            return true;
        }

        //1. CAS失败
        //2. state > 0 且 当前线程不是占用着锁
        return false;
    }

    /**
     * true 当前线程前面有等待者线程
     * @return
     *
     * 调用链
     * lock -> acquire -> tryAcquire -> hasQueuedPredecessor (state值为0时，即当前Lock属于无主状态)
     *
     * 什么时候返回false？
     * 1. 当前队列为空
     * 2. 当前线程为head.next节点的线程。head.next在任何时候，都有权利去争取一下lock
     */
    private boolean hasQueuedPredecessor(){
        Node h = head;
        Node t = tail;
        Node s;

        // 条件一：h != t
        // 成立：当前队列已经有node了
        // 不成立： 1. h == t == null   2. h == t == head 第一个获取锁失败的线程，会为当前持有锁的线程，补充创建一个head节点

        // 条件二：前置条件：条件一成立。((s = h.next) == null || s.thread != Thread.currentThread())
        // 排序几个情况
        // 条件2.1 (s = h.next) == null
        //      极端情况，第一个获取锁失败的线程，会为持锁的线程补充创建head节点，然后再自旋入队。1. cas设置tail的时候成功了，2. pred【head】.next = node;
        //      其实想表达的就是：已经有head.next节点了，其他线程再来这时，需要返回true

        // 条件2.2 s.thread != Thread.currentThread() 大概率是判断这个
        //      条件成立：当前线程就不是head.next节点对应的线程
        //      条件不成立：说明当前线程，就是head.next节点对应的线程，需要返回false，回头线程回去竞争锁了。
        return h != t && ((s = h.next) == null || s.thread != Thread.currentThread());
    }

    @Override
    public void unLock() {
        release(1);
    }

    private void release(int arg){
        // 条件成立：说明线程已经完全释放锁了
        // 发现阻塞队里还有好几个睡觉的线程，是不是应该喊醒一个线程
        if(tryRelease(arg)){
            Node head = this.head;

            // 首先要知道有没有等待者，就是去判断head.next == null 说明没有等待者，否则说明有等待者
            if(head.next != null){
                // 公平锁，就是唤醒head.next 节点的线程
                unparkSuccessor(head);
            }
        }
    }

    private void unparkSuccessor(Node node){
        Node s = node.next;
        if (s != null && s.thread != null) {
            LockSupport.unpark(s.thread);
        }
    }

    /**
     * 完全释放锁成功，则返回true
     * 否则，说明当前state > 0，返回false
     * @param arg
     * @return
     */
    private boolean tryRelease(int arg){
        int c = getState() - arg;

        if (getExclusiveOwnerThread() != Thread.currentThread()) {
            throw new RuntimeException("must getLock!");
        }

        // 如果执行到这里，不存在并发，只有当前线程是持有锁的线程，才会来到这里

        // 条件成立：说明当前线程持有的lock已经完全释放了
        if (c == 0) {
            // 需要做什么？
            // 1. ExclusiveOwnerThread置为空
            // 2. 设置state == 0
            this.exclusiveOwnerThread = null;
            this.state = c;
            return true;
        }

        this.state = c;
        return false;
    }

    public void setHead(Node node) {
        this.head = node;
        // 当前node已经是获取锁成功的线程了，就不会把当前线程给wait/park掉了
        node.thread = null;
        node.prev = null;
    }

    public int getState() {
        return state;
    }

    public Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    private static final Unsafe unsafe;
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);

            unsafe = (Unsafe) f.get(null);
            stateOffset = unsafe.objectFieldOffset(
                    MiniReentrantLock.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset(
                    MiniReentrantLock.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset(
                    MiniReentrantLock.class.getDeclaredField("tail"));

        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private final boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    private final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    private final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

}
