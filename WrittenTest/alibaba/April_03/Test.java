package alibaba.April_03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * 找出数组中有价值的数的个数，有价值的数定义为：该数左边有比他大的数，找出最小值，右边有比他小的数，找出最大值，而且这两个值能成倍数关系。
 * 输入有两行，第一行是数组长度n，第二行是数组a中每个数的值，数组长度的限制为小于1000，数组元素的值限制为小于10^18
 * 输出是有价值的数的个数
 * 例如：
 * 输入
 * 3
 * 4 3 2
 * 输出
 * 1
 * <p>
 * 思路：先对数组排序，再使用单调栈。
 * 例如，一个数组：
 * 数组索引： 0    1     2   3    4     5     6
 * 数组的值：200  150   80  100   82    50   90
 * 则带着索引一起，对数组的值排序后，变成：
 * 数组索引： 5   2    4    6     3    1     0
 * 数组的值：50   80   82   90  100   150   200
 * <p>
 * 可以看出排序前某个数左边大数的最小值，就是去排序后它的右边找第一个比它索引小的索引对应的值，
 * 同理，对于排序前某个数右边小数的最大值，就是去排序后它的左边找第一个比它索引大的索引对应的值。可以用单调栈实现。
 */
public class Test {
    static class Pair {
        int index;
        long val;

        Pair(int index, long val) {
            this.index = index;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        long[] nums = {200,150,80,100,82,50,90};
        System.out.println(getValue(nums));

    }

    public static int getValue(long[] nums) {
        Pair[] pairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new Pair(i, nums[i]);
        }

        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return (int)(o1.val - o2.val);
            }
        });

//         * 例如，一个数组：
//                * 数组索引： 0    1     2   3    4     5     6
//                * 数组的值：200  150   80  100   82    50   90
//                * 则带着索引一起，对数组的值排序后，变成：
//                * 数组索引： 5   2    4    6     3    1     0
//                * 数组的值：50   80   82   90  100   150   200
        //                   -1   0    1    1    3    2     3

        Stack<Integer> stack = new Stack<>();
        //该数左边有比他大的数，找出最小值: 排序后的索引中，右边离它最近的比它小的索引值
        int[] leftMoreMin = new int[nums.length];
        Arrays.fill(leftMoreMin, -1);
        for (int i = 0; i < pairs.length; i++) {
            while(!stack.isEmpty() && stack.peek() > pairs[i].index){
                int index = stack.pop();
                leftMoreMin[index] = pairs[i].index;
            }
            stack.push(pairs[i].index);
        }

        stack.clear();
        // 右边有比他小的数，找出最大值：排序的索引中，左边离它最近的比它大的索引值
        int[] rightLessMax = new int[nums.length];
        Arrays.fill(rightLessMax, -1);
        for (int i = pairs.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() < pairs[i].index){
                int index = stack.pop();
                rightLessMax[index] = pairs[i].index;
            }
            stack.push(pairs[i].index);
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftMoreMin[i] != -1 && rightLessMax[i] != -1 && nums[leftMoreMin[i]] % nums[rightLessMax[i]] == 0) {
                count++;
            }
        }
        return count;
    }
}
