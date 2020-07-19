package introductoryCourse.Chapter_2.AfterClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/*
编写一个程序，将输入字符串中的字符按如下规则排序。

规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。

如，输入： Type 输出： epTy

规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。

如，输入： BabA 输出： aABb

规则 3 ：非英文字母的其它字符保持原来的位置。


如，输入： By?e 输出： Be?y


注意有多组测试数据，即输入有多行，每一行单独处理（换行符隔开的表示不同行）



输入描述:
输入字符串


输出描述:
输出字符串
示例1
输入
A Famous Saying: Much Ado About Nothing (2012/8).
输出
A aaAAbc dFgghh: iimM nNn oooos Sttuuuy (2012/8).
 */
public class Test_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (br.ready()) {
            String string = br.readLine();
            System.out.println(orderString(string));
        }

    }

    public static String orderString(String string){
        List<Character> list = new LinkedList<>();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                list.add(c);
            }
        }

        char[] chars1 = new char[list.size()];
        for (int i = 0; i < chars1.length; i++) {
            chars1[i] = list.get(i);
        }

        for (int i = 0; i < chars1.length; i++) {
            for (int j = 0; j < chars1.length - 1 - i; j++) {
                char c1 = chars1[j];
                char c2 = chars1[j+1];
                if (getIndex(c1) > getIndex(c2)) {
                    swap(chars1, j, j+1);
                }
            }
        }

        int index = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                sb.append(chars1[index++]);
            }else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void swap(char[] chars, int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static int getIndex(char ch){
        if (ch >= 'a' && ch <= 'z') {
            return ch - 'a';
        }
        if (ch >= 'A' && ch <= 'Z') {
            return ch - 'A';
        }
        return -1;
    }
}
