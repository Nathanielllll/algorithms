import java.util.*;

public class offer060 {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null) {
            return new int[]{};
        }

        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            maxHeap.add(new int[]{entry.getKey(), entry.getValue()});
        }

        List<Integer> ansList = new ArrayList<>();
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            ansList.add(maxHeap.poll()[0]);
        }

        int[] result = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) {
            result[i] = ansList.get(i);
        }

        return result;
    }
}
