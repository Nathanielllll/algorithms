package stackAndQueue;

import java.util.Stack;

/**
 * 栈的压入、弹出序列
 * <p>
 * 压入栈的所有数字均不相等
 * <p>
 * 要记住规律才能写
 */
public class Code_31_ATTENTION {
    public static boolean isPopOrder(int[] pushed, int[] popped) {
        if (pushed.length == 0 && popped.length == 0) {
            return true;
        }

        if (pushed.length != popped.length) {
            return false;
        }

        int len = pushed.length;
        int p1 = 0;//push
        int p2 = 0;//pop

        Stack<Integer> stack = new Stack<>();

        while (p2 < len) {
            //不能在push了，如果stack.peek() != 要弹出的数字，只能为false
            if (p1 >= len && stack.peek() != popped[p2]) {
                return false;
            }
            //stack为空 或者 stack.peek() != 要弹出的数字
            if (stack.isEmpty() || (!stack.isEmpty() && stack.peek() != popped[p2])) {
                stack.push(pushed[p1]);
                p1++;
            //stack.peek() == 要弹出的数字
            } else if (!stack.isEmpty() && stack.peek() == popped[p2]){
                stack.pop();
                p2++;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop1 = {4, 5, 3, 2, 1};
        int[] pop2 = {3, 5, 4, 2, 1};
        int[] pop3 = {4, 3, 5, 1, 2};
        int[] pop4 = {3, 5, 4, 1, 2};

        System.out.println("true: " + isPopOrder(push, pop1));
        System.out.println("true: " + isPopOrder(push, pop2));
        System.out.println("false: " + isPopOrder(push, pop3));
        System.out.println("false: " + isPopOrder(push, pop4));

        int[] push5 = {1};
        int[] pop5 = {2};
        System.out.println("false: " + isPopOrder(push5, pop5));

        int[] push6 = {1, 2};
        int[] pop6 = {1, 2};
        System.out.println("true: " + isPopOrder(push6, pop6));

        System.out.println("false: " + isPopOrder(null, null));

    }
}
