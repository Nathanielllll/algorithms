package sort;

import java.util.Arrays;

/**
 * 调整数组顺序，使得奇数位与偶数的前面
 */
public class Code_21 {
    /**
     * 使用了快速排序的思想，其中right没什么作用
     *
     * @param arr
     */
    public static void reorderOddEven(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int odd = left - 1;
        int even = right + 1;
        while (left < even) {
            //奇数
            if (arr[left] % 2 == 1) {
                swap(arr, ++odd, left++);
            } else {
                swap(arr, --even, left);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{2, 3, 5, 3, 7, 3, 4, 6, 8, 10};
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        reorderOddEven(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = new int[]{2, 3, 5, 3, 7, 3, 4, 6, 8, 10};
        reorderOddEven(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        reorderOddEven(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}
