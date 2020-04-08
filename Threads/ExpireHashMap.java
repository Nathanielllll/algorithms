import java.util.Date;
import java.util.HashMap;

/**
 * 初始化的时候有个aliveTime，用两个hashmap一个用来存key～ value，一个用来存key～createTime，
 * 然后put的时候两个map都存一下，获取的时候先get一下createTime，判断时间有没有超过aliveTime，
 * 超过就返回null，否则返回value就好啦（时间是用Date类来获取的）
 */
public class ExpireHashMap {
    private HashMap<Integer, Integer> cache;
    private HashMap<Integer, Long> expireMap;
    private long expireTime;

    public ExpireHashMap() {
    }

    public ExpireHashMap(long expireTime) {
        this.cache = new HashMap<>();
        this.expireMap = new HashMap<>();
        this.expireTime = expireTime * 1000;
    }

    public void put(int key, int value){
        long createTime = new Date().getTime();
        cache.put(key, value);
        expireMap.put(key, createTime);
    }

    public int get(int key){
        long currentTime = new Date().getTime();
        long createTime = expireMap.get(key);
        if (currentTime - createTime > expireTime) {
            return -1;
        }else {
            return cache.get(key);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ExpireHashMap hashMap = new ExpireHashMap(2);//2秒
        hashMap.put(1,3);
        Thread.sleep(3000);
        System.out.println(hashMap.get(1));
    }




}
