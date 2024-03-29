package stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈的下列操作：
 *
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 *
 * 你只能使用队列的基本操作-- 也就是push to back, peek/pop from front, size, 和is empty这些操作是合法的。
 * 你所使用的语言也许不支持队列。你可以使用 list 或者 deque（双端队列）来模拟一个队列, 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 */
public class Test_225 {
    class MyStack {
        Queue<Integer> queue;
        Queue<Integer> help;

        /** Initialize your data structure here. */
        public MyStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue.add(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if (queue.isEmpty() && help.isEmpty()) {
                return -1;
            }
            while (queue.size() > 1) {
                help.add(queue.poll());
            }
            int res = queue.poll();
            swap();
            return res;
        }

        /** Get the top element. */
        public int top() {
            if (queue.isEmpty() && help.isEmpty()) {
                return -1;
            }
            while (queue.size() != 1) {
                help.add(queue.poll());
            }
            int res = queue.peek();
            help.add(queue.poll());
            swap();
            return res;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty() && help.isEmpty();
        }

        private void swap(){
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
        }
    }
}
