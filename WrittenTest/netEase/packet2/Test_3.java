package netEase.packet2;

import java.util.Scanner;

public class Test_3 {
    /**
     * 策略：
     * 能跳最高的柱子，则跳最高的柱子；
     * 不能跳了，则使用超能力跳到最高的柱子上面
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        outer:
        while(t-- != 0){
            int n = scanner.nextInt();
            int k = scanner.nextInt();

            int[] h = new int[n];
            for (int j = 0; j < n; j++) {
                h[j] = scanner.nextInt();
            }

            boolean isUseSuperAbility = false;
            int index = 0;

            while (true) {
                if (index == n - 1) {
                    System.out.println("YES");
                    continue outer;
                }
                int maxCanJumpIndex = -1;
                int maxCanJumpValue = -1;
                int maxSuperAbilityIndex = -1;
                int maxSuperAbilityValue = -1;
                boolean isCanJump = false;

                //表示index+1~index+k的柱子
                for (int i = index + 1; i <= index + k && i < n; i++) {
                    if(h[index] >= h[i]){
                        //找到k以内最高的杆子
                        if (h[i] > maxCanJumpValue) {
                            maxCanJumpIndex = i;
                            maxCanJumpValue = h[i];
                        }
                        isCanJump = true;
                    }else {
                        if (h[i] > maxSuperAbilityValue) {
                            maxSuperAbilityIndex = i;
                            maxSuperAbilityValue = h[i];
                        }
                    }
                }
                //不能跳
                if (!isCanJump) {
                    if (isUseSuperAbility) {
                        System.out.println("NO");
                        continue outer;
                    }else {
                        index = maxSuperAbilityIndex;
                        isUseSuperAbility = true;
                    }
                }else {
                    index = maxCanJumpIndex;
                }
            }
        }
    }
}
