package twoPointers;

public class Test_344 {
    public void reverseString(char[] s) {
        if(s==null || s.length < 1){
            return;
        }
        reverseProcess(s, 0, s.length - 1);
    }
    public void reverseProcess(char[] s, int start, int end){
        while (start <= end){
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}
