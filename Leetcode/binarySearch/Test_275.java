package binarySearch;

/**
 * @author luzhi
 * @date 2021/3/7
 */
public class Test_275 {
    public static void main(String[] args) {
        int[] citations = {0, 1, 3, 3, 4, 5, 6};
        System.out.println(hIndex(citations));
    }

    public static int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int left = 0;
        int right = citations.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (citations[mid] == citations.length - mid) {
                return citations[mid];
            } else if (citations[mid] < citations.length - mid) {
                left =  mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 如果在while循环里，没有return掉．那么就意味着要找的那个Ｈ在right到left之间，而数组中没有．所以出了while循环后return citations.length - left一定为为要找的H
        return citations.length - left;
    }

}
