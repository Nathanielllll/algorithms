package dp;

import java.util.LinkedList;
import java.util.List;

public class Test_119 {
    /*
    在杨辉三角中，每个数是它左上方和右上方的数的和。

    示例:

    输入: 3
    输出: [1,3,3,1]
     */
    public static void main(String[] args) {
        List<Integer> result = getRow(0);
        System.out.println(result);
    }

    public static List<Integer> getRow(int rowIndex) {
        List<LinkedList<Integer>> dp = new LinkedList<>();
        LinkedList<Integer> linkedList_0 = new LinkedList<>();
        linkedList_0.add(1);
        dp.add(linkedList_0);

        for (int i = 1; i <= rowIndex; i++) {
            LinkedList<Integer> pre_linkedList = dp.get(i - 1);

            LinkedList<Integer> cur_linkedList = new LinkedList<>();
            for (int j = 0; j <= pre_linkedList.size() - 2; j++) {
                cur_linkedList.add(pre_linkedList.get(j) + pre_linkedList.get(j + 1));
            }
            cur_linkedList.addFirst(1);
            cur_linkedList.addLast(1);
            dp.add(cur_linkedList);
        }
        return dp.get(rowIndex);
    }

}
