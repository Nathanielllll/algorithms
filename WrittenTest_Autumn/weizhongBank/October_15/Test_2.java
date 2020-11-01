package weizhongBank.October_15;

import java.util.Scanner;
/*
现在你在玩一款游戏，叫做节奏小师。它有三种判定

P : Perfect 完美，加200分。

G : Great 很棒，加100分。

M : Miss 错过，不加分也不扣分，但累计三次Miss就会输掉游戏。

另外有一种奖励是连击奖励。一旦连续三个Perfect之后，后续连击的Perfect分数将变成250分，但一旦打出了Great或者Miss则连击数将重新开始计算。

你的任务是根据游戏记录计算分数。特别地，失败记为零分。
 */
public class Test_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

//        String string = "PPPPPGPPMP";
        int score = 0;

        System.out.println(process(string, score));
    }


    private static int process(String string, int score){
        int count_M = 0;
        int fast = 0;
        while (fast < string.length()){

            if(fast < string.length() && string.substring(fast, fast + 1).equals("P")){
                int slow = fast;
                while(fast < string.length()
                        && string.substring(fast, fast + 1).equals(string.substring(slow, slow + 1))){
                    fast++;
                }
                score += 200 * (fast - slow);
                if (fast - slow > 3) {
                    score += 50 * (fast - slow - 3);
                }
            }

            if(fast < string.length() && string.substring(fast, fast + 1).equals("G")){
                int slow = fast;
                while(fast < string.length()
                        && string.substring(fast, fast + 1).equals(string.substring(slow, slow + 1))){
                    fast++;
                }
                score += 100 * (fast - slow);
            }

            if(fast < string.length() && string.substring(fast, fast + 1).equals("M")){
                int slow = fast;
                while(fast < string.length()
                        && string.substring(fast, fast + 1).equals(string.substring(slow, slow + 1))){
                    fast++;
                    count_M++;
                    if(count_M == 3) {
                        return 0;
                    }
                }
            }
        }
        return score;
    }
//    // PPPPPGPPMP
//    private static int process(String string, int score){
//        int count_M = 0;
//        int record_P = 0;
//
//        for (int i = 0; i < string.length(); i++) {
//            String curString = string.substring(i, i + 1);
//            if (curString.equals("M")) {
//                count_M++;
//                record_P = i;
//                if(count_M == 3){
//                    return 0;
//                }
//            } else if (curString.equals("G")) {
//                record_P = i;
//                score += 100;
//            }else if(curString.equals("P")){
//                if(i - record_P >= 3){
//                    score += 250;
//                }else {
//                    score += 200;
//                }
//            }
//        }
//        return score;
//    }
}
