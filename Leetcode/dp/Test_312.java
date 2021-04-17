package dp;

/*
有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组nums中。

现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得nums[left] * nums[i] * nums[right]个硬币。这里的left和right代表和i相邻的两个气球的序号。注意当你戳破了气球 i 后，气球left和气球right就变成了相邻的气球。

求所能获得硬币的最大数量。

说明:

你可以假设nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
示例:

输入: [3,1,5,8]
输出: 167
解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
    coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

 */
public class Test_312 {
    private static int maxCoin;

    // 带记忆化的dfs
    public static int maxCoins(int[] nums) {
        maxCoin = 0;
        //空数组处理
        if (nums == null) {
            return maxCoin;
        }
        //加虚拟边界
        int length = nums.length;
        int[] nums2 = new int[length + 2];
        System.arraycopy(nums, 0, nums2, 1, length);
        nums2[0] = 1;
        nums2[length + 1] = 1;
        length = nums2.length;
        //创建缓存数组
        int[][] cache = new int[length][length];
        //调用分治函数
        return dfs(nums2, 0, length - 1, cache);
    }

    // 那么我们换一种划分方式，既然两个子问题都依赖 i 和两个边界，那么我们划分子问题时，i 与两个边界的气球我们都不戳破，
    // 求出 i+1 到 k-1 与 k+1 到 j-1 之间的解。这样两个子问题间的依赖便被消除了，两个边界及气球 k 不被戳破，两个子问题的依赖都不会越过 k 到另一个子问题上，子问题间是相互独立的。
    //
    // 并且在两个子问题解决后，气球序列还剩下 k 与两个边界的气球没有戳破，那么我们用两个子问题的解与戳破 k 与两个边界的最大值即可求出原问题的解。
    //
    // 那么 def( i , j ) 函数的定义则为，不戳破 i 与 j ，仅戳破 i 与 j 之间的气球我们能得到的最大金币数。
    private static int dfs(int[] nums, int start, int end, int[][] cache) {
        if (start + 1 == end) {
            return 0;
        }
        if (cache[start][end] != 0) {
            return cache[start][end];
        }

        int max = 0;
        for (int i = start + 1; i < end; i++) {
            int left = dfs(nums, start, i, cache);
            int right = dfs(nums, i, end, cache);
            int curCoins = nums[i] * nums[start] * nums[end];
            max = Math.max(max, curCoins + left + right);
        }
        cache[start][end] = max;
        return max;
    }


    // 动态规划
    public int maxCoins_1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int[] pointers = new int[len + 2];
        pointers[0] = pointers[len + 1] = 1;
        for (int i = 1; i <= len; i++) {
            pointers[i] = nums[i - 1];
        }
        int[][] dp = new int[len + 2][len + 2];
        //从下往上&&从左往右
        for (int i = len; i >= 0; i--) {
            for (int j = i + 1; j < len + 2; j++) {
                for (int k = i + 1; k <= j - 1; k++) {
                    dp[i][j] = Math.max(
                            dp[i][j],
                            dp[i][k] + dp[k][j] + pointers[i] * pointers[k] * pointers[j]);
                }
            }
        }
        return dp[0][len + 1];
    }
}
