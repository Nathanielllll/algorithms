public class Test_16_16 {
    /*
    给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
    注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。

示例：

输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
输出： [3,9]

以升序为例，我们要保证后面的元素比前面的元素都大，从头遍历数组，维护一个最大值变量，每遍历一个元素就更新最大值，同时如果当前元素小于最大值，就更新此时的索引。

 [1,2,4,7,10,11,7,12,6,7,16,18,19]以此为例，当遍历到7时，此时最大值是12，那么我们就将索引更新为7所指的索引，也就是需要排序的最短序列的右边界。

同理维护最小值求出左边界。
     */
    public static void main(String[] args) {
        Test_16_16 test = new Test_16_16();
        int[] nums = {1,2,4,7,10,11,7,12,6,7,16,18,19};
        int[] ans = test.subSort(nums);
        System.out.println(ans[0] + " " + ans[1]);
    }

    public int[] subSort(int[] array) {
        if (array == null || array.length == 0) {
            return new int[]{-1, -1};
        }

        int max = Integer.MIN_VALUE;
        int right_index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= max) {
                max = array[i];
            }else {
                right_index = i;
            }
        }

        int min = Integer.MAX_VALUE;
        int left_index = -1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] <= min) {
                min = array[i];
            }else {
                left_index = i;
            }
        }
        return new int[]{left_index, right_index};
    }
}
