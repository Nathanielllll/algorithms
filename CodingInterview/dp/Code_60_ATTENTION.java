package dp;

/**
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * 示例 1:
 *
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 *
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 *
 * 当n=1时，F(1,s)=1,其中s=1,2,3,4,5,6
 * 当n≥2时，F(n,s)=F(n−1,s−1)+F(n−1,s−2)+F(n−1,s−3)+F(n−1,s−4)+F(n−1,s−5)+F(n−1,s−6) s=n,n+1,...,6*n
 *
 */
public class Code_60_ATTENTION {

    public double[] twoSum(int n) {
        int[][] dp = new int[n+1][6*n+1];
        //n=1的时候，先在此初始化。
        for (int j = 1; j <= 6; j++) {
            dp[1][j] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6*i; j++) {
                for (int k = 1; k <= 6; k++) {
                    /**注意这个条件*/
                    if(j-k>=i-1){
                        dp[i][j] += dp[i-1][j-k];
                    }
                }
            }
        }

        double total = Math.pow((double)6,(double)n);
        double[] ans = new double[5*n+1];

        for(int i=n;i<=6*n;i++){
            ans[i-n]=((double)dp[n][i])/total;
        }
        return ans;
        
    }
}
