package string;

/**
 * 替换空格
 * <p>
 * 使用双指针
 */
public class Code_5 {
    public static String replaceBlank(String s) {
        if (s == null) {
            System.out.println("输入错误！");
            return null;
        }

        int oldLength = s.length();
        //空格的个数
        int numberOfBlank = 0;
        for (int i = 0; i < oldLength; i++) {
            if (s.charAt(i) == ' ') {
                numberOfBlank++;
            }
        }

        //新的string的长度
        int newLength = numberOfBlank * 2 + oldLength;
        char[] ans = new char[newLength];

        //p1, p2双指针
        int p1 = oldLength - 1;
        int p2 = newLength - 1;

        while (p1 != p2) {
            if (s.charAt(p1) != ' ') {
                ans[p2--] = s.charAt(p1);
            } else {
                ans[p2--] = '0';
                ans[p2--] = '2';
                ans[p2--] = '%';
            }
            p1--;
        }
        while (p1 >= 0) {
            ans[p1] = s.charAt(p1);
            p1--;
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        String s1 = "We are happy.";
        System.out.println(replaceBlank(s1));
    }

}


