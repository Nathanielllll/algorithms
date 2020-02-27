import java.util.ArrayList;
import java.util.List;

/**
 * 和为s的两个数字。
 * 输入一个递增排序的数组和一个数字s，在数组中找到两个数，使得他们的和正好是s。
 * <p>
 * 使用双指针。
 */
public class Code_57 {
    public static void findNumbersWithSum(int[] data, int sum) {
        if (data == null || data.length <= 0) {
            throw new RuntimeException("invalid input");
        }

        int low = 0;
        int high = data.length - 1;
        while (low != high) {
            int curSum = data[low] + data[high];
            if (curSum == sum) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(data[low]);
                arrayList.add(data[high]);
                System.out.println(arrayList);
                break;
            } else if (curSum > sum) {
                high--;
            } else {
                low++;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 4, 7, 11, 15};
        findNumbersWithSum(data, 15);
    }
}
