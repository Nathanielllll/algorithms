package netEase.packet2;

import java.util.HashMap;
import java.util.Scanner;

public class Test_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //人数，询问次数
        int total = scanner.nextInt();
        int count = scanner.nextInt();

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < total; i++) {
            int salary = scanner.nextInt();
            //工资，工资出现的次数
            hashMap.put(salary, hashMap.getOrDefault(salary, 0) + 1);
        }

        for (int i = 0; i < count; i++) {
            int k = scanner.nextInt();
            if (hashMap.containsKey(k)) {
                System.out.println(hashMap.get(k));
            }else {
                System.out.println(0);
            }
        }
    }
}
