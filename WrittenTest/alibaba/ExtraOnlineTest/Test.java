package alibaba.ExtraOnlineTest;

//判断数字是否是回文对称数字
public class Test {
    public static boolean isHuiWen(long num){
        //负数的话，还考虑回文吗？
        if (num < 0) {
            return false;
        }
        String value = String.valueOf(num);
        int length = value.length();
        for (int i = 0; i < length/2; i++) {
            if (value.charAt(i) != value.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        long num = 99;
        System.out.println(isHuiWen(num));
    }

}
