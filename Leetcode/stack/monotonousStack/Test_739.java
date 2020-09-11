package stack.monotonousStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。


单调栈分为单调递增栈和单调递减栈，单调递增栈即栈内元素保持单调递增的栈，同理单调递减栈即栈内元素保持单调递减的栈，
范式：
单调递增栈:
for(int i = 0; i < T.size(); i++){
  while(! stk.empty() && stk.top() > T[i]){
    ​stk.pop();
  }
  stk.push(A[i]);
}

单调递减栈:
for(int i = T.size() - 1; i >= 0; i--){
  while(! stk.empty() && T[i] >= stk.top()){
    stk.pop();
  }
  stk.push(i);
}

单调栈的作用：
可以以 O(1) 的时间复杂度得知某个位置左右两侧比他大（或小）的数的位置，当你需要高效率获取某个位置左右两侧比他大（或小）的数的位置的的时候就可以用到单调栈。

求解数组中元素【右边】第一个比它【小】的元素的下标，从前往后，构造单调递增栈；
求解数组中元素【右边】第一个比它【大】的元素的下标，从前往后，构造单调递减栈；
求解数组中元素【左边】第一个比它【小】的元素的下标，从后往前，构造单调递增栈；
求解数组中元素【左边】第一个比它【大】的元素的下标，从后往前，构造单调递减栈。

 */
public class Test_739 {

    /**更具有适普性的方法*/
    public int[] dailyTemperatures_1(int[] T) {
        int[] res = new int[T.length];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while(!stack.isEmpty() && T[stack.peek().get(0)] < T[i]){
                List<Integer> popIs = stack.pop();
                // 取位于下面位置的列表中，最晚加入的那个
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
                        stack.peek().size() - 1);
                for (Integer popi : popIs) {
                    res[popi] = i;
                }
            }
            if (!stack.isEmpty() && T[stack.peek().get(0)] == T[i]) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            // 取位于下面位置的列表中，最晚加入的那个
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
                    stack.peek().size() - 1);
            for (Integer popi : popIs) {
                res[popi] = -1;
            }
        }

        for (int i = 0; i < res.length; i++) {
            if (res[i] == -1) {
                res[i] = 0;
            }else {
                res[i] = res[i] - i;
            }
        }
        return res;
    }


    public static int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            //每当我们遇到一个比当前栈顶所对应的数（T[stack.peek()]）大的数的时候，我们就遇到了一个“大数“。
            //我们弹出栈内所有对应数比这个数小的栈内元素，并更新它们在返回数组中对应位置的值。

            //因为这个栈本身的单调性，当我们栈顶元素所对应的数比这个元素大的时候，我们可以保证，栈内所有元素都比这个元素大。
            //每个元素出栈，是说明它找到了它在原数组中的next greater element.
            while(!stack.isEmpty() && T[stack.peek()] < T[i]){
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] b = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] b = {89,62,70,58,47,47,46,76,100,70};
//        [8,1,5,4,3,2,1,1,0,0]
        int[] c = dailyTemperatures(b);
        for (int i : c) {
            System.out.print(i + " ");
        }
    }
}
