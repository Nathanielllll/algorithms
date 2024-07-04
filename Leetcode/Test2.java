import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Test2 {

  public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;
    for (int coin : coins) {
      for (int i = 0; i <= amount; i++) {
        if (i - coin >= 0) {
          dp[i] += dp[i - coin];
        }
      }
    }
    return dp[amount];
  }

  public int uniquePaths(int m, int n) {
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[j] = dp[j] + dp[j - 1];
      }
    }
    return dp[n - 1];
  }

  public List<String> findItinerary(List<List<String>> tickets) {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    for (List<String> ticket : tickets) {
      String from = ticket.get(0);
      String to = ticket.get(1);
      graph.computeIfAbsent(from, key -> new PriorityQueue<>()).offer(to);
    }
    Stack<String> stack = new Stack<>();
    findItineraryDfs(graph, stack, "JFK");
    return new ArrayList<>(stack);
  }

  private void findItineraryDfs(Map<String, PriorityQueue<String>> graph, Stack<String> stack,
      String cur) {
    stack.push(cur);
    PriorityQueue<String> children = graph.get(cur);
    while (children != null && !children.isEmpty()) {
      String child = children.poll();
      findItineraryDfs(graph, stack, child);
    }
  }

  public static void main(String[] args) {
    Test2 test2 = new Test2();
    int[] nums = {0, 10, 10, 10, 10, 10, 10, 10, 10, 10, -10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
        0};
    System.out.println(test2.maxProduct(nums));
  }

  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    int[] prices = new int[n];
    Arrays.fill(prices, Integer.MAX_VALUE);
    prices[src] = 0;
    for (int i = 0; i <= k; i++) {
      int[] tmpPrices = Arrays.copyOf(prices, n);
      for (int[] flight : flights) {
        int s = flight[0];
        int d = flight[1];
        int p = flight[2];
        if (prices[s] == Integer.MAX_VALUE) {
          continue;
        }
        if (prices[s] + p < tmpPrices[d]) {
          tmpPrices[d] = prices[s] + p;
        }
      }
      prices = tmpPrices;
    }
    return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
  }

  public int networkDelayTime(int[][] times, int n, int k) {
    Map<Integer, List<int[]>> edges = new HashMap<>();
    for (int[] time : times) {
      edges.computeIfAbsent(time[0], key -> new ArrayList<>()).add(new int[]{time[1], time[2]});
    }
    int result = 0;
    Set<Integer> visited = new HashSet<>();
    // int[0] dist, int[1] idx
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    minHeap.offer(new int[]{0, k});
    while (!minHeap.isEmpty()) {
      int[] curr = minHeap.poll();
      int minDis = curr[0];
      int idx = curr[1];
      if (visited.contains(idx)) {
        continue;
      }
      visited.add(idx);
      result = minDis;
      if (edges.get(idx) != null) {
        for (int[] edge : edges.get(idx)) {
          int nextIdx = edge[0];
          int dis = edge[1];
          if (!visited.contains(nextIdx)) {
            minHeap.offer(new int[]{dis + minDis, nextIdx});
          }
        }
      }
    }
    return visited.size() == n ? result : -1;
  }

  public int swimInWater(int[][] grid) {
    int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int m = grid.length;
    int n = grid[0].length;
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        minHeap.offer(new int[]{grid[i][j], i, j});
      }
    }
    int firstIdx = calIdx(0, 0, n);
    int lastIdx = calIdx(m - 1, n - 1, n);
    Djset djset = new Djset(m * n + 1);
    while (!minHeap.isEmpty()) {
      int[] info = minHeap.poll();
      int val = info[0];
      int row = info[1];
      int col = info[2];
      for (int i = 0; i < 4; i++) {
        int nextRow = row + directs[i][0];
        int nextCol = col + directs[i][1];
        if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n
            && val >= grid[nextRow][nextCol]) {
          int curIdx = calIdx(row, col, n);
          int nextIdx = calIdx(nextRow, nextCol, n);
          djset.union(curIdx, nextIdx);
        }
        if (djset.findRoot(firstIdx) == djset.findRoot(lastIdx)) {
          return val;
        }
      }
    }
    return -1;
  }

  private int calIdx(int i, int j, int n) {
    return i * n + j;
  }

  class Djset {

    int[] parent;
    int[] rank;

    public Djset(int length) {
      parent = new int[length];
      rank = new int[length];
      for (int i = 0; i < length; i++) {
        parent[i] = i;
      }
    }

    public int findRoot(int value) {
      if (value != parent[value]) {
        parent[value] = findRoot(parent[value]);
      }
      return parent[value];
    }

    public boolean union(int x, int y) {
      int xRoot = findRoot(x);
      int yRoot = findRoot(y);
      if (xRoot == yRoot) {
        return false;
      }

      if (rank[xRoot] < rank[yRoot]) {
        parent[xRoot] = yRoot;
      } else if (rank[yRoot] < rank[xRoot]) {
        parent[yRoot] = xRoot;
      } else {
        rank[yRoot]++;
        parent[xRoot] = yRoot;
      }
      return true;
    }
  }

  public List<Integer> partitionLabels(String s) {
    Map<Character, Integer> charToLastIdx = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      charToLastIdx.put(s.charAt(i), i);
    }
    List<Integer> result = new ArrayList<>();
    int goal = 0;
    int curLen = 0;
    for (int i = 0; i < s.length(); i++) {
      curLen++;
      char ch = s.charAt(i);
      goal = Math.max(goal, charToLastIdx.get(ch));
      if (goal == i) {
        result.add(curLen);
        curLen = 0;
      }
    }
    return result;
  }

  public boolean isNStraightHand(int[] hand, int groupSize) {
    if (hand.length % groupSize != 0) {
      return false;
    }
    Map<Integer, Integer> cnt = new HashMap<>();
    for (int h : hand) {
      cnt.put(h, cnt.getOrDefault(h, 0) + 1);
    }
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    minHeap.addAll(cnt.keySet());
    while (!minHeap.isEmpty()) {
      int first = minHeap.peek();
      for (int i = 0; i < groupSize; i++) {
        int num = first + i;
        if (!cnt.containsKey(num)) {
          return false;
        }
        cnt.put(num, cnt.get(num) - 1);
        if (cnt.get(num) == 0) {
          if (minHeap.isEmpty() || num != minHeap.peek()) {
            return false;
          }
          minHeap.poll();
        }
      }
    }
    return true;
  }

  public int canCompleteCircuit(int[] gas, int[] cost) {
    if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) {
      return -1;
    }
    int total = 0;
    int result = 0;
    for (int i = 0; i < gas.length; i++) {
      total += gas[i] - cost[i];
      if (total < 0) {
        total = 0;
        result = i + 1;
      }
    }
    return result;
  }

  public int jump(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    int j = 0;
    for (int i = 1; i < n; i++) {
      // 找到最早能达到i点的j点
      while (j < i && (j + nums[j] < i)) {
        ++j;
      }
      dp[i] = dp[j] + 1;
    }
    return dp[n - 1];
  }

  public int[][] merge(int[][] intervals) {
    int n = intervals.length;
    List<int[]> resultList = new ArrayList<>();

    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    int left = intervals[0][0];
    int right = intervals[0][1];
    for (int i = 1; i < n; i++) {
      if (right >= intervals[i][0]) {
        left = Math.min(left, intervals[i][0]);
        right = Math.max(right, intervals[i][1]);
      } else {
        resultList.add(new int[]{left, right});
        left = intervals[i][0];
        right = intervals[i][1];
      }
    }
    resultList.add(new int[]{left, right});
    return resultList.toArray(new int[0][]);
  }

  public int[][] insert(int[][] intervals, int[] newInterval) {
    int n = intervals.length;
    int idx = 0;
    List<int[]> resultList = new ArrayList<>();
    // part1
    while (idx < n && intervals[idx][1] < newInterval[0]) {
      resultList.add(intervals[idx]);
      idx++;
    }
    // part2
    while (idx < n && intervals[idx][0] <= newInterval[1]) {
      newInterval[0] = Math.min(newInterval[0], intervals[idx][0]);
      newInterval[1] = Math.max(newInterval[1], intervals[idx][1]);
      idx++;
    }
    resultList.add(newInterval);
    // part3
    while (idx < n) {
      resultList.add(intervals[idx]);
      idx++;
    }
    return resultList.toArray(new int[0][]);
  }

  public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int result = 1;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      result = Math.max(result, dp[i]);
    }
    return result;
  }

  public boolean wordBreak(String s, List<String> wordDict) {
    int n = s.length();
    boolean[] dp = new boolean[n + 1];
    dp[0] = true;
    for (int i = 1; i <= n; i++) {
      for (String word : wordDict) {
        int wordLen = word.length();
        if (i - wordLen >= 0 && word.equals(s.substring(i - wordLen, i))) {
          dp[i] = dp[i] || dp[i - wordLen];
        }
      }
    }
    return dp[n];
  }

  public int maxProduct(int[] nums) {
    int n = nums.length;
    long dp_max = nums[0];
    long dp_min = nums[0];
    long result = nums[0];

    for (int i = 1; i < n; i++) {
      int num = nums[i];
      long pre_max = dp_max;
      long pre_min = dp_min;
      dp_max = Math.max(num, Math.max(pre_max * num, pre_min * num));
      dp_min = Math.min(num, Math.min(pre_max * num, pre_min * num));

      result = Math.max(result, dp_max);
    }
    return (int) result;
  }

  public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;
    for (int coin : coins) {
      for (int i = 1; i <= amount; i++) {
        if (i >= coin) {
          dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        }
      }
    }
    return dp[amount] == amount + 1 ? -1 : dp[amount];
  }

  public int numDecodings(String s) {
    int n = s.length();
    s = " " + s;
    int[] dp = new int[n + 1];
    dp[0] = 1;
    char[] chars = s.toCharArray();

    for (int i = 1; i <= n; i++) {
      int a = chars[i] - '0';
      int b = (chars[i - 1] - '0') * 10 + (chars[i] - '0');
      if (1 <= a && a <= 9) {
        dp[i] += dp[i - 1];
      }
      if (10 <= b && b <= 26) {
        dp[i] += dp[i - 2];
      }
    }
    return dp[n];
  }

  public int countSubstrings(String s) {
    int result = 0;
    int len = s.length();
    boolean[][] dp = new boolean[len][len];
    for (int j = 0; j < len; j++) {
      for (int i = 0; i <= j; i++) {
        if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
          dp[i][j] = true;
          result++;
        }
      }
    }
    return result;
  }

  public int rob(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return nums[0];
    }
    int[] nums1 = Arrays.copyOfRange(nums, 0, n - 1);
    int[] nums2 = Arrays.copyOfRange(nums, 1, n);
    int rob1 = subRob(nums1);
    int rob2 = subRob(nums2);
    return Math.max(rob1, rob2);
  }

  public int subRob(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return nums[0];
    }
    int[] dp = new int[n];
    dp[0] = nums[0];
    dp[1] = Math.max(nums[0], nums[1]);
    for (int i = 2; i < n; i++) {
      dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
    }
    return dp[n - 1];
  }

  public int minCostClimbingStairs(int[] cost) {
    int n = cost.length;
    int[] dp = new int[n + 1];
    dp[0] = cost[0];
    dp[1] = cost[1];

    for (int i = 2; i <= n; i++) {
      int c = i == n ? 0 : cost[i];
      dp[i] = Math.min(dp[i - 1], dp[i - 2]) + c;
    }
    return dp[n];
  }

  public Node cloneGraph(Node node) {
    Map<Node, Node> cache = new HashMap<>();
    return cloneGraphDfs(node, cache);
  }

  public Node cloneGraphDfs(Node node, Map<Node, Node> cache) {
    if (node == null) {
      return null;
    }
    if (cache.containsKey(node)) {
      return cache.get(node);
    }
    Node copy = new Node(node.val);
    cache.put(node, copy);
    for (Node neighbor : node.neighbors) {
      Node copyNeighbor = cloneGraphDfs(neighbor, cache);
      copy.neighbors.add(copyNeighbor);
    }
    return copy;
  }

  class Node {

    public int val;
    public List<Node> neighbors;

    public Node() {
      val = 0;
      neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
      val = _val;
      neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
      val = _val;
      neighbors = _neighbors;
    }
  }

  public int findKthLargest(int[] nums, int k) {
    int len = nums.length;
    int targetIdx = len - k;

    int l = 0;
    int r = len - 1;
    int idx = partition(nums, l, r);
    while (idx != targetIdx) {
      if (idx < targetIdx) {
        l = idx + 1;
      } else {
        r = idx - 1;
      }
      idx = partition(nums, l, r);
    }
    return nums[targetIdx];
  }

  private int partition(int[] nums, int l, int r) {
    int pivot = nums[r];
    int pointer = l;
    for (int i = l; i < r; i++) {
      if (nums[i] <= pivot) {
        swap(nums, i, pointer);
        ++pointer;
      }
    }
    swap(nums, pointer, r);
    return pointer;
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  public int maxPathSum(TreeNode root) {
    PathSumInfo info = maxPathSumDfs(root);
    return info.maxPathSum;
  }

  public PathSumInfo maxPathSumDfs(TreeNode root) {
    if (root == null) {
      new PathSumInfo(0, Integer.MIN_VALUE);
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
    int curNotCrossRootSinglePathSum = Math.max(leftInfo.maxPathSum, rightInfo.maxPathSum);

    // max path sum
//        Math.max(leftInfo.crossRootSinglePathSum, rightInfo.crossRootSinglePathSum) + root.val;
    int curMaxPathSum = Math.max(
        leftInfo.crossRootSinglePathSum + rightInfo.crossRootSinglePathSum + root.val,
        Math.max(leftInfo.maxPathSum, rightInfo.maxPathSum));
    return new PathSumInfo(curCrossRootSinglePathSum, curMaxPathSum);
  }

  class PathSumInfo {

    int crossRootSinglePathSum;
    int maxPathSum;

    public PathSumInfo(int crossRootSinglePathSum, int maxPathSum) {
      this.crossRootSinglePathSum = crossRootSinglePathSum;
      this.maxPathSum = maxPathSum;
    }
  }

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    if (inorder.length == 0 || postorder.length == 0) {
      return null;
    }
    int len = inorder.length;
    int val = postorder[len - 1];
    int midIdx = -1;
    for (int i = 0; i < inorder.length; i++) {
      if (inorder[i] == val) {
        midIdx = i;
        break;
      }
    }
    int[] leftInorder = Arrays.copyOfRange(inorder, 0, midIdx);
    int[] rightInorder = Arrays.copyOfRange(inorder, midIdx + 1, len);

    int[] leftPostorder = Arrays.copyOfRange(postorder, 0, midIdx);
    int[] rightPostorder = Arrays.copyOfRange(postorder, midIdx, len - 1);
    TreeNode node = new TreeNode(val);
    node.left = buildTree(leftInorder, leftPostorder);
    node.right = buildTree(rightInorder, rightPostorder);
    return node;
  }

//  public TreeNode buildTree(int[] preorder, int[] inorder) {
//    if (preorder.length == 0 || inorder.length == 0) {
//      return null;
//    }
//    int len = preorder.length;
//    int val = preorder[0];
//    int midIdx = -1;
//    for (int i = 0; i < inorder.length; i++) {
//      if (inorder[i] == val) {
//        midIdx = i;
//        break;
//      }
//    }
//    int[] leftPreorder = Arrays.copyOfRange(preorder, 1, midIdx + 1);
//    int[] rightPreorder = Arrays.copyOfRange(preorder, midIdx + 1, len);
//
//    int[] leftInorder = Arrays.copyOfRange(inorder, 0, midIdx);
//    int[] rightInorder = Arrays.copyOfRange(inorder, midIdx + 1, len);
//    TreeNode node = new TreeNode(val);
//    node.left = buildTree(leftPreorder, leftInorder);
//    node.right = buildTree(rightPreorder, rightInorder);
//
//    return node;
//  }

  private long preNum;
  private boolean res;

  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (root.left == null && root.right == null) {
      return true;
    }
    preNum = Long.MIN_VALUE;
    res = true;
    isValidBSTDfs(root);
    return res;
  }

  private void isValidBSTDfs(TreeNode root) {
    if (root == null) {
      return;
    }
    isValidBSTDfs(root.left);
    if (root.val <= preNum) {
      res = false;
      return;
    }
    preNum = root.val;
    isValidBSTDfs(root.right);
  }

  public boolean isValidBST_inorder(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (root.left == null && root.right == null) {
      return true;
    }
    long preNum = Long.MIN_VALUE;
    Stack<TreeNode> stack = new Stack<>();
    while (root != null || !stack.isEmpty()) {
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        TreeNode tmp = stack.pop();
        if (tmp.val <= preNum) {
          return false;
        }
        preNum = tmp.val;
        root = tmp.right;
      }
    }
    return true;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }
    if (root == p || root == q) {
      return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left != null && right != null) {
      return root;
    } else if (left != null) {
      return left;
    } else {
      return right;
    }
  }

  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (root == null || subRoot == null) {
      return false;
    }
    if (isSameTree(root, subRoot)) {
      return true;
    } else {
      return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
  }

  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }
    if (p == null || q == null) {
      return false;
    }
    if (p.val == q.val) {
      return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    } else {
      return false;
    }
  }

  public boolean isBalanced(TreeNode root) {
    BalancedInfo info = isBalancedDfs(root);
    return info.balanced;
  }

  public BalancedInfo isBalancedDfs(TreeNode root) {
    if (root == null) {
      return new BalancedInfo(0, true);
    }
    BalancedInfo leftInfo = isBalancedDfs(root.left);
    BalancedInfo rightInfo = isBalancedDfs(root.right);
    int curDepth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
    if (!leftInfo.balanced || !rightInfo.balanced) {
      return new BalancedInfo(curDepth, false);
    }
    //
    boolean balanced = Math.abs(leftInfo.depth - rightInfo.depth) <= 1;
    return new BalancedInfo(curDepth, balanced);
  }

  private static class BalancedInfo {

    int depth;
    boolean balanced;

    public BalancedInfo(int depth, boolean balanced) {
      this.depth = depth;
      this.balanced = balanced;
    }
  }

  public int diameterOfBinaryTree(TreeNode root) {
    DiameterInfo info = diameterOfBinaryTreeDfs(root);
    return info.diameter;
  }

  private DiameterInfo diameterOfBinaryTreeDfs(TreeNode root) {
    if (root == null) {
      return new DiameterInfo(0, 0);
    }
    DiameterInfo leftInfo = diameterOfBinaryTreeDfs(root.left);
    DiameterInfo rightInfo = diameterOfBinaryTreeDfs(root.right);
    int depth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
    // 经过root
    int diameter1 = leftInfo.depth + rightInfo.depth;
    // 不经过root
    int diameter2 = Math.max(leftInfo.diameter, rightInfo.diameter);

    return new DiameterInfo(depth, Math.max(diameter1, diameter2));
  }

  private static class DiameterInfo {

    int depth;
    int diameter;

    public DiameterInfo(int depth, int diameter) {
      this.depth = depth;
      this.diameter = diameter;
    }
  }

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      return 1;
    }
    int leftDepth = root.left == null ? 1 : maxDepth(root.left);
    int rightDepth = root.right == null ? 1 : maxDepth(root.right);
    return 1 + Math.max(leftDepth, rightDepth);
  }

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode newNode = new TreeNode(root.val);
    newNode.left = invertTree(root.right);
    newNode.right = invertTree(root.left);
    return newNode;
  }

  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public int findMin(int[] nums) {
    int l = 0;
    int r = nums.length - 1;
    if (nums[r] >= nums[l]) {
      return nums[l];
    }

    while (l <= r) {
      int c = l + (r - l) / 2;
      if (nums[c] >= nums[0]) {
        l = c + 1;
      } else {
        r = c - 1;
      }
    }
    return nums[l];
  }

  public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    int[] result = new int[n - k + 1];

    Deque<Integer> window = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      while (!window.isEmpty() && nums[window.peekLast()] < nums[i]) {
        window.removeLast();
      }
      window.offerLast(i);
      if (!window.isEmpty() && i - k >= window.peekFirst()) {
        window.removeFirst();
      }
      //
      if (i - k + 1 >= 0) {
        result[i - k + 1] = nums[window.peekFirst()];
      }
    }
    return result;
  }

  public String minWindow(String s, String t) {
    if (t.length() > s.length()) {
      return "";
    }
    if (t.length() == s.length() && s.equals(t)) {
      return s;
    }
    Map<Character, Integer> window = new HashMap<>();
    Map<Character, Integer> needs = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      char ch = t.charAt(i);
      needs.put(ch, needs.getOrDefault(ch, 0) + 1);
    }

    char[] chars = s.toCharArray();
    int left = 0;
    int right = 0;
    int n = chars.length;
    int match = 0;
    int minLength = Integer.MAX_VALUE;
    String result = "";
    while (right < n) {
      char rightChar = chars[right];
      if (needs.containsKey(rightChar)) {
        window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
        if (window.get(rightChar).equals(needs.get(rightChar))) {
          ++match;
        }
      }
      ++right;

      while (match == needs.size()) {
        if (right - left < minLength) {
          minLength = right - left;
          result = s.substring(left, right);
        }
        char leftChar = chars[left];
        if (needs.containsKey(leftChar)) {
          window.put(leftChar, window.getOrDefault(leftChar, 0) - 1);
          if (needs.get(leftChar) > window.get(leftChar)) {
            --match;
          }
        }
        ++left;
      }
    }
    return result;
  }

  public boolean checkInclusion(String s1, String s2) {
    Map<Character, Integer> window = new HashMap<>();
    Map<Character, Integer> needs = new HashMap<>();
    for (int i = 0; i < s1.length(); i++) {
      char ch = s1.charAt(i);
      needs.put(ch, needs.getOrDefault(ch, 0) + 1);
    }

    char[] chars = s2.toCharArray();
    int left = 0;
    int right = 0;
    int n = chars.length;
    int match = 0;
    while (right < n) {
      char rightChar = chars[right];
      if (needs.containsKey(rightChar)) {
        window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
        if (window.get(rightChar).equals(needs.get(rightChar))) {
          ++match;
        }
      }
      ++right;

      while (match == needs.size()) {
        if (right - left == s1.length()) {
          return true;
        }
        char leftChar = chars[left];
        if (needs.containsKey(leftChar)) {
          window.put(leftChar, window.getOrDefault(leftChar, 0) - 1);
          if (needs.get(leftChar) > window.get(leftChar)) {
            --match;
          }
        }
        ++left;
      }
    }
    return false;
  }

//  public int characterReplacement(String s, int k) {
//    int result = 0;
//    Map<Character, Integer> window = new HashMap<>();
//    char[] chars = s.toCharArray();
//    int left = 0;
//    int right = 0;
//    int n = chars.length;
//    while (right < n) {
//      char rightChar = chars[right];
//      window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
//      ++right;
//
//      while (window.get(rightChar) > k) {
//        char leftChar = chars[left];
//        window.put(leftChar, window.getOrDefault(leftChar, 0) - 1);
//        ++left;
//      }
//      result = Math.max(result, right - left);
//    }
//    return result;
//  }

  public int lengthOfLongestSubstring(String s) {
    int result = 0;
    Map<Character, Integer> window = new HashMap<>();
    char[] chars = s.toCharArray();
    int left = 0;
    int right = 0;
    int n = chars.length;
    while (right < n) {
      char rightChar = chars[right];
      window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
      ++right;

      while (window.get(rightChar) > 1) {
        char leftChar = chars[left];
        window.put(leftChar, window.getOrDefault(leftChar, 0) - 1);
        ++left;
      }
      result = Math.max(result, right - left);
    }
    return result;
  }

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode mergeKLists(ListNode[] lists) {
    return merge(lists, 0, lists.length - 1);
  }

  private ListNode merge(ListNode[] lists, int left, int right) {
    if (left == right) {
      return lists[left];
    }
    int mid = (left + right) >> 1;
    ListNode l1 = merge(lists, left, mid);
    ListNode l2 = merge(lists, mid + 1, right);
    return mergeTwoLists(l1, l2);
  }

  private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    if (l1.val <= l2.val) {
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists(l1, l2.next);
      return l2;
    }
  }


  class LruNode {

    int key;
    int val;
    LruNode prev;
    LruNode next;

    public LruNode(int key, int val) {
      this.key = key;
      this.val = val;
    }
  }

  class LRUCache {

    int capacity;
    LruNode head;
    LruNode tail;
    Map<Integer, LruNode> cache;

    public LRUCache(int capacity) {
      this.capacity = capacity;
      this.head = new LruNode(-1, -1);
      this.tail = new LruNode(-1, -1);
      this.head.next = this.tail;
      this.tail.prev = this.head;
      this.cache = new HashMap<>();
    }

    public int get(int key) {
      if (cache.containsKey(key)) {
        LruNode node = cache.get(key);
        remove(node);
        insert(node);
        return node.val;
      } else {
        return -1;
      }
    }

    public void put(int key, int value) {
      if (cache.containsKey(key)) {
        remove(cache.get(key));
      }
      LruNode newNode = new LruNode(key, value);
      cache.put(key, newNode);
      insert(newNode);
      if (cache.size() > this.capacity) {
        LruNode lru = this.head.next;
        remove(lru);
        cache.remove(lru.key);
      }
    }

    private void remove(LruNode node) {
      LruNode prev = node.prev;
      LruNode next = node.next;
      prev.next = next;
      next.prev = prev;
    }

    private void insert(LruNode node) {
      LruNode prev = this.tail.prev;
      prev.next = node;
      node.next = this.tail;
      node.prev = prev;
      this.tail.prev = node;
    }
  }

  public static int maxArea(int[] heights) {
    int l = 0;
    int r = heights.length - 1;
    int maxArea = 0;
    while (l < r) {
      int minHeight = Math.min(heights[l], heights[r]);
      int curArea = (r - l) * minHeight;
      maxArea = Math.max(maxArea, curArea);
      if (heights[l] < heights[r]) {
        ++l;
      } else {
        --r;
      }
    }
    return maxArea;
  }

  public static List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    int len = nums.length;
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < len - 2; i++) {
      if (nums[i] > 0) {
        break;
      }
      if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
        break;
      }
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      int l = i + 1;
      int r = len - 1;
      while (l < r) {
        int sum = nums[i] + nums[l] + nums[r];
        if (sum > 0) {
          while (l < r && nums[r] == nums[--r])
            ;
        } else if (sum < 0) {
          while (l < r && nums[l] == nums[++l])
            ;
        } else {
          List<Integer> list = new LinkedList<>();
          list.add(nums[i]);
          list.add(nums[l]);
          list.add(nums[r]);
          result.add(list);
          while (l < r && nums[r] == nums[--r])
            ;
          while (l < r && nums[l] == nums[++l])
            ;
        }
      }
    }
    return result;
  }


  public int longestConsecutive(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }

    int result = 0;
    for (int num : nums) {
      // start num
      if (!set.contains(num - 1)) {
        int curNum = num;
        while (true) {
          if (set.contains(curNum + 1)) {
            curNum += 1;
          } else {
            result = Math.max(result, curNum - num + 1);
            break;
          }
        }
      }
    }
    return result;
  }
}
