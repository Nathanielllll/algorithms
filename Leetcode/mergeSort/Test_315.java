package mergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author luzhi
 * @date 2021/4/17
 */
public class Test_315 {
    /*
    给定一个整数数组 nums，按要求返回一个新数组counts。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于nums[i] 的元素的数量。

    示例：

    输入：nums = [5,2,6,1]
    输出：[2,1,1,0]
    解释：
    5 的右侧有 2 个更小的元素 (2 和 1)
    2 的右侧仅有 1 个更小的元素 (1)
    6 的右侧有 1 个更小的元素 (1)
    1 的右侧有 0 个更小的元素
     */
    private int[] count;
    // 重要！用索引数组进行排序！
    private int[] indexes;
    private int[] temp;

    public List<Integer> countSmaller(int[] nums) {
        int length = nums.length;
        count = new int[length];
        temp = new int[length];
        indexes = new int[length];
        for (int i = 0; i < length; i++) {
            indexes[i] = i;
        }

        merge(nums, 0, length - 1);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            result.add(count[i]);
        }
        return result;
    }

    private void merge(int[] nums, int left, int right) {
        if (left < right) {
            int mid = (left + right) >>> 1;
            merge(nums, left, mid);
            merge(nums, mid + 1, right);

            int p1 = left;
            int p2 = mid + 1;
            int k = left;

            while (p1 <= mid || p2 <= right) {
                if (p2 > right) {
                    temp[k++] = indexes[p1++];
                } else if (p1 > mid) {
                    temp[k++] = indexes[p2++];
                } else {
                    if (nums[indexes[p1]] > nums[indexes[p2]]) {
                        count[indexes[p1]] += right - p2 + 1;
                        temp[k++] = indexes[p1++];
                    } else {
                        temp[k++] = indexes[p2++];
                    }
                }
            }

            for (int i = left; i <= right; i++) {
                indexes[i] = temp[i];
            }
        }
    }
}
