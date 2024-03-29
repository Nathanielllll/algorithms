package string;

/**
 * @author luzhi
 * @date 2021/3/29
 */
public class Test_171 {
    /*
    给定一个Excel表格中的列名称，返回其相应的列序号。

    例如，

        A -> 1
        B -> 2
        C -> 3
        ...
        Z -> 26
        AA -> 27
        AB -> 28
        ...
    示例 1:

    输入: "A"
    输出: 1
    示例2:

    输入: "AB"
    输出: 28
    示例3:

    输入: "ZY"
    输出: 701

     */
    public static int titleToNumber(String columnTitle) {
        int result = 0;
        int length = columnTitle.length();
        for (int i = 0; i < length; i++) {
            result *= 26;
            int ch = columnTitle.charAt(i);
            result += ch - 'A' + 1;
        }
        return result;
    }
}
