package binarySearch;

/**
 * @author luzhi
 * @date 2021/4/15
 */
public class Test_875 {
    public int minEatingSpeed(int[] piles, int H) {
        int maxVal = 1;
        for (int pile : piles) {
            maxVal = Math.max(maxVal, pile);
        }

        int left = 1;
        int right = maxVal;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (meet(piles, mid, H)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean meet(int[] piles, int speed, int H) {
        int sum = 0;
        for (int pile : piles) {
            // 上取整可以这样写
            sum += (pile + speed - 1) / speed;
        }
        return sum <= H;
    }
}
