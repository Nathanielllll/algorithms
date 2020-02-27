public class Singleton {
    /**
     * uniqueInstance = new Singleton();分成三步
     * 1、为uniqueInstance分配内存空间
     * 2、初始化uniqueInstance
     * 3、将uniqueInstance指向这个内存空间
     * 因此要有volatile来防止指令重排
     */
    private volatile static Singleton uniqueInstance;

    private Singleton(){
    }

    public static Singleton getUniqueInstance(){
        //首选判断uniqueInstance是否被初始化过
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (Singleton.class){
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
