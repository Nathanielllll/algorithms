package dp.longestIncreasingSubsequence;

/**
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 * <p>
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * <p>
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,7,4,9,2,5]
 * 输出: 6
 * 解释: 整个序列均为摆动序列。
 * 示例 2:
 * <p>
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 * 示例 3:
 * <p>
 * 输入: [1,2,3,4,5,6,7,8,9]
 * 输出: 2
 */
public class Test_376 {
    public int wiggleMaxLength_1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        //表示在以i为结尾的地方，上升序列最大值的长度
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], up[j] + 1);
                }
                if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], down[j] + 1);
                }
            }
        }

        return 1 + Math.max(up[nums.length - 1], down[nums.length - 1]);
    }

    /**
     * 数组中的任何元素都对应下面三种可能状态中的一种：
     *
     * 上升的位置，意味着 nums[i] > nums[i - 1]
     * 下降的位置，意味着 nums[i] < nums[i - 1]
     * 相同的位置，意味着 nums[i] == nums[i - 1]
     * 更新的过程如下：
     *
     * 如果 nums[i] > nums[i-1]，意味着这里在摆动上升，前一个数字肯定处于下降的位置【应该down前一个数字的位置表示down的最大长度，而这个最大长度的位置肯定是下降的位置】。
     * 所以 up[i] = down[i-1] + 1 ， down[i] 与 down[i-1]保持相同。
     *
     * 如果 nums[i] < nums[i-1] ，意味着这里在摆动下降，前一个数字肯定处于上升的位置。所以 down[i] = up[i-1] + 1 ， up[i] 与 up[i-1] 保持不变。
     *
     * 如果 nums[i] == nums[i-1]nums[i]==nums[i−1] ，意味着这个元素不会改变任何东西因为它没有摆动。所以 down[i]down[i] 与 up[i]up[i] 与 down[i-1]down[i−1] 和 up[i-1]up[i−1] 都分别保持不变。
     *
     * 最后，我们可以将 up[length-1]up[length−1] 和 down[length-1]down[length−1] 中的较大值作为问题的答案，其中 length是给定数组中的元素数目。
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = down[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }
        return Math.max(down[nums.length - 1], up[nums.length - 1]);
    }

    public int wiggleMaxLength_3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

}
