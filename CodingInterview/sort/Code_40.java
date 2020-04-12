package sort;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 最小的k个数，要分是不是大数的情况
 */
public class Code_40 {
    //方法一，使用Code_39的思想，要使用partition的内容
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition(int[] arr, int left, int right) {
        int less = left - 1;
        int more = right;

        while (left != more) {
            if (arr[left] < arr[right]) {
                swap(arr, ++less, left++);
            } else if (arr[left] > arr[right]) {
                swap(arr, --more, left);
            } else {
                left++;
            }
        }

        swap(arr, left, right);
        return left;
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length <= 0 || k <= 0 || k > arr.length) {
            return new int[]{};
        }

        int start = 0;
        int end = arr.length - 1;
        int index = partition(arr, start, end);
        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
            } else {
                start = index + 1;
            }
            index = partition(arr, start, end);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 6, 2, 7, 3};

//        System.out.println(Arrays.toString(partition_test(arr, 0, arr.length - 1)));
//        System.out.println(Arrays.toString(getLeastNumbers(arr, 4)));
//
        System.out.println(getLeastNumbers_3(arr, 4));
    }

//    //方法二，手撸使用最大堆作为容器来进行储存
//    public static int[] getLeastNumbers_2(int[] arr, int k) {
//
//    }

    //方法三，使用java自带的最大堆作为容器
    public static int[] getLeastNumbers_3(int[] arr, int k) {
        if (arr == null || arr.length <= 0 || k <= 0 || k > arr.length) {
            return new int[]{};
        }

        //方法1
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        //方法2
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2.compareTo(o1);
//            }
//        });

        //方法3
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));

        for (int i = 0; i < arr.length; i++) {
            if (maxHeap.size() < k) {
                maxHeap.offer(arr[i]);
            } else if (!maxHeap.isEmpty() && maxHeap.peek() > arr[i]) {
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
        }

        int[] result = new int[k];
        int i = 0;
        for (Integer integer : maxHeap) {
            if (i < k) {
                result[i] = integer;
            }
            i++;
        }
        return result;
    }
}
