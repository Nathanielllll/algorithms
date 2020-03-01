import java.util.Stack;

public class MyBlockQueue<T> {
    //push锁
    private final static Object pushLock = new Object();
    //pop锁
    private final static Object popLock = new Object();

    //数据存储
    private Stack<T> stack;
    //队列最大长度
    private  int maxSize = 0;
    //队列最小长度
    private int minSize = 0;
    public MyBlockQueue(int size) {
        this.maxSize = size;
        stack = new Stack<T>();
    }

    public synchronized void push(T t) {
        if(stack.size() >= maxSize) {
            pushLock();
        }
        stack.push(t);
        popUnLock();
    }

    public synchronized T pop() {

        if(stack.size() == minSize) {
            popLock();
        }
        T t = stack.pop();
        pushUnLock();
        return t;
    }

    private void pushLock() {
        synchronized (pushLock) {
            try {
                pushLock.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void pushUnLock() {
        synchronized (pushLock) {
            pushLock.notify();
        }
    }

    private  void popLock() {
        synchronized (popLock) {
            try {
                popLock.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void  popUnLock() {
        synchronized (popLock) {
            popLock.notify();
        }
    }

}
