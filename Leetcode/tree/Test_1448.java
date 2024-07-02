package tree;

/**
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 * <p>
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [3,1,4,3,null,1,5] 输出：4 解释：图中蓝色节点为好节点。 根节点 (3) 永远是个好节点。 节点 4 -> (3,4) 是路径中的最大值。 节点 5 ->
 * (3,4,5) 是路径中的最大值。 节点 3 -> (3,1,3) 是路径中的最大值。
 */
public class Test_1448 {

  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public int goodNodes(TreeNode root) {
    return goodNodesDfs(root, root.val);
  }

  public int goodNodesDfs(TreeNode root, int maxVal) {
    if (root == null) {
      return 0;
    }
    int res = 0;
    if (root.val >= maxVal) {
      res = 1;
      maxVal = root.val;
    }
    res += goodNodesDfs(root.left, maxVal);
    res += goodNodesDfs(root.right, maxVal);
    return res;
  }
}
