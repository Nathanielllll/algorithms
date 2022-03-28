public class offer096 {

    static int[][] memo;
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        memo = new int[s1.length()][s2.length()];
        return dfs(s1, s2, s3, 0, 0, 0, "");
    }
    private static boolean dfs(String s1, String s2, String s3, int i, int j, int k, String curString) {
        if (i < s1.length() && j < s2.length() && memo[i][j] != 0) {
            return memo[i][j] == 1;
        }

        if (s3.equals(curString)) {
            return true;
        }

        boolean p1 = false;
        if (i < s1.length() && k < s3.length()
                && s1.charAt(i) == s3.charAt(k)) {
            p1 = dfs(s1, s2, s3, i + 1, j, k + 1, curString + s1.charAt(i));
        }

        boolean p2 = false;
        if (j < s2.length() && k < s3.length()
                && s2.charAt(j) == s3.charAt(k)) {
            p2 = dfs(s1, s2, s3, i, j + 1, k + 1, curString + s2.charAt(j));
        }

        if (i < s1.length() && j < s2.length()) {
            memo[i][j] = p1 || p2 ? 1 : 2;
        }

        return p1 || p2;
    }

    public static void main(String[] args) {
        String s1 = "", s2 = "", s3 = "";
        System.out.println(isInterleave(s1, s2, s3));
    }
}
