public class offer001 {
    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative = (a > 0 && b < 0) || (a < 0 && b > 0);
        long x = Math.abs((long)a);
        long y = Math.abs((long)b);

        if (x < y || x == 0) {
            return 0;
        }
        return negative ? -subProcess(x, y) : subProcess(x, y);
    }

    private static int subProcess(long a, long b) {
        int result = 0;
        while (a >= 0 && a >= b) {
            int n = 0;
            while (true) {
                ++n;
                long copy_a = a;
                copy_a -= (long) b << n;
                if (copy_a < 0) {
                    --n;
                    break;
                }
            }
            a -= b << n;
            result += 1 << n;
        }
        return result;
    }
}
