package dp;

/**
 * 题目要求：
 * 给定数组A[0,1...n-1]，求B[0,1...n-1]，要求B[i] = A[0]*A[1]...A[i-1]*A[i+1]...A[n-1]，不能使用除法。
 *
 * 动态规划：
 *
 * 使用纯乘法，还有更高效的思路。定义C[i]=A[0]*A[1]...A[i-1]，那么C[i]=C[i-1]*A[i-1]；
 * 定义D[i]=A[i+1]*...A[n-2]*A[n-1],那么D[i] =D[i+1]*A[i+1]；
 * 因此C[i],D[i]都可以递推地求出来，且B[i]=C[i]*D[i]。
 *
 * 和LeetCode238一样
 */
public class Code_66 {
    public static int[] multiply(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }

        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int temp = 1;//temp即为D[i+1]
        for (int i = res.length - 2; i >= 0; i--) {
            temp = temp * nums[i + 1];
            res[i] = res[i] * temp;
        }
        return res;
    }

    public static int[] multiply_1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }

        int[] res = new int[nums.length];
        int[] C = new int[nums.length];
        int[] D = new int[nums.length];

        C[0] = 1;
        for (int i = 1; i < C.length; i++) {
            C[i] = C[i - 1] * nums[i - 1];
        }

        D[nums.length - 1] = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            D[i] = D[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            res[i] = C[i] * D[i];
        }

        return res;
    }

}
