package weiZhong;

import java.util.Scanner;
/*
有n位小朋友去小明家里拜年，小明准备了m份礼物。小明想把所有礼物平均分给每个小朋友，每个小朋友得到相同个数的礼物。但是m未必能被n整除，小明可以使用以下两种操作任意多次（两种操作可以同时使用）。

1、 给其中一个小朋友发红包，收到红包的小朋友会离开小明家。每个红包需要花费a元。

2、 购买一个新礼物，每个礼物价值为b元。

问小明最少花费多少元，才能使得所有礼物可以被剩下的小朋友平分。
7 5 10 100 -> 20

2 2 10 10 -> 0

3 1 10 10 -> 20
 */
public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int personNum = scanner.nextInt();
        int giftNum = scanner.nextInt();
        int redPocket = scanner.nextInt();
        int giftPrice = scanner.nextInt();

        //1. personNum - giftNum > 0
        if (personNum > giftNum) {
            int redPocketMoney = (personNum - giftNum) * redPocket;
            int giftMoney = (personNum - giftNum) * giftPrice;
            System.out.println(Math.min(redPocketMoney, giftMoney));
        } else if (personNum == giftNum) {
            System.out.println(0);
        }else {
            //1. 增加礼物
            int rest = giftNum % personNum;
            int addGiftNum = (rest + 1) * personNum - giftNum;
            int giftMoney = addGiftNum * giftPrice;

            //2. 减少人
            //2.1礼物为奇数
            int redPocketMoney = 0;
            if (giftNum % 2 == 1) {
                giftNum++;
                int restPersonNum = giftNum / 2;
                redPocketMoney = (personNum - restPersonNum) * redPocket + giftPrice;
            }else {
                int restPersonNum = giftNum / 2;
                redPocketMoney = (personNum - restPersonNum) * redPocket;
            }
            System.out.println(Math.min(redPocketMoney, giftMoney));

        }

    }
}
