package weizhongBank.October_15;

import java.util.Arrays;
import java.util.Scanner;

public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = scanner.nextInt();
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(i==nums.length - 1){
                System.out.print(nums[i]);
            }else {
                System.out.print(nums[i] + " ");
            }
        }
    }
}
