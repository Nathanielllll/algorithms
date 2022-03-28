import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class offer087 {

    static List<String> result;

    public static List<String> restoreIpAddresses(String s) {
        result = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() > 12) {
            return result;
        }

        int n = s.length();
        dfs(0, n, new Stack<>(), s);
        return result;
    }

    private static void dfs(int pos, int n, Stack<String> stack, String s) {
        if (stack.size() == 4 && pos == n) {
            result.add(String.join(".", stack));
        }

        for (int i = 1; i <= 3; i++) {
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

    private static boolean require(String str) {
        if (str.startsWith("0") && str.length() > 1) {
            return false;
        }
        return str.compareTo("0") >= 0 && Integer.parseInt(str) <= 255;
    }

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
    }
}
