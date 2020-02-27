package dp;

/**
 * 面试题63：股票的最大利润
 * <p>
 * 题目要求：
 * 求买卖股票一次能获得的最大利润。
 * 例如，输入{9，11，8，5，7，12，16，14}，5的时候买入，16的时候卖出，则能获得最大利润11。
 */
public class Code_63 {
    public static int maxDiff(int[] data) {
        if (data == null || data.length < 2) {
            return 0;
        }

        int min = data[0];
        int maxDiff = Integer.MIN_VALUE;
        for (int i = 1; i < data.length; i++) {
            maxDiff = Math.max(maxDiff, data[i] - min);
            min = Math.min(min, data[i]);
        }

        return maxDiff;
    }

    public static void main(String[] args) {
        int[] data = {9,11,8,5,7,12,16,14};
        System.out.println(maxDiff(data));

        int[] data2 = new int[]{9,8,7,6,5,4,3,1};
        System.out.println(maxDiff(data2)); //-1
    }
}
