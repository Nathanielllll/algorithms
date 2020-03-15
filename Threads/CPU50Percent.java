import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CPU50Percent {
    public static void main(String args[]) throws InterruptedException {
        Runtime r = Runtime.getRuntime();//获得当前系统的CPU数量，根据这个数值创建对应数量的线程
        ExecutorService pool = Executors.newFixedThreadPool(r.availableProcessors());
        for (int i = 0; i < r.availableProcessors(); i++) {
            pool.execute(new Loop());
        }
        pool.shutdown();
    }
}

class Loop implements Runnable {
    @Override
    public void run() {

        /**
         * busyTime和idleTime如果是ms级别的，对我们宏观而言，其就是cpu负载率busyTime：idleTime。在微观上的切换，我们是看不出来的
         * 如果二者是s级别，我们可以从宏观上来到先是有busyTime时间的100%的cpu负载率，然后是idleTime时间的0%的cpu负载率，二者来回切换
         */
        int busyTime = 10;//可调节参数，单位为ms。50ms后线程休眠50毫秒，然后再经系统调度。该数值越小，则线程被调度得越频繁，则CPU使用率也就越高（平均）
//        int idleTime = busyTime;
        int idleTime = 10;

        while (true) {
            long startTime = System.currentTimeMillis();
//            busy loop:
            while ((System.currentTimeMillis() - startTime) <= busyTime) ;

            try {
                Thread.sleep(idleTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

