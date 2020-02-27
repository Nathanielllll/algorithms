package search;

/**
 * 数组中值和下标相等的元素
 * 单调递增的数组中，每个元素都是整数并且唯一的。如{-3,-1,1,3,5}中，数字3和它的下标相等
 * <p>
 * 仍然使用二分查找的思想
 */
public class Code_53_3 {
    public static int getNumberSameAsIndex(int[] numbers) {
        if (numbers == null || numbers.length < 0) {
            throw new RuntimeException("invalid input");
        }
        int start = 0;
        int end = numbers.length - 1;
        while (start <= end) {
            int middle = (start + end) >> 1;
            if (numbers[middle] == middle) {
                return middle;
            } else if (numbers[middle] > middle) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }
}
