package tree;

import java.util.List;

/**
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * 例如，给定一个 3叉树 :
 *
 *
 * 解决这个问题的最直观方法就是递归。
 * 此处展示了深度优先搜索的策略。
 *
 *
 * 时间复杂度：每个节点遍历一次，所以时间复杂度是 O(N)，其中 NN 为节点数。
 *
 * 空间复杂度：最坏情况下, 树完全非平衡，
 * 例如 每个节点有且仅有一个孩子节点，递归调用会发生 NN 次（等于树的深度），所以存储调用栈需要 O(N)O(N)。
 * 但是在最好情况下（树完全平衡），树的高度为 \log(N)log(N)。
 * 所以在此情况下空间复杂度为 O(\log(N))O(log(N))。
 */
public class Test_559 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int dep = 0;
        for(Node child : root.children){
            int d = maxDepth(child);
            dep = Math.max(d, dep);
        }
        return dep + 1;
    }
}
