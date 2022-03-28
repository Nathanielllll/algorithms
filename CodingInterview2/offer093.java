import java.util.HashMap;
import java.util.Map;

public class offer093 {
    public static int lenLongestFibSubseq(int[] arr) {
        int length = arr.length;
        Map<Integer, Integer> num2Idx = new HashMap<>();
        for (int i = 0; i < length; i++) {
            num2Idx.put(arr[i], i);
        }

        int[][] dp = new int[length][length];

        int result = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                int curNum = arr[i];
                int preNum = arr[j];
                int gap = curNum - preNum;

                if (preNum > gap && num2Idx.containsKey(gap)) {
                    dp[i][j] = dp[j][num2Idx.get(gap)] + 1;
                } else {
                    dp[i][j] = 2;
                }
                result = Math.max(result, dp[i][j]);
            }

        }
        return result > 2 ? result : 0;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(lenLongestFibSubseq(nums));
    }
}
