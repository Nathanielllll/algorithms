package bit;

/**
 * @author luzhi
 * @date 2021/3/29
 */
public class Test_231 {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(Integer.MIN_VALUE));
    }

    // 参考hashMap当中，长度是2的幂的时候， hashcode % table.length 等价于 hashcode & (length-1);
    // 如果是2的幂，n-1的每一位都是1，n的第一位是1其余都为0  ===>   (n & (n - 1)) == 0
    public static boolean isPowerOfTwo(int n) {
        // 方法一：
        // 得到最右边的1的数字
        long N = (long) n;
        if (N == 0) {
            return false;
        }
        long mostRightOne = N & (~N + 1);
        return N == mostRightOne;


        // 方法二，但是不够通用。纯背题
//        return n > 0 && (n & (n - 1)) == 0;
    }
}
