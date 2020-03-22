package alibaba;

/**
 * 有一叠扑克牌，每张牌介于1和10之间
 * <p>
 * 有四种出牌方法：
 * <p>
 * 单出1张
 * 出2张对子
 * 出五张顺子，如12345
 * 出三连对子，如112233
 * 给10个数，表示1-10每种牌有几张，问最少要多少次能出完
 */
public class Test_1 {
    //直接暴力搜索当前poker，按照3连对，5顺子，2对子，1单张的方式。
    public static int poker(int[] cardNumber) {
        int counter = 0;
        int i = 0;
        //3连对。从第一个牌数大于等于2且要连续3次。
        while (true) {
            //当i到了8时，退出
            if (i >= 8) {
                break;
            }
            //如果当前以i为起点的紧连着的三个牌数都大于等于2，则满足一次出牌条件。
            if (cardNumber[i] >= 2 && cardNumber[i + 1] >= 2 && cardNumber[i + 2] >= 2) {
                //出牌次数加1
                counter++;
                //同时删除已经出的牌
                cardNumber[i] = cardNumber[i] - 2;
                cardNumber[i + 1] = cardNumber[i + 1] - 2;
                cardNumber[i + 2] = cardNumber[i + 2] - 2;
            } else {//否则i进行加1，往后寻找满足的3连对！
                i++;
            }
        }

        //从0开始重新出牌
        i = 0;
        //5顺子。从第一个牌数大于等于1且要连续5次。
        while (true) {
            //当i到了6时，退出，因为后面只有6，7，8，9 这四种牌了
            if (i >= 6) {
                break;
            }
            //如果当前以i为起点的紧连着的5个牌数都大于等于1，则满足一次出牌条件。
            if (cardNumber[i] >= 1 && cardNumber[i + 1] >= 1 && cardNumber[i + 2] >= 1 && cardNumber[i + 3] >= 1 && cardNumber[i + 4] >= 1) {
                //出牌次数加1
                counter++;
                //同时删除已经出的牌
                cardNumber[i] = cardNumber[i] - 1;
                cardNumber[i + 1] = cardNumber[i + 1] - 1;
                cardNumber[i + 2] = cardNumber[i + 2] - 1;
                cardNumber[i + 3] = cardNumber[i + 3] - 1;
                cardNumber[i + 4] = cardNumber[i + 4] - 1;
            } else {//否则i进行加1，往后寻找满足的5连顺！
                i++;
            }
        }

        //从0开始重新出牌
        i = 0;
        //2对子。从第一个牌数大于等于2的牌。
        while (true) {
            //当i到了10时，退出，因为后面没有牌了
            if (i >= 10) {
                break;
            }
            //如果当前以i为起点的个牌数都大于等于2，则满足一次出牌条件。
            if (cardNumber[i] >= 2) {
                //出牌次数加1
                counter++;
                //同时删除已经出的牌
                cardNumber[i] = cardNumber[i] - 2;
            } else {//否则i进行加1，往后寻找满足的5连顺！
                i++;
            }
        }

        return counter + cardNumber[0] + cardNumber[1] + cardNumber[2] + cardNumber[3] + cardNumber[4]
                + cardNumber[5] + cardNumber[6] + cardNumber[7] + cardNumber[8] + cardNumber[9];
    }


    public static void main(String[] args) {
        int[] cardNumber = {2,2,2,3,4,5,6,8,8,5};
        System.out.println(poker(cardNumber));
    }

}
