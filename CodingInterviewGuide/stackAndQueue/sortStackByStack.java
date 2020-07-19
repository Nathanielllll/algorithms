package stackAndQueue;

import java.util.Stack;
/*
将该栈从顶到底按从大到小的顺序排序。只能另外申请一个栈
 */
public class sortStackByStack {

    public static void sortStackByStack(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int num = stack.pop();
            while (!help.isEmpty() && help.peek() <= num) {
                stack.push(help.pop());
            }
            help.push(num);
        }

        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(7);
        stack.push(1);
        stack.push(2);
        stack.push(9);
        stack.push(6);
        sortStackByStack(stack);
        for(int num : stack){
            System.out.println(num);
        }
    }
}
