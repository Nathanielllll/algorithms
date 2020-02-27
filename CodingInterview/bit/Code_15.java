package bit;

public class Code_15 {
    /**
     * int是32位的
     * 避免死循环的方法，移动的是flag
     */
    public static int numberOfOne1(int n) {
        /**
         运算规则：
         >> ：按二进制形式把所有的数字向右移动对应位数，低位移出（舍弃），高位的空位补符号位，即正数补零，负数补1。符号位不变。
         >>>：按二进制形式把所有的数字向右移动对应位数，低位移出（舍弃），高位的空位补零。对于正数来说和带符号右移相同，对于负数来说不同。
         */
        int result = 0;
        int flag = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & flag) != 0) {
                result++;
            }
            flag <<= 1;
        }
        return result;
    }

    //特殊情况，特殊技巧，记住即可
    //如1100 & 1011 -> 1000，最右边的1变成0
    public static int numberOfOne2(int n) {
        int result = 0;
        /**
         * 把一个整数减去1，再和原整数做与运算，会把该整数最右边的1变成0。
         * 因此，一个整数的二进制表示中有多少个1，就可以进行多少次这样的操作。
         */
        while (n != 0) {
            result++;
            n = (n - 1) & n;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numberOfOne1(-7));
//        System.out.println(5 & 4);
//        System.out.println((1 << 1) << 1);
        System.out.println(numberOfOne2(-7));
    }
}
