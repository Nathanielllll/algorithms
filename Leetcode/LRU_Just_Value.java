import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class LRU_Just_Value {

    public static void main(String[] args) {
        LRU_Just_Value lru = new LRU_Just_Value(3);
        lru.put(5);
        lru.put(4);
        lru.put(2);

        lru.put(6);
        System.out.println(lru.get(6));
        lru.put(9);
        System.out.println(lru.get(4));
        lru.put(7);
    }

    HashSet<Integer> cache;
    LinkedList<Integer> list;
    int cap;

    public LRU_Just_Value(int cap) {
        cache = new HashSet<>();
        list = new LinkedList<>();
        this.cap = cap;
    }

    public void put(int num){
        if (cache.contains(num)) {
            //只要更新一下list即可

            //复杂度高，
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i) == num){
                    list.remove(i);
                }
            }

            list.addFirst(num);
        }else {
            if(list.size()==cap){
                int last_num = list.removeLast();
                cache.remove(last_num);
            }

            cache.add(num);
            list.addFirst(num);
        }
    }

    public boolean get(int num){
        if (cache.contains(num)) {
            put(num);
            return true;
        }else {
            return false;
        }
    }
}

