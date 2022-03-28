package dp;

/*
最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：

Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
Paste (粘贴) : 你可以粘贴你上一次复制的字符。
给定一个数字n。你需要使用最少的操作次数，在记事本中打印出恰好n个 'A'。输出能够打印出n个 'A' 的最少操作次数。

示例 1:

输入: 3
输出: 3
解释:
最初, 我们只有一个字符 'A'。
第 1 步, 我们使用 Copy All 操作。
第 2 步, 我们使用 Paste 操作来获得 'AA'。
第 3 步, 我们使用 Paste 操作来获得 'AAA'。

综合上面的分析，我们得出分析结果：

1、质数次数为其本身。

2、合数次数为将其分解到所有不能再分解的质数和。

举例子：36 = 2 * 2 * 3 * 3  ===>  10
 */
public class Test_650 {
    public static void main(String[] args) {
        System.out.println(minSteps(36));
    }

    public static int minSteps(int n) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                ans += i;
                n /= i;
            }
        }
        return ans;
    }

    // DFS递归
    public static int minSteps01(int n) {
        if (n == 1) return 0;
        return dfs(n, 1, 0);
    }

    // n：固定参数，要达到的目标（输出n个'A'）
    // cur：当前记事本上已输出的'A'的数量
    // paste：当前粘贴板上已有的'A'的数量
    // 返回：在当前cur、paste的情况下，达到目标，所需要的最少操作次数
    private static int dfs(int n, int cur, int paste) {
        if (cur == n) return 0; // 当前记事本输出已达目标，无需操作
        if (cur > n) return INF; // 当前记事本输出超过了目标，不可能达到目标，表示之前的DFS尝试方案无效
        // 1）本次操作，选择复制（如果当前粘贴板上'A'数量 != 当前记事本上'A'数量，则可以有此选择，否则，复制操作无意义，不做此选择）：
        int p1 = cur != paste ? 1 + dfs(n, cur, cur) : INF;
        // 2）本次操作，选择粘贴（如果当前粘贴板上'A'数量 > 0，则可以有此选择，否则，粘贴操作无意义，不做此选择）：
        int p2 = paste > 0 ? 1 + dfs(n, cur + paste, paste) : INF;
        return Math.min(p1, p2);
    }

    private static final int INF = Integer.MAX_VALUE / 2;
}
