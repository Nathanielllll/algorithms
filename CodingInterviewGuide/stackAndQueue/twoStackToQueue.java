package stackAndQueue;

import java.util.Stack;

public class twoStackToQueue {
    private Stack<Integer> stack_push;
    private Stack<Integer> stack_pop;

    public twoStackToQueue() {
        this.stack_push = new Stack<>();
        this.stack_pop = new Stack<>();
    }

    public void add(int value){
        stack_push.push(value);
    }

    public int poll(){
        if (stack_push.isEmpty() && stack_pop.isEmpty()) {
            throw new RuntimeException("Error");
        }

        if (stack_pop.isEmpty()) {
            while (stack_push.size() > 0) {
                stack_pop.push(stack_push.pop());
            }
        }

        return stack_pop.pop();
    }

    public int peek(){
        if (stack_push.isEmpty() && stack_pop.isEmpty()) {
            throw new RuntimeException("Error");
        }

        if (stack_pop.isEmpty()) {
            while (stack_push.size() > 0) {
                stack_pop.push(stack_push.pop());
            }
        }

        return stack_pop.peek();
    }
}
