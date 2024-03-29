package array;

import java.util.HashSet;

/*
爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。

因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）

返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1]是 Bob 必须交换的糖果棒的大小。

如果有多个答案，你可以返回其中任何一个。保证答案存在。



示例 1：

输入：A = [1,1], B = [2,2]
输出：[1,2]
示例 2：

输入：A = [1,2], B = [2,3]
输出：[1,2]
示例 3：

输入：A = [2], B = [1,3]
输出：[2,3]
 */
public class Test_888 {
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0;
        for(int num : A){
            sumA += num;
        }

        int sumB = 0;
        for(int num : B){
            sumB += num;
        }

        int gap = (sumB - sumA)/2; // gap + x = y；x在A中，y在B中
        HashSet<Integer> hashSet = new HashSet<>();
        for(int num : B){
            hashSet.add(num);
        }

        for(int num : A){
            if (hashSet.contains(num + gap)) {
                return new int[]{num, num + gap};
            }
        }
        return new int[]{};
    }
}
