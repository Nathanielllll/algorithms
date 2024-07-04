import java.util.Random;

public class QuickSort {

  private final static Random random = new Random(System.currentTimeMillis());

  public void quickSort(int[] array) {
    quickSort(array, 0, array.length - 1);
  }

  public void quickSort(int[] array, int low, int high) {
    if (low < high) {
      int pointer = partition(array, low, high);
      quickSort(array, low, pointer - 1);
      quickSort(array, pointer + 1, high);
    }
  }

  private int partition(int[] nums, int left, int right) {
    // 如果不随机的话，在顺序/逆序的情况下，会退化为O(n^2)
    int randomIndex = left + random.nextInt(right - left + 1);
    swap(nums, left, randomIndex);
    // all in nums[left + 1..le) <= pivot;
    // all in nums(ge..right] >= pivot;
    int pivot = nums[left];
    int le = left + 1;
    int ge = right;
    while (true) {
      while (le <= ge && nums[le] <= pivot) {
        le++;
      }
      while (le <= ge && nums[ge] >= pivot) {
        ge--;
      }
      if (le >= ge) {
        break;
      }
      swap(nums, le, ge);
      le++;
      ge--;
    }
    swap(nums, left, ge);
    return ge;
  }

  public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    QuickSort quickSort = new QuickSort();
    int[] nums = {4, 5, 7, 3, 2, 3, 5, 1, 1, 2, 3};
    quickSort.quickSort(nums, 0, nums.length - 1);
  }
}
