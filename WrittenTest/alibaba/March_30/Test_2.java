package alibaba.March_30;

import java.util.Arrays;
import java.util.Stack;
/*
对一个数字串，等概率取任意一段连续子串，求取出的子串的最大值的期望。

作者：卖报人
链接：https://www.nowcoder.com/discuss/396652
来源：牛客网

问题转化为计算每个连续子串中的最大值，用栈的方法，时间、空间均O(n)复杂度
dp[i]:标明所有以v[i]为串尾的子串的最大值的和 （简单可知，dp[i]是i+1个子串最大值的和）
用栈来记录情况如下：
   对于v[i]，我们让栈元素依次弹出，直到找到比v[i]大的元素：
   如果栈空了，证明v[i]为串尾的所有串的最大值都是v[i]，所以dp[i] = (i+1)*v[i]；
   如果栈没空，假设此时栈顶元素对应v[j]，则对串k~i，如果k<=j，则这些串的最大值的和和dp[j]一致；
   如果k>j，则这些串的最大值为v[i]，加起来即可
串总数是(1+n)*n/2，只要把所有dp加起来除以总数就可以了
 */
public class Test_2 {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Arrays.sort(nums);
        total(nums, 0);
        double result = ((double)sum) / ((double)count);
        System.out.println(result);
    }

    //先排序
    static Stack<Integer> stack = new Stack<>();
    static int count = 0;
    static long sum = 0;
    public static void total(int[] nums, int start){
        if (stack.size() < nums.length && !stack.isEmpty()) {
            count++;
            sum += stack.peek();
        }

        //假设每个数字不重复
        for (int i = start; i < nums.length; i++) {
            if(i>start && nums[i] == nums[i-1]){
                continue;
            }
            stack.push(nums[i]);
            total(nums, i + 1);
            stack.pop();
        }
    }
}
