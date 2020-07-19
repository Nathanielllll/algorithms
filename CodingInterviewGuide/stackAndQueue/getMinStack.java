package stackAndQueue;

import java.util.Stack;

public class getMinStack {
    private Stack<Integer> stack_push;
    private Stack<Integer> stack_min;

    public getMinStack() {
        this.stack_push = new Stack<>();
        this.stack_min = new Stack<>();
    }

    public void push(int value){
        stack_push.push(value);
        if (stack_min.isEmpty() || value <= stack_min.peek()) {
            stack_min.push(value);
        }
    }

    public int pop(){
        if (stack_push.isEmpty()) {
            throw new RuntimeException("Null");
        }
        int value = stack_push.pop();
        if (stack_min.peek() == value) {
            stack_min.pop();
        }
        return value;
    }

    public int getMin(){
        if (stack_push.isEmpty()) {
            throw new RuntimeException("Null");
        }
        return stack_min.peek();
    }

    public static void main(String[] args) {
        getMinStack stack = new getMinStack();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(7);
        System.out.println(stack.pop());

        System.out.println(stack.getMin());
    }
}
