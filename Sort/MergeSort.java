import java.util.ArrayList;
import java.util.Arrays;

/**
 * 归并排序是Code_51的基础
 */
public class MergeSort {
    //两路归并算法，两个排好序的子序列合并为一个子序列
    public static void merge(int[] data, int left, int mid, int right, int[] temp) {

        int p1 = left;//左边子序列的指针
        int p2 = mid + 1;//右边子序列的指针
        int k = left;//k是存放指针

        while (p1 <= mid && p2 <= right) {
            if (data[p1] <= data[p2]) {
                temp[k++] = data[p1++];
            } else {
                temp[k++] = data[p2++];
            }
        }

        //如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while (p1 <= mid) {
            temp[k++] = data[p1++];
        }
        while (p2 <= right) {
            temp[k++] = data[p2++];
        }

        //返回原数组
        for (int i = left; i <= right; i++) {
            data[i] = temp[i];
        }
    }

    public static void mergeSort(int[] data, int start, int end, int[] temp) {
        if (start < end) {
            int mid = (start + end) >> 1;
            mergeSort(data, start, mid, temp);
            mergeSort(data, mid + 1, end, temp);
            merge(data, start, mid, end, temp);
        }
    }

    public static void mergeSort(int[] data) {
        if (data == null || data.length <= 0) {
            throw new RuntimeException("invalid input");
        }
        int[] temp = new int[data.length];
        mergeSort(data, 0, data.length - 1, temp);
    }

    public static void main(String[] args) {
        int[] data = {3, 4, 6, 8, 4, 3, 25, 3, 2, 6};
        mergeSort(data);
        System.out.println(Arrays.toString(data));
    }
}
