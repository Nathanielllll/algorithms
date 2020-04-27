package backTracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 字符串的排序
 * <p>
 * 看成两部分：
 * 第一步求所有可能出现在第一个位置的字符，即把第一个字符和后面的所有的字符交换
 * 第二步固定第一个字符，求后面的所有字符的排序。
 * 使用递归
 *
 * 回溯法
 *
 * 示例:
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 */
public class Code_38 {

    private List<String> res;
    private boolean[] used;

    public String[] permutation(String string) {
        int len = string.length();
        res = new ArrayList<>();

        if (len == 0) {
            return new String[]{};
        }

        char[] chars = string.toCharArray();
        // 修改 1：首先排序，之后才有可能发现重复分支
        Arrays.sort(chars);

        // 如果是降序，需要把 nums 变为包装数组类型，输入 Arrays.sort() 方法才生效，并且还要传入一个比较器，搜索之前，再转为基本类型数组，因此不建议降序排序
        // Integer[] numsBoxed = IntStream.of(nums).boxed().collect(Collectors.toList()).toArray(new Integer[0]);
        // Arrays.sort(numsBoxed, Collections.reverseOrder());
        // nums = Arrays.stream(numsBoxed).mapToInt(Integer::valueOf).toArray();

        used = new boolean[len];
        findPermuteUnique(chars, new Stack<>());

        String[] ans = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }

    private void findPermuteUnique(char[] chars, Stack<Character> stack) {
        if(stack.size() == chars.length){
            StringBuffer stringBuffer = new StringBuffer();
            for(char c : stack){
                stringBuffer.append(c);
            }
            res.add(stringBuffer.toString());
        }
        for (int i = 0; i < chars.length; i++) {
            if (!used[i]) {
                // 修改 2：因为排序以后重复的数一定不会出现在开始，故 i > 0
                // 和之前的数相等，并且之前的数还未使用过，只有出现这种情况，才会出现相同分支
                // 这种情况跳过即可
                if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) {
                    continue;
                }
                used[i] = true;
                stack.push(chars[i]);
                findPermuteUnique(chars, stack);
                stack.pop();
                used[i] = false;
            }
        }
    }
}
