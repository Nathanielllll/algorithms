import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Test2 {

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
