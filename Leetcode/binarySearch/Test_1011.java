package binarySearch;

/**
 * @author luzhi
 * @date 2021/4/15
 */
/*
传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。

传送带上的第 i个包裹的重量为weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。

返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。


示例 1：

输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
输出：15
解释：
船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
第 1 天：1, 2, 3, 4, 5
第 2 天：6, 7
第 3 天：8
第 4 天：9
第 5 天：10

请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。 
 */
public class Test_1011 {
    public static void main(String[] args) {
        Test_1011 test = new Test_1011();
        int[] nums = {1,2,3,1,1};
        int D = 4;
//        System.out.println(test.canShip(nums, D, 15));
        System.out.println(test.shipWithinDays(nums, D));
    }

    public int shipWithinDays(int[] weights, int D) {
        int sumWeight = 0;
        for (int weight : weights) {
            sumWeight += weight;
        }

        int left = 1;
        int right = sumWeight;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(weights, D, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean check(int[] weights, int D, int m) {
        int cnt = 0, remainder = m;
        for (int weight : weights) {
            if (weight > m) return false; // 如果包裹重量大于运载力就返回false
            if (weight <= remainder) { // 重量小于还能装载的能力就需要装载
                remainder -= weight;
            } else { // 包裹太重就放到下一次装载
                cnt++;
                remainder = m - weight;
            }
        }
        cnt++; // 需要计算最后一次装载
        return cnt <= D;
    }
}
