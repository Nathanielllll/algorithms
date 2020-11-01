package xiecheng.October_13;

import java.util.Scanner;

public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


//        System.out.println(getRest(30, 100));

        System.out.println(buyCoke(2, 1, 4, 3, 250));
    }


    // 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？
    static int buyCoke(int m, int a, int b, int c, int x) {
        int[] nums = {c, b, a};
        int count = 0;

        for (int i = 0; i < m; i++) {
            int price = x;
            int money = 0;
            int[] rest = new int[3];

            // 100
            int num_100 = 0;
            int money_100 = 0;
            while(price > money && nums[0] > num_100){
                num_100++;
                money_100 = 100 * num_100;
            }
            nums[0] -= num_100;
            money += money_100;
            // 50
            int num_50 = 0;
            int money_50 = 0;
            while(price > money && nums[1] > num_50){
                num_50++;
                money_50 = 50 * num_50;
            }
            nums[1] -= num_50;
            money += money_50;
            // 10
            int num_10 = 0;
            int money_10 = 0;
            while(price > money && nums[2] > num_10){
                num_10++;
                money_10 = 10 * num_10;
            }
            nums[2] -= num_10;
            money += money_10;


            count += num_100 + num_50 + num_10;

            rest = getRest(price, money);
            addNums(nums, rest);
        }
        return count;
    }

    static void addNums(int[] nums, int[] rest){
        for (int i = 0; i < nums.length; i++) {
            nums[i] += rest[i];
        }
    }

    // 贩卖机只支持硬币支付，且收退都只支持10 ，50，100 三种面额。
    // 一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
    static int[] getRest(int price, int money) {
        int[] ans = new int[3];
        int rest = money - price;

        int num_100 = 0;
        int num_50 = 0;
        int num_10 = 0;

        while (rest >= 100 * num_100) {
            num_100++;
        }
        rest -= 100 * (num_100 - 1);

        while (rest >= 50 * num_50) {
            num_50++;
        }
        rest -= 50 * (num_50 - 1);

        while (rest >= 10 * num_10) {
            num_10++;
        }
        rest -= 10 * (num_10 - 1);

        ans[0] = num_100 > 0 ? num_100 - 1 : 0;
        ans[1] = num_50 > 0 ? num_50 - 1 : 0;
        ans[2] = num_10 > 0 ? num_10 - 1 : 0;
        return ans;
    }
}
