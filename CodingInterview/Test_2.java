public class Test_2 {
    public static void main(String[] args) {
        Test_2 test = new Test_2();
        int num = 0;
        System.out.println(test.translateNum(num));
    }

    public int translateNum(int num) {
        String s_num = String.valueOf(num);
        int length = s_num.length();
        int[] dp = new int[length + 1];
        dp[length] = 1;
        dp[length - 1] = 1;

        for (int i = length - 2; i >= 0; i--) {
            int value_1 = dp[i + 1];
            int value_2 = 0;

            int ten = (s_num.charAt(i) - '0') * 10;
            int zero = s_num.charAt(i + 1) - '0';
            int sum = ten + zero;
            if (sum >= 10 && sum <= 25) {
                value_2 = dp[i + 2];
            }
            dp[i] = value_1 + value_2;
        }
        return dp[0];
    }
}
