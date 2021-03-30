package bit;

/**
 * @author luzhi
 * @date 2021/3/29
 */
public class Test_190 {
    public static int reverseBits(int n) {
        int mask = 1;
        int result = 0;

        for (int i = 0; i < 32; i++) {
            result <<= 1;
            if ((n & mask) != 0) {
                result += 1;
            }

            mask <<= 1;
        }
        return result;
    }
}
