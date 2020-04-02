package netEase.packet1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Test_4 {
    //    3
//            100 98 87
//            3
//            1
//            2
//            3
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
            //分数，顺序的序号
            hashMap.put(i + 1, nums[i]);
        }
        Arrays.sort(nums);

        HashMap<Integer, Integer> hashMap2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            hashMap2.put( nums[i], i+1);
        }

        int size = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            int num = scanner.nextInt();
            String res = String.format("%.6f", subProcess(num, nums, hashMap, n, hashMap2));
            System.out.println(res);
        }
    }

    static double subProcess(int num, int[] nums, HashMap<Integer, Integer> hashMap, int n, HashMap<Integer, Integer> hashMap2) {
        int score = hashMap.get(num);
        int index = hashMap2.get(score);

        double result = (double)(index - 1) / n * 100;
        return result;
    }
}
