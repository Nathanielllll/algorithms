//package dp;
///*
//给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
//
//返回符合要求的最少分割次数。
//
//示例:
//
//输入: "aab"
//输出: 1
//解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
// */
//public class Test_132 {
//    public int minCut(String s) {
//
//    }
//
//    private boolean isPalindrome(String s){
//        int i = 0;
//        int j = s.length() - 1;
//        while(i < j){
//            if(s.charAt(i) != s.charAt(j)){
//                return false;
//            }
//            i++;
//            j--;
//        }
//        return true;
//    }
//}
