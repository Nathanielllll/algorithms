package string;

/**
 * 验证给定的字符串是否可以解释为十进制数字。
 * <p>
 * 例如:
 * <p>
 * "0"=>true
 * " 0.1 "=>true
 * "abc"=>false
 * "1 a"=>false
 * "2e10"=>true
 * " -90e3 "=>true
 * " 1e"=>false
 * "e3"=>false
 * " 6e-1"=>true
 * " 99e2.5"=>false
 * "53.5e93"=>true
 * " --6 "=>false
 * "-+3"=>false
 * "95a54e53"=>false
 * <p>
 * 说明:我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 * <p>
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 * 当然，在输入中，这些字符的上下文也很重要。
 * <p>
 * 更新于 2015-02-10:
 * C++函数的形式已经更新了。如果你仍然看见你的函数接收const char * 类型的参数，请点击重载按钮重置你的代码。
 */
public class Test_65_TODO {
    public static boolean isNumber(String s) {
        int len = s.length();
        int index = 0;
        //处理空格 ' '
        while (s.charAt(index) == ' ') {
            index++;
        }
        if (index >= len) {
            return false;
        }

        //处理正负号
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            index++;
            if (index >= len || s.charAt(index) == '+' || s.charAt(index) == '-') {
                return false;
            }
        }

        //处理普通数字
        index = isDigit(s, index);
        if(index >= len){
            //如果 "986"
            return true;
        }

        //有小数点的情况
        if (s.charAt(index) == '.') {
            index++;
            //认为"986."是错误的
            if (index >= len) {
                return false;
            }
            index = isDigit(s, index);
            if(index >= len){
                return true;
            }
        }

        //有e
        if (s.charAt(index) == 'e') {
            index++;
            if (index >= len) {
                return false;
            }
            //有没有正负号
            if (s.charAt(index) == '+' || s.charAt(index) == '-') {
                index++;
                if (index >= len || s.charAt(index) == '+' || s.charAt(index) == '-') {
                    return false;
                }
            }
            //数字
            index = isDigit(s, index);
            if (index >= len) {
                return true;
            }
        }

        /***/
        while (s.charAt(index) == ' ') {
            index++;
        }
        if (index >= len) {
            return true;
        }

        return index >= len;
    }

    public static int isDigit(String s, int index) {
        while (index < s.length() && s.charAt(index) <= '9' && s.charAt(index) >= '0') {
            index++;
        }
        return index;
    }

    public static void main(String[] args) {
        String s1 = "0";
        String s2 = " 0.1 ";
        String s3 = "abc";
        String s4 = "1 a";
        String s5 = "2e10";
        String s6 = " -90e3 ";
        String s7 = " 1e";
        String s8 = "e3";
        String s9 = " 6e-1";
        String s10 = " 99e2.5";
        String s11 = "53.5e93";
        String s12 = " --6 ";
        String s13 = "-+3";
        String s14 = "95a54e53";

        System.out.println(isNumber(s1));
        System.out.println(isNumber(s2));
        System.out.println(isNumber(s3));
        System.out.println(isNumber(s4));
        System.out.println(isNumber(s5));
        System.out.println(isNumber(s6));
        System.out.println(isNumber(s7));
        System.out.println(isNumber(s8));
        System.out.println(isNumber(s9));
        System.out.println(isNumber(s10));
        System.out.println(isNumber(s11));
        System.out.println(isNumber(s12));
        System.out.println(isNumber(s13));
        System.out.println(isNumber(s14));

    }
}
