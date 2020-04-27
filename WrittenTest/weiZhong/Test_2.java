package weiZhong;

import java.util.HashMap;
import java.util.Scanner;

/*
Cassidy和Eleanore是一对好朋友，她们经常会一起玩游戏。某一天她们玩了一个回文游戏。游戏规则是这样的：给出一个仅包含小写字母的字符串S，在每一个人的回合中，她们会进行两个操作：

1. 尝试重新排列这个字符串，如果可以把这个字符串排列成回文字符串，则立即获胜。

2. 否则，她们必须删掉字符串中的一个字符。

已知，Cassidy先手，在两个人都采取最佳策略的情况下，谁可以获胜。

2
aba
ab
 */
public class Test_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        for (int i = 0; i < total; i++) {
            String string = scanner.next();
            String name = subProcess(string);
            System.out.println(name);
        }
    }

    public static String subProcess(String string){
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            hashMap.put(ch, hashMap.getOrDefault(ch, 0) + 1);
        }

        int res = 0;
        for(Character ch : hashMap.keySet()){
            if(hashMap.get(ch) % 2 == 1){
                res++;
            }
        }

        String name1 = "Cassidy";
        String name2 = "Eleanore";
        if (res % 2 == 1) {
            return name1;
        }else {
            return name2;
        }
    }

}




















