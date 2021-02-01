package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test_501 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int count;
    private int maxCount;
    private int preValue;
    private List<Integer> resultList;
    public int[] findMode(TreeNode root) {
        count = 0;
        maxCount = 0;
        preValue = Integer.MAX_VALUE;
        resultList = new ArrayList<>();
        helper(root);

        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }


    private void helper(TreeNode root){
        if(root == null) return;

        helper(root.left);

        //下面是对当前节点的一些逻辑操作
        int nodeValue = root.val;
        if (nodeValue == preValue) {
            //如果节点值等于current，count就加1
            count++;
        } else {
            //否则，就表示遇到了一个新的值，curent和count都要
            //重新赋值
            preValue = nodeValue;
            count = 1;
        }
        if (count == maxCount) {
            //如果count == maxCount，就把当前节点加入到集合中
            resultList.add(nodeValue);
        } else if (count > maxCount) {
            //否则，当前节点的值重复量是最多的，直接把list清空，然后
            //把当前节点的值加入到集合中
            resultList.clear();
            resultList.add(nodeValue);
            maxCount = count;
        }

        helper(root.right);
    }
}
