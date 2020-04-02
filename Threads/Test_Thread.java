import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test_Thread {
    static Object lock = new Object();
    private static volatile int num1 = 0;

    public static void test1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (; num1 <= 100; ) {
                        System.out.println(Thread.currentThread().getName() + ":" + num1++);

                        try {
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    lock.notifyAll();
                }
            }
        };

        Thread t1 = new Thread(runnable, "偶数");
        Thread t2 = new Thread(runnable, "奇数");

        t1.start();
        t2.start();
    }


    private static volatile int num = 1;
    private static volatile boolean flag = false;
    private static ReentrantLock lock1 = new ReentrantLock();
    private static Condition conditionA = lock1.newCondition();
    private static Condition conditionB = lock1.newCondition();
    private static Condition conditionC = lock1.newCondition();

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void test2() throws InterruptedException {
        long loop = countDownLatch.getCount();
        new Thread(()->{
            for (int i = 1; i <= loop; i++) {
                try {
                    printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <= loop; i++) {
                try {
                    printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <= loop; i++) {
                try {
                    printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        countDownLatch.await();
    }

    public static void printA() throws InterruptedException {
        lock1.lock();
        if (num != 1) {
            conditionA.await();
        }
        System.out.println(Thread.currentThread().getName());
        num = 2;
        conditionB.signal();
        lock1.unlock();
    }

    public static void printB() throws InterruptedException {
        lock1.lock();
        if (num != 2) {
            conditionB.await();
        }
        System.out.println(Thread.currentThread().getName());
        num = 3;
        conditionC.signal();
        lock1.unlock();
    }

    public static void printC() throws InterruptedException {
        lock1.lock();
        if (num != 3) {
            conditionC.await();
        }
        System.out.println(Thread.currentThread().getName());
        num = 1;
        conditionA.signal();
        countDownLatch.countDown();
        lock1.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        test2();
    }


}
