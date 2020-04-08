package netEase.April_07;

import java.util.HashSet;
import java.util.Scanner;

public class Test_3 {
    /**
     *
     * 10 2 0人数、家庭聚会、第一个病人的编号
     * 2 0 3
     * 3 0 1 2
     *
     * 4
     *
     * AC 60%
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int peopleTotal = scanner.nextInt();
        int partyTotal = scanner.nextInt();
        int firstPeople = scanner.nextInt();

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(firstPeople);

        //10 2 0
        //2 0 3
        //3 0 1 2
        for (int i = 0; i < partyTotal; i++) {
            //家庭聚会上的人数
            int peopleInPartyNum = scanner.nextInt();
            //读取数据
            int[] peopleInParty = new int[peopleInPartyNum];
            for (int j = 0; j < peopleInPartyNum; j++) {
                peopleInParty[j] = scanner.nextInt();
            }

            //先查看聚会上有没有病人
            boolean flag = false;
            for (int j = 0; j < peopleInPartyNum; j++) {
                if(hashSet.contains(peopleInParty[j])){
                    flag = true;
                    break;
                }
            }

            //如果有病人，则把所有人都加入到hashSet
            if (flag) {
                for (int j = 0; j < peopleInPartyNum; j++) {
                    if (!hashSet.contains(peopleInParty[j])) {
                        hashSet.add(peopleInParty[j]);
                    }
                }
            }
        }

        int count = hashSet.size();
        System.out.println(count);
    }
}
