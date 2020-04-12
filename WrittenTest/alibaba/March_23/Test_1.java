package alibaba.March_23;

import java.util.Scanner;

/**
 * 1、从n个人中选择任意数量的人员组成一支队伍，然后从一支队伍中选出一位队长，不同的队长算不同的组合，问这样的组合的数量对10^9+7取模 。
 */
public class Test_1 {
    private static int mod = 1000000007;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println((pow(n - 1) * n) % mod);
    }

    public static long pow(int n) {
        if (n == 0){
            return 1;
        }
        if (n == 1) {
            return 2;
        }

        long half = pow(n / 2);
        if (n % 2 == 0)
            return (half * half) % mod;
        else
            return (half * half * 2) % mod;
    }


}
