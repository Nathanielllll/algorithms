package baidu;

public class Test_2 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//
//        long[] nums = new long[n];
//        long sum = 0;
//        for (int i = 0; i < n; i++) {
//            nums[i] = sc.nextLong();
//        }
        int n = 3;
        long[] nums = {1,0,11};
        long sum = 0;

        while (isValid(nums)) {
            long max = 0;
            int index = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                    index = i;
                }
            }
            sum += max / n;

            for (int i = 0; i < n; i++) {
                if (i == index) {
                    continue;
                }
                nums[i] += max / n;
            }
            nums[index] = max % n;
        }

        System.out.println(sum);
        for (int i = 0; i < n; i++) {
            System.out.print(nums[i]+",");
        }
    }

    private static boolean isValid(long[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= nums.length) {
                return true;
            }
        }
        return false;
    }

}
