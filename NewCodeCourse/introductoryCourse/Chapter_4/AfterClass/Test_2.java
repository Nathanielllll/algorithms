package introductoryCourse.Chapter_4.AfterClass;

import java.util.HashMap;

/*
给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），需要满足 index1 小于index2.。注意：下标是从1开始的
假设给出的数组中只存在唯一解
例如：
给出的数组为 {2, 7, 11, 15},目标值为9
输出 ndex1=1, index2=2



Given an array of integers, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2


 */
public class Test_2 {
    public static void main(String[] args) {
        int[] numbers={2, 7, 11, 15}; int target=9;
        int[] result = twoSum(numbers, target);
        for (int i = 0; i < 2; i++) {
            System.out.println(result[i]);
        }
    }

    public static int[] twoSum (int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return new int[]{};
        }

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int rest = target - numbers[i];
            if (hashMap.containsKey(rest)) {
                return new int[] {hashMap.get(rest) + 1, i + 1};
            }
            hashMap.put(numbers[i],i);
        }
        return new int[]{};
    }
}
