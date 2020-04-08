package netEase.April_07;

import java.util.Arrays;
import java.util.Scanner;

/**
 * AC 60%
 */
public class Test_2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //怪物个数
        int n = scanner.nextInt();
        //勇者的防御力
        int defence = scanner.nextInt();

        //arr[i][0]怪物的破防能力
        //arr[i][1]怪物的伤害
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            arr[i][1] = scanner.nextInt();
        }

//        //Test
//        int[][] arr = {{100,1000},{50,1000},{52,1000},{51,1000}};
//        int defence = 50;
//        int n = 4;




        Arrays.sort(arr, (a, b) -> {
                    return a[0] - b[0];
                }
        );

        //承受的伤害总数
        int count = 0;

        for (int i = 0; i < n; i++) {
            if(defence >= arr[i][0]){
                defence++;
            }else {
                count += arr[i][1];
            }
        }

        System.out.println(count);

    }

}
