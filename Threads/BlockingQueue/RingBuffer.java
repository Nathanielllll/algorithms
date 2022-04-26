package BlockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RingBuffer {
    private Object[] list;
    private int capacity;
    private volatile int writePos;
    private volatile int readPos;

    private ReentrantLock lock;
    private Condition product;
    private Condition consumer;

    public RingBuffer(int capacity) {
        list = new Object[capacity];
        this.capacity = capacity;
        writePos = 0;
        readPos = 0;

        lock = new ReentrantLock();
        product = lock.newCondition();
        consumer = lock.newCondition();
    }

    private boolean isFull() {
        return (writePos + 1) % capacity == readPos;
    }

    private boolean isEmpty() {
        return writePos == readPos;
    }

    public void put(Object value) {
        try {
            lock.lock();
            while (isFull()) {
                product.await();
            }

            list[writePos] = value;
            writePos = (writePos + 1) % capacity;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public Object get() {
        Object value = null;
        try {
            lock.lock();
            while (isEmpty()) {
                consumer.await();
            }
            value = list[readPos];
            readPos = (readPos + 1) % capacity;
            product.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        RingBuffer blockingQueue2 = new RingBuffer(10);

        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName() + " is consuming: ");
                    System.out.println(blockingQueue2.get());
                    System.out.println();
                }
            }, "consumer" + i).start();
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
