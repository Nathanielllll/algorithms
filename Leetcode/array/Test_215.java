package array;

import java.util.Random;

public class Test_215 {

  public static void main(String[] args) {
    int[] nums = {6,8,3,1,4,7,2,5};
    int k = 4;
    Test_215 test = new Test_215();
    System.out.println(test.findKthLargest(nums, k));
  }

  private final static Random random = new Random(System.currentTimeMillis());
  // 时间复杂度O(n),空间复杂度O(1)
  public int findKthLargest(int[] nums, int k) {
    int len = nums.length;
    int targetIdx = len - k;

    int l = 0;
    int r = len - 1;
    int idx = partition(nums, l, r);
    while (idx != targetIdx) {
      if (idx < targetIdx) {
        l = idx + 1;
      } else {
        r = idx - 1;
      }
      idx = partition(nums, l, r);
    }
    return nums[targetIdx];
  }

  // 双路快排（为了解决数据重复的情况），和pivot相等的数，会平均分配到左右的两个区间
  private int partition(int[] nums, int left, int right) {
    int randomIndex = left + random.nextInt(right - left + 1);
    swap(nums, left, randomIndex);
    // all in nums[left + 1..le) <= pivot;
    // all in nums(ge..right] >= pivot;
    int pivot = nums[left];
    int le = left + 1;
    int ge = right;
    while (true) {
      while (le <= ge && nums[le] < pivot) {
        le++;
      }
      while (le <= ge && nums[ge] > pivot) {
        ge--;
      }
      if (le >= ge) {
        break;
      }
      swap (nums, le, ge);
      le++;
      ge--;
    }
    swap(nums, left, ge);
    return ge;
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
