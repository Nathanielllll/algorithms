public class InsertionSort {
    /**
     * 第一轮，从未排序区域中随机拿出一个数字，既然是随机，那么我们就获取第一个，然后插入到已排序区域中，
     * 已排序区域是空，那么就不做比较，默认自身已经是有序的了。（当然了，第一轮在代码中是可以省略的，从下标为1的元素开始即可）
     *
     * @param nums
     * @return
     */
    public static void insertionSort(int[] nums) {
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            int value = nums[i];
            int insertIndex = i - 1;//插入的位置
            while (insertIndex >= 0) {
                if (nums[insertIndex] > value) {
                    nums[insertIndex + 1] = nums[insertIndex];//移动数据
                    insertIndex--;
                } else {
                    break;
                }
            }
            nums[insertIndex + 1] = value;//插入数据
        }
    }
}
