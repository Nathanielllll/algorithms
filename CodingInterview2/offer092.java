public class offer092 {
    public static int minFlipsMonoIncr(String s) {
        // 必须是0开头的模式
        int length = s.length();
        int[][] dp = new int[length][2];
        int isZero;
        int isOne = s.charAt(0) == '1' ? 1 : 0;
        dp[0][0] = isOne;
        dp[0][1] = Integer.MAX_VALUE;
        for (int i = 1; i < length; i++) {
            isZero = s.charAt(i) == '0' ? 1 : 0;
            isOne = s.charAt(i) == '1' ? 1 : 0;

            dp[i][0] = dp[i - 1][0] + isOne;
            dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0]) + isZero;
        }

        int p1 = Math.min(dp[length - 1][0], dp[length - 1][1]);

        // 全为1的模式
        int p2 = 0;
        for (int i = 0; i < length; i++) {
            if(s.charAt(i) == '0'){
                ++p2;
            }
        }

        return Math.min(p1, p2);
    }

    public static void main(String[] args) {
        System.out.println(minFlipsMonoIncr("1111"));
    }
}
