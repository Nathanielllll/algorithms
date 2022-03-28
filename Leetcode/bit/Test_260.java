package bit;

/**
 * 给定一个整数数组nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 *
 * 示例 :
 *
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * 注意：
 *
 * 结果输出的顺序并不重要，对于上面的例子，[5, 3]也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 */
public class Test_260 {
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length < 2) {
            throw new RuntimeException("invalid input");
        }

        int resultExclusiveOR = 0;
        for (int i = 0; i < nums.length; i++) {
            resultExclusiveOR = resultExclusiveOR ^ nums[i];
        }

        int index = findFirstIndex(resultExclusiveOR);

        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (isFirstOne(nums[i], index)) {
                num1 = num1 ^ nums[i];
            }else {
                num2 = num2 ^ nums[i];
            }
        }
        return new int[] {num1, num2};
    }

    private int findFirstIndex(int num){
        int index = 0;
        while (index < 32 && (num & 1) == 0) {
            num = num >> 1;
            index++;
        }
        return index;
    }

    private boolean isFirstOne(int num, int index){
        num = num >> index;
        return (num & 1) == 1;
    }
}
