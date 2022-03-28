public class offer075 {
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int upper = 0;
        for (int num : arr1) {
            upper = Math.max(upper, num);
        }

        int[] cnt = new int[upper + 1];
        for (int num : arr1) {
            ++cnt[num];
        }

        int[] result = new int[arr1.length];
        int idx = 0;

        for (int num : arr2) {
            while (cnt[num] > 0) {
                result[idx++] = num;
                --cnt[num];
            }
        }

        for (int i = 0; i <= upper; i++) {
            while (cnt[i] > 0 && idx < arr1.length) {
                result[idx++] = i;
                --cnt[i];
            }
        }

        return result;
    }

}
