package dp;

/**
 * 丑数。使用动态规划
 * <p>
 * M是已有的最大丑数。把已有的每个丑数乘以2、3、5得到第一个大于M的结果，分别得到M2、M3、M5。下一个丑数是M2、M3、M5的最小值
 * 但是实际上并不需要求出M2、M3、M5。只需要记录下它们的位置T2、T3、T5即可
 */
public class Code_49 {
    public static int getUglyNumber(int index) {
        if (index <= 0) {
            return -1;
        }

        int[] uglyNumbers = new int[index];
        uglyNumbers[0] = 1;
        //3个指针 & 1个总指针
        int multiply2 = 0;
        int multiply3 = 0;
        int multiply5 = 0;
        int nextUglyIndex = 1;

        while (nextUglyIndex < index) {
            int min = getMin(uglyNumbers[multiply2] * 2, uglyNumbers[multiply3] * 3, uglyNumbers[multiply5] * 5);
            uglyNumbers[nextUglyIndex] = min;

            //分别得到T2、T3、T5
            while (uglyNumbers[multiply2] * 2 <= uglyNumbers[nextUglyIndex]) {
                multiply2++;
            }
            while (uglyNumbers[multiply3] * 3 <= uglyNumbers[nextUglyIndex]) {
                multiply3++;
            }
            while (uglyNumbers[multiply5] * 5 <= uglyNumbers[nextUglyIndex]) {
                multiply5++;
            }
            nextUglyIndex++;
        }
        return uglyNumbers[index - 1];
    }

    public static int getMin(int i, int j, int k) {
        int min = Math.min(i, j);
        min = Math.min(min, k);
        return min;
    }

    public static void main(String[] args) {
        System.out.println(getUglyNumber(67));
    }
}
