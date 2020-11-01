package string;

public class Test_125 {
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && check(s.charAt(i)) < 0) {
                i++;
            }
            while (i < j && check(s.charAt(j)) < 0) {
                j--;
            }
            if (i == j) {
                continue;
            }
            if (!isEqual(s.charAt(i), s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean isEqual(char c1, char c2) {
        if (bothNum(c1, c2)) {
            return c1 == c2;
        } else if (bothLetter(c1, c2)) {
            return check(c1) == check(c2);
        } else {
            return false;
        }
    }

    private boolean bothNum(char c1, char c2) {
        return '0' <= c1 && c1 <= '9' && '0' <= c2 && c2 <= '9';
    }

    private boolean bothLetter(char c1, char c2) {
        return isLetter(c1) && isLetter(c2);
    }

    private boolean isLetter(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    private int check(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        } else if ('a' <= c && c <= 'z') {
            return c - 'a';
        } else if ('A' <= c && c <= 'Z') {
            return c - 'A';
        } else {
            return -1;
        }
    }
}
