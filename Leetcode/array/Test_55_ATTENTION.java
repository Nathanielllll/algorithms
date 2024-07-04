package array;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 */
public class Test_55_ATTENTION {

    // DP问题：
    // [2,3,1,1,4]
    // f(0) = max(f(1), f(2));
    // f(1) = max(f(2), f(3), f(4));
    // f(2) = max(f(3), f(4));
    // f(3) = max(f(4));

    // dp=[,,,0,4]

    public static boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }

    /**
     * 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
     * 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
     * 如果可以一直跳到最后，就成功了。
     * */
    public static boolean canJump_2(int[] nums){
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= k) {
                //最远能达到的位置
                //每到一个位置，都会更新最大的距离
                k = Math.max(k, i + nums[i]);
            } else{
                return false;
            }

        }
        return true;
    }

    public static boolean canJump_3(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++){
            if (i > max) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {0};
        System.out.println(canJump(nums));
    }
}
