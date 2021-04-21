package array;

import java.util.HashMap;

/**
 * @author luzhi
 * @date 2021/4/20
 */
public class Test_454 {
    /*
    给定四个包含整数的数组列表A , B , C , D ,计算有多少个元组 (i, j, k, l)，使得A[i] + B[j] + C[k] + D[l] = 0。

为了使问题简单化，所有的 A, B, C, D 具有相同的长度N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过231 - 1 。

例如:

输入:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

输出:
2

解释:
两个元组如下:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     */

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int length = nums1.length;
        HashMap<Integer, Integer> hashMap1 = new HashMap<>();
        HashMap<Integer, Integer> hashMap2 = new HashMap<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                hashMap1.put(nums1[i] + nums2[j], hashMap1.getOrDefault(nums1[i] + nums2[j], 0) + 1);
                hashMap2.put(nums3[i] + nums4[j], hashMap2.getOrDefault(nums3[i] + nums4[j], 0) + 1);
            }
        }

        int result = 0;
        for (int key : hashMap1.keySet()) {
            int tmp = hashMap2.getOrDefault(-1 * key, 0);
            if (tmp > 0) {
                result += hashMap1.get(key) * tmp;
            }
        }
        return result;
    }
}
