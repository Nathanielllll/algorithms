package zhaohang.March_29;

import java.util.*;

/*
6 5
1 2 2 4 2 3
 */
public class Test_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long total = scanner.nextLong();
        //需要至少same座相同
        long same = scanner.nextLong();


        ArrayList<Long> towers = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            towers.add(scanner.nextLong());
        }

        System.out.println(minTimes(towers, same));

    }

    public static int minTimes(ArrayList<Long> towers, long same) {
        HashMap<Long, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < towers.size(); i++) {
            long num = towers.get(i);
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }


        //1.求数量最多的那个数字
        int targetNumCount = 0;
        long targetNum = 0;
        for (long num : hashMap.keySet()) {
            if (targetNumCount < hashMap.get(num)) {
                targetNumCount = hashMap.get(num);
                targetNum = num;
            }
        }

        //2.求中位数

        int count = 0;
        while (targetNumCount < same) {
            long max = Collections.max(towers);
            long min = Collections.min(towers);

            if (Math.abs(max - targetNum) > 0) {
                if (max - 1 == targetNum) {
                    targetNumCount++;
                }
                towers.set(towers.indexOf(max), max - 1);
            } else if (Math.abs(targetNum - min) > 0) {
                if (min + 1 == targetNum) {
                    targetNumCount++;
                }
                towers.set(towers.indexOf(min), min + 1);
            }
            count++;
        }

        return count;

    }


}
