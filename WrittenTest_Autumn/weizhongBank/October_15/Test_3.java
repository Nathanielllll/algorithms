package weizhongBank.October_15;

import java.util.*;

public class Test_3 {
    static long count;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        long n = scanner.nextInt();
//        long m = scanner.nextInt();

        long n = 23892840;
        long m = 2;


        String num = String.valueOf(n);
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            list.add(num.charAt(i) - '0');
        }

        count = 0;
        Collections.sort(list);
        Stack<Integer> stack = new Stack<>();
        boolean[] used = new boolean[list.size()];

        backTracking(list, stack, used, m);
        System.out.println(count);
    }

    private static void backTracking(LinkedList<Integer> list, Stack<Integer> stack, boolean[] used, long m){
        if(stack.size() == list.size()){
            if(calculaten(stack, m)){
                count++;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if(!used[i]){
                if(i > 0 && list.get(i).equals(list.get(i-1)) && !used[i-1]) continue;

                used[i] = true;
                stack.push(list.get(i));
                backTracking(list, stack, used, m);
                stack.pop();
                used[i] = false;
            }
        }
    }

    private static boolean calculaten(Stack<Integer> stack, long m){
        String value = "";
        for(long l : stack){
            value += l + "";
        }
        long n = Long.parseLong(value);
        return n % m == 0;
    }
}
