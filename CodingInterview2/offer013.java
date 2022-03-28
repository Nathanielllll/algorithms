import java.util.Stack;

public class offer013 {
    /*
    请根据每日 气温 列表 temperatures，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用0 来代替。
示例 1:

输入: temperatures = [73,74,75,71,69,72,76,73]
输出:[1,1,4,2,1,1,0,0]
示例 2:

输入: temperatures = [30,40,50,60]
输出:[1,1,1,0]
示例 3:

输入: temperatures = [30,60,90]
输出: [1,1,0]
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] result = new int[length];
        // index
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int curIndex = stack.pop();
                result[curIndex] = i - curIndex;
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] temperatures = {30,60,90};
        System.out.println(dailyTemperatures(temperatures));
    }
}
