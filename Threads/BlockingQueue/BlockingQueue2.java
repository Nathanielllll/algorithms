package BlockingQueue;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 *
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 *
 */
public class BlockingQueue2<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition product = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t){
        try{
            lock.lock();
            while (lists.size() == MAX) {
                product.await();
            }

            lists.add(t);
            ++count;
            consumer.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public T get(){
        T t = null;
        try{
            lock.lock();
            while (lists.size() == 0) {
                consumer.await();
            }

            t = lists.removeFirst();
            --count;
            product.signalAll();//通知生产者进行生产
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue2<String> blockingQueue2 = new BlockingQueue2<>();

        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName() + " is consuming: ");
                    System.out.println(blockingQueue2.get());
                    System.out.println();
                }
            },"consumer" + i).start();
        }


        //生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    blockingQueue2.put(Thread.currentThread().getName() + " " + j);
                }
            }, "producer" + i).start();
        }
    }
}
