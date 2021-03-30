package array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luzhi
 * @date 2021/3/27
 */
/*
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。

 

示例 1：

输入：nums = [10,2]
输出："210"
 */
public class Test_179 {
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        list.sort((s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        // 防止类似[0, 0]的情况
        if (list.get(0).equals("0")) {
            return "0";
        }

        StringBuffer resultBuffer = new StringBuffer();
        for (String num : list) {
            resultBuffer.append(num);
        }

        return resultBuffer.toString();
    }
}
