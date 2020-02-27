package string;

/**
 * 题目：请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *
 * 如果12.或12.e+12都是正确的
 */
public class Code_20_TODO {

    public static boolean isNumeric(String string) {
        if (string == null || string.length() <= 0) {
            return false;
        }
        int index = 0;

        if (string.charAt(index) == '+' || string.charAt(index) == '-') {
            index++;
        }
        if (index >= string.length()) {
            return false;
        }

        boolean numeric = true;
        index = scanDigits(string, index);
        //还未达到末尾
        if (index < string.length()) {
            //如果是小数点
            if (string.charAt(index) == '.') {
                index++;

//                //例如防止12.或者12.e+12的情况
//                if (index == scanDigits(string, index)) {
//                    return false;
//                }

                index = scanDigits(string, index);
                //此时已经移动到最末尾
                if (index >= string.length()) {
                    numeric = true;
                    //未到最末尾并且遇到了e或E
                } else if (index < string.length() && (string.charAt(index) == 'e' || string.charAt(index) == 'E')) {
                    numeric = isExponential(string, index);
                } else {
                    numeric = false;
                }
            } else if (string.charAt(index) == 'e' || string.charAt(index) == 'E') {
                numeric = isExponential(string, index);
            } else {
                numeric = false;
            }
        } else {
            numeric = true;
        }

        //最后结果
        return numeric;
    }

    //E+18
    public static boolean isExponential(String string, int index) {
        //如果index超过了，或者不是e或者E，则返回false
        if (index >= string.length()) {
            return false;
        }
        //较为容易理解的写法
        if (string.charAt(index) == 'e' || string.charAt(index) == 'E') {
            index++;
        } else {
            return false;
        }

        //如果index加1后超过了，则返回false
        if (index >= string.length()) {
            return false;
        }

        //遇到+或者-，index++
        if (string.charAt(index) == '+' || string.charAt(index) == '-') {
            index++;
        }
        if (index >= string.length()) {
            return false;
        }

        index = scanDigits(string, index);
        // 如果已经处理到了的数字的末尾就认为是正确的指数
        return index >= string.length();
    }

    /**
     * 扫描字符串部分的数字部分
     *
     * @param string 字符串
     * @param index  开始扫描的位置
     * @return 从扫描位置开始第一个数字字符的位置
     */
    public static int scanDigits(String string, int index) {
        while (index < string.length() && string.charAt(index) >= '0' && string.charAt(index) <= '9') {
            index++;
        }
        return index;
    }


    public static void main(String[] args) {
        System.out.println(isNumeric("100") + "[" + true + "]");
        System.out.println(isNumeric("123.45e+6") + "[" + true + "]");
        System.out.println(isNumeric("+500") + "[" + true + "]");
        System.out.println(isNumeric("5e2") + "[" + true + "]");
        System.out.println(isNumeric("3.1416") + "[" + true + "]");
        System.out.println(isNumeric("600.") + "[" + true + "]");
        System.out.println(isNumeric("-.123") + "[" + true + "]");
        System.out.println(isNumeric("-1E-16") + "[" + true + "]");
        System.out.println(isNumeric("100") + "[" + true + "]");
        System.out.println(isNumeric("1.79769313486232E+308") + "[" + true + "]");
        System.out.println();

        System.out.println(isNumeric("12e") + "[" + false + "]");
        System.out.println(isNumeric("1a3.14") + "[" + false + "]");
        System.out.println(isNumeric("1+23") + "[" + false + "]");
        System.out.println(isNumeric("1.2.3") + "[" + false + "]");
        System.out.println(isNumeric("+-5") + "[" + false + "]");
        System.out.println(isNumeric("12e+5.4") + "[" + false + "]");


    }
}
