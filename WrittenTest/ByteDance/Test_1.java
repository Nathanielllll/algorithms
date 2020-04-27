package ByteDance;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/*
2
6
3 7 1 4 1 2
3 7 3 6 3 2
5
1 1 1 1 1
1 2 1 3 1
 */
public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();

        outer:
        for (int t = 0; t < total; t++) {
            int length = scanner.nextInt();
            int[] a = new int[length];
            int[] b = new int[length];
            for (int i = 0; i < length; i++) {
                a[i] = scanner.nextInt();
            }

            for (int i = 0; i < length; i++) {
                b[i] = scanner.nextInt();
            }

            int[] Gaps = new int[length];
            for (int i = 0; i < length; i++) {
                int gap = a[i] - b[i];
                if (gap != 0) {
                    Gaps[i] = gap;
                }
            }

            int pre_gap = Integer.MAX_VALUE;
            int pre_gap_index = -1;
            for (int i = 0; i < length; i++) {
                if(Gaps[i] != 0){
                    int gap = Gaps[i];
                    if (pre_gap != Integer.MAX_VALUE) {
                        if (pre_gap != gap || (i - pre_gap_index != 1)) {
                            System.out.println("NO");
                            continue outer;
                        }
                    }

                    pre_gap = gap;
                    pre_gap_index = i;
                }
            }
            System.out.println("YES");


        }

    }
}
