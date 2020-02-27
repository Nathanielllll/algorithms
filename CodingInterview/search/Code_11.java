package search;

/**
 * 旋转数组的最小数字，要用双指针（一定在程度上利用了二分查找思想）
 */
public class Code_11 {
    public static int findMin(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        /**重要的条件，不要忘记*/
        if (nums.length == 1 || nums[left] < nums[right]) {
            return nums[0];
        }

        if (nums[left] == nums[right]){
            int result = nums[left];
            for (int i = left + 1; i <= right; i++) {
                if (result > nums[i]) {
                    result = nums[i];
                }
            }
            return result;
        }

        while (left <= right) {
            int mid = (left + right) >> 1;
            /**
             * 注意这两个退出条件
             * 即左边的数字要比右边的数字 大！
             * 然后退出右边的数字！
             */
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            if (nums[mid] >= nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 5, 6, 7, 8, 9};
        int[] arr1 = new int[]{2};
        int[] arr2 = new int[]{8, 9, 3, 4, 5, 6, 7};
        int[] arr3 = new int[]{1,3,5};
        System.out.println(findMin(arr));
        System.out.println(findMin(arr1));
        System.out.println(findMin(arr2));
        System.out.println(findMin(arr3));
    }
}
