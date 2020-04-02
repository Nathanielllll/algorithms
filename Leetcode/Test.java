import java.util.*;

public class Test {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            int n = sc.nextInt();
//            int[] nums = new int[n];
//            for (int i = 0; i < n; i++) {
//                nums[i] = sc.nextInt();
//            }
//
//            int start = 0;
//            int end = 0;
//            while (end < nums.length) {
//                if (end < nums.length - 1 && nums[end] > nums[end + 1]){
//                    start = end;
//                    while (end < nums.length - 1 && nums[end] > nums[end + 1]){
//                        end++;
//                    }
//                    reverse(nums, start, end);
//                    break;
//                }
//                end++;
//            }
//
//            for (int i = 0; i < nums.length - 1; i++) {
//                if(nums[i] > nums[i+1]){
//                    System.out.println("no");
//                }
//            }
//
//            System.out.println("yes");
//
//        }
//    }


    public static void main(String[] args) {
        float res = (float) Math.PI;
        String ans = String.format("%.5f", res);
        System.out.println(ans);
    }
    private static void reverse(int[] nums, int i, int j){
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
