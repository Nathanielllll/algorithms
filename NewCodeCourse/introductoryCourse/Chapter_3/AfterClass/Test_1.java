package introductoryCourse.Chapter_3.AfterClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (br.ready()) {
            int total = Integer.parseInt(br.readLine());
            String[] strings = br.readLine().split(" ");
            int[] array = new int[total];
            for (int i = 0; i < total; i++) {
                array[i] = Integer.parseInt(strings[i]);
            }
            int method = Integer.parseInt(br.readLine());//排序方法0（降序）或者1（升序）
            quickSort(array);
            if(method == 1) {
                for (int i = 0; i < array.length / 2; i++) {
                    swap(array, i, array.length - i - 1);
                }
            }
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<array.length;i++){
                sb.append(array[i]+" ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    public static void quickSort(int[] arr){
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int start, int end){
        if(start < end){
            int index = partition(arr, start, end);
            quickSort(arr, start, index - 1);
            quickSort(arr, index + 1, end);
        }
    }

    public static int partition(int[] arr, int left, int right){
        int less = left - 1;
        int more = right;
        int target = arr[right];
        while(left != more){
            if (arr[left] < target) {
                swap(arr, ++less, left++);
            }else if(arr[left] > target){
                swap(arr, --more, left);
            }else {
                left++;
            }
        }
        swap(arr, left, right);
        return left;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
