package dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author luzhi
 * @date 2021/3/28
 */
public class Test_354 {
    /*
    给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。

    当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

    请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

    注意：不允许旋转信封。

    示例 1：

    输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
    输出：3
    解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
    示例 2：

    输入：envelopes = [[1,1],[1,1],[1,1]]
    输出：1
     */
    public int maxEnvelopes1(int[][] envelopes) {
        // 当第一维相等时，比较第二维。第二维更大的排在前面。
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o2[1] - o1[1];
                }
            }
        });

        int n = envelopes.length;
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = envelopes[i][1];
        }
        return lengthOfLIS(heights);
    }

    // 二分查找法
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        int length = nums.length;
        int end = 0;

        // tail[i] 表示：长度为 i + 1 的 所有 上升子序列的结尾的最小值。

        // 首先需要证明tail是严格递增的！！！
        // 证明：即对于任意的下标 0 <= i < j < len ，都有 tail[i] < tail[j]。
        // 使用反证法：假设对于任意的下标 i < j ，存在某个 tail[i] >= tail[j]。
        // 对于此处的 tail[i] 而言，对应一个上升子序列 [a_0, a_1, ..., a_i]，依据定义 tail[i] = ai ；
        // 对于此处的 tail[j] 而言，对应一个上升子序列 [b_0, b_1, ..., b_i, ... , b_j]，依据定义 tail[j] = bj  ； 由于 tail[i] >= tail[j]，等价于 ai ≥ bj  。
        // 而在上升子序列 [b_0, b_1, ..., b_i, ... , b_j] 中，bi  严格小于 bj  ，
        // 故有 ai>=bj>bi， 则上升子序列 [b_0, b_1, ..., b_i]是一个长度也为 i + 1 但是结尾更小的数组，与 ai  的最小性矛盾。 因此原命题成立。（证完）
        int[] tail = new int[length];
        tail[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > tail[end]) {
                ++end;
                tail[end] = nums[i];
            } else {
                //nums[i] <= tail[end]，则找到在0~size中找到第一个大于等于nums[i]的idx
                int idx = binarySearch(tail, end, nums[i]);
                tail[idx] = nums[i];
            }
        }
        ++end;
        return end;
    }

    // 在0~size中找到第一个大于等于target的idx
    private int binarySearch(int[] nums, int size, int target) {
        int left = 0;
        int right = size;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
