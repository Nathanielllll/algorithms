package stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

若队列为空，pop_front 和 max_value需要返回 -1

示例 1：

输入:
["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
[[],[1],[2],[],[],[]]
输出:[null,null,null,2,1,2]
示例 2：

输入:
["MaxQueue","pop_front","max_value"]
[[],[],[]]
输出:[null,-1,-1]

 */
public class Code_59_2 {
    class MaxQueue {
        Queue<Integer> queue;
        Deque<Integer> help;

        public MaxQueue() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public int max_value() {
            if (help.isEmpty()) {
                return -1;
            }
            return help.peek();
        }

        public void push_back(int value) {
            while (!help.isEmpty() && help.getLast() < value) {
                help.removeLast();
            }
            help.add(value);
            queue.add(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            int ans = queue.poll();
            if (ans == help.peek()) {
                help.removeFirst();
            }
            return ans;
        }
    }
}
