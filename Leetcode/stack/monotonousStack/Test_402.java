package stack.monotonousStack;

import java.util.Stack;

/*
给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。

注意:

num 的长度小于 10002 且 ≥ k。
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
 */
public class Test_402 {
    public String removeKdigits(String num, int k) {
        //初始化一个stack
        Stack<Character> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            //当栈顶的元素大于即将要加入栈的元素，弹出栈顶元素（要在k不为0的前提下）
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        //再次判断k是否为0，不为0的话，说明还有栈顶的元素需要被弹出
        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder res = new StringBuilder();
        boolean headZero = true;//移除的是100204这个case中的前两个0的这种情况，204之间的0不需要被移除（k=1）
        for (char c : stack) {
            if (c == '0' && headZero) {
                continue;
            }
            res.append(c);
            headZero = false;
        }
        //再次判断下是否没有元素了
        return res.toString().equals("") ? "0" : res.toString();
    }
}
