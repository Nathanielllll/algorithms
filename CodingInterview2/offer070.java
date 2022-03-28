public class offer070 {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        // 检查第一位
        if (nums[0] != nums[1]) {
            return 0;
        }
        // 检查最后一位
        if (nums[nums.length - 1] != nums[nums.length - 2]) {
            return nums.length - 1;
        }

        int left = 1;
        int right = nums.length - 2;
        while (left <= right) {
            int mid = (left + right) >> 1;

            if (nums[mid] > nums[mid - 1] && nums[mid] < nums[mid + 1]) {
                return nums[mid];
            } else if (nums[mid] == nums[mid - 1]) {
                // 奇数
                if ((mid & 1) == 1) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid] == nums[mid + 1]) {
                // 奇数
                if ((mid & 1) == 1) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
