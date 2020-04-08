package BlockingQueue;

import java.util.LinkedList;

public class BlockingQueue1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;

    public synchronized void put(T t) {
        // 为什么用while而不是if？
        // 99%的情况下，wait()要和while一起用
        /**
         * 如果是if的情况，队列是满的情况下，线程A就在这里wait()了，因为释放了锁；
         * 此时被拿走一个，此线程A被叫醒了，它会从wait继续往下执行，想往队列里面扔一个。
         * 但是！！它还没往里面扔一个的时候，已经有另外一个线程B往里面扔了一个
         */

        while (lists.size() == MAX) {
            try {
                this.wait();//进入睡眠，并且释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //----------------------------------//
        lists.add(t);
        ++count;
        this.notifyAll(); //通知消费者线程进行消费
    }

    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count--;
        this.notifyAll(); //通知生产者进行生产
        return t;
    }

    public static void main(String[] args) {
        BlockingQueue1<String> blockingQueue1 = new BlockingQueue1<>();
        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(blockingQueue1.get());
                }
            },"c" + i).start();
        }

        //生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    blockingQueue1.put(Thread.currentThread().getName()+" " + j);
                }
            },"p" + i).start();
        }
    }
}
