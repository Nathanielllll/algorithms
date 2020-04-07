package meituan;

import java.util.Scanner;

public class Test_3 {
    /**
     *
     * 2
     * 0.80 0.50
     * 1 2
     * 样例1解释
     * 选择用一颗子弹射击：如果命中则再用余下子弹射击（仅剩一颗子弹故只能选择一颗）：0.80 * 1 + 0.80 * 0.80 * 1= 1.44
     * 选择用两颗子弹射击：0.5 * 2 = 1.00
     * 此时最高期望得分为1.44
     *
     * 输入样例2
     * 3
     * 0.90 0.10 0.10
     * 2 1 1
     * 输出样例2
     * 4.88
     *
     * 选择用一颗子弹射击：如果命中则再用一颗子弹进行射击，如果命中则再用一颗子弹进行射击（即3轮均使用了一颗子弹进行）：0.90 * 2 + 0.90 * 0.90 * 2+0.90 * 0.90 * 0.90 * 2= 4.878≈4.88  此种情况的期望得分最高，故为4.88
     */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[] pi = new double[n];
        int[] ai = new int[n];

        for (int i = 0; i < n; i++) {
            pi[i] = scanner.nextDouble();
        }

        for (int i = 0; i < n; i++) {
            ai[i] = scanner.nextInt();
        }

        double p = pi[0];
        int a = ai[0];

        double ans = subProcess(p, n,a);
        String res = String.format("%.2f", ans);
        System.out.println(res);

    }

    public static double subProcess(double p, int n, int a){
        double ans = (pow(p, n+1)-p)/(p-1);
        return ans * a;
    }

    public static double pow(double num, int n){
        if (n == 0) {
            return 1.0;
        }

        double subAns = pow(num, n >> 1);

        if (n % 2 == 1) {
            return subAns * subAns * num;
        }else {
            return subAns * subAns;
        }
    }
}
