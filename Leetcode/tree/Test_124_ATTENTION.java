package tree;

/*
给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。



示例 1：

输入：[1,2,3]

       1
      / \
     2   3

输出：6
示例2：

输入：[-10,9,20,null,null,15,7]

  -10
 / \
 9 20
  / \
 15  7

输出：42
 */
public class Test_124_ATTENTION {

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  // 见 Test_110，Test_543。都是一样的写法！
  private static class PathSumInfo {

    int crossRootSinglePathSum; // 经过root，且是单边的
    int pathSum;

    public PathSumInfo(int crossRootSinglePathSum, int pathSum) {
      this.crossRootSinglePathSum = crossRootSinglePathSum;
      this.pathSum = pathSum;
    }
  }

  public static int maxPathSum(TreeNode root) {
    PathSumInfo info = maxPathSumDfs(root);
    return info.pathSum;
  }

  private static PathSumInfo maxPathSumDfs(TreeNode root) {
    if (root == null) {
      return new PathSumInfo(0, Integer.MIN_VALUE);
    }

    PathSumInfo leftInfo = maxPathSumDfs(root.left);
    PathSumInfo rightInfo = maxPathSumDfs(root.right);

    // cross root
    int p1 = root.val;
    int p2 = root.val + leftInfo.crossRootSinglePathSum;
    int p3 = root.val + rightInfo.crossRootSinglePathSum;
    int curCrossRootSinglePathSum = Math.max(p1, Math.max(p2, p3));
    int p4 = root.val + leftInfo.crossRootSinglePathSum + rightInfo.crossRootSinglePathSum;
    int curCrossRootPathSum = Math.max(p4, curCrossRootSinglePathSum);

    // not cross root
    int curNotCrossRootSinglePathSum = Math.max(leftInfo.pathSum, rightInfo.pathSum);

    // path sum
    int pathSum = Math.max(curCrossRootPathSum, curNotCrossRootSinglePathSum);

    return new PathSumInfo(curCrossRootSinglePathSum, pathSum);
  }
}
