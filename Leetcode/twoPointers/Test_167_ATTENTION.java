package twoPointers;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */

/**假设会有重复的数字！！*/
//这个不就是三数之和的简化版嘛？即15题
public class Test_167_ATTENTION {
    public static int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }

        int index1 = 0;
        int index2 = numbers.length - 1;

        while (index1 < index2) {
            int sum = numbers[index1] + numbers[index2];
            if (target < sum) {
                while (index1 < index2 && numbers[index2] == numbers[--index2]) ;
            } else if (target < sum) {
                while (index1 < index2 && numbers[index1] == numbers[++index1]) ;
            } else {
                return new int[]{index1, index2};
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }

}
