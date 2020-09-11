package dp;
/*
综合上面的分析，我们得出分析结果：

1、质数次数为其本身。

2、合数次数为将其分解到所有不能再分解的质数的操作次数的和。
 */
public class Test_650 {
    public int minSteps(int n) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                ans += i;
                n /= i;
            }
        }
        return ans;
    }
}
