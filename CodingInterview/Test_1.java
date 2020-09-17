public class Test_1 {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >> 1;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                if (middle == 0) {
                    return 0;
                } else if (middle > 0 && nums[middle - 1] < target) {
                    return middle;
                } else {
                    right = middle - 1;
                }
            } else {
                if (middle == nums.length - 1) {
                    return nums.length;
                } else if (middle < nums.length - 1 && nums[middle + 1] > target) {
                    return middle;
                } else {
                    left = middle + 1;
                }
            }
        }
        return -1;
    }
}
