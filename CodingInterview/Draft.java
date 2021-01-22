import java.util.*;

public class Draft {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "C++");
        map.put(3, "PHP");
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey() + entry.getValue());
        }
    }
}
