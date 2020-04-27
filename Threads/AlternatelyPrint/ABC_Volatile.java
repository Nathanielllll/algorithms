package AlternatelyPrint;

public class ABC_Volatile {
    private volatile static int num = 1;
    private static int loop = 10;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 1; i <= loop; i++) {
                try {
                    printA();
                } catch (InterruptedException e) {
                }
            }
        }, "A").start();
    }

    //打印‘A’的方法
    public static void printA() throws InterruptedException {
//        if (num != 1) { //标识符等于1的时候打印A，不等于1的时候阻塞当前线程
//            conditionA.await();
//        }
//        System.out.print(Thread.currentThread().getName());
//        num = 2;
//        conditionB.signal();
//        lock.unlock();
    }
}
