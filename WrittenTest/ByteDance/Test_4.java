package ByteDance;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * 3
 * 4
 * 1 4 3 3
 * 5
 * 8 2 2 4 6
 * 6
 * 5 8 1 3 2 9
 *
 * 0 3 1 1
 * 4 1 1 2 3
 * 0 4 0 2 0 5
 */
public class Test_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        for (int t = 0; t < total; t++) {
            int length = scanner.nextInt();
            int[] nums = new int[length];
            for (int i = 0; i < length; i++) {
                nums[i] = scanner.nextInt();
            }
            int[] res = subProcess(nums);
            for (int i = 0; i < length; i++) {
                if (i != length - 1) {
                    System.out.print(res[i] + " ");
                }else {
                    System.out.println(res[i]);
                }
            }

        }

//        int[] nums = {8,2,2,4,6};
//        int[] res = subProcess(nums);
//        for (int i = 0; i < res.length; i++) {
//            System.out.println(res[i]);
//        }


    }

    public static int[] subProcess(int[] nums){


        int length = nums.length;

        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            int count = 0;
            //向左
            for (int j = i - 1; j >= 0; j--) {
                if(nums[i] >= nums[j]){
                    count++;
                }else {
                    break;
                }
            }

            //向右
            for (int j = i + 1; j < length; j++) {
                if(nums[i] >= nums[j]){
                    count++;
                }else {
                    break;
                }
            }
            res[i] = count;
        }
        return res;

//        int length = nums.length;
//        int[] left_max = new int[nums.length];
//        int[] left_max_index = new int[nums.length];
//
//        left_max[0] = 0;
//        left_max_index[0] = 0;
//        for (int i = 1; i < nums.length; i++) {
//            if(nums[i - 1] > left_max[i - 1]){
//                left_max[i] = nums[i - 1];
//                left_max_index[i] = i - 1;
//            }else {
//                left_max[i] = left_max[i - 1];
//                left_max_index[i] = left_max_index[i - 1];
//            }
//        }
//
//        for (int i = 0; i < length; i++) {
//            if(left_max_index[i]==i){
//                left_max_index[i] = -1;
//            }
//        }
//        int[] res = new int[length];
//        for (int i = 0; i < length; i++) {
//            res[i] += i - left_max_index[i] - 1;
//        }
//
//
//
//        int[] right_max = new int[nums.length];
//        right_max[length - 1] = nums[length - 1];
//        for (int i = length - 2; i >= 0; i--) {
//            right_max[i] = Math.max(right_max[i + 1], nums[i]);
//        }
//
//        return left_max;
//
//
//
//
////        Stack<Integer> stack = new Stack<>();
////        int[] ans = new int[nums.length];
////        for (int i = 0; i < nums.length; i++) {
////            while(!stack.isEmpty() && nums[i] < nums[stack.peek()]){
////                stack.clear();
////            }
////            ans[i] += stack.size();//??
////
////            stack.push(i);
////        }
////
////        stack.clear();
////
////        for (int i = nums.length - 1; i >= 0; i--) {
////            while(!stack.isEmpty() && nums[i] < nums[stack.peek()]){
////                stack.clear();
////            }
////            ans[i] += stack.size();
////            stack.push(i);
////        }
////
//////        for (int i = 0; i < nums.length; i++) {
//////            ans[i]++;
//////        }
////        return ans;
    }
}
