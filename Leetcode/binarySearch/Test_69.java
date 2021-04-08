package binarySearch;

/**
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 */
public class Test_69 {
    // 找到最后一个 mid*mid<=x
    public static int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        long left = -1;
        long right = x / 2 + 1;
        while (left + 1 != right) {
            long mid = (left + right) >> 1;
            if (mid * mid <= x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return (int) left;
    }


    public static int mySqrt_2(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int left = 0;
        int right = x / 2 + 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (mid <= x / mid && (mid + 1) > x / (mid + 1)) {
                return mid;
            }
            if (mid <= x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 注意了long，不然会出现越界的情况
     *
     * @param x
     * @return
     */
    public static int mySqrt_1(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        long left = 0;
        long right = x / 2 + 1;
        while (left <= right) {
            long mid = (left + right) >> 1;
            if (x >= mid * mid) {
                if (x < (mid + 1) * (mid + 1)) {
                    return (int) mid;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
