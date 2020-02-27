package search;

import java.util.HashMap;

/**
 * 不修改数组找到重复的数字
 * <p>
 * method_1:所以直接用hashMap不就完事了，要什么辅助数组？
 * 使用额外的O(n)辅助空间，创建一个长度为n+1的辅助数组，然后注意把原数组的每一个数字复制。如果原数组中被复制的数字是m，
 * 则把它复制到辅助数组中下标为m的位置

 * method_2
 * 一个长度n+1的数组里所有数字都在1~n范围内，所以数组中至少有一个数字是重复的
 *
 * countRange将被调用O(logn)次,每次需要O(n)时间，因此总的时间复杂度为O(nlogn)，空间复杂度为O(1)
 * 但是此算法不能保证找到所有重复的数字
 */
public class Code_3_2_ATTENTION {
    /**method_1*/
    public static int getDuplication_1(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            throw new RuntimeException("invalid input");
        }

        // 一个长度n+1的数组里所有数字都在1~n范围内
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 1 || numbers[i] > numbers.length - 1) {
                throw new RuntimeException("invalid input");
            }
        }

//        int[] help = new int[numbers.length];
//
//        for (int number : numbers){
//            if(help[number] == 0){
//                help[number] = number;
//            }else {
//                return number;
//            }
//        }

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int number:numbers){
            if (!hashMap.containsKey(number)) {
                hashMap.put(number, 1);
            }else {
                return number;
            }
        }

        return -1;
    }

    /**method_2*/
    //计算在numbers当中的数字，其在start~end范围内出现数字的次数
    public static int countRange(int[] numbers, int start, int end) {
        if (numbers == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] >= start && numbers[i] <= end) {
                count++;
            }
        }
        return count;
    }

    //主函数
    public static int getDuplication(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            throw new RuntimeException("invalid input");
        }

        //一个长度n+1的数组里所有数字都在1~n范围内
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 1 || numbers[i] > numbers.length - 1) {
                throw new RuntimeException("invalid input");
            }
        }

        int start = 1;
        int end = numbers.length - 1; // n

        while (start <= end) {
            int middle = (end + start) >> 1;
            //start~middle出现的个数
            int count = countRange(numbers, start, middle);
            //重复的时候(start == end && count > 1的时候才是重复)
            if (start == end) {
                if (count > 1) {
                    return start;
                } else {
                    //没有重复的数字
                    break;
                }
            }
            //重复数字在start～middle中间
            if (count > (middle - start + 1)) {
                /**是end = middle 而不是end = middle - 1?*/
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 3, 5, 4, 3, 2, 6, 7};
        int[] arr2 = new int[]{2, 3, 5, 4, 1, 2, 6, 7};
        System.out.println(getDuplication(arr2));
//        System.out.println(getDuplication_1(arr1));
    }


}
