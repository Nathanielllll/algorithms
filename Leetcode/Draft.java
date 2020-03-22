import java.util.*;

public class Draft {

    public static int minLength(int N, int L){
        int left = 0;
        int right = 0;
        int minLength = Integer.MAX_VALUE;
        int mid = (N + 1)/2;
        int sum = 0;

        while(left < mid){
            if(sum < N){
                right++;
                sum += right;
            }else{
                if(right - left + 1 >= L){
                    minLength = Math.min(minLength, right - left + 1);
                }
                sum -= left;
                left++;
            }
        }
        return minLength;
    }

    public static void main(String[] args) {
        System.out.println(minLength(18, 2));
    }
}
