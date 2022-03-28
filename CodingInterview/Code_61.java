import java.util.HashSet;

/*
从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
示例1:
输入: [1,2,3,4,5]
输出: True

示例2:
输入: [0,0,1,2,5]
输出: True
 */
public class Code_61 {
    public boolean isStraight(int[] nums) {
        HashSet<Integer> visited = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int num : nums){
            if (num == 0) {
                continue;
            }
            if (visited.contains(num)) {
                return false;
            }

            min = Math.min(min, num);
            max = Math.max(max, num);
            visited.add(num);
        }
        // 五个里面，必然有最大值-最小值<5，才会是一个顺子。
        return max - min < 5;
    }
}
