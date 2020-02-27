package bit;

/**
 * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。
 *
 * 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。
 * 你能在O(n)的时间解决这个问题吗？
 * 示例:
 *
 * 输入: [3, 10, 5, 25, 2, 8]
 * 输出: 28
 * 解释: 最大的结果是 5 ^ 25 = 28.
 *
 * 解决这个问题，我们首先需要利用异或运算的一个性质：
 * 如果 a ^ b = c 成立，那么a ^ c = b 与 b ^ c = a 均成立。
 * 即 如果有三个数，满足其中两个数的异或值等于另一个值，那么这三个数的顺序可以任意调换。
 * （说明：利用这条性质，可以不使用第 3 个变量而交换两个变量的值。）
 * 那么如何理解这个性质呢？因为异或运算其实就是 二进制下不进位的加法，你不妨自己举几个例子，在草稿纸上验证一下。
 */
public class Test_421 {
    public static int findMaximumXOR(int[] nums) {
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                int value = nums[i] ^ nums[j];
                result = Math.max(result, value);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println(findMaximumXOR(nums));
    }
}
