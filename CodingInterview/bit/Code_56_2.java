package bit;

/**
 * 数组中唯一只出现一次的数字。
 * 在一个数组中除一个数字只出现一次外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * <p>
 * 把数组中所有的数字的二进制表示的每一位都加起来，如果某一个能被3整除，
 * 那么只出现一次的数字二进制表示中对应的那一位是0；否则是1
 */
public class Code_56_2 {
    public static int findNumberAppearingOnce(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            throw new RuntimeException("invalid input");
        }

        int[] bitSum = new int[32];
        for (int i = 0; i < numbers.length; i++) {
            int bitMask = 1;
            for (int j = 31; j >= 0; j--) {
                int bit = numbers[i] & bitMask;
                if (bit != 0) {
                    bitSum[j] += 1;
                }
                bitMask = bitMask << 1;
            }
        }

        for (int i = 0; i < 32; i++) {
            bitSum[i] = bitSum[i] % 3;
        }

        int result = 0;
        /**记住！从数组存放的二进制转化为十进制*/
        for (int i = 0; i < 32; i++) {
            result = result << 1;
            result += bitSum[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = {3, 4, 5, 5, 4, 7, 3, 3, 4, 5};//7
        System.out.println(findNumberAppearingOnce(numbers));
    }
}
