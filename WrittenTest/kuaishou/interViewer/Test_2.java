package kuaishou.interViewer;

import java.util.Arrays;
import java.util.HashMap;

public class Test_2 {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(GetPowerFactor(39, 3)));

    }

    //贪心算法
    public static int[] GetPowerFactor (int R, int N) {
        // write code here
        int index = 0;
        //1,3可能要另外比较
        while(Math.pow(N, index) <= R){
            index++;
        }
        index--;

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        while (true) {
            if (R <= 0) {
                break;
            }
            if (Math.pow(N, index) <= R) {
                R -= Math.pow(N, index);

                hashMap.put(index, hashMap.getOrDefault(index, 0) + 1);
                if (hashMap.get(index) == 2) {
                    return new int[]{};
                }
            }else {
                index--;
            }
        }

        int[] res = new int[hashMap.size()];
        int i = 0;
        for(int num : hashMap.keySet()){
            res[i++] = num;
        }

        Arrays.sort(res);
        return res;

    }
}
