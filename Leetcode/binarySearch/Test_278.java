package binarySearch;

/**
 * @author luzhi
 * @date 2021/3/7
 */
public class Test_278 {
    boolean isBadVersion(int version){
        return false;
    }
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        // left 和 right 的值相等，此时它们就表示了第一个错误版本的位置。因此这里是 <
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid; //[left, mid]
            } else {
                left = mid + 1; //[mid, right]
            }
        }
        return left;
    }
}
