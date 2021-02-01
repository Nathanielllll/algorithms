package twoPointers;

import java.util.Arrays;

public class Test_581 {
    // O(Nlog(N))
    public int findUnsortedSubarray(int[] nums) {
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        int p1 = 0;
        int p2 = nums.length - 1;
        while (p1 < nums.length) {
            if (nums[p1] != sortedNums[p1]) {
                break;
            }
            p1++;
        }
        if (p1 == nums.length) {
            return 0;
        }

        while (p2 >= 0) {
            if (nums[p2] != sortedNums[p2]) {
                break;
            }
            p2--;
        }
        return p2 - p1 + 1;
    }

    // O(N)

}
