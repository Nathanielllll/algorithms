package sort;

/**
 * 把数组排成最小的数
 * 考虑大数问题，因此采用字符串/数组
 */
public class Code_45 {

    public static void main(String[] args) {
        int[] arr = {3, 32, 321};
        System.out.println(printMinNumber(arr));
    }

    public static String printMinNumber(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return null;
        }
        if (arr.length == 1) {
            return String.valueOf(arr[0]);
        }
        String string = "";
        //本质上用冒泡排序，先排好最前面的
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                String a = arr[i] + "" + arr[j];
                String b = arr[j] + "" + arr[i];
                //大于0，则a大于b,交换i,j
                if (a.compareTo(b) > 0) {
                    swap(arr, i, j);
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            string += String.valueOf(arr[i]);
        }
        return string;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
