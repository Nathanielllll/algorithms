package search;

/**
 * 在排序数组中查找数字。使用二分查找的思想
 * <p>
 * 1、找到第一个k：
 * 数组中间的数字和k进行比较
 * 1）如果中间的数字比k大，那么k只可能出现在数组的前半段
 * 2）如果中间的26数字比k小，那么k只可能出现在数组的后半段
 * 3）如果中间的数字等于k，1）中间数字的前面一个不是k，那么中间的数字就是第一个k
 *                      2）中间数字的前面一个是k，那么第一个k肯定在前半段
 */
public class Code_53 {
    public static int getNumberOfK(int[] data, int k) {
        if (data == null || k < data[0] || k > data[data.length - 1]) {
            throw new RuntimeException("invalid input");
        }
        int first = getFirstK(data, k, 0, data.length - 1);
        int last = getLastK(data, k, 0, data.length - 1);
        return last - first + 1;
    }

    public static int getFirstK(int[] data, int k, int start, int end) {
        //method-1
        while (start <= end) {
            int middle = (end + start) >> 1;
            if (data[middle] == k) {
                //注意这个条件！细节。还有middle==0的特殊情况，第一个元素的时候
                if ((middle > 0 && data[middle - 1] != k) || middle == 0) {
                    return middle;
                } else {
                    end = middle - 1;
                }
            } else if (data[middle] > k) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        //特殊情况，此时到最末尾了，即最后一个元素的情况
        //和Code_53_2的情况又略有不同，要十分注意
        return start == data.length - 1 ? data.length - 1 : -1;

        //method-2
//        int middle = (end + start) >> 1;
//        if (data[middle] == k) {
//            //注意这个条件！细节
//            if ((middle > 0 && data[middle - 1] != k) || middle == 0) {
//                return middle;
//            } else {
//                end = middle - 1;
//            }
//        } else if (data[middle] > k) {
//            end = middle - 1;
//        } else {
//            start = middle + 1;
//        }
//        return getFirstK(data, k, start, end);
    }

    public static int getLastK(int[] data, int k, int start, int end) {
        int middle = (end + start) >> 1;
        if (data[middle] == k) {
            //注意这个条件！
            if ((middle < data.length - 1 && data[middle + 1] != k) || middle == data.length - 1) {
                return middle;
            } else {
                start = middle + 1;
            }
        } else if (data[middle] > k) {
            end = middle - 1;
        } else {
            start = middle + 1;
        }
        return getLastK(data, k, start, end);
    }

    public static void main(String[] args) {
        int[] data = {1, 3, 5, 5, 5, 6, 7, 9};
        System.out.println(getFirstK(data, 9, 0, data.length - 1));
//        System.out.println(getNumberOfK(data, 5));

    }


}
