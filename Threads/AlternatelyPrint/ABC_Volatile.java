package AlternatelyPrint;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABC_Volatile {
    private static int num = 1;

    private static Lock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    private static CountDownLatch countDownLatch = new CountDownLatch(10);


    public static void main(String[] args) throws InterruptedException {
        long loop = countDownLatch.getCount();

        new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                printA();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                printB();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < loop; i++) {
                printC();
            }
        }).start();

//        countDownLatch.await();
    }
    private static void printA(){
        lock.lock();
        try{
            if (num != 1) {
                conditionA.await();
            }

            System.out.print(countDownLatch.getCount()+":");
            System.out.print("A");
            num = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void printB(){
        lock.lock();
        try{
            if (num != 2) {
                conditionB.await();
            }

            System.out.print("B");
            num = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void printC(){
        lock.lock();
        try{
            if (num != 3) {
                conditionC.await();
            }

            System.out.print("C ");
            num = 1;
            conditionA.signal();

            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
