package stackAndQueue;

import java.util.Stack;

/*
栈压入1，2，3，4，5，只用递归函数来和实现栈中元素的逆序
 */
public class recvAndStackToReverse {
    //将stack的栈底元素返回并移除
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }else {
            int lastElement = getAndRemoveLastElement(stack);
            stack.push(result);
            return lastElement;
        }
    }

    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()) {
            return;
        }
        int value = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(value);
    }


}
