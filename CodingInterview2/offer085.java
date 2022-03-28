import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class offer085 {
    List<String> result;
    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        dfs("", n, n);
        return result;
    }


    /**
     * 当前左右括号都有大于 0 个可以使用的时候，才产生分支；
     * 产生左分支的时候，只看当前是否还有左括号可以使用；
     * 产生右分支的时候，还受到左分支的限制，右边剩余可以使用的括号数量一定得在严格大于左边剩余的数量的时候，才可以产生分支；
     * 在左边和右边剩余的括号数都等于 0 的时候结算。
     *
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param curStr
     * @param l
     * @param r
     */
    private void dfs(String curStr, int l, int r) {
        if (l == 0 && r == 0) {
            result.add(curStr);
            return;
        }

        if (l > r) {
            return;
        }

        if (l > 0) {
            dfs(curStr + "(", l - 1, r);
        }

        if (r > 0) {
            dfs(curStr + ")", l, r - 1);
        }
    }


}
