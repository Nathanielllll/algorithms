import java.util.Random;

public class offer071 {
    class Solution {

        int[] sums;
        Random random;
        int[] w;
        public Solution(int[] w) {
            this.w = w;
            sums = new int[w.length];
            sums[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                sums[i] = sums[i - 1] + w[i];
            }

            random = new Random(System.currentTimeMillis());
        }

        public int pickIndex() {
            int randomNum = random.nextInt(sums[sums.length - 1]);
            return binarySearchIdx(randomNum + 1);
        }

        private int binarySearchIdx(int target){
            int left = 0;
            int right = sums.length - 1;

            while (left <= right) {
                int mid = (left + right) >> 1;
                if (sums[mid] == target) {
                    right = mid - 1;
                } else if (sums[mid] > target) {
                    right = mid - 1;
                } else if (sums[mid] < target) {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
    }

}
