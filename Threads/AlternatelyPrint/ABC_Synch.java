package AlternatelyPrint;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABC_Synch {
    private static int num = 1; //打印“谁”标识符

    private static Lock lock = new ReentrantLock();
    private static final Condition conditionA = lock.newCondition();
    private static final Condition conditionB = lock.newCondition();
    private static final Condition conditionC = lock.newCondition();

    private static CountDownLatch countDownLatch = new CountDownLatch(10); //使用闭锁计数器


    public static void main(String[] args) throws Exception {

        long loop = countDownLatch.getCount(); //初始化打印轮数

        new Thread(() -> {
            for (int i = 1; i <= loop; i++) {
                try {
                    printA();
                } catch (InterruptedException e) {
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= loop; i++) {
                try {
                    printB();
                } catch (InterruptedException e) {
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= loop; i++) {
                try {
                    printC();
                } catch (InterruptedException e) {
                }
            }
        }, "C").start();

        countDownLatch.await();
    }

    //打印‘A’的方法
    public static void printA() throws InterruptedException {
        lock.lock();
        if (num != 1) { //标识符等于1的时候打印A，不等于1的时候阻塞当前线程
            conditionA.await();
        }
        System.out.print(Thread.currentThread().getName());
        num = 2;
        conditionB.signal();
        lock.unlock();
    }

    //打印‘B’的方法
    public static void printB() throws InterruptedException {
        lock.lock();
        if (num != 2) { //标识符等于2的时候打印B
            conditionB.await();
        }
        System.out.print(Thread.currentThread().getName());
        num = 3;
        conditionC.signal();
        lock.unlock();
    }

    //打印‘C’的方法
    public static void printC() throws InterruptedException {
        lock.lock();
        if (num != 3) { //标识符等于3的时候打印A
            conditionC.await();
        }
        System.out.print(Thread.currentThread().getName());
        num = 1;
        conditionA.signal();
        countDownLatch.countDown();
        lock.unlock();
    }

}
