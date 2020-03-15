package string;

import java.util.*;

public class Draft {
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        String[] result = new String[numRows];
        Arrays.fill(result, "");

        int curRow = 0;
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            result[curRow] += s.substring(i, i+1);
            if (curRow == 0 || curRow==s.length() - 1) {
                flag = !flag;
            }

            curRow = flag ? ++curRow : --curRow;
        }
        String ans = "";
        for (int i = 0; i < result.length; i++) {
            ans += result[i];
        }
        return ans;


    }
}
