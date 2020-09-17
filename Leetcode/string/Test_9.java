package string;

public class Test_9 {
    public boolean isPalindrome(int x) {
        String string = String.valueOf(x);
        int i = 0, j = string.length() - 1;
        while (i < j) {
            if (string.charAt(i++) != string.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
