package stackAndQueue;

import java.util.LinkedList;
/*
给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7]
解释:

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 */
public class Code_59 {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[]{};
        }
        int[] result = new int[nums.length - k + 1];
        LinkedList<Integer> window = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //如果 追加的值的索引 跟 队列头部的值的索引 的差，超过窗口大小，那就删掉头部的值(滑动窗口已经满了)
            if(!window.isEmpty() && i - k >= window.getFirst()){
                window.removeFirst();
            }

            //如果新来的值比尾部的大，那就删掉尾部，再追加到后面
            while (!window.isEmpty() && nums[i] > nums[window.getLast()]) {
                window.removeLast();
            }
            window.add(i);

            if (i >= k - 1){
                result[i - k + 1] = nums[window.getFirst()];
            }
        }
        return result;
    }
}
