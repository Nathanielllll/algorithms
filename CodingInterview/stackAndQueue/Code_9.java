package stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用两个栈实现一个队列 && 用两个队列实现一个栈
 */
public class Code_9 {
    //用两个栈实现一个队列
    public static class TwoStacksQueue {
        Stack<Integer> stackPush;
        Stack<Integer> stackPop;

        //构造函数，初始化的作用
        public TwoStacksQueue() {
            this.stackPush = new Stack<Integer>();
            this.stackPop = new Stack<Integer>();
        }

        public void add(int pushInt) {
            stackPush.add(pushInt);
        }

        public int poll() {
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("数据为空");
            } else if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("数据为空");
            } else if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.peek();
        }
    }

    //用两个队列实现一个栈
    public static class TwoQueuesStack {
        Queue<Integer> queue;
        Queue<Integer> help;

        //构造函数，初始化的作用
        public TwoQueuesStack() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(int pushInt) {
            queue.add(pushInt);
        }

        public int pop() {
            if (queue.isEmpty() && help.isEmpty()) {
                throw new RuntimeException("数据为空");
            }
            while (queue.size() != 1) {
                help.add(queue.poll());
            }
            int res = queue.poll();
            swap();
            return res;
        }

        public int peek() {
            if (queue.isEmpty() && help.isEmpty()) {
                throw new RuntimeException("数据为空");
            }
            while (queue.size() != 1) {
                help.add(queue.poll());
            }
            int res = queue.peek();
            help.add(queue.poll());
            swap();
            return res;
        }

        private void swap() {
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
        }
    }
}
