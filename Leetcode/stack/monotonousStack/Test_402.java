package stack.monotonousStack;

import java.util.Stack;

/*
给定一个以字符串表示的非负整数num，移除这个数中的 k 位数字，使得剩下的数字最小。

注意:

num 的长度小于 10002 且≥ k。
num 不会包含任何前导零。
示例 1 :

输入: num = "1432219", k = 3
输出: "1219"
解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
示例 2 :

输入: num = "10200", k = 1
输出: "200"
解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
示例 3 :

输入: num = "10", k = 2
输出: "0"
解释: 从原数字移除所有的数字，剩余为空就是0。


一个简单的思路就是：
每次丢弃一次，k 减去 1。当 k 减到 0 ，我们可以提前终止遍历。
而当遍历完成，如果 k 仍然大于 0。不妨假设最终还剩下 x 个需要丢弃，那么我们需要选择删除末尾 x 个元素。

和Test_1673是一样的！
 */
public class Test_402 {
    public String removeKdigits(String num, int k) {
        char[] numsCharArray = num.toCharArray();
        Stack<Character> stack = new Stack<>();
        int poped = k;
        int rest = num.length() - k;

        for(char curNum : numsCharArray){
            while (!stack.isEmpty() && stack.peek() > curNum && poped > 0) {
                stack.pop();
                poped--;
            }
            stack.push(curNum);
        }
        StringBuffer resultBuffer = new StringBuffer();

        boolean headZero = true;
        for (int i = 0; i < rest; i++) {
            char c = stack.get(i);
            if (c == '0' && headZero) {
                continue;
            }
            resultBuffer.append(c);
            headZero = false;
        }
        return resultBuffer.toString().equals("") ? "0" : resultBuffer.toString();
    }
}
