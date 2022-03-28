public class offer073 {

    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left <= right) {
            int mid = (left + right) >> 1;
            int hourCnt = getHourCnt(piles, mid);

            if (hourCnt == h) {
                right = mid - 1;
            } else if (hourCnt > h) {
                left = mid + 1;
            } else if (hourCnt < h) {
                right = mid - 1;
            }
        }
        return left;
    }

    // 耗时时间
    public static int getHourCnt(int[] piles, int k) {
        int hourCnt = 0;
        for (int pile : piles) {
            hourCnt += pile / k;
            if ((pile % k) > 0) {
                ++hourCnt;
            }
        }
        return hourCnt;
    }

    public static void main(String[] args) {
        int[] piles = {873375536, 395271806, 617254718, 970525912, 634754347, 824202576, 694181619, 20191396, 886462834, 442389139, 572655464, 438946009, 791566709, 776244944, 694340852, 419438893, 784015530, 588954527, 282060288, 269101141, 499386849, 846936808, 92389214, 385055341, 56742915, 803341674, 837907634, 728867715, 20958651, 167651719, 345626668, 701905050, 932332403, 572486583, 603363649, 967330688, 484233747, 859566856, 446838995, 375409782, 220949961, 72860128, 998899684, 615754807, 383344277, 36322529, 154308670, 335291837, 927055440, 28020467, 558059248, 999492426, 991026255, 30205761, 884639109, 61689648, 742973721, 395173120, 38459914, 705636911, 30019578, 968014413, 126489328, 738983100, 793184186, 871576545, 768870427, 955396670, 328003949, 786890382, 450361695, 994581348, 158169007, 309034664, 388541713, 142633427, 390169457, 161995664, 906356894, 379954831, 448138536};
        int H = 943223529;
        System.out.println(minEatingSpeed(piles, H));

    }
}
