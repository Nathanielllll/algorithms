package binarySearch;

/**
 * @author luzhi
 * @date 2021/3/17
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个排序好的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * <p>
 * 整数 a 比整数 b 更接近 x 需要满足：
 * <p>
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * 示例 2：
 * <p>
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 */
public class Test_658 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 3, 4, 7, 7, 8};
        int k = 3;
        int x = 5;
//        System.out.println(findClosestElements(arr, k, x));
    }

//    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
//        int left = 0;
//        int right = arr.length - 1;
//        while (left <= right) {
//            if (right - left + 1 == k) {
//                break;
//            }
//
//            int mid = (left + right) >> 1;
//            if (Math.abs(x - arr[left]) <= Math.abs(arr[right] - x)) {
//                right = mid - 1;
//            } else {
//                left = mid + 1;
//            }
//        }
//
//        List<Integer> result = new ArrayList<>();
//        for (int i = left; i <= right; i++) {
//            result.add(arr[i]);
//        }
//        return result;
//    }


    // 方法一：排除法（双指针）
    // 时间复杂度：O(N)，这里 N 是数组的长度。空间复杂度：O(1)，只使用了常数个额外的辅助空间。
    public static List<Integer> findClosestElements_1(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;
        int removeCount = arr.length - k;
        while (removeCount > 0) {
            if (Math.abs(x - arr[left]) <= Math.abs(arr[right] - x)) {
                right--;
            } else {
                left++;
            }

            removeCount--;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }
        return result;
    }



}
