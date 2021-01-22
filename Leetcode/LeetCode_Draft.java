import org.apache.commons.lang.StringEscapeUtils;

import java.util.*;

public class LeetCode_Draft {

    
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, List<List<String>>> hashMap = new HashMap<>();
        for(List<String> list : accounts){
            if (hashMap.containsKey(list.get(0))) {
                hashMap.get(list.get(0)).add(list);
            }else {
                List<List<String>> lists = new ArrayList<>();
                lists.add(list);
                hashMap.put(list.get(0), lists);
            }
        }

        List<List<String>> result = new ArrayList<>();

        for(Map.Entry<String, List<List<String>>> entry : hashMap.entrySet()){
            List<List<String>> curLists = entry.getValue();
            boolean[] visited = new boolean[curLists.size()];
            for (int i = 0; i < curLists.size(); i++) {
                List<String> list = curLists.get(i);
                for (int j = i + 1; j < curLists.size(); j++) {
                    List<String> result_1 = mergeTwoAccounts(list, curLists.get(j), visited, i, j);
                    if (!result_1.isEmpty()) {
                        list = result_1;
                    }
                    visited[j] = true;
                }
                visited[i] = true;
                result.add(list);
            }
        }
        return result;
    }

    private static List<String> mergeTwoAccounts(List<String> account1, List<String> account2, boolean[] visited, int index_1, int index_2){
        List<String> result = new ArrayList<>();
        if(visited[index_1] || visited[index_2]){
            return result;
        }

        List<String> subList1 = account1.subList(1, account1.size());
        List<String> subList2 = account1.subList(1, account2.size());

        boolean intersection = subList1.retainAll(subList2);
        if (intersection) {
            subList1.removeAll(subList2);
            subList1.addAll(subList2);
            result.add(account2.get(0));
            result.addAll(subList1);
            return result;
        }
        return result;
    }






    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;

        int index = 0;
        outer:
        while(index < gas.length){
            int cur_gas = 0;
            for (int j = index; j <= index + gas.length; j++) {
                cur_gas = cur_gas + gas[j % len] - cost[j % len];
                if(cur_gas < 0) {
                    index = j + 1;
                    continue outer;
                }else if (j == index + gas.length ) {
                    return index % gas.length;
                }
            }
            index++;
        }

        return -1;
    }

    public static void main(String[] args) {
//        int[] gas = {2,3,4};
//        int[] cost = {3,4,3};
//        System.out.println(canCompleteCircuit(gas, cost));

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("a\"b\"cd\"e\"");
        System.out.println(stringBuffer.toString());

        String str = "a\"b\"cd\"e\"";

        System.out.println(StringEscapeUtils.escapeJava(str));

    }




    public static int findNthDigit(int n) {
        int digits = 0;
        while (true) {
            if (n >= countOfIntegers(digits)) {
                n -= countOfIntegers(digits);
                digits++;
            } else {
                return digitAtIndex(n, digits);
            }
        }
    }

    private static int digitAtIndex(int index, int digits) {
        int curNum = beginNumberFor(digits) + index / digits;
        index %= digits;
        return Integer.parseInt(String.valueOf(curNum).substring(index, index + 1));
    }

    public static int countOfIntegers(int digits) {
        if (digits == 0) {
            return 1;
        }
        int count = (int) Math.pow(10, digits - 1) * digits;
        return 9 * count;
    }

    public static int beginNumberFor(int digits) {
        if (digits == 0) {
            return 0;
        }
        return (int) Math.pow(10, digits - 1);
    }


    /*
    把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

    你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

    示例 1:

    输入: 1
    输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
    示例 2:

    输入: 2
    输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
     */
    public double[] twoSum(int n) {
        double[][] dp = new double[n + 1][6 * n + 1];
        for (int j = 1; j <= 6; j++) {
            dp[1][j] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6 * i; j++) {
                for (int k = 1; k <= 6 && j - k >= i - 1; k++) {
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        double total = Math.pow(6, n);
        double[] ans = new double[5 * n + 1];
        for (int i = n; i <= 6 * n; i++) {
            ans[i - n] = dp[n][i] / total;
        }
        return ans;
    }





}


