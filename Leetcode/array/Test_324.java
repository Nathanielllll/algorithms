package array;

import java.util.Arrays;

/**
 * @author luzhi
 * @date 2021/3/31
 */
public class Test_324 {
    /*
    给你一个整数数组nums，将它重新排列成nums[0] < nums[1] > nums[2] < nums[3]...的顺序。

    你可以假设所有输入数组都可以得到满足题目要求的结果。

    示例 1：

    输入：nums = [1,5,1,1,6,4]
    输出：[1,6,1,5,1,4]
    解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
    示例 2：

    输入：nums = [1,3,2,2,3,1]
    输出：[2,3,1,3,1,2]
     */

    /*
    数组按照从小到大排序后，从中间切分,比如 123456 切分后123，456 穿插进行后142536符合题意
    但是1223这种就不行了，但是穿插规则可以变一下，两部分逆序穿插，即2 3 1 2
     */
    public void wiggleSort(int[] nums) {
        int[] help = nums.clone();
        Arrays.sort(help);

        int index = help.length - 1;
        for (int i = 1; i < nums.length; i+=2) {
            nums[i] = help[index--];
        }
        for (int i = 0; i < nums.length; i+=2) {
            nums[i] = help[index--];
        }
    }
}
