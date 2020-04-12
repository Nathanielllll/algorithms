package alibaba.March_30;

import java.util.Collections;
import java.util.PriorityQueue;

public class Test_1 {

    public static void main(String[] args) {
//        public static int resud(int[] nums, int n, int k, int m) {
        int[] nums = {3,4,5,6,2};
        int n = nums.length;
        int k = 2;
        int m = 3;
        System.out.println(resud(nums, n, k, m));
    }

    /**
     * @param nums n个鸡场的鸡的数量
     * @param n    n个鸡场
     * @param k    每天新增的鸡数
     * @param m    m天后
     * @return
     *
     * 第一题：N个养鸡场，每个鸡场有Ni只鸡，每天每个鸡场增加K只鸡，
     * 每天结束时卖掉鸡最多的那个场的所有鸡的一半，求M天后剩余鸡总数：
     *
     * 首先构造优先队列（大顶堆），维护M次即可。
     * 注意：每次我们只需要维护队头元素，而不需要对所有元素都在每次循环中都更新数据
     * 每次队头元素出队，值改为（当前值-当前是第几天*每天的增长量）/2（实际上这个更新是相对大小的更新）
     */
    public static int resud(int[] nums, int n, int k, int m) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        //n个鸡场
        for (int i = 0; i < n; i++) {
            maxHeap.add(nums[i]);
        }

        for (int i = 1; i <= m; i++) {
            int max = maxHeap.peek()- i*k;
            //第一天，max = max + k
            if (max < 0 && max % 2 != 0) {
                max = max / 2 - 1;
            }else {
                max = max / 2;
            }

            maxHeap.poll();
            maxHeap.add(max);
        }

        int sum = 0;
        for (int num : maxHeap) {
            sum += num;
        }

        return sum + n * m * k;
    }

}
