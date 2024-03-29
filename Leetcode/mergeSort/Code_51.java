package mergeSort;

public class Code_51 {

    /*
    在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     */
    static int count;

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
        int k = start;

        while (p1 <= mid && p2 <= end) {
            if(data[p1] <= data[p2]){
                temp[k++] = data[p1++];
            }else {
                count += mid - p1 + 1;
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

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 7, 6, 5};
        System.out.println(inversePairs(data)); // 3
        int[] data2 = {6, 5, 4, 3, 2, 1};
        System.out.println(inversePairs(data2)); //  15
        int[] data3 = {1, 2, 3, 4, 5, 6};
        System.out.println(inversePairs(data3)); // 0
        int[] data4 = {1};
        System.out.println(inversePairs(data4)); // 0
        int[] data5 = {1, 2};
        System.out.println(inversePairs(data5)); // 0
        int[] data6 = {2, 1};
        System.out.println(inversePairs(data6)); // 1
        int[] data7 = {1, 2, 1, 2, 1};
        System.out.println(inversePairs(data7)); // 3
        int[] data8 = {7,5,6,4};
        System.out.println(inversePairs(data8)); // 5
    }
}
