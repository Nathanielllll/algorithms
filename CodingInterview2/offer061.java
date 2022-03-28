import java.util.*;

public class offer061 {
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]);
        priorityQueue.offer(new int[]{0, 0});
        Set<String> set = new HashSet<>();

        while (k-- > 0 && !priorityQueue.isEmpty()) {
            int[] pair = priorityQueue.poll();
            result.add(Arrays.asList(nums1[pair[0]], nums2[pair[1]]));

            if (pair[0] + 1 < nums1.length) {
                String key = String.valueOf(pair[0] + 1) + "_" + String.valueOf(pair[1]);
                if (set.add(key)) {
                    priorityQueue.add(new int[]{pair[0] + 1, pair[1]});
                }
            }

            if (pair[1] + 1 < nums2.length) {
                String key = String.valueOf(pair[0]) + "_" + String.valueOf(pair[1] + 1);
                if (set.add(key)) {
                    priorityQueue.add(new int[]{pair[0], pair[1] + 1});
                }
            }
        }
        return result;
    }
}
