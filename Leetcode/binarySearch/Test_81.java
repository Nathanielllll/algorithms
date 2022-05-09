package binarySearch;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组[0,0,1,2,2,5,6]可能变为[2,5,6,0,0,1,2])。
 * <p>
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回true，否则返回false。
 * <p>
 * 示例1:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例2:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 * <p>
 * 这是 搜索旋转排序数组的延伸题目，本题中的nums 可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */
public class Test_81 {
    public static boolean search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        // 恢复二段性。举个例子：[0,1,2,2,2,3,4,5] 变成 [2,3,4,5,0,1,2,2]就会失去二段性，因此需要恢复二段性
        while (l < r && nums[0] == nums[r]) r--;

        // 第一次「二分」：从中间开始找，找到满足 >=nums[0] 的分割点（旋转点）
        // 查找旋转点的idx。本题中有：nums[idx] >= nums[0]
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] >= nums[0]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        // 旋转点为idx
        int idx = n;
        if (nums[r] >= nums[0] && r + 1 < n) idx = r + 1;

        // 第二次二分，找目标值
        // 从0~idx-1找
        int ans = find(nums, 0, idx - 1, target);
        if (ans != -1) return true;
        // 从idx~n-1找
        ans = find(nums, idx, n - 1, target);
        return ans != -1;
    }

    static int find(int[] nums, int l, int r, int t) {
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= t) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[r] == t ? r : -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 3, 1};
        System.out.println(search(nums, 1));
        System.out.println(search(nums, 3));
        System.out.println(search(nums, 7));
        System.out.println(search(nums, 6));
    }
}
