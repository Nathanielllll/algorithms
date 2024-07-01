package binarySearch;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组[0,1,2,4,5,6,7]可能变为[4,5,6,7,0,1,2])。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回-1。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是O(logn) 级别。
 * <p>
 * 示例 1:
 * <p>               middle
 * 输入: nums = [4,5,6,7,0,1,2], target = 0 输出: 4 示例2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3 输出: -1
 */
public class Test_33 {

  public int search(int[] nums, int target) {
    int n = nums.length;
    int l = 0;
    int r = n - 1;
    while (l <= r) {
      int c = l + (r - l) / 2;
      if (nums[c] == target) {
        return c;
      }

      if (nums[c] >= nums[0]) {
        // target < nums[0] 说明target在第二个区间
        if (nums[c] < target || target < nums[0]) {
          l = c + 1;
        } else {
          r = c - 1;
        }
      } else {
        if (nums[c] > target || target >= nums[0]) {
          r = c - 1;
        } else {
          l = c + 1;
        }
      }
    }
    return -1;
  }
}
