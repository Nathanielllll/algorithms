package tencent;

import java.util.Scanner;

public class Test_3 {

    static int count;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int total = 1 << n;
        int[] data = new int[total];
        for (int i = 0; i < total; i++) {
            data[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int num = scanner.nextInt();
            int reverseGap = 1 << num;
            int index = 0;
            if (num != 0) {
                while(index < total && index + reverseGap - 1 < total){
                    reverse(data, index, index + reverseGap - 1);
                    index += reverseGap;
                }
            }

            int[] temp = new int[data.length];
            count = 0;
            int[] data_copy = data.clone();
            mergeSort(data_copy, 0, data.length - 1, temp);
            System.out.println(count);
        }
    }

    private static void reverse(int[] reversed_data, int start, int end){
        while(start < end){
            int temp = reversed_data[start];
            reversed_data[start] = reversed_data[end];
            reversed_data[end] = temp;
            start++;
            end--;
        }
    }


    private static void mergeSort(int[] data, int start, int end, int[] temp) {
        if (start < end) {
            int mid = (start + end) >> 1;
            mergeSort(data, start, mid, temp);
            mergeSort(data, mid + 1, end, temp);
            merge(data, start, end, mid, temp);
        }
    }

    public static void merge(int[] nums, int left, int right, int mid, int[] temp){
        int index = left;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            if(nums[p1] < nums[p2]){
                temp[index++] = nums[p1++];
            }else {
                //假设前后都已经排序好了
                count += mid - p1 + 1;
                temp[index++] = nums[p2++];
            }
        }
        while (p1 <= mid){
            temp[index++] = nums[p1++];
        }
        while (p2 <= right) {
            temp[index++] = nums[p2++];
        }
        for (int i = left; i <= right; i++) {
            nums[i] = temp[i];
        }
    }
}
