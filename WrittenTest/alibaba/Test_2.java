package alibaba;

import java.util.*;

/**
 * 小明在学旋律，每段旋律都可以用字符串来表示，并且旋律的每个
 * 字符的ASCALL码递增的
 * 比如以下4段旋律 :
 * aaa
 * bcd
 * bcdef
 * zzz
 * 现在就是要求，小明能够吧这些旋律拼接起来组成最长的旋律。
 * 比如上述例子输出 11 最长的旋律为 aaabcdefzzz
 * aaa bcdef bcd zzz
 */
public class Test_2 {
    public static int melodiesSolver(List<String> melodies){

        Collections.sort(melodies, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.charAt(0) == o2.charAt(0)) {
                    //长的排前面
                    return o2.charAt(o2.length() - 1) - o1.charAt(o1.length() - 1);
//                    return o2.length() - o1.length();
                }else {
                    //<0排前面
                    return o1.charAt(0) - o2.charAt(0);
                }
            }
        });

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < melodies.size(); i++) {
            stringBuffer.append(melodies.get(i));
        }

        int res = 0;

        String string = stringBuffer.toString();
        int[] dp = new int[string.length()];
        Arrays.fill(dp, 1);
        for (int i = 0; i < string.length(); i++) {
            for (int j = 0; j < i; j++) {
                if(string.charAt(i) >= string.charAt(j)){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // aaa bcdef bcd zzz
        List<String> list = new LinkedList<>();
        list.add("aaa");
        list.add("bcd");
        list.add("bcdef");
        list.add("zzz");

        System.out.println(melodiesSolver(list));
    }
}
