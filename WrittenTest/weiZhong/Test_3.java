package weiZhong;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
/*
抽卡是一类类似于博弈的游戏。现在有一种抽卡方式，描述如下：

初始你只有一次抽卡机会。每次抽卡浪费一次抽卡机会，获得一张卡片。这张卡片上有两个数字，第一个数字代表你能获得的钱，第二个数字代表你能获得的额外抽卡次数。额外的抽卡次数是可以累计的。

现在，你知道了卡片的数量，所有的卡片上的数字，以及所有卡片的顺序。你只需要安排一种抽卡顺序，使得你能获得钱数最多。
 */
public class Test_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        int[][] nums = new int[total][2];
        for (int i = 0; i < total; i++) {
            for (int j = 0; j < 2; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }

        System.out.println(getMostMoney(nums));
    }

    public static int getMostMoney(int[][] nums){
        //最大堆
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.times == o2.times) {
                    return o2.money - o1.money;
                }else {
                    return o2.times - o1.times;
                }
            }
        });

        for (int[] num : nums){
            maxHeap.add(new Pair(num[0], num[1]));
        }

        int count = 1;
        int result = 0;
        while(!maxHeap.isEmpty() && count > 0){
            count--;
            Pair pair = maxHeap.poll();
            result += pair.money;
            count += pair.times;
        }
        return result;
    }

    public static class Pair{
        int money;
        int times;

        public Pair(int money, int times) {
            this.money = money;
            this.times = times;
        }
    }


}

