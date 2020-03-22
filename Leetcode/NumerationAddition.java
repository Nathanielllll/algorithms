import java.util.Arrays;
import java.util.List;

/**
 * 36进制由0-9，a-z，共36个字符表示，最小为'0'
 * '0''9'对应十进制的09，'a''z'对应十进制的1035
 * 例如：'1b' 换算成10进制等于 1 * 36^1 + 11 * 36^0 = 36 + 11 = 47
 * 要求按照加法规则计算出任意两个36进制正整数的和
 * 如：按照加法规则，计算'1b' + '2x' = '48'
 *
 */
public class NumerationAddition {
    static Character[] nums = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' };
    static List<Character> list = Arrays.asList(nums);

    public static String addition(String str1, String str2){
        int i = str1.length() - 1;
        int j = str2.length() - 1;
        StringBuffer stringBuffer = new StringBuffer();

        int carry = 0;
        while (i >= 0 && j >= 0){
            //num1, num2是在list上面的位置
            int num1 = list.indexOf(str1.charAt(i));
            int num2 = list.indexOf(str2.charAt(j));

            int sum = num1 + num2 + carry;
            if (sum >= 36) {
                carry = 1;
                stringBuffer.append(list.get(sum % 36));
            }else {
                carry = 0;
                stringBuffer.append(list.get(sum));
            }
            i--;
            j--;
        }

        while (i >= 0) {
            //num1, num2是在list上面的位置
            int num1 = list.indexOf(str1.charAt(i));

            int sum = num1 + carry;
            if (sum >= 36) {
                carry = 1;
                stringBuffer.append(list.get(sum % 36));
            }else {
                carry = 0;
                stringBuffer.append(list.get(sum));
            }
            i--;
        }

        while (j >= 0) {
            //num1, num2是在list上面的位置
            int num2 = list.indexOf(str2.charAt(i));

            int sum = num2 + carry;
            if (sum >= 36) {
                carry = 1;
                stringBuffer.append(list.get(sum % 36));
            }else {
                carry = 0;
                stringBuffer.append(list.get(sum));
            }
            j--;
        }

        if (carry == 1) {
            stringBuffer.append(1);
        }

        return stringBuffer.reverse().toString();
    }

    public static void main(String[] args) {
        //'1b' + '2x' = '48'
        String str1 = "1b";
        String str2 = "2x";
        System.out.println(addition(str1, str2));
    }

}
