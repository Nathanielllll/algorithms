package search;

import java.util.LinkedList;
import java.util.List;

/**
 * 0~n-1中缺失的数字
 * 一个长度为n-1的递增排序数组中所有数字都是唯一的，并且每个数字都在0～n-1之内。找出不在该数组中的这个数组
 * <p>
 * 基于二分查找：
 * 如果中间的值和下标相等，那么下一轮查找右半边。
 * 如果不相等，并且它前一个元素和它的下标相等，则这个中间数字就是第一个值和下标不想等的元素。
 * 如果不相等，并且它前一个元素和它的下标不相等，那么下一轮查找左半边。
 */
public class Code_53_2 {
    public static int getMissingNumber(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            throw new RuntimeException("invalid input");
        }
        for (int i = 0; i < numbers.length; i++) {
            //长度为n-1, 0~n-1
            if (numbers[i] < 0 || numbers[i] > numbers.length) {
                throw new RuntimeException("invalid input");
            }
        }

        int start = 0;
        int end = numbers.length - 1;
        while (start <= end) {
            int middle = (start + end) >> 1;
            if (numbers[middle] != middle) {
                //还包含了middle==0的特殊情况，第一个元素的时候
                if ((middle > 0 && numbers[middle - 1] == middle - 1) || middle == 0) {
                    return middle;
                } else {
                    end = middle - 1;
                }
            } else {
                start = middle + 1;
            }
        }
        //Code_53似乎也可以用这样的方式来求解
        //特殊情况，此时到最末尾了，即最后一个元素的情况
        return start == numbers.length ? numbers.length : -1;
    }

    /**
     * 我们可以用 高斯求和公式 求出 [0..n][0..n] 的和，减去数组中所有数的和，就得到了缺失的数字。高斯求和公式即
     * 甚至都不需要原来的数组的排序的！！
     */
    public static int missingNumber(int[] nums) {
        int expectedSum = nums.length*(nums.length + 1)/2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        int[] numbers = {0, 1, 2, 3, 4, 5};
        System.out.println(getMissingNumber(numbers));
        System.out.println(missingNumber(numbers));
    }
}
