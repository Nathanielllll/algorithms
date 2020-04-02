package AlternatelyPrint;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class AlternatelyPrint1 {
    private static byte[] block = new byte[0];
    public static volatile int i = 1;
    public volatile static int j = 1;


    //交替打印1～100的数字
    //使用synchronized、wait、notify来实现
    public static void test1(){
        new Thread(()->{
            synchronized (block){
                for ( ;i <= 100; ) {
                    System.out.println("t1: " + i++);
                    try {
                        block.notifyAll();
                        block.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                block.notifyAll();
            }
        }, "t1").start();


        new Thread(()->{
            synchronized (block){
                for ( ;j <= 100; ) {
                    System.out.println("t2: " + j++);
                    try {
                        block.notifyAll();
                        block.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                block.notifyAll();
            }
        }, "t2").start();
    }

    //交替打印1～100奇偶数
    //使用synchronized、wait、notify来实现
    public static void test2(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                synchronized (block) {
                    for (; i <= 100; ) {
                        System.out.println(Thread.currentThread().getName() + "" + i++);
                        try {
                            block.notifyAll();
                            block.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    block.notifyAll();
                }
            }
        };

        Thread t1 = new Thread(runnable, "偶数");
        Thread t2 = new Thread(runnable, "奇数");

        t2.start();
        t1.start();
    }

    //交替打印1～100
    //用flag、ReentrantLock、Condition来实现
    private volatile static boolean flag = false; //打印“谁”标识符
    private static Lock lock = new ReentrantLock();
    private static final Condition conditionA = lock.newCondition();
    private static final Condition conditionB = lock.newCondition();
    public static void test3(){
        new Thread(() -> {
            for ( ;i <= 100; ) {
                lock.lock();
                if (flag) {
                    try {
                        conditionA.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1:"+ i++);
                flag = !flag;
                conditionB.signal();
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            for ( ;j <= 100; ) {
                lock.lock();
                if (!flag) {
                    try {
                        conditionB.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2:"+ j++);
                flag = !flag;
                conditionA.signal();
                lock.unlock();
            }
        }, "t2").start();
    }


    public static void main(String[] args) {
        test3();
    }

}
