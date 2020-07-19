package tree;

import java.util.List;
import java.util.Stack;

public class Code_33_ATTENTION {
    public boolean verifyPostorder(int[] postorder) {
        int start = 0;
        int end = postorder.length - 1;

        return helper(postorder, start, end);
    }

    private boolean helper(int[] postorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        int value = postorder[end];
        int index = start;
        while (index < end && postorder[index] < value) {
            index++;
        }
        int right = index;
        while (index < end && postorder[index] > value) {
            index++;
        }
        return index == end && helper(postorder, start, right - 1) && helper(postorder, right, end - 1);
    }

}
