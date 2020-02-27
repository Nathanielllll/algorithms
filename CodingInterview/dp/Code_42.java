package dp;

/**
 * 连续子数组的最大和
 * <p>
 * 需要用curSum来记录累加的子数组和。和max来记录最大子数组和
 * <p>
 * 动态规划思想，其动态方程为max( dp[ i ] ) = getMax( max( dp[ i -1 ] ) + arr[ i ] ,arr[ i ] )
 */
public class Code_42 {
    public static int findGreatestSumOfSubArray(int[] arr) {
        if (arr == null || arr.length <= 0) {
            throw new RuntimeException("invalid input");
        }

        int curSum = 0;
        //另外需要的一个变量，用来存储最大的和
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            // 如果当前和小于等于0，就重新设置当前和
            if (curSum <= 0) {
                curSum = num;
                // 如果当前和大于0，累加当前和
            } else {
                curSum += num;
            }
            // 更新记录到的最大的子数组和
            if (max < curSum) {
                max = curSum;
            }
        }
        return max;
    }

    public static int findGreatestSumOfSubArray_2(int[] arr) {
        if (arr == null || arr.length <= 0) {
            throw new RuntimeException("invalid input");
        }
        int max = arr[0];
        int curSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            curSum = getMax(curSum + arr[i], arr[i]);
            if (max < curSum) {
                max = curSum;
            }
        }
        return max;
    }

    public static int getMax(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
        int[] data2 = {-2, -8, -1, -5, -9};
        int[] data3 = {2, 8, 1, 5, 9};

        System.out.println(findGreatestSumOfSubArray(data));
        System.out.println(findGreatestSumOfSubArray(data2));
        System.out.println(findGreatestSumOfSubArray(data3));
        System.out.println("===============>");
        System.out.println(findGreatestSumOfSubArray_2(data));
        System.out.println(findGreatestSumOfSubArray_2(data2));
        System.out.println(findGreatestSumOfSubArray_2(data3));
    }

}
