public class QuickSort {

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

  public int partition(int[] array, int low, int high) {
    int pivot = array[high];

    int pointer = low;
    for (int i = low; i < high; i++) {
      if (array[i] <= pivot) {
        swap(array, pointer, i);
        pointer++;
      }
    }
    swap(array, high, pointer);
    return pointer;
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
