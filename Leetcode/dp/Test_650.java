package dp;
/*
最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：

Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
Paste (粘贴) : 你可以粘贴你上一次复制的字符。
给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。

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
}
