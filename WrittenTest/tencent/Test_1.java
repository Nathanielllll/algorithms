package tencent;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test_1 {
    //解压缩
    //HG[3|B[2|CA]]F−>HG[3|BCACA]F−>HGBCACABCACABCACAF
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String line = in.next();
        String string = "HG[3|B[2|CA]]F";
        System.out.println(deCoding(string));

    }

    public static String deCoding(String string){
        Stack<String> stack= new Stack<>();
        for (int i = 0; i < string.length(); i++) {
            String s = string.substring(i, i + 1);
            if (!s.equals("]")) {
                stack.push(s);
            }else {
                StringBuffer stringBuffer = new StringBuffer();
                //此时遇到了']', 需要出栈，直到遇到第一个匹配的'['
                while (!stack.isEmpty() && !stack.peek().equals("[")) {
                    stringBuffer.append(stack.pop());
                }
                stack.pop();//弹出无用的'['

                //CBA|3->3|ABC
                String temp = stringBuffer.reverse().toString();//因为是从栈中弹出的,所以需要反转,并且此时不包含'['与']'了
                int times = Integer.parseInt(temp.substring(0, temp.indexOf("|")));//解析出重复的次数
                String repeatedStr = reverse(temp.substring(temp.indexOf("|") + 1));//解析出重复的字符串(放入栈中,需要反转)
                //把局部解析出的字符串放入栈中,以便后续解析
                for (int j = 0; j < times; j++) {
                    stack.push(repeatedStr);
                }
            }
        }
        StringBuffer ans = new StringBuffer();
        while(!stack.isEmpty()){
            ans.append(stack.pop());
        }
        return ans.reverse().toString();
    }

    public static String reverse(String s){
        char[] chars = s.toCharArray();
        int left = 0, right = s.length() - 1;
        while(left < right){
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return String.valueOf(chars);
    }


    public static String subProcess_1(String string){
        String pattern = "\\[(\\d+)\\|(\\w+)\\]";
        Pattern pc = Pattern.compile(pattern);
        Matcher m = pc.matcher(string);

        while (m.find()) {
            int num = Integer.valueOf(m.group(1));
            String chs = "";
            for (int i = 0; i < num; i++) {
                chs += m.group(2);
            }
            string = m.replaceFirst(chs);
            m = pc.matcher(string);
        }
        return string;
    }
}
