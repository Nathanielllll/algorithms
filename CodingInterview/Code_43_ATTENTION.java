/***
 * 1～n整数中1出现的次数【有一定的规律需要记忆】
 *
 * 思路比《剑指OFFER》一书简单
 * 总结规律如下（思路比《剑指OFFER》一书简单）：
 * 对于整数n，我们将这个整数分为三部分：当前位数字cur，更高位数字high，更低位数字low，如：对于n=21034，当位数是十位时，
 * cur=3，high=210，low=4。我们从个位到最高位 依次计算每个位置出现1的次数：
 * 1）当前位的数字等于0时，例如n=21034，在百位上的数字cur=0，百位上是1的情况有：00100~00199，01100~01199，……，20100~20199。
 * 一共有21*100种情况，即high*100;【cur=0, high=21, low=34】
 * 2）当前位的数字等于1时，例如n=21034，在千位上的数字cur=1，千位上是1的情况有：01000~01999，11000~11999，21000~21034。
 * 一共有2*1000+（34+1）种情况，即high*1000+(low+1)。【cur=1, high=2, low=34】
 * 3）当前位的数字大于1时，例如n=21034，在十位上的数字cur=3，十位上是1的情况有：00010～00019，……，21010～21019。
 * 一共有（210+1）*10种情况，即(high+1)*10。
 * 这个方法只需要遍历每个位数，对于整数n，其位数一共有lgn个，所以时间复杂度为O(logn)。
 *
 */
public class Code_43_ATTENTION {
    public static int countDigitOne(int n) {
        int count = 0;
        long i = 1;//指向遍历的位数，如i=1即个位，i=10即十位，...，因为n可以有31位，所以10^31用long存储
        while (n / i != 0) {
            //n/i控制遍历的次数，将所有的位数都遍历完毕
            long high = n / (10 * i);//将当前位之前的所有高位都存在high中
            long cur = (n / i) % 10;//将当前位记录在cur中，即我们每次都需要统计当前位上1出现的次数
            long low = n % i;
            if (cur == 0) {
                count += high * i;
            } else if (cur == 1) {
                count += high * i + low + 1;
            } else {
                count += (high + 1) * i;
            }
            i = i * 10;//准备遍历下一位
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 1410065408;
        System.out.println(countDigitOne(n));
    }
}
