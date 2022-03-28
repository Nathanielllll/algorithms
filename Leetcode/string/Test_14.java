package string;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串""。
 * <p>
 * 示例1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例2:
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
        String ans = strs[0];

        for (int i = 1; i < strs.length; i++) {
            String s = strs[i];
            int p1 = 0, p2 = 0;
            while(p1 < ans.length() && p2 < s.length()){
                if (ans.charAt(p1) != s.charAt(p2)) {
                    break;
                }
                p1++;
                p2++;
            }
            ans = ans.substring(0, p1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] strs = {"aa","a"};
        System.out.println(longestCommonPrefix(strs));
    }
}
