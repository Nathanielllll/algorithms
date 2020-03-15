package binarySearch;

public class Draft {
    /**
     * * 示例 1:
     *  *
     *  * 输入: 4
     *  * 输出: 2
     *  * 示例 2:
     *  *
     *  * 输入: 8
     *  * 输出: 2
     *  * 说明: 8 的平方根是 2.82842...,
     *  *      由于返回类型是整数，小数部分将被舍去。
     *  *
     */
    public static int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int left = 0;
        int right = x / 2 + 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if(mid <= x / mid && (mid + 1) > x / (mid + 1)){
                return mid;
            } else if (mid > x / mid) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
