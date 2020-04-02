import java.util.*;

public class Draft {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        boolean flag = true;// 如果是正数就是true，负数就是false

        String Num = cin.nextLine();
        if (Num.charAt(0) == '-') {
            flag = false;
            Num = Num.substring(1);
        }


        String numMap = cin.nextLine();
        String[] List = numMap.split(" ");

        String result = "";
        for (int index = 0; index < Num.length(); index++) {
            int I = Num.charAt(index) - '0';
            result += List[I - 1];
        }

        if (!flag)
            result = "-" + result;
        System.out.println(result);
    }
}