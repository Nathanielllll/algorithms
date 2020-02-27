package string;

import java.util.*;

public class Draft {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int[] result = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i++) {
            for (int j = num2.length() - 1; j >= 0; j++) {
                int n1 = num1.charAt(i) - '0';
                int n2 = num2.charAt(j) - '0';
                int temp = result[i + j + 1] + n1 * n2;
                result[i + j + 1] = temp % 10;
                result[i + j] += temp / 10;
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 0 && stringBuffer.length() == 0) {
                continue;
            }
            stringBuffer.append(result[i]);
        }
        return stringBuffer.toString();

    }
}
