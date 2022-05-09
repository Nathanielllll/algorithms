package slidingWindow;

import java.util.*;

/**
 * 给定一个数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * 
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤输入数组的大小。
 * <p>
 * <p>
 * 思路一：
 * <p>
 * 用双端队列来存储数组元素的索引
 * <p>
 * 如果新来的值比队列尾部的数小，那就追加到后面，因为它可能在前面的最大值划出窗口后成为最大值
 * 如果新来的值比尾部的大，那就删掉尾部，再追加到后面
 * 如果 追加的值的索引 跟 队列头部的值的索引 的差，超过窗口大小，那就删掉头部的值(滑动窗口已经满了)
 * 每次队列的头都是滑动窗口中值最大的
 */
public class Test_239 {
    public static int[] maxSlidingWindow_final(int[] nums, int k) {
        // 双向队列，记录索引位置。最大为k
        Deque<Integer> deque = new LinkedList<>();

        int length = nums.length;
        int[] result = new int[length - k + 1];
        for (int i = 0; i < length; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
            if (!deque.isEmpty() && i >= deque.peek() + k) {
                deque.removeFirst();
            }

            // 开始记录
            if (i + 1 - k >= 0) {
                result[i + 1 - k] = nums[deque.peek()];
            }
        }
        return result;
    }



    public static int[] maxSlidingWindow_1(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k < 1) {
            return new int[0];
        }
        if (nums.length == 1) {
            return nums;
        }

        List<Integer> result = new ArrayList<>();
        Deque<Integer> window = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!window.isEmpty()) {
                //如果 追加的值的索引 跟 队列头部的值的索引 的差，超过窗口大小，那就删掉头部的值(滑动窗口已经满了)
                if (i - k >= window.peek()) {
                    //删掉头部的值
                    window.pop();
                }
                //如果新来的值比尾部的大，那就删掉尾部，再追加到后面
                while(!window.isEmpty() && nums[i] > nums[window.getLast()]){
                    //删掉尾部的值
                    window.removeLast();
                }
            }
            //如果新来的值比队列尾部的数小，那就追加到后面，因为它可能在前面的最大值划出窗口后成为最大值
            window.offer(i);
            //当坐标正式超过k的时候，可以开始往result添加元素了
            if (i + 1 >= k) {
                result.add(nums[window.peek()]);
            }

        }
        int[] result_1 = new int[nums.length - k + 1];
        for (int i = 0; i < result_1.length; i++) {
            result_1[i] = result.get(i);
        }
        return result_1;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k < 1 || nums.length < k) {
            return new int[0];
        }
        if (nums.length == 1) {
            return nums;
        }
        int[] result = new int[nums.length - k + 1];

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty()) {
                //如果追加的值比的索引跟队列头部的值的索引超过窗口大小，那就删掉头部的值
                if (i - k >= deque.peek()) {
                    deque.pop();
                }

                //有while，一般条件要和上一层if的条件保持相同
                //如果新来的值比尾部的大，那就删掉尾部，再追加到后面
                while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                    deque.removeLast();
                }
            }

            deque.offer(i);
            System.out.println(deque.peek());

            if (i + 1 >= k) {
                result[i - k + 1] = nums[deque.peek()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] num = {1,-1};
        int size = 1;
        System.out.println(Arrays.toString(maxSlidingWindow(num, size)));
    }

}
