package string;

/**
 * 比较两个版本号 version1和 version2。
 * 如果version1>version2返回1，如果version1<version2 返回 -1， 除此之外返回 0。
 *
 * 你可以假设版本字符串非空，并且只包含数字和. 字符。
 *
 * . 字符不代表小数点，而是用于分隔数字序列。
 *
 * 例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。
 *
 * 你可以假设版本号的每一级的默认修订版号为 0。例如，版本号 3.4 的第一级（大版本）和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。
 * 
 *
 * 示例1:
 *
 * 输入: version1 = "0.1", version2 = "1.1"
 * 输出: -1
 * 示例 2:
 *
 * 输入: version1 = "1.0.1", version2 = "1"
 * 输出: 1
 * 示例 3:
 *
 * 输入: version1 = "7.5.2.4", version2 = "7.5.3"
 * 输出: -1
 * 示例4：
 *
 * 输入：version1 = "1.01", version2 = "1.001"
 * 输出：0
 * 解释：忽略前导零，“01” 和 “001” 表示相同的数字 “1”。
 */
public class Test_165 {
    public int compareVersion(String version1, String version2) {
        String[] strings1 = version1.split("\\.");
        String[] strings2 = version2.split("\\.");

        int len1 = strings1.length;
        int len2 = strings2.length;
        int len = Math.max(len1, len2);

        for (int i = 0; i < len; i++) {
            int num1 = 0;
            if (i < len1) {
                num1 = Integer.valueOf(strings1[i]);
            }

            int num2 = 0;
            if (i < len2) {
                num2 = Integer.valueOf(strings2[i]);
            }

            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }

        }
        return 0;
    }
}
