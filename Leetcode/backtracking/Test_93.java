package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。

示例:

输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]
 */
public class Test_93 {
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
    }
    public static List<String> restoreIpAddresses(String s){
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        Stack<String> stack = new Stack<>();
        backTracking(s, 0, stack, ans);
        return ans;
    }

    // 中间两个参数解释：pos-当前遍历到 s 字符串中的位置，cur-当前存放已经确定好的 ip 段的数量
    private static void backTracking(String s, int pos, Stack<String> stack, List<String> ans){
        // 递归的出口，当cur的大小为4的时候
        if (stack.size() == 4) {
            // 如果此时 pos 也刚好遍历完整个 s
            if (pos == s.length()) {
                // join 用法：例如 [[255],[255],[111],[35]] -> 255.255.111.35
                ans.add(String.join(".", stack));
            }
            return;
        }

        // ip 地址每段最多有三个数字
        for (int i = 1; i <= 3; i++) {
            // 如果当前位置距离 s 末尾小于 3 就不用再分段了，直接跳出循环即可。
            if (pos + i > s.length()) {
                break;
            }
            // 将 s 的子串开始分段
            String segment = s.substring(pos, pos + i);
            // 剪枝条件：段的起始位置不能为 0，段拆箱成 int 类型的长度不能大于 255
            if (segment.startsWith("0") && segment.length() > 1 || (i == 3 && Integer.parseInt(segment) > 255)) {
                continue;
            }
            // 符合要求就加入到 cur 数组中
            stack.push(segment);
            // 继续递归遍历下一个位置
            backTracking(s, pos + i, stack, ans);
            // 回退到上一个元素，即回溯
            stack.pop();
        }
    }
}
