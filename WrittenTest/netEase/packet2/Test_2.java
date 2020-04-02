package netEase.packet2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test_2 {
    /**
     * 思路：先剔除list中因数， 将最大数变为因数， 计算剩下数字变为1的代价
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int B = scanner.nextInt();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }

        Collections.sort(list);
        int count = 0;

        while (B != 1) {
            for (int i = list.size() - 1; i >= 0; i--) {
                //要从大的先开始除
                //list.get(i)是B的因数，则B除以list.get(i)，并且list把其remove掉
                if (B % list.get(i) == 0) {
                    B /= list.get(i);
                    list.remove(i);
                }
            }

            //此时list中还有数
            if(!list.isEmpty() && B!=1){
                int last = list.get(list.size() - 1);
                //last不是其因数
                while (B % last != 0) {
                    last--;
                    count++;
                }
                B /= last;
                list.remove(list.size() - 1);
            }
        }
        //把剩余的都变成1
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                count += list.get(i) - 1;
            }
        }
        System.out.println(count);

    }
}
