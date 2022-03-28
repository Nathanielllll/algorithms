public class offer072 {

    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int left = 1;
        int right = x / 2;

        while (left <= right) {
            int mid = (left + right) >> 1;

            long l1 = (long) mid * mid;
            long l2 = (long) (mid + 1) * (mid + 1);
            if (l1 == x) {
                return mid;
            } else if (l1 > x) {
                right = mid - 1;
            } else if (l1 < x) {
                if (l2 > x) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
