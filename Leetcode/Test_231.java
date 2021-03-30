/**
 * @author luzhi
 * @date 2021/3/29
 */
public class Test_231 {
    // 参考hashMap当中，长度是2的幂的时候， hashcode % table.length 等价于 hashcode & (length-1);
    // 如果是2的幂，n-1的每一位都是1，n的第一位是1其余都为0  ===>   (n & (n - 1)) == 0
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
