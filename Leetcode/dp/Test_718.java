package dp;

public class Test_718 {
    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int result = 0;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
//                1. 如果它们俩的前缀数组的「末尾项」不相同，由于子数组的连续性，前缀数组不能为它们俩提供公共长度
//                2. 如果它们俩的前缀数组的「末尾项」相同，则可以为它们俩提供公共长度：至于提供多长的公共长度？这又取决于前缀数组的末尾项是否相同……
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
    }
}
