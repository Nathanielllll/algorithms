//package string;
//
///**
// * @author luzhi
// * @date 2021/3/28
// */
//public class Test_214 {
//    /*
//    给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
//
//    示例:
//
//    输入：s = "aacecaaa"
//    输出："aaacecaaa"
//
//    输入：s = "abcd"
//    输出："dcbabcd"
//     */
//    public static String shortestPalindrome(String s) {
//        int length = s.length();
//        int begin = 0;
//        int maxLength = 1;
//        boolean[][] isPalindrome = new boolean[length][length];
//        for (int j = 0; j < length; j++) {
//            for (int i = 0; i <= j; i++) {
//                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPalindrome[i + 1][j - 1])) {
//                    isPalindrome[i][j] = true;
//                    if(maxLength < j - i + 1){
//                        maxLength = j - i + 1;
//                        begin = i;
//                    }
//                }
//            }
//        }
//
//
//    }
//}
