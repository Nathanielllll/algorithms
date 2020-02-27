package string;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 */
public class Test_14 {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }

        String initString = strs[0];
        //遍历字符串组
        for (int i = 1; i < strs.length; i++) {
            //字符串的index
            int j = 0;
            for (; j < strs[i].length() && j < initString.length(); j++) {
                if (initString.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            initString = initString.substring(0, j);
        }
        return initString;
    }

    public static void main(String[] args) {
        String[] strs = {"aa","a"};
        System.out.println(longestCommonPrefix(strs));
    }
}
