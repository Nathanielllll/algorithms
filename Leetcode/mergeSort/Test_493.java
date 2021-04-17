package mergeSort;

/**
 * @author luzhi
 * @date 2021/4/17
 */
public class Test_493 {
    /*
    给定一个数组nums，如果i < j且nums[i] > 2*nums[j]我们就将(i, j)称作一个重要翻转对。

    你需要返回给定数组中的重要翻转对的数量。

    示例 1:

    输入: [1,3,2,3,1]
    输出: 2
    示例 2:

    输入: [2,4,3,5,1]
    输出: 3
     */
    public static void main(String[] args) {
        int[] nums = {1,3,2,3,1};
        System.out.println(reversePairs(nums));
    }

    static int count;
    public static int reversePairs(int[] nums) {
        return inversePairs(nums);
    }

    public static int inversePairs(int[] data) {
        count = 0;
        if (data == null || data.length <= 0) {
            return 0;
        }
        int[] temp = new int[data.length];
        mergeSort(data, 0, data.length - 1, temp);
        return count;
    }

    private static void mergeSort(int[] data, int start, int end, int[] temp) {
        if (start < end) {
            int mid = (start + end) >> 1;
            mergeSort(data, start, mid, temp);
            mergeSort(data, mid + 1, end, temp);
            merge(data, start, mid, end, temp);
        }
    }

    private static void merge(int[] data, int start, int mid, int end, int[] temp) {
        int p1 = start;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= end) {
            if (data[p1] > 2 * (long) data[p2]) {
                count += mid - p1 + 1;
                p2++;
            } else {
                p1++;
            }
        }

        p1 = start;
        p2 = mid + 1;
        int k = start;

        while (p1 <= mid && p2 <= end) {
            if(data[p1] <= data[p2]){
                temp[k++] = data[p1++];
            }else {
                temp[k++] = data[p2++];
            }
        }
        while (p1 <= mid){
            temp[k++] = data[p1++];
        }
        while (p2 <= end) {
            temp[k++] = data[p2++];
        }
        for (int i = start; i <= end; i++) {
            data[i] = temp[i];
        }
    }
}
