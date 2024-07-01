package binarySearch;

/**
 * @author luzhi
 * @date 2021/4/15
 */
public class Test_875 {

    public static void main(String[] args) {
        int[] piles = new int[]{805306368,805306368,805306368};
        int h = 1000000000;
        System.out.println(minEatingSpeed(piles, h));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        // max speed is max; min speed is 1
        int l = 1;
        int r = max;

        while (l <= r) {
            int c = l + (r - l) / 2;
            if (calculateTime(piles, c) <= h) {
                r = c - 1; // 更新后，r右边必然满足时间 <= h
            } else {
                l = c + 1; // 更新后，l左边必然满足时间 > h
            }
        }
        return l;
    }

    // 使用long。否则会有溢出风险
    private static long calculateTime(int[] piles, int speed) {
        long sumTime = 0;
        for (int pile : piles) {
            if (pile % speed > 0) {
                sumTime += (pile / speed + 1);
            } else {
                sumTime += (pile / speed);
            }
        }
        return sumTime;
    }
}
