package tree;

public class MaxBSTSize {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        public int maxBSTSize;
        public boolean isBST;
        public int min;
        public int max;

        public Info(int maxBST, boolean is, int mi, int ma) {
            maxBSTSize = maxBST;
            isBST = is;
            min = mi;
            max = ma;
        }
    }

    // 和x自己无关（左树的MaxBSTSize，右树的MaxBSTSize）。
    // 和x自己有关（x为头的树，整棵树是BST）：左树整体是BST（MaxBSTSize>0），右树整体是BST。左树的最大值小于我，右树最小值要大于我
    public static Info process(Node x){
        if (x == null) {
            return new Info(0, true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        boolean isBST = false;
        int maxBSTSize = Math.max(leftInfo.maxBSTSize, rightInfo.maxBSTSize); // p1, p2
        if(leftInfo.isBST
                && rightInfo.isBST
                && leftInfo.max < x.value
                && rightInfo.min > x.value) {
            //p3
            maxBSTSize = leftInfo.maxBSTSize + 1 + rightInfo.maxBSTSize;
            isBST = true;
        }
        int min = Math.min(x.value, Math.min(leftInfo.min, rightInfo.min));
        int max = Math.max(x.value, Math.max(leftInfo.max, rightInfo.max));
        return new Info(maxBSTSize, isBST, min, max);
    }

}
