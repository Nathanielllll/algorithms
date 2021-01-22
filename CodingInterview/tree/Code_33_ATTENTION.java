package tree;

import java.util.List;
import java.util.Stack;

public class Code_33_ATTENTION {
    public boolean verifyPostorder(int[] postorder) {
        return process(postorder, 0, postorder.length - 1);
    }

    private boolean process(int[] postorder, int start, int end){
        if (start >= end) {
            return true;
        }

        int value = postorder[end];
        // 右子树，第一个大于postorder[end]的位置 ~ end - 1
        int right_start = start;
        while (postorder[right_start] < value) {
            right_start++;
        }

        // 我们还需要确定postorder[right_start]后面的值都要比根节点root大，
        // 如果后面有比根节点小的直接返回false
        for (int i = right_start; i < end; i++) {
            if(postorder[i] < postorder[end]){
                return false;
            }
        }

        // 递归验证 左子树 && 右子树
        return process(postorder, start, right_start - 1) && process(postorder, right_start, end - 1);
    }

}
