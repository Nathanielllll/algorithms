package stackAndQueue;

import java.util.Stack;

/**
 * 包含min函数的栈
 * 调用min、push及pop的时间复杂度都是O(1)
 */
public class Code_30_ATTENTION {
    public static class StackWithMin {
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        public StackWithMin() {
            this.dataStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int number) {
            dataStack.push(number);
            /** 注意是 <= */
            if (minStack.isEmpty() || number <= minStack.peek()) {
                minStack.push(number);
            }
        }

        public int pop() {
            if (dataStack.isEmpty()) {
                throw new RuntimeException("Stack不能为空");
            }
            //dataStack不为空则minStack也一定不为空
            /**一定要是int min = minStack.peek();再进行比较，否则就会报错！！*/
            int min = minStack.peek();
            if (dataStack.peek() == min) {
                minStack.pop();
            }
            return dataStack.pop();
        }

        public int getMin() {
            if (dataStack.isEmpty()) {
                throw new RuntimeException("Stack不能为空");
            }
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        StackWithMin stack = new StackWithMin();
        stack.push(3);
        System.out.println(stack.getMin() == 3);
        stack.push(4);
        System.out.println(stack.getMin() == 3);
        stack.push(2);
        System.out.println(stack.getMin() == 2);
        stack.push(3);
        System.out.println(stack.getMin() == 2);
        stack.pop();
        System.out.println(stack.getMin() == 2);
        stack.pop();
        System.out.println(stack.getMin() == 3);
        stack.push(0);
        System.out.println(stack.getMin() == 0);
    }
}

