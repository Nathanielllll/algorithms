package backtracking;

import java.util.*;

public class Draft {

    ArrayList<String> res;
    boolean[] used;
    public ArrayList<String> Permutation(String str) {
        used = new boolean[str.length()];
        res = new ArrayList<>();
        if(str==null){
            return res;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);

        Stack<Character> stack = new Stack<>();
        subProcess(chars, stack);
        return res;

    }

    public void subProcess(char[] chars, Stack<Character> stack){
        if(stack.size() == chars.length){
            String string = "";
            for(char c : stack){
                string += c;
            }
            res.add(string);
        }

        for(int i=0; i<chars.length; i++){

            if(!used[i]){
                if(i>0 && chars[i] == chars[i-1]  && !used[i - 1]){
                    continue;
                }
                used[i] = true;
                stack.push(chars[i]);
                subProcess(chars, stack);
                stack.pop();
                used[i] = false;
            }

        }
    }

    public static void main(String[] args) {
        Draft test = new Draft();
        String string = "abca";
        System.out.println(test.Permutation(string));
    }


}
