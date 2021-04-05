package stack;

import java.util.Stack;

/**
 * @author luzhi
 * @date 2021/4/1
 */
public class Test_1006 {
    public int clumsy(int N) {
        Stack<Integer> stack = new Stack<>();
        int op = 0;
        int sum = 0;
        stack.push(N);
        for (int i = N - 1; i > 0; i--) {
            if (op == 0) {
                stack.push(stack.pop() * i);
            } else if (op == 1) {
                stack.push(stack.pop() / i);
            } else if (op == 2) {
                stack.push(i);
            } else {
                stack.push(-i);
            }
            op = (op + 1) % 4;
        }

        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    // 方法二：数学规律
    public int clumsy_1(int N) {
        if (N == 1 || N == 2)
            return N;
        if (N == 3)
            return 6;
        if (N == 4)
            return 7;
        //上面是特殊情况，下面是根据公式推算的
        if (N % 4 == 0)
            return N + 1;
        if (N % 4 == 1 || N % 4 == 2)
            return N + 2;
        return N - 1;
    }
}
