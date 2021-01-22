package dp;

/**
 * 把数字翻译成字符串
 * 0翻译城"a"，25翻译成"z"。12258有5种不同的翻译分别是"bccfi" "bwfi" "bczi" "mcfi" "mzi"
 * <p>
 * 递归函数为：f(i)=f(i+1)+g(i,i+1)*f(i+2)。f(i)表示从第i位数字开始的不同翻译的数目。
 * 当第i位和第i+1位两位数字拼接起来在10～25的时候，g(i,i+1)为1，否则为0。
 * <p>
 * 使用递归则表明，我们从数字的末尾开始，然后从右到左翻译并计算不同的翻译数目。
 */
public class Code_46 {
    /**
     * 在numDecoding当中
     * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     */
    //从0开始记 0～a
    public static int translateNum(int num) {
        String num_string = String.valueOf(num);
        int len = num_string.length();

        int[] dp = new int[len + 1];
        dp[len] = 1;
        dp[len - 1] = 1;

        for (int i = len - 2; i >= 0; i--) {
            int ans1 = dp[i + 1];
            int ans2 = meetRequire(num_string.substring(i, i + 2)) ? dp[i+2] : 0;
            dp[i] = ans1 + ans2;
        }
        return dp[0];
    }

    private static boolean meetRequire(String str){
        return "10".compareTo(str) <= 0 && str.compareTo("25") <= 0;
    }

    //从1开始记 1～a
    public int numDecodings(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1; //将递归法的结束条件初始化为 1
        //最后一个数字不等于 0 就初始化为 1
        if (s.charAt(len - 1) != '0') {
            dp[len - 1] = 1;
        }
        for (int i = len - 2; i >= 0; i--) {
            //当前数字时 0 ，直接跳过，0 不代表任何字母
            if (s.charAt(i) == '0') {
                continue;
            }
            int ans1 = dp[i + 1];
            //判断两个字母组成的数字是否小于等于 26
            int ans2 = 0;
            int ten = (s.charAt(i) - '0') * 10;
            int one = s.charAt(i + 1) - '0';
            //因为有if (s.charAt(i) == '0') {
            //                continue;
            //            }
            //所以ten+one肯定 >=10, 因为如果=='0'，已经跳过去了
            //所以也可以写成if (ten + one <= 26 && ten+one>=10) {
            if (ten + one <= 26) {
                ans2 = dp[i + 2];
            }
            dp[i] = ans1 + ans2;
        }
        return dp[0];
    }

    public int numDecoding(String s) {
        int len = s.length();
        int end = 1;
        int cur = 0;
        if (s.charAt(len - 1) != '0') {
            cur = 1;
        }
        for (int i = len - 2; i >= 0; i--) {
            // 当前数字时 0 ，直接跳过，0 不代表任何字母
            if (s.charAt(i) == '0') {
                end = cur;// end 前移
                cur = 0;
                continue;
            }
            // 判断两个字母组成的数字是否小于等于 26
            int ans1 = cur;
            int ans2 = 0;
            int ten = (s.charAt(i) - '0') * 10;
            int one = s.charAt(i + 1) - '0';
            if (ten + one <= 26) {
                ans2 = end;
            }
            end = cur;// end 前移
            cur = ans1 + ans2;
        }
        return cur;
    }


    public static int getTranslation(int number) {
        if (number < 0) {
            return 0;
        }
        String numberInString = String.valueOf(number);
        return getTranslationCount(numberInString);

    }

    public static int getTranslationCount(String numberInString) {
        int length = numberInString.length();
        int[] counts = new int[length];
        //表示counts里面每一位对应的count
        int count = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (i == length - 1) {
                count = 1;
            } else {
                count = counts[i + 1];
            }

            if (i < length - 1) {
                int digit1 = numberInString.charAt(i) - '0';
                int digit2 = numberInString.charAt(i + 1) - '0';
                int converted = digit1 * 10 + digit2;
                if (converted >= 10 && converted <= 25) {
                    //i的位置
                    if (i < length - 2) {
                        count += counts[i + 2];
                    } else {
                        count += 1;
                    }
                }
            }
            counts[i] = count;
        }

        return counts[0];
    }

    public static void main(String[] args) {
        System.out.println(getTranslation(12258));
    }
}
