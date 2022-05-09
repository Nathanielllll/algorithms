package hashtable;

import java.util.HashMap;
import java.util.Map;

public class Test_974 {
    /*
    给定一个整数数组 nums和一个整数 k ，返回其中元素之和可被 k整除的（连续、非空） 子数组 的数目。

子数组 是数组的 连续 部分。



示例 1：

输入：nums = [4,5,0,-2,-3,1], k = 5
输出：7
解释：
有 7 个子数组满足其元素之和可被 k = 5 整除：
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
示例 2:

输入: nums = [5], k = 9
输出: 0

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /*

    题解：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/solution/he-ke-bei-k-zheng-chu-de-zi-shu-zu-by-leetcode-sol/
 P[j]即为前缀和。
判断子数组的和能否被 k 整除就等价于判断 (P[j] - P[i-1]) mod k == 0，
根据 同余定理，只要 P[j] mod k == P[i-1] mod k，就可以保证上面的等式成立。


因此使用map去记录余下值出现的次数modulus2Cnt，当出现时，在result中加即可

     */
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> modulus2Cnt = new HashMap<>();
        modulus2Cnt.put(0, 1);
        int preSum = 0;
        int result = 0;
        for (int num : nums) {
            preSum += num;
            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            int modulus = (preSum % k + k) % k;
            // 取模余值出现的次数。因为：根据 同余定理，只要 P[j] mod k == P[i-1] mod k，就可以保证上面的等式成立。
            int cnt = modulus2Cnt.getOrDefault(modulus, 0);
            // 在result中加即可
            result += cnt;
            // 需要加上本次出现的次数
            modulus2Cnt.put(modulus, cnt + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println((-101) % 10);
    }
}
