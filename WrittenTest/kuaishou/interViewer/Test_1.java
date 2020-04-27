package kuaishou.interViewer;

import java.util.Scanner;
import java.util.Stack;

/**
 * 1*2+(3+3)))(((
 *
 * 1 3 2
 */
public class Test_1  {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        int[] count = new int[3];
//        //对称的计数
//        int count1 = 0;
//        //'('的计数
//        int count2 = 0;
//        //')'的计数
//        int count3 = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == '('){
                stack.push(i);
            }else if(line.charAt(i) == ')'){
                if (!stack.isEmpty()) {
                    stack.pop();
                    count[0]++;
                }else {
                    count[2]++;
                }
            }
        }

        if (!stack.isEmpty()) {
            count[1] = stack.size();
        }

        for (int i = 0; i < 3; i++) {
            if (i != 2) {
                System.out.print(count[i] + " ");
            }else {
                System.out.println(count[i]);
            }

        }


    }


}
