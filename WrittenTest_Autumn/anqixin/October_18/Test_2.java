package anqixin.October_18;

import java.util.Scanner;

public class Test_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        if(N < 1 || N > 200){
            System.out.println("Error");
            return;
        }
//        int N = 100;

        String res = "1";
        for (int i = 1; i <= N; i++) {
            String str1 = res;
            String str2 = "" + i;
            res = multiply(str1, str2);
        }
        System.out.println(res);

    }

    private static String multiply(String str1, String str2){
        int[] num1 = new int[str1.length()];
        int[] num2 = new int[str2.length()];
        int[] result = new int[str1.length() + str2.length()];
        for (int i = 0; i < str1.length(); i++) {
            num1[i] = Integer.parseInt(str1.substring(i, i+1));
        }
        for (int j = 0; j < str2.length(); j++) {
            num2[j] = Integer.parseInt(str2.substring(j, j+1));
        }

        for (int i = 0; i < num1.length; i++) {
            for (int j = 0; j < num2.length; j++) {
                result[i+j] += num1[i] * num2[j];
            }
        }

        int carry;
        for (int k = result.length - 1; k > 0; k--) {
            carry = result[k] / 10;
            result[k-1] += carry;
            result[k] %= 10;
        }

        String res_string = "";
        for (int k = 0; k < result.length - 1; k++) {
            res_string += "" + result[k];
        }
        return res_string;
    }



}
