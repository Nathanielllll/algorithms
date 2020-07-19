//package stack;
//
//import java.util.*;
//
///*
//给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
//
//求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
//
//说明: 请尽可能地优化你算法的时间和空间复杂度。
//
//示例 1:
//
//输入:
//nums1 = [3, 4, 6, 5]
//nums2 = [9, 1, 2, 5, 8, 3]
//k = 5
//输出:
//[9, 8, 6, 5, 3]
//示例 2:
//
//输入:
//nums1 = [6, 7]
//nums2 = [6, 0, 4]
//k = 5
//输出:
//[6, 7, 6, 0, 4]
//示例 3:
//
//输入:
//nums1 = [3, 9]
//nums2 = [8, 9]
//k = 3
//输出:
//[9, 8, 9]
// */
//public class Test_321 {
//    class Pair{
//        int index;
//        int value;
//
//        public Pair(int index, int value) {
//            this.index = index;
//            this.value = value;
//        }
//    }
//
//    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
//        Pair[] Pairs_1 = new Pair[nums1.length];
//        for (int i = 0; i < nums1.length; i++) {
//            Pairs_1[i] = new Pair(i, nums1[i]);
//        }
//        Arrays.sort(Pairs_1, ((o1, o2) -> {
//            return o2.value - o1.value;
//        }));
//
//        Pair[] Pairs_2 = new Pair[nums2.length];
//        for (int i = 0; i < nums2.length; i++) {
//            Pairs_2[i] = new Pair(i, nums2[i]);
//        }
//        Arrays.sort(Pairs_2, ((o1, o2) -> {
//            return o2.value - o1.value;
//        }));
//
//        List<Integer> list = new LinkedList<>();
//        Stack<Pair> stack_1 = new Stack<>();
//        Stack<Pair> stack_2 = new Stack<>();
//        int i_1 = 0, i_2 = 0;
//        while (i_1 < nums1.length && i_2 < nums2.length) {
//
//                while (!stack_1.isEmpty() && stack_1.peek().index < Pairs_1[i_1].index) {
//                    Pair pair = stack_1.pop();
//                    list.add(pair.value);
//                }
//                stack_1.push(Pairs_1[i_1]);
//                i_1++;
//            }else {
//                while (!stack_2.isEmpty() && stack_2.peek().index < Pairs_2[i_2].index) {
//                    Pair pair = stack_2.pop();
//                    list.add(pair.value);
//                }
//                stack_2.push(Pairs_2[i_2]);
//                i_2++;
//            }
//        }
//
//        return null;
//
//
//    }
//
//    public static void main(String[] args) {
//        int[] nums1 = {3, 4, 6, 5};
//        int[] nums2 = {9, 1, 2, 5, 8, 3};
//        int k = 5;
//        Test_321 test = new Test_321();
//        System.out.println(test.maxNumber(nums1, nums2, k));
//    }
//}
