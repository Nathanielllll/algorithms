package shopee;

import java.util.*;

/**
 * @author luzhi
 * @date 2021/4/21
 */
public class Test_2 {
    // [1,2]
    // [[],[1],[1,2],[2]]
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        str = str.substring(1, str.length() - 1);
        String[] strings = str.split(",");
        int[] nums = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            nums[i] = Integer.parseInt(strings[i]);
        }
        List<List<Integer>> res = subsets(nums);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < res.size(); i++) {
            sb.append("[");
            for (int j = 0; j < res.get(i).size(); j++) {
                sb.append(res.get(i).get(j));
                if (j != res.get(i).size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            if (i != res.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        System.out.println(sb.toString().equals("[[],[1],[1,2],[2]]"));
    }

    static Stack<Integer> stack;
    static List<List<Integer>> result;
    public static List<List<Integer>> subsets(int[] nums){
        stack = new Stack<>();
        result = new ArrayList<>();
        Arrays.sort(nums);
        subProcess(nums, 0);
        return result;
    }

    private static void subProcess(int[] nums, int start){
        result.add(new ArrayList<>(stack));
        for (int i = start; i < nums.length; i++) {
            stack.push(nums[i]);
            subProcess(nums, i + 1);
            stack.pop();
        }
    }
}
