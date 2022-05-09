package array;

import java.util.*;

/**
 * @author luzhi
 * @date 2021/3/27
 */
public class Test_692 {
    public static void main(String[] args) {
        String[] words = {"a", "aa", "aaa"};
        int k = 2;
        System.out.println(topKFrequent(words, k));
    }

    public static List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        // 小顶堆。数量相同的情况下，字段顺序靠后的排在前面；数量不同的情况下，数量少的排在前面。
        PriorityQueue<String> heap = new PriorityQueue<>(
                (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                        w2.compareTo(w1) : count.get(w1) - count.get(w2));

        for (String word : count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(heap.poll());
        }
        Collections.reverse(result);
        return result;
    }


}
