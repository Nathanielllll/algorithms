import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class offer086 {

    static List<List<String>> res_list;

    public static String[][] partition(String s) {
        res_list = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return new String[0][0];
        }
        int n = s.length();
        dfs(0, n, new Stack<>(), s);
        // List<List<String>> 转 String[][]，这里不重要
        String[][] ans = new String[res_list.size()][];
        for (int i = 0; i < res_list.size(); i++) {
            ans[i] = new String[res_list.get(i).size()];
            for (int j = 0; j < ans[i].length; j++) {
                ans[i][j] = res_list.get(i).get(j);
            }
        }
        return ans;
    }

    private static void dfs(int pos, int n, Stack<String> stack, String s) {
        if (pos == n) {
            res_list.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (pos + i > n) {
                break;
            }

            String segment = s.substring(pos, pos + i);
            if (require(segment)) {
                stack.push(segment);
                dfs(pos + i, n, stack, s);
                stack.pop();
            }
        }
    }

    private static boolean require(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println(partition("google"));
    }
}
