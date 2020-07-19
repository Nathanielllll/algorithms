package introductoryCourse.Chapter_2.AfterClass;

import java.util.Arrays;

/*
对于一个无序数组A，请设计一个算法，求出需要排序的最短子数组的长度。

给定一个整数数组A及它的大小n，请返回最短子数组的长度。

测试样例：
[1,5,3,4,2,6,7],7
返回：4
 */
public class Test_3 {
    public static void main(String[] args) {
        int[] A = {1,2,3,3,8,9};
        int n = 6;
        System.out.println(findShortest(A, n));
    }

    public static int findShortest(int[] A, int n) {
        int[] clone = A.clone();
        Arrays.sort(clone);
        int i = 0;
        for (; i < A.length; i++) {
            if (A[i] != clone[i]) {
                break;
            }
        }

        int j = n - 1;
        for (; j >= 0; j--) {
            if (A[j] != clone[j]) {
                break;
            }
        }
        if (j > i) {
            return  j - i + 1;
        }else {
            return 0;
        }
    }
}
