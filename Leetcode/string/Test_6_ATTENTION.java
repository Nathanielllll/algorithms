package string;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * rows = 4
 * s[0]           s[6]             s[12]
 * s[1]      s[5] s[7]       s[11] s[13]
 * s[2] s[4]      s[8] s[10]       s[14]
 * s[3]           s[9]             s[15]
 * <p>
 * 整体的思路是遍历字符串，遍历过程中将每行都看成新的字符串构成字符串数组，最后再将该数组拼接起来即可
 * 如果 numRows=1 则说明当前字符串即为结果，直接返回
 * 否则整个字符串需要经历，向下向右，向下向右，这样的反复循环过程，设定 down 变量表示是否向下，loc 变量表示当前字符串数组的下标
 * 如果 down 为 true，则 loc+=1，字符串数组下标向后移动，将当前字符加入当前字符串中
 * 如果 down 为 false，则表示向右，则 loc-=1，字符串数组下标向前移动，将当前字符加入当前字符串中
 * 时间复杂度：O(n)，nn为字符串s的长度
 */
public class Test_6_ATTENTION {
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        String[] strings = new String[numRows];
        for (int i = 0; i < numRows; i++) {
            strings[i] = "";
        }

        int curRow = 0;
        boolean flag = false;

        for (int i = 0; i < s.length(); i++) {
            strings[curRow] += s.substring(i, i + 1);
            if (curRow == 0 || curRow == numRows - 1) {
                flag = !flag;
            }
            curRow = flag ? ++curRow : --curRow;
        }

        String result = "";
        for (int i = 0; i < numRows; i++) {
            result += strings[i];
        }
        return result;
    }

    public static void main(String[] args) {
//        输入: s = "LEETCODEISHIRING", numRows = 4
//        输出: "LDREOEIIECIHNTSG"
        System.out.println(convert("LEETCODEISHIRING", 4).equals("LDREOEIIECIHNTSG"));
    }
}
