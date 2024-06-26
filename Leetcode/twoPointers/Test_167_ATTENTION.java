package twoPointers;

/**
 * 给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1必须小于index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9 输出: [1,2] 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2
 * 。
 */

//这个不就是三数之和的简化版嘛？即15题
public class Test_167_ATTENTION {

  //假设会有重复的数字
  public int[] twoSum1(int[] numbers, int target) {
    int left = 0;
    int right = numbers.length - 1;
    while (left < right) {
      int sum = numbers[left] + numbers[right];
      if (sum == target) {
        return new int[]{left + 1, right + 1};
      } else if (sum > target) {
        while (left < right && numbers[right] == numbers[--right]) ;
      } else {
        while (left < right && numbers[left] == numbers[++left]) ;
      }
    }
    return new int[]{-1, -1};
  }

  public int[] twoSum(int[] numbers, int target) {
    int l = 0;
    int r = numbers.length - 1;
    while (l < r) {
      int sum = numbers[l] + numbers[r];
      if (sum == target) {
        return new int[]{l + 1, r + 1};
      } else if (sum > target) {
        r--;
      } else {
        l++;
      }
    }
    return new int[]{};
  }

}
