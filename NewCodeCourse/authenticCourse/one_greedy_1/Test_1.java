package authenticCourse.one_greedy_1;

import java.util.Arrays;

/*
给定一个有序数组arr，代表数轴上从左到右有n个点arr[0]...arr[n-1]。给定一个正数L，
代表一根长度为L的绳子，求绳子最多能覆盖其中的几个点

使用滑动窗口的概念？或者是双指针
 */
public class Test_1 {
    public static int maxPoint(int[] arr, int L){
        int left = 0, right = 0;
        int N = arr.length;
        int maxRes = 0;

        while (right < N) {
            while (right < N && arr[right] - arr[left] <= L) {
                right++;
            }
            maxRes = Math.max(maxRes, right - left);
            left++;
        }

        return maxRes;
    }


    //for test
    public static int[] generateArray(int len, int max){
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }
}
