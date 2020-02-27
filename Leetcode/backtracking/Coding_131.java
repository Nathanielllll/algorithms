package backtracking;

import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 *
 * 就是很经典的回溯法，一个 for 循环，添加元素，递归，删除元素。这里判断是否是回文串，我们就直接用 dp 数组。
 */
public class Coding_131 {
//    public List<List<String>> partition(String s) {
//        boolean[][] dp = new boolean[s.length()][s.length()];
//
//
//
//    }
}
