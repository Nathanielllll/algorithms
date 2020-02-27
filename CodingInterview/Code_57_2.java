import java.util.ArrayList;
import java.util.List;

/**
 * 和为s的连续正数序列
 * 输入一个正数s，打印所有和为s的连续序列（至少含有两个数）
 * <p>
 * 使用双指针，从第1、第2个数开始
 */
public class Code_57_2 {
    public static int getSum(int low, int high) {
        return (low + high) * (high - low + 1) / 2;
    }

    public static void findContinuousSequence(int sum) {
        if (sum < 3) {
            throw new RuntimeException("invalid input");
        }
        int low = 1;
        int high = 2;
        int middle = (1 + sum) / 2;
        while (low < middle) {
            int curSum = getSum(low, high);
            if (curSum < sum) {
                high++;
            } else if (curSum > sum) {
                low++;
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(low);
                arrayList.add(high);
                System.out.println(arrayList);
                high++;
            }
        }
    }

    public static void main(String[] args) {
        findContinuousSequence(15);
    }
}
