package tree;

public class Test_889 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    static int[] pre;
    static int[] post;

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        return make(0, 0, pre.length);
    }

    public static TreeNode make(int i0, int i1, int N) {
        if (N == 0) return null;
        TreeNode root = new TreeNode(pre[i0]);
        if (N == 1) return root;

        int L = 1;
        for (; L < N; ++L)
            if (post[i1 + L - 1] == pre[i0 + 1])
                break;

        root.left = make(i0 + 1, i1, L);
        root.right = make(i0 + L + 1, i1 + L, N - 1 - L);
        return root;
    }

    public static void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);

        // 在中序遍历的过程中修改节点指向
        System.out.println(node.val);

        inorder(node.right);
    }

    public static void main(String[] args) {
//        Test_889 solution = new Test_889();
//
//        int[] pre = {1,2,4,5,3,6,7};
//        int[] post = {4,5,2,6,7,3,1};
//        TreeNode root = solution.constructFromPrePost(pre, post);
//        inorder(root);

        System.out.println((0x23 & 0x34) == 0x21);

    }




}
