package alibaba.March_30;

public class Test_1 {

    public static void main(String[] args) {

    }

    /**
     * @param nums n个鸡场的鸡的数量
     * @param n    n个鸡场
     * @param k    每天新增的鸡数
     * @param m    m天后
     * @return
     */
    public static int subProcess(int[] nums, int n, int k, int m) {
        int sum = 0;
        for (int day = 0; day < m; day++) {
            subProcess_1(nums, n, k);
        }

        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        return sum;
    }

    public static void subProcess_1(int[] nums, int n, int k) {
        //每天先增加k个
        for (int i = 0; i < n; i++) {
            nums[i] += k;
        }

        //每天卖最多的一半
        int index = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (max < nums[i]) {
                max = nums[i];
                index = i;
            }
        }

        nums[index] = nums[index] / 2;
    }
}
