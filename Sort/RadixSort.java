// 桶排序有计数排序，基数排序等
// 计数排序的使用范围比较窄，需要数字处于比较窄的范围内

// 因此得出基数排序
// 找到最大数，以及最大数十进制下的位数，将其余不足此位数的数用0补足。并且弄10个桶（队列结构）
// 根据个位数字选择进不同的桶，并且根据桶的顺序将数字倒出。
// 根据十位数字选择进不同的桶，并且根据桶的顺序将数字倒出。
// 根据百位数字选择进不同的桶，并且根据桶的顺序将数字倒出。
// 时间复杂度位O(logMax * N) -> O(N)
public class RadixSort {
    /*
    [13, 17, 9, 23, 21, 100]
    [013, 017, 009, 023, 021, 100]

    先进桶的数字先出（队列结构），因此013在023前面
    根据个位数字选择进不同的桶，并且根据桶的顺序将数字倒出。
    [100, 021, 013, 023, 017, 009]
    根据十位数字选择进不同的桶，并且根据桶的顺序将数字倒出。
    [100, 009, 013, 017, 021, 023]
    根据百位数字选择进不同的桶，并且根据桶的顺序将数字倒出。
    [009, 013, 017, 021, 023, 100]

     */

    // only for no-negative value
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    // arr[L..R]排序, digit: 最大的数字有多少个十进制位
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数准备多少个辅助空间
        int[] bucket = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) { // 有多少位就进出几次
            // 10个空间
            int[] count = new int[radix]; // count[0..9]
            // count[i] 当前位(d位)是i的数字有多少个
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            // ==> 累加，含义发生改变
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是(0和1)的数字有多少个
            // count[2] 当前位(d位)是(0、1和2)的数字有多少个
            // count[i] 当前位(d位)是(0~i)的数字有多少个
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 用一个巧妙的设计，代替了创建10个队列的操作
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            // 复制回原数组
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    //对x而言，从右往左第d位上的个数
    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    public static void main(String[] args) {
        int num = 12345;
        int d = 2;
        System.out.println(getDigit(num, d));
    }


}
