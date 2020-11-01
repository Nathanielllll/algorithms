package baidu.October_12;

import java.util.*;

public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String size = scanner.nextLine();
        String str = scanner.nextLine();
//        String str = "21202";
        System.out.println(process(str));
    }

    private static int process(String str) {
        int len = str.length();
        int[] nums = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            nums[i] = c - '0';
        }

        HashMap<Integer, Integer> hashMap = findLeastIndex(nums);
        int[] dp = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            int num = nums[i-1];
            int leastIndex = hashMap.get(num);
            if(leastIndex == i-1){
                dp[i] = dp[i - 1] + 1;
            }else {
                dp[i] = Math.min(dp[i - 1], dp[leastIndex]) + 1;
            }


//            List<Integer> preIndexes = findPreIndexes(nums, i - 1, nums[i - 1]);
//            if (preIndexes.size() == 0) {
//                dp[i] = dp[i - 1] + 1;
//            } else {
//                int min = len;
//                for (int j = 0; j < preIndexes.size(); j++) {
//                    min = Math.min(min, preIndexes.get(j));
//                }
//                dp[i] = Math.min(dp[i - 1], min) + 1;
//            }
        }
        return dp[len];
    }

    private static HashMap<Integer, Integer> findLeastIndex(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            hashMap.put(num, i);
        }
        return hashMap;
    }

    private static List<Integer> findPreIndexes(int[] nums, int curIndex, int num) {
        List<Integer> res = new LinkedList<>();
        for (int i = curIndex - 1; i >= 0; i--) {
            if (nums[i] == num) res.add(i);
        }
        return res;
    }
}
