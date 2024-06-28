import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Test {

  public static void main(String[] args) {
    int[][] grid = new int[][]{{0, 1}, {2, 3}};
    Test test = new Test();
    System.out.println(test.swimInWater(grid));
  }

  public boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));

    for (int i = 0; i < intervals.length - 1; i++) {
      // 遍历会议，如果下一个会议在前一个会议结束之前就开始了，返回 false
      if (intervals[i][1] > intervals[i + 1][0]) {
        return false;
      }
    }

    return true;
  }

  //1，如果后面区间的头小于当前区间的尾，
  //比如当前区间是[3,6]，后面区间是[4,5]或者是[5,9]。一定要注意是 right > intervals[i][0]是重复的。因为根据题意，right == intervals[i][0]是不重合的
  //说明这两个区间有重复，必须要移除一个，那么要移除哪个呢，为了防止在下一个区间和现有区间有重叠，我们应该让现有区间越短越好，所以应该移除尾部比较大的，保留尾部比较小的。
  //2，如果后面区间的头不小于当前区间的尾，说明他们没有重合，不需要移除
  public int eraseOverlapIntervals(int[][] intervals) {
    Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));
    int n = intervals.length;
    int right = intervals[0][1];
    int result = 0;
    for (int i = 1; i < n; i++) {
      if (right > intervals[i][0]) {
        right = Math.min(right, intervals[i][1]);
        ++result;
      } else {
        right = intervals[i][1];
      }
    }
    return result;
  }

  public int[][] merge(int[][] intervals) {
    // 必须保证左边界是上升的，不然后续循环体内没法比较
    Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));
    List<int[]> resultList = new ArrayList<>();
    int n = intervals.length;

    int left = intervals[0][0];
    int right = intervals[0][1];
    for (int i = 1; i < n; i++) {
      if (right >= intervals[i][0]) {
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
    List<int[]> resultList = new ArrayList<>();
    int n = intervals.length;
    int idx = 0;
    //
    while (idx < n && intervals[idx][1] < newInterval[0]) {
      resultList.add(intervals[idx]);
      idx++;
    }
    // 反例：intervals[idx][0] > newInterval[1]，则说明没有重合了
    while (idx < n && intervals[idx][0] <= newInterval[1]) {
      newInterval[0] = Math.min(newInterval[0], intervals[idx][0]);
      newInterval[1] = Math.max(newInterval[1], intervals[idx][1]);
      idx++;
    }
    resultList.add(newInterval);
    //
    while (idx < n) {
      resultList.add(intervals[idx]);
      idx++;
    }
    return resultList.toArray(new int[0][]);
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

    // int[0]是距离root的最短距离，int[1]是节点号
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
    minHeap.offer(new int[]{0, k});
    // 访问过的节点号
    Set<Integer> visited = new HashSet<>();

    int result = 0;
    while (!minHeap.isEmpty()) {
      int[] curr = minHeap.poll();
      int minDis = curr[0];
      int idx = curr[1];
      if (visited.contains(idx)) {
        continue;
      }
      visited.add(idx);
      // 会被一直更新，直到最长的距离
      result = minDis;

      if (edges.get(idx) != null) {
        for (int[] edge : edges.get(idx)) {
          int curIdx = edge[0];
          int dis = edge[1];
          if (!visited.contains(curIdx)) {
            minHeap.add(new int[]{dis + minDis, curIdx});
          }
        }
      }
    }
    return visited.size() == n ? result : -1;
  }


  public List<String> findItinerary(List<List<String>> tickets) {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    for (List<String> ticket : tickets) {
      String from = ticket.get(0);
      String to = ticket.get(1);
      PriorityQueue<String> children = graph.getOrDefault(from, new PriorityQueue<>());
      children.offer(to);
      graph.put(from, graph.getOrDefault(from, children));
    }
    List<String> result = new ArrayList<>();
    findItineraryDfs(graph, result, "JFK");
    return result;
  }

  private void findItineraryDfs(Map<String, PriorityQueue<String>> graph, List<String> result,
      String cur) {
    PriorityQueue<String> children = graph.get(cur);
    while (children != null && !children.isEmpty()) {
      String child = children.poll();
      findItineraryDfs(graph, result, child);
    }
    result.add(0, cur);
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

    Djset djset = new Djset(m * n);
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

  public int minCostConnectPoints(int[][] points) {
    int n = points.length;
    PriorityQueue<int[]> minHead = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int[] edge = calEdge(points, i, j);
        minHead.add(edge);
      }
    }

    Djset djset = new Djset(n);
    int result = 0;
    int edgeCnt = 0;
    while (!minHead.isEmpty()) {
      int[] edge = minHead.poll();
      if (djset.union(edge[1], edge[2])) {
        result += edge[0];
        edgeCnt++;
      }
      if (edgeCnt == n - 1) {
        break;
      }
    }
    return result;
  }

  private int[] calEdge(int[][] points, int idx1, int idx2) {
    int dist =
        Math.abs(points[idx1][0] - points[idx2][0]) + Math.abs(points[idx1][1] - points[idx2][1]);
    return new int[]{dist, idx1, idx2};
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

  public boolean checkValidString(String s) {
    Stack<Integer> left = new Stack<>();
    Stack<Integer> star = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (ch == '(') {
        left.push(i);
      } else if (ch == '*') {
        star.push(i);
      } else if (ch == ')') {
        if (!left.isEmpty()) {
          left.pop();
        } else if (!star.isEmpty()) {
          star.pop();
        } else {
          return false;
        }
      }
    }
    while (!left.isEmpty()) {
      if (star.isEmpty()) {
        return false;
      }
      if (left.peek() < star.peek()) {
        left.pop();
        star.pop();
      } else {
        return false;
      }
    }
    return true;
  }


  public List<Integer> partitionLabels(String s) {
    Map<Character, Integer> charToLastIdx = new HashMap<>();
    char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      charToLastIdx.put(chars[i], i);
    }

    int curLen = 0;
    int goal = 0;
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < chars.length; i++) {
      curLen++;
      char ch = chars[i];
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
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(cnt.keySet());

    while (!minHeap.isEmpty()) {
      int first = minHeap.peek();
      for (int i = 0; i < groupSize; i++) {
        int num = i + first;
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
    int n = gas.length;
    int total = 0;
    int result = 0;
    for (int i = 0; i < n; i++) {
      total += gas[i] - cost[i];
      if (total < 0) {
        total = 0;
        result = i + 1;
      }
    }
    return result;
  }

  public int maxSubArray(int[] nums) {
    int max = Integer.MIN_VALUE;
    int sum = 0;
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      if (sum <= 0) {
        sum = nums[i];
      } else {
        sum += nums[i];
      }
      max = Math.max(max, sum);
    }
    return max;
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

  public boolean canJump(int[] nums) {
    int max = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i > max) {
        return false;
      }
      max = Math.max(max, i + nums[i]);
    }
    return true;
  }

  public String multiply(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) {
      return "0";
    }
    int m = num1.length();
    int n = num2.length();
    int[] num = new int[m + n];

    for (int i = m - 1; i >= 0; i--) {
      for (int j = n - 1; j >= 0; j--) {
        int cur1 = num1.charAt(i) - '0';
        int cur2 = num2.charAt(j) - '0';
        int temp = num[i + j + 1] + cur1 * cur2;
        num[i + j + 1] = temp % 10;
        num[i + j] += temp / 10;
      }
    }

    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < num.length; i++) {
      if (stringBuilder.length() == 0 && num[i] == 0) {
        continue;
      }
      stringBuilder.append(num[i]);
    }
    return stringBuilder.toString();

  }

  public double myPow(double x, int n) {
    if (n == 0) {
      return 1;
    }

    long exponent = Math.abs((long) n);
    double result = myPowProcess(x, exponent);

    return n < 0 ? 1 / result : result;
  }

  private double myPowProcess(double base, long exponent) {
    if (exponent == 1) {
      return base;
    }
    if (exponent == 0) {
      return 1;
    }

    double sub = myPowProcess(base, exponent / 2);
    if (exponent % 2 == 1) {
      return sub * sub * base;
    } else {
      return sub * sub;
    }
  }

  public int[] plusOne(int[] digits) {
    int carry = 0;
    int n = digits.length;
    for (int i = n - 1; i >= 0; i--) {
      int num;
      if (i == n - 1) {
        num = digits[i] + carry + 1;
      } else {
        if (carry == 0) {
          break;
        }
        num = digits[i] + carry;
      }
      digits[i] = num % 10;
      carry = num / 10;
    }
    if (carry == 0) {
      return digits;
    }
    int[] newDigits = new int[n + 1];
    newDigits[0] = carry;
    System.arraycopy(digits, 0, newDigits, 1, n);
    return newDigits;
  }

  public boolean isHappy(int n) {
    int slow = n;
    int fast = sumSquareDigits(n);
    while (slow != fast) {
      fast = sumSquareDigits(fast);
      fast = sumSquareDigits(fast);
      slow = sumSquareDigits(slow);
    }
    return slow == 1;
  }

  private int sumSquareDigits(int n) {
    int output = 0;
    while (n != 0) {
      int rest = n % 10;
      output += rest * rest;
      n = n / 10;
    }
    return output;
  }

  public void setZeroes(int[][] matrix) {
    Set<Integer> rows = new HashSet<>();
    Set<Integer> cols = new HashSet<>();
    int m = matrix.length;
    int n = matrix[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 0) {
          rows.add(i);
          cols.add(j);
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (rows.contains(i) || cols.contains(j)) {
          matrix[i][j] = 0;
        }
      }
    }
  }

  public static List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> result = new ArrayList<>();
    int tRow = 0;
    int tCol = 0;
    int dRow = matrix.length - 1;
    int dCol = matrix[0].length - 1;

    while (tRow <= dRow && tCol <= dCol) {
      spiralOrderProcess(matrix, tRow++, tCol++, dRow--, dCol--, result);
    }
    return result;
  }

  private static void spiralOrderProcess(int[][] matrix, int upRow, int upCol, int downRow,
      int downCol, List<Integer> result) {
    if (upRow == downRow) {
      int times1 = downCol - upCol;
      for (int i = 0; i <= times1; i++) {
        result.add(matrix[upRow][upCol + i]);
      }
    } else if (upCol == downCol) {
      int times2 = downRow - upRow;
      for (int i = 0; i <= times2; i++) {
        result.add(matrix[upRow + i][downCol]);
      }
    } else {
      int times1 = downCol - upCol;
      int times2 = downRow - upRow;
      for (int i = 0; i < times1; i++) {
        result.add(matrix[upRow][upCol + i]);
      }
      for (int i = 0; i < times2; i++) {
        result.add(matrix[upRow + i][downCol]);
      }
      for (int i = 0; i < times1; i++) {
        result.add(matrix[downRow][downCol - i]);
      }
      for (int i = 0; i < times2; i++) {
        result.add(matrix[downRow - i][upCol]);
      }
    }

  }


  public void rotate(int[][] matrix) {
    int tRow = 0;
    int tCol = 0;
    int dRow = matrix.length - 1;
    int dCol = matrix[0].length - 1;

    while (tRow <= dRow) {
      rotateProcess(matrix, tRow++, tCol++, dRow--, dCol--);
    }
  }

  private void rotateProcess(int[][] matrix, int upRow, int upCol, int downRow, int downCol) {
    int times = downRow - upRow;
    for (int i = 0; i < times; i++) {
      int tmp = matrix[upRow][upCol + i];
      matrix[upRow][upCol + i] = matrix[downRow - i][upCol];
      matrix[downRow - i][upCol] = matrix[downRow][downCol - i];
      matrix[downRow][downCol - i] = matrix[upRow + i][downCol];
      matrix[upRow + i][downCol] = tmp;
    }
  }

  public int reverse(int x) {
    int MAX = Integer.MAX_VALUE;
    int MIN = Integer.MIN_VALUE;
    int result = 0;
    while (x != 0) {
      int digit = x % 10;
      x = x / 10;

      if (result > MAX / 10 || (result == MAX / 10 && digit > MAX % 10)) {
        return 0;
      }
      if (result < MIN / 10 || (result == MIN / 10 && digit < MIN % 10)) {
        return 0;
      }
      result = result * 10 + digit;
    }
    return result;
  }

  public int getSum(int a, int b) {
    int ans = 0;
    for (int i = 0, t = 0; i < 32; i++) {
      int u1 = (a >> i) & 1, u2 = (b >> i) & 1;
      if (u1 == 1 && u2 == 1) {
        ans |= (t << i);
        t = 1;
      } else if (u1 == 1 || u2 == 1) {
        ans |= ((1 ^ t) << i);
      } else {
        ans |= (t << i);
        t = 0;
      }
    }
    return ans;
  }


  public int missingNumber(int[] nums) {
    int expectedSum = nums.length * (nums.length + 1) / 2;
    int actualSum = 0;
    for (int num : nums) {
      actualSum += num;
    }
    return expectedSum - actualSum;
  }

  public int reverseBits(int n) {
    int result = 0;

    for (int i = 0; i < 32; i++) {
      result = result << 1;
      if ((n & 1) == 1) {
        result += 1;
      }
      n = n >> 1;
    }
    return result;
  }

  public int[] countBits(int n) {
    // 奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
    // 偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。

    int[] dp = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      if ((i & 1) == 1) {
        dp[i] = dp[i - 1] + 1;
      } else {
        dp[i] = dp[i / 2];
      }
    }
    return dp;
  }

  public int hammingWeight(int n) {
    int cnt = 0;
    for (int i = 0; i < 32; i++) {
      if ((n & 1) == 1) {
        cnt++;
      }
      n = n >> 1;
    }
    return cnt;
  }

  public int singleNumber(int[] nums) {
    int resultExclusiveOR = 0;
    for (int i = 0; i < nums.length; i++) {
      resultExclusiveOR = resultExclusiveOR ^ nums[i];
    }
    return resultExclusiveOR;
  }

  public boolean isMatch(String s_string, String p_string) {
    int m = s_string.length();
    int n = p_string.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    for (int j = 2; j <= n; j += 2) {
      if (p_string.charAt(j - 1) == '*') {
        dp[0][j] = dp[0][j - 2];
      }
    }
    char[] s = s_string.toCharArray();
    char[] p = p_string.toCharArray();

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (s[i - 1] == p[j - 1] || p[j - 1] == '.') {
          dp[i][j] = dp[i - 1][j - 1];
        } else if (p[j - 1] == '*') {
          if (s[i - 1] == p[j - 2] || p[j - 2] == '.') {
            // 假设为：##b , ###b*
            dp[i][j] = dp[i - 1][j] // ## , ###b*
                || dp[i][j - 1] // ##b , ###b
                || dp[i][j - 2];// ##b , ###
          } else {
            dp[i][j] = dp[i][j - 2];
          }
        }
      }
    }
    return dp[m][n];
  }

  public int maxProfit2(int[] prices) {
    int dp_i_k_0 = 0;
    int dp_i_k_1 = Integer.MIN_VALUE;
    int dp_imin1_k_0 = 0;
    int n = prices.length;
    for (int i = 0; i < n; i++) {
      int pre_dp_i_k_0 = dp_i_k_0;
      int pre_dp_i_k_1 = dp_i_k_1;
      dp_i_k_0 = Math.max(pre_dp_i_k_0, pre_dp_i_k_1 + prices[i]);
      dp_i_k_1 = Math.max(pre_dp_i_k_1, dp_imin1_k_0 - prices[i]);
      dp_imin1_k_0 = pre_dp_i_k_0;
    }
    return dp_i_k_0;
  }

  public int maxProfit(int[] prices) {
    int dp_i_1_0 = 0;
    int dp_i_1_1 = Integer.MIN_VALUE;
    int n = prices.length;
    for (int i = 0; i < n; i++) {
      int pre_dp_i_1_0 = dp_i_1_0;
      int pre_dp_i_1_1 = dp_i_1_1;
      dp_i_1_0 = Math.max(pre_dp_i_1_0, pre_dp_i_1_1 + prices[i]);
      dp_i_1_1 = Math.max(pre_dp_i_1_1, -prices[i]);
    }
    return dp_i_1_0;
  }

  public int maxCoins(int[] nums) {
    int length = nums.length;
    int[] newNums = new int[length + 2];
    System.arraycopy(nums, 0, newNums, 1, length);
    newNums[0] = 1;
    newNums[length + 1] = 1;
    int newLen = newNums.length;
    int[][] memo = new int[newLen][newLen];
    return maxCoinsDfs(newNums, memo, 0, newLen - 1);
  }

  // def( i , j ) 函数的定义则为，不戳破 i 与 j ，仅戳破 i 与 j 之间的气球我们能得到的最大金币数。
  public int maxCoinsDfs(int[] nums, int[][] memo, int i, int j) {
    if (i + 1 == j) {
      return 0;
    }
    if (memo[i][j] != 0) {
      return memo[i][j];
    }

    int max = 0;
    for (int k = i + 1; k < j; k++) {
      int left = maxCoinsDfs(nums, memo, i, k);
      int right = maxCoinsDfs(nums, memo, k, j);
      int cur = nums[k] * nums[i] * nums[j];
      max = Math.max(max, left + right + cur);
    }
    return memo[i][j] = max;
  }

  public int numDistinct(String s, String t) {
    int m = s.length();
    int n = t.length();
    int[][] dp = new int[m + 1][n + 1];
    // 相当于对 word1 执行 i 次删除操作
    for (int i = 0; i <= m; i++) {
      dp[i][0] = 1;
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[m][n];
  }

  public static int minDistance1(String word1, String word2) {
    int size1 = word1.length();
    int size2 = word2.length();
    int[][] memo = new int[size1][size2];

    return minDistanceDfs(size1 - 1, size2 - 1, word1, word2, memo);
  }

  private static int minDistanceDfs(int i, int j, String word1, String word2, int[][] memo) {
    if (i == -1) {
      return j + 1;
    }
    if (j == -1) {
      return i + 1;
    }

    if (memo[i][j] != 0) {
      return memo[i][j];
    }

    char ch1 = word1.charAt(i);
    char ch2 = word2.charAt(j);

    if (ch1 == ch2) {
      memo[i][j] = minDistanceDfs(i - 1, j - 1, word1, word2, memo);
    } else {
      memo[i][j] = Math.min(minDistanceDfs(i - 1, j, word1, word2, memo),
          Math.min(minDistanceDfs(i, j - 1, word1, word2, memo),
              minDistanceDfs(i - 1, j - 1, word1, word2, memo))) + 1;
    }
    return memo[i][j];
  }

  public int minDistance(String word1, String word2) {
    int len1 = word1.length();
    int len2 = word2.length();
    // dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数。因此它们都是已经匹配好的。
    int[][] dp = new int[len1 + 1][len2 + 1];
    // 对word1删除操作
    for (int i = 0; i <= len1; i++) {
      dp[i][0] = i;
    }
    for (int j = 0; j <= len2; j++) {
      dp[0][j] = j;
    }
    for (int i = 1; i <= len1; i++) {
      for (int j = 1; j <= len2; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
        }
      }
    }
    return dp[len1][len2];
  }

  public int longestIncreasingPath(int[][] matrix) {
    return longestIncreasingPathDfs(matrix);
  }

  private int longestIncreasingPathDfs(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int result = 0;
    int[][] memo = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        result = Math.max(result, longestIncreasingPathDfs(matrix, i, j, m, n, directs, memo));
      }
    }
    return result;
  }

  private int longestIncreasingPathDfs(int[][] matrix, int i, int j, int m, int n,
      int[][] directs, int[][] memo) {
    if (memo[i][j] != 0) {
      return memo[i][j];
    }

    int level = 1;
    for (int k = 0; k < 4; k++) {
      int x = i + directs[k][0];
      int y = j + directs[k][1];
      if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
        level = Math.max(level, 1 + longestIncreasingPathDfs(matrix, x, y, m, n, directs, memo));
      }
    }
//    memo[i][j] = Math.max(memo[i][j], level);
    // 下面这种写法也可以的。因为memo表示从当前位置【出发】，最长的长度
    memo[i][j] = level;
    return level;
  }

  private int longestIncreasingPathBfs(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int result = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        result = Math.max(result, longestIncreasingPathBfs(matrix, i, j, m, n, directs));
      }
    }
    return result;
  }

  private int longestIncreasingPathBfs(int[][] matrix, int i, int j, int m, int n,
      int[][] directs) {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{i, j});

    int level = 0;
    while (!queue.isEmpty()) {
      int cnt = queue.size();
      for (int k = 0; k < cnt; k++) {
        int[] mat = queue.poll();
        int r = mat[0];
        int c = mat[1];
        for (int l = 0; l < 4; l++) {
          int x = r + directs[l][0];
          int y = c + directs[l][1];
          if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[r][c]) {
            queue.add(new int[]{x, y});
          }
        }
      }
      ++level;
    }
    return level;
  }

  public static int findTargetSumWays(int[] nums, int target) {
//                  sum(P) - sum(N) = target
//        sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
//                               2 * sum(P) = target + sum(nums)
    int sum = Arrays.stream(nums).sum();
    if (sum < target || (sum + target) % 2 == 1) {
      return 0;
    }
    int realTarget = (sum + target) / 2;
    int[] dp = new int[realTarget + 1];
    dp[0] = 1;
    for (int num : nums) {
      for (int i = realTarget; i >= 0; i--) {
        if (i - num >= 0) {
          dp[i] = dp[i] + dp[i - num];
        }
      }
    }
    return dp[realTarget];
  }

  public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;
    for (int coin : coins) {
      for (int i = 1; i <= amount; i++) {
        if (i - coin >= 0) {
          dp[i] = dp[i] + dp[i - coin];
        }
      }
    }
    return dp[amount];
  }

//  public int maxProfit(int[] prices) {
//    int n = prices.length;
//    if (n <= 1) {
//      return 0;
//    }
//
//
//  }

  public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (text1.charAt(i) == text2.charAt(j)) {
          int pre = j - 1 >= 0 && i - 1 >= 0 ? dp[i - 1][j - 1] : 0;
          dp[i][j] = pre + 1;
        } else {
          int up = j - 1 >= 0 ? dp[i][j - 1] : 0;
          int left = i - 1 >= 0 ? dp[i - 1][j] : 0;
          dp[i][j] = Math.max(up, left);
        }
      }
    }
    return dp[m - 1][n - 1];
  }

  public static int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = 1;
        }
      }
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }
    return dp[m - 1][n - 1];
  }

  public boolean canPartition(int[] nums) {
    int sum = Arrays.stream(nums).sum();
    if (sum % 2 == 1) {
      return false;
    }
    int target = sum / 2;

    boolean[] dp = new boolean[target + 1];
    dp[0] = true;
    for (int num : nums) {
      for (int i = target; i >= num; i--) {
        if (dp[target]) {
          return true;
        }
        dp[i] = dp[i] || dp[i - num];
      }
    }
    return dp[target];
  }

  public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int res = 1;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (nums[j] > nums[i]) {
          dp[j] = Math.max(dp[j], dp[i] + 1);
        }
        res = Math.max(res, dp[j]);
      }
    }
    return res;
  }

  public boolean wordBreak(String s, List<String> wordDict) {
    int len = s.length();
    boolean[] dp = new boolean[len + 1];
    dp[0] = true;
    for (int i = 1; i <= len; i++) {
      for (String word : wordDict) {
        int wordLen = word.length();
        if (i - wordLen >= 0) {
          if (word.equals(s.substring(i - wordLen, i))) {
            dp[i] = dp[i - wordLen];
          }
        }
      }
    }
    return dp[len];
  }

  public int maxProduct(int[] nums) {
    int dp_min = nums[0];
    int dp_max = nums[0];
    int result = nums[0];

    for (int i = 1; i < nums.length; i++) {
      int num = nums[i];
      int pre_max = dp_max;
      int pre_min = dp_min;
      dp_max = Math.max(num, Math.max(pre_max * num, pre_min * num));
      dp_min = Math.min(num, Math.min(pre_max * num, pre_min * num));

      result = Math.max(dp_max, result);
    }
    return result;
  }

  public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;
    for (int coin : coins) {
      for (int i = 0; i <= amount; i++) {
        if (i - coin >= 0) {
          dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
        }
      }
    }
    return dp[amount] == amount + 1 ? -1 : dp[amount];
  }

  public int countSubstrings(String s) {
    int len = s.length();
    boolean[][] dp = new boolean[len][len];
    int result = 0;
    for (int j = 0; j < len; j++) {
      for (int i = 0; i <= j; i++) {
        if (s.charAt(i) == s.charAt(j)) {
          if (j - i <= 1 || dp[i + 1][j - 1]) {
            dp[i][j] = true;
            result++;
          }
        }
      }
    }
    return result;
  }

  public static String longestPalindrome(String s) {
    int len = s.length();
    int[][] dp = new int[len][len];
    String result = "";

    for (int j = 0; j < len; j++) {
      for (int i = 0; i <= j; i++) {
        if (s.charAt(i) == s.charAt(j)) {
          if (j - i <= 1) {
            dp[i][j] = j - i + 1;
          } else if (dp[i + 1][j - 1] > 0) {
            dp[i][j] = dp[i + 1][j - 1] + 2;
          }
          if (dp[i][j] > result.length()) {
            result = s.substring(i, j + 1);
          }
        }
      }
    }
    return result;
  }

  public String expandAroundCenter(String string, int left, int right) {
    while (left >= 0 && right < string.length()) {
      if (string.charAt(left) == string.charAt(right)) {
        left--;
        right++;
      } else {
        break;
      }
    }
    return string.substring(left + 1, right);
  }

  public int rob2(int[] nums) {
    int n = nums.length;
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return nums[0];
    }
    if (n == 2) {
      return Math.max(nums[0], nums[1]);
    }

    int[] subNums1 = Arrays.copyOfRange(nums, 0, n - 1);
    int[] subNums2 = Arrays.copyOfRange(nums, 1, n);

    return Math.max(rob(subNums1), rob(subNums2));
  }

  public int rob(int[] nums) {
    int n = nums.length;
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return nums[0];
    }
    if (n == 2) {
      return Math.max(nums[0], nums[1]);
    }

    int[] dp = new int[n];
    dp[0] = nums[0];
    dp[1] = Math.max(nums[0], nums[1]);
    for (int i = 2; i < n; i++) {
      dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
    }
    return dp[n - 1];
  }

  public int minCostClimbingStairs(int[] cost) {
    int n = cost.length;

    int[] dp = new int[n + 1];
    System.arraycopy(cost, 0, dp, 0, 2);
    for (int i = 2; i <= n; i++) {
      int c = i == n ? 0 : cost[i];
      dp[i] = Math.min(dp[i - 1], dp[i - 2]) + c;
    }
    return dp[n];
  }

  public int climbStairs(int n) {
    if (n <= 2) {
      return n;
    }
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (!wordList.contains(endWord)) {
      return 0;
    }
    if (!wordList.contains(beginWord)) {
      wordList = new ArrayList<>(wordList);
      wordList.add(beginWord);
    }
    Map<String, List<String>> graph = new HashMap<>();
    for (int i = 0; i < wordList.size(); i++) {
      for (int j = i + 1; j < wordList.size(); j++) {
        String w1 = wordList.get(i);
        String w2 = wordList.get(j);
        if (isClose(w1, w2)) {
          graph.putIfAbsent(w1, new ArrayList<>());
          graph.putIfAbsent(w2, new ArrayList<>());
          graph.get(w1).add(w2);
          graph.get(w2).add(w1);
        }
      }
    }

    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    queue.add(beginWord);
    visited.add(beginWord);
    int level = 1;
    while (!queue.isEmpty()) {
      int cnt = queue.size();
      for (int i = 0; i < cnt; i++) {
        String curWord = queue.poll();
        if (curWord.equals(endWord)) {
          return level;
        }
        for (String neighbor : graph.getOrDefault(curWord, new ArrayList<>())) {
          if (visited.contains(neighbor)) {
            continue;
          }
          queue.add(neighbor);
          visited.add(neighbor);
        }
      }
      level++;
    }
    return 0;
  }

  private boolean isClose(String w1, String w2) {
    int n = w1.length();
    int diffCnt = 0;
    for (int i = 0; i < n; i++) {
      if (w1.charAt(i) == w2.charAt(i)) {
        continue;
      }
      diffCnt++;
    }
    return diffCnt == 1;
  }

//  public int[] findRedundantConnection(int[][] edges) {
//    int n = edges.length;
//    Djset djset = new Djset(n);
//    for (int[] edge : edges) {
//      int unionRes = djset.union(edge[0] - 1, edge[1] - 1);
//      if (unionRes == 0) {
//        return edge;
//      }
//    }
//    return new int[]{};
//  }
//
//  public int countComponents(int n, int[][] edges) {
//    Djset djset = new Djset(n);
//    int result = n;
//    for (int[] edge : edges) {
//      if (djset.union(edge[0], edge[1]) == 1) {
//        result--;
//      }
//    }
//    return result;
//  }
//
//  public boolean validTree(int n, int[][] edges) {
//    Djset djset = new Djset(n);
//    int result = n;
//    for (int[] edge : edges) {
//      int unionRes = djset.union(edge[0], edge[1]);
//      if (unionRes == 0) {
//        return false;
//      } else if (unionRes == 1) {
//        result--;
//      }
//    }
//    return result == 1;
//  }
//
//  static class Djset {
//
//    int[] parent;
//    int[] rank;
//
//    public Djset(int length) {
//      parent = new int[length];
//      rank = new int[length];
//      for (int i = 0; i < length; i++) {
//        parent[i] = i;
//      }
//    }
//
//    public int findRoot(int val) {
//      if (val != parent[val]) {
//        parent[val] = findRoot(parent[val]);
//      }
//      return parent[val];
//    }
//
//    public int union(int x, int y) {
//      int xRoot = findRoot(x);
//      int yRoot = findRoot(y);
//      if (xRoot == yRoot) {
//        return 0;
//      } else {
//        if (rank[xRoot] < rank[yRoot]) {
//          parent[xRoot] = yRoot;
//        } else if (rank[yRoot] < rank[xRoot]) {
//          parent[yRoot] = xRoot;
//        } else {
//          parent[xRoot] = yRoot;
//          rank[yRoot]++;
//        }
//      }
//      return 1;
//    }
//  }

  private boolean validTreeDfs(int n, int[][] edges) {
    if (n == 1) {
      return edges.length == 0;
    }
    if (edges.length == 0) {
      return false;
    }
    Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
    for (int[] edge : edges) {
      int node1 = edge[0];
      int node2 = edge[1];
      adjacencyList.putIfAbsent(node1, new ArrayList<>());
      adjacencyList.putIfAbsent(node2, new ArrayList<>());
      adjacencyList.get(node1).add(node2);
      adjacencyList.get(node2).add(node1);
    }

    Set<Integer> visited = new HashSet<>();
    if (!depthFirstSearch(edges[0][0], visited, adjacencyList, -1)) {
      return false;
    }
    return visited.size() == n;
  }

  private boolean depthFirstSearch(int node, Set<Integer> visited,
      Map<Integer, List<Integer>> adjacencyList, int previous) {
    if (visited.contains(node)) {
      return false;
    }

    visited.add(node);
    List<Integer> edges = adjacencyList.get(node);
    for (Integer edge : edges) {
      if (edge == previous) {
        continue;
      }
      if (!depthFirstSearch(edge, visited, adjacencyList, node)) {
        return false;
      }
    }
    return true;
  }

  public int leastInterval(char[] tasks, int n) {
    int[] taskCnt = new int[26];
    for (char task : tasks) {
      taskCnt[task - 'A']++;
    }

    int maxCnt = 0;
    for (int i = 0; i < 26; i++) {
      maxCnt = Math.max(maxCnt, taskCnt[i]);
    }
    int p = 0;
    for (int i = 0; i < 26; i++) {
      if (taskCnt[i] == maxCnt) {
        ++p;
      }
    }
    int ans = (maxCnt - 1) * (n + 1) + p;
    return Math.max(ans, tasks.length);
  }

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    return pacificAtlanticDfs(heights);
  }

  private List<List<Integer>> pacificAtlanticDfs(int[][] heights) {
    int m = heights.length;
    int n = heights[0].length;
    Set<Integer> pacific = new HashSet<>();
    Set<Integer> atlantic = new HashSet<>();
    int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // pacific
        if (i == 0 || j == 0) {
          pacificAtlanticDfs(heights, i, j, m, n, directs, pacific);
        }
        // atlantic
        if (i == m - 1 || j == n - 1) {
          pacificAtlanticDfs(heights, i, j, m, n, directs, atlantic);
        }
      }
    }
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int coord = i * n + j;
        if (pacific.contains(coord) && atlantic.contains(coord)) {
          res.add(Arrays.asList(i, j));
        }
      }
    }
    return res;
  }

  private void pacificAtlanticDfs(int[][] heights, int i, int j, int m, int n, int[][] directs,
      Set<Integer> visited) {
    int coord = i * n + j;
    if (visited.contains(coord)) {
      return;
    }
    visited.add(coord);
    for (int k = 0; k < 4; k++) {
      int x = i + directs[k][0];
      int y = j + directs[k][1];
      if (x >= 0 && x < m && y >= 0 && y < n && heights[x][y] >= heights[i][j]) {
        pacificAtlanticDfs(heights, x, y, m, n, directs, visited);
      }
    }
  }

  public static int[] findOrder(int numCourses, int[][] prerequisites) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    int[] in = new int[numCourses];
    for (int[] edge : prerequisites) {
      int pre = edge[1];
      int next = edge[0];
      in[next]++;
      graph.computeIfAbsent(pre, k -> new HashSet<>()).add(next);
    }

    List<Integer> resultList = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < in.length; i++) {
      if (in[i] == 0) {
        queue.add(i);
      }
    }
    while (!queue.isEmpty()) {
      int cnt = queue.size();
      for (int i = 0; i < cnt; i++) {
        int course = queue.poll();
        resultList.add(course);
        Set<Integer> nexts = graph.get(course);
        if (nexts != null) {
          for (Integer next : nexts) {
            in[next]--;
            if (in[next] == 0) {
              queue.add(next);
            }
          }
        }
      }
    }
    for (int i = 0; i < numCourses; i++) {
      if (in[i] != 0) {
        return new int[]{};
      }
    }

    int[] result = new int[resultList.size()];
    for (int i = 0; i < resultList.size(); i++) {
      result[i] = resultList.get(i);
    }
    return result;
  }


  public boolean canFinish(int numCourses, int[][] prerequisites) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    int[] in = new int[numCourses];
    for (int[] edge : prerequisites) {
      int pre = edge[1];
      int next = edge[0];
      in[next]++;
      graph.computeIfAbsent(pre, k -> new HashSet<>()).add(next);
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < in.length; i++) {
      if (in[i] == 0) {
        queue.add(i);
      }
    }
    while (!queue.isEmpty()) {
      int cnt = queue.size();
      for (int i = 0; i < cnt; i++) {
        int course = queue.poll();
        Set<Integer> nexts = graph.get(course);
        if (nexts != null) {
          for (Integer next : nexts) {
            in[next]--;
            if (in[next] == 0) {
              queue.add(next);
            }
          }
        }
      }
    }
    //如果还有入度>0的节点，则不存在拓扑排序，则存在环。
    for (int i = 0; i < numCourses; i++) {
      if (in[i] != 0) {
        return false;
      }
    }

    return true;
  }

  public void solve(char[][] board) {
    solveDfs(board);
  }

  public void solveDfs(char[][] board) {
    int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
          if (board[i][j] == 'O') {
            solveDfs(board, i, j, m, n, directs);
          }
        }
      }
    }
    //
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'O') {
          board[i][j] = 'X';
        } else if (board[i][j] == '#') {
          board[i][j] = 'O';
        }
      }
    }
  }

  private void solveDfs(char[][] board, int i, int j, int m, int n, int[][] directs) {
    board[i][j] = '#';
    for (int k = 0; k < 4; k++) {
      int x = i + directs[k][0];
      int y = j + directs[k][1];
      if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
        solveDfs(board, x, y, m, n, directs);
      }
    }
  }

  private void solveBfs(char[][] board) {
    int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    Queue<int[]> queue = new LinkedList<>();
    int m = board.length;
    int n = board[0].length;
    // 处理边界的'O'
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
          if (board[i][j] == 'O') {
            queue.add(new int[]{i, j});
          }
        }
      }
    }
    while (!queue.isEmpty()) {
      int[] treasure = queue.poll();
      int row = treasure[0];
      int col = treasure[1];
      board[row][col] = '#';
      for (int j = 0; j < 4; j++) {
        int x = row + directs[j][0];
        int y = col + directs[j][1];
        if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
          queue.add(new int[]{x, y});
        }
      }
    }
    //
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'O') {
          board[i][j] = 'X';
        } else if (board[i][j] == '#') {
          board[i][j] = 'O';
        }
      }
    }
  }

  public int orangesRotting(int[][] grid) {
    return orangesRottingBfs(grid);
  }

  private static int orangesRottingBfs(int[][] grid) {
    int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int fresh = 0;
    Queue<int[]> queue = new LinkedList<>();
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 2) {
          queue.add(new int[]{i, j});
        }
        if (grid[i][j] == 1) {
          ++fresh;
        }
      }
    }

    boolean flag = false;
    int level = 0;
    while (!queue.isEmpty()) {
      int cnt = queue.size();
      if (flag) {
        level++;
      } else {
        flag = true;
      }
      for (int i = 0; i < cnt; i++) {
        int[] treasure = queue.poll();
        int row = treasure[0];
        int col = treasure[1];
        for (int j = 0; j < 4; j++) {
          int x = row + directs[j][0];
          int y = col + directs[j][1];
          if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
            --fresh;
            grid[x][y] = 2;
            queue.add(new int[]{x, y});
          }
        }
      }
    }
    return fresh == 0 ? level : -1;
  }

  public void islandsAndTreasure(int[][] grid) {
//    int m = grid.length;
//    int n = grid[0].length;
//    for (int i = 0; i < m; i++) {
//      for (int j = 0; j < n; j++) {
//        if (grid[i][j] == 0) {
//          islandsAndTreasureDfs(grid, i, j, m, n);
//        }
//      }
//    }
    islandsAndTreasureBfs(grid);
  }

  int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

  private void islandsAndTreasureDfs(int[][] grid, int row, int col, int m, int n) {
    if (grid[row][col] < 0) {
      return;
    }
    for (int i = 0; i < 4; i++) {
      int x = row + directs[i][0];
      int y = col + directs[i][1];
      if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == Integer.MAX_VALUE) {
        grid[x][y] = grid[row][col] + 1;
        islandsAndTreasureDfs(grid, x, y, m, n);
      }
    }
  }

  // bfs会把最近的inf, 率先赋值掉了。而dfs不同，要看岛屿开始的位置，所以不同的岛屿出发，inf的被赋的值也不同！
  private void islandsAndTreasureBfs(int[][] grid) {
    int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    // bfs，找到不同岛屿离宝藏的level距离
    Queue<int[]> queue = new LinkedList<>();
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 0) {
          queue.add(new int[]{i, j});
        }
      }
    }

    while (!queue.isEmpty()) {
      int[] treasure = queue.poll();
      int row = treasure[0];
      int col = treasure[1];
      for (int i = 0; i < 4; i++) {
        int x = row + directs[i][0];
        int y = col + directs[i][1];
        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == Integer.MAX_VALUE) {
          grid[x][y] = grid[row][col] + 1;
          queue.add(new int[]{x, y});
        }
      }
    }
  }

  public int maxAreaOfIsland(int[][] grid) {
    int result = 0;
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          int areaOfIsland = maxAreaOfIslandDfs(grid, i, j, m, n);
          result = Math.max(result, areaOfIsland);
        }
      }
    }
    return result;
  }

  private int maxAreaOfIslandDfs(int[][] grid, int i, int j, int m, int n) {
    int cnt = 0;
    if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1) {
      ++cnt;
      grid[i][j] = 0;
      cnt =
          cnt + maxAreaOfIslandDfs(grid, i - 1, j, m, n) + maxAreaOfIslandDfs(grid, i + 1, j, m, n)
              + maxAreaOfIslandDfs(grid, i, j - 1, m, n) + maxAreaOfIslandDfs(grid, i, j + 1, m, n);
    }
    return cnt;
  }

  public int numIslands(char[][] grid) {
    int landsCnt = 0;
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          ++landsCnt;
          numIslandsDfs(grid, i, j, m, n);
        }
      }
    }
    return landsCnt;
  }

  private void numIslandsDfs(char[][] grid, int i, int j, int m, int n) {
    if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == '1') {
      grid[i][j] = '0';
      numIslandsDfs(grid, i - 1, j, m, n);
      numIslandsDfs(grid, i + 1, j, m, n);
      numIslandsDfs(grid, i, j - 1, m, n);
      numIslandsDfs(grid, i, j + 1, m, n);
    }
  }


  class Twitter {

    private int incIdx;
    // int[]的中int[0] 是自增索引号，int[1]是tweetId;
    private Map<Integer, List<int[]>> tweetMap;
    private Map<Integer, Set<Integer>> followMap;

    public Twitter() {
      incIdx = 0;
      tweetMap = new HashMap<>();
      followMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
      tweetMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(new int[]{incIdx++, tweetId});
    }

    public List<Integer> getNewsFeed(int userId) {
      // copy
      Set<Integer> userSet = new HashSet<>(followMap.getOrDefault(userId, new HashSet<>()));
      userSet.add(userId);

      PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
      for (Integer user : userSet) {
        List<int[]> tweetList = tweetMap.get(user);
        if (tweetList != null) {
          maxHeap.addAll(tweetList);
        }
      }

      List<Integer> result = new ArrayList<>();
      while (!maxHeap.isEmpty() && result.size() < 10) {
        result.add(maxHeap.poll()[1]);
      }
      return result;
    }

    public void follow(int followerId, int followeeId) {
      followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
      followMap.computeIfPresent(followerId, (k, v) -> {
        v.remove(followeeId);
        return v;
      });
    }
  }

  /**
   * Your Twitter object will be instantiated and called as such: Twitter obj = new Twitter();
   * obj.postTweet(userId,tweetId); List<Integer> param_2 = obj.getNewsFeed(userId);
   * obj.follow(followerId,followeeId); obj.unfollow(followerId,followeeId);
   */

  class MedianFinder {

    // large elements - minHeap
    private final PriorityQueue<Integer> largeHeap;
    //small elements - maxHeap
    private final PriorityQueue<Integer> smallHeap;

    public MedianFinder() {
      largeHeap = new PriorityQueue<>();
      smallHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
//      largeHeap.offer(num);
//      smallHeap.offer(largeHeap.poll());
//      if (largeHeap.size() < smallHeap.size()) {
//        largeHeap.offer(smallHeap.poll());
//      }

      largeHeap.offer(num);
      if ((largeHeap.size() - smallHeap.size() > 1)
          ||
          (!largeHeap.isEmpty() && !smallHeap.isEmpty() && largeHeap.peek() < smallHeap.peek())) {
        smallHeap.offer(largeHeap.poll());
      }
      if (smallHeap.size() > largeHeap.size()) {
        largeHeap.offer(smallHeap.poll());
      }
    }

    public double findMedian() {
      if (largeHeap.isEmpty()) {
        return 0.0;
      } else {
        if (largeHeap.size() == smallHeap.size()) {
          return (largeHeap.peek() + smallHeap.peek()) * 0.5;
        } else {
          return largeHeap.peek() * 1.0;
        }
      }
    }
  }

  public int[][] kClosest(int[][] points, int k) {
    // int[]: int[0] 表示points的idx，int[1]表示大小
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    for (int i = 0; i < points.length; i++) {
      int[] point = points[i];
      int val = point[0] * point[0] + point[1] * point[1];
      int[] info = new int[]{i, val};
      minHeap.add(info);
    }
    int[][] result = new int[k][2];
    for (int i = 0; i < k; i++) {
      int[] info = minHeap.poll();
      int idx = info[0];
      result[i] = points[idx];
    }
    return result;
  }

  public int lastStoneWeight(int[] stones) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
    for (int stone : stones) {
      maxHeap.offer(stone);
    }
    while (maxHeap.size() > 1) {
      int first = maxHeap.poll();
      int second = maxHeap.poll();
      if (first > second) {
        maxHeap.offer(first - second);
      }
    }
    maxHeap.offer(0);
    return maxHeap.peek();
  }

  public static int findKthLargest(int[] nums, int k) {
    int l = 0;
    int r = nums.length - 1;
    int targetIdx = nums.length - k; //[3,2,1,5,6,4], k = 2 -> 第4个最小元素
    int idx = partition1(nums, l, r);
    while (idx != targetIdx) {
      if (idx < targetIdx) {
        l = idx + 1;
      } else {
        r = idx - 1;
      }
      idx = partition1(nums, l, r);
    }
    return nums[targetIdx];
  }

  private static int partition1(int[] nums, int l, int r) {
    int pivot = nums[r];
    int pointer = l;
    for (int i = l; i < r; i++) {
      if (nums[i] <= pivot) {
        swap(nums, pointer, i);
        ++pointer;
      }
    }
    swap(nums, pointer, r);
    return pointer;
  }

  private static void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  public void sortArray(int[] nums) {
    buildHeap(nums, nums.length);

    for (int i = nums.length - 1; i >= 0; i--) {
      swap(nums, 0, i);
      heapify(nums, i, 0);
    }
  }


  private void buildHeap(int[] array, int length) {
    int lastIdx = length - 1;
    int lastParentIdx = (lastIdx - 1) / 2;
    for (int i = lastParentIdx; i >= 0; i--) {
      heapify(array, length, i);
    }
  }

  private void heapify(int[] array, int length, int idx) {
    if (idx > length) {
      return;
    }
    int leftIdx = idx * 2 + 1;
    int rightIdx = idx * 2 + 2;
    int maxValIdx = idx;
    if (leftIdx < length && array[leftIdx] > array[maxValIdx]) {
      maxValIdx = leftIdx;
    }
    if (rightIdx < length && array[rightIdx] > array[maxValIdx]) {
      maxValIdx = rightIdx;
    }
    if (maxValIdx != idx) {
      swap(array, idx, maxValIdx);
      heapify(array, length, maxValIdx);
    }
  }

  public static void quickSort(int[] array) {
    quickSort(array, 0, array.length - 1);
  }

  public static void quickSort(int[] array, int low, int high) {
    if (low < high) {
      int pointer = partition(array, low, high);
      quickSort(array, low, pointer - 1);
      quickSort(array, pointer + 1, high);
    }
  }

  public static int partition(int[] array, int low, int high) {
    int pivot = array[high];

    int pointer = low;
    for (int i = low; i < high; i++) {
      if (array[i] <= pivot) {
        swap(array, pointer, i);
        pointer++;
      }
    }
    swap(array, high, pointer);
    return pointer;
  }

  public static void mergeSort(int[] data) {
    int[] tmp = new int[data.length];
    mergeSort(data, 0, data.length - 1, tmp);
  }

  private static void mergeSort(int[] data, int l, int r, int[] tmp) {
    if (l < r) {
      int m = (l + r) >> 1;
      mergeSort(data, l, m, tmp);
      mergeSort(data, m + 1, r, tmp);
      merge(data, l, r, m, tmp);
    }
  }

  private static void merge(int[] data, int l, int r, int m, int[] tmp) {
    int p1 = l;
    int p2 = m + 1;
    int p = l;
    while (p1 <= m && p2 <= r) {
      if (data[p1] < data[p2]) {
        tmp[p++] = data[p1++];
      } else {
        tmp[p++] = data[p2++];
      }
    }
    while (p1 <= m) {
      tmp[p++] = data[p1++];
    }
    while (p2 <= r) {
      tmp[p++] = data[p2++];
    }

    for (int i = l; i <= r; i++) {
      data[i] = tmp[i];
    }
  }

  public int[] bubbleSort(int[] nums) {
    for (int i = nums.length - 1; i >= 0; i--) {
      for (int j = 0; j < i; j++) {
        if (nums[j] > nums[j + 1]) {
          swap(nums, j, j + 1);
        }
      }
    }
    return nums;
  }


  public List<String> findWords(char[][] board, String[] words) {
    PrefixTree2 tree = new PrefixTree2();
    for (String word : words) {
      tree.insert(word);
    }

    int m = board.length;
    int n = board[0].length;
    List<String> result = new ArrayList<>();
    int[][] used = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        findWordsDfs(board, used, i, j, m, n, result, tree);
      }
    }
    return result;
  }

  private void findWordsDfs(char[][] board, int[][] used, int i, int j,
      int m, int n, List<String> result, PrefixTree2 tree) {
    if (tree.word != null) {
      result.add(tree.word);
      tree.word = null;
    }

    if (i >= 0 && i < m && j >= 0 && j < n && used[i][j] == 0
        && tree.children[board[i][j] - 'a'] != null) {
      used[i][j] = 1;
      tree = tree.children[board[i][j] - 'a'];
      findWordsDfs(board, used, i - 1, j, m, n, result, tree);
      findWordsDfs(board, used, i + 1, j, m, n, result, tree);
      findWordsDfs(board, used, i, j - 1, m, n, result, tree);
      findWordsDfs(board, used, i, j + 1, m, n, result, tree);
      used[i][j] = 0;
    }
  }

  class PrefixTree2 {

    String word;
    PrefixTree2[] children;

    public PrefixTree2() {
      this.word = null;
      this.children = new PrefixTree2[26];
    }

    void insert(String word) {
      PrefixTree2 root = this;
      char[] chars = word.toCharArray();
      for (char ch : chars) {
        if (root.children[ch - 'a'] == null) {
          root.children[ch - 'a'] = new PrefixTree2();
        }
        root = root.children[ch - 'a'];
      }
      root.word = word;
    }
  }

  class WordDictionary {

    PrefixTree trie;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
      trie = new PrefixTree();
    }

    public void addWord(String word) {
      trie.insert(word);
    }

    public boolean search(String word) {
      return trie.search(word);
    }
  }

  class PrefixTree {

    private PrefixTreeNode root;

    private class PrefixTreeNode {

      PrefixTreeNode[] children;
      boolean isEnd;

      public PrefixTreeNode() {
        this.children = new PrefixTreeNode[26];
        this.isEnd = false;
      }
    }

    public PrefixTree() {
      this.root = new PrefixTreeNode();
    }

    public void insert(String word) {
      char[] chars = word.toCharArray();
      PrefixTreeNode tmp = root;
      for (char ch : chars) {
        int idx = ch - 'a';
        if (tmp.children[idx] == null) {
          tmp.children[idx] = new PrefixTreeNode();
        }
        tmp = tmp.children[idx];
      }
      tmp.isEnd = true;
    }

    public boolean search(String word) {
      char[] chars = word.toCharArray();
      PrefixTreeNode tmp = root;
      return dfs(chars, tmp, 0);
    }

    private boolean dfs(char[] chars, PrefixTreeNode root, int pos) {
//      if (pos > chars.length) {
//        return false;
//      }
      if (pos == chars.length && root.isEnd) {
        return true;
      }
      boolean result = false;
      if (pos < chars.length) {
        char ch = chars[pos];
        if (ch == '.') {
          for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
              result = result || dfs(chars, root.children[i], pos + 1);
            }
          }
        } else if (root.children[ch - 'a'] != null) {
          result = dfs(chars, root.children[ch - 'a'], pos + 1);
        }
      }
      return result;
    }
  }

  public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    Stack<String> stack = new Stack<>();
    partitionDfs(s, 0, result, stack);
    return result;
  }

  private void partitionDfs(String s, int pos, List<List<String>> result, Stack<String> stack) {
    if (pos == s.length()) {
      result.add(new ArrayList<>(stack));
      return;
    }

    for (int i = pos; i < s.length(); i++) {
      if (isPalindrome(s, pos, i)) {
        stack.add(s.substring(pos, i + 1));
        // 需要特别注意，这里的pos值是i + 1。说明已经使用过的字母，就不能再被使用了
        partitionDfs(s, i + 1, result, stack);
        stack.pop();
      }
    }
  }

  private boolean isPalindrome(String str, int l, int r) {
    while (l < r) {
      if (str.charAt(l) != str.charAt(r)) {
        return false;
      }
      ++l;
      --r;
    }
    return true;
  }

  public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<>();
    if (digits.isEmpty()) {
      return res;
    }
    Map<Character, String> digitToChar = new HashMap<>();
    digitToChar.put('2', "abc");
    digitToChar.put('3', "def");
    digitToChar.put('4', "ghi");
    digitToChar.put('5', "jkl");
    digitToChar.put('6', "mno");
    digitToChar.put('7', "qprs");
    digitToChar.put('8', "tuv");
    digitToChar.put('9', "wxyz");
    Stack<Character> stack = new Stack<>();
    letterCombinationsDfs(digits, digitToChar, stack, res, 0);
    return res;
  }

  private void letterCombinationsDfs(String digits, Map<Character, String> digitToChar,
      Stack<Character> stack, List<String> result, int idx) {
    if (stack.size() == digits.length()) {
      StringBuilder res = new StringBuilder();
      for (char c : stack) {
        res.append(c);
      }
      result.add(res.toString());
      return;
    }

    char digit = digits.charAt(idx);
    String str = digitToChar.get(digit);
    for (int i = 0; i < str.length(); i++) {
      stack.push(str.charAt(i));
      letterCombinationsDfs(digits, digitToChar, stack, result, idx + 1);
      stack.pop();
    }
  }


  public boolean exist(char[][] board, String word) {
    int m = board.length;
    int n = board[0].length;
    int[][] used = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (existDfs(board, word, used, 0, i, j, m, n)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean existDfs(char[][] board, String word, int[][] used, int idx, int i, int j, int m,
      int n) {
    if (idx == word.length()) {
      return true;
    }

    boolean result = false;
    if (i >= 0 && i < m && j >= 0 && j < n && used[i][j] == 0 && board[i][j] == word.charAt(idx)) {
      used[i][j] = 1;
      result = existDfs(board, word, used, idx + 1, i - 1, j, m, n)
          || existDfs(board, word, used, idx + 1, i + 1, j, m, n)
          || existDfs(board, word, used, idx + 1, i, j - 1, m, n)
          || existDfs(board, word, used, idx + 1, i, j + 1, m, n);
      used[i][j] = 0;
    }
    return result;
  }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> result = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    combinationSum2Dfs(candidates, target, result, stack, 0, 0);
    return result;
  }

  private static void combinationSum2Dfs(int[] nums, int target, List<List<Integer>> result,
      Stack<Integer> stack, int sum, int start) {
    if (sum == target) {
      result.add(new ArrayList<>(stack));
    }
    for (int i = start; i < nums.length; i++) {
      if (sum <= target) {
        if (i > start && nums[i] == nums[i - 1]) {
          continue;
        }
        stack.push(nums[i]);
        // 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
        combinationSum2Dfs(nums, target, result, stack, sum + nums[i], i + 1);
        stack.pop();
      }
    }
  }

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    permuteDfs(nums, result, stack);
    return result;
  }

  private void permuteDfs(int[] nums, List<List<Integer>> result, Stack<Integer> stack) {
    if (stack.size() == nums.length) {
      result.add(new ArrayList<>(stack));
      return;
    }
    for (int num : nums) {
      if (!stack.contains(num)) {
        stack.push(num);
        permuteDfs(nums, result, stack);
        stack.pop();
      }
    }
  }

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    subsetsWithDupDfs(nums, 0, result, stack);
    return result;
  }

  private void subsetsWithDupDfs(int[] nums, int start, List<List<Integer>> result,
      Stack<Integer> stack) {
    result.add(new ArrayList<>(stack));
    for (int i = start; i < nums.length; i++) {
      if (i > start && nums[i] == nums[i - 1]) {
        continue;
      }
      stack.push(nums[i]);
      subsetsWithDupDfs(nums, i + 1, result, stack);
      stack.pop();
    }
  }


  public static List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    combinationSumDfs(nums, target, result, stack, 0, 0);
    return result;
  }

  private static void combinationSumDfs(int[] nums, int target, List<List<Integer>> result,
      Stack<Integer> stack, int sum, int idx) {
    if (sum >= target || idx >= nums.length) {
      if (sum == target) {
        result.add(new ArrayList<>(stack));
      }
      return;
    }

    // choose
    stack.add(nums[idx]);
    combinationSumDfs(nums, target, result, stack, sum + nums[idx], idx);
    stack.pop();
    // not choose
    combinationSumDfs(nums, target, result, stack, sum, idx + 1);
  }

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    subsetsDfs(nums, 0, result, stack);
    return result;
  }

  private void subsetsDfs(int[] nums, int idx, List<List<Integer>> result, Stack<Integer> stack) {
    if (idx >= nums.length) {
      result.add(new ArrayList<>(stack));
      return;
    }

    // 选择当前数字
    stack.add(nums[idx]);
    subsetsDfs(nums, idx + 1, result, stack);

    // 不选择当前数字
    stack.pop();
    subsetsDfs(nums, idx + 1, result, stack);
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0 || inorder.length == 0) {
      return null;
    }
    int val = preorder[0];
    int midIdx = -1;
    for (int i = 0; i < inorder.length; i++) {
      if (inorder[i] == val) {
        midIdx = i;
        break;
      }
    }

    TreeNode root = new TreeNode(val);
    int[] leftPreorder = Arrays.copyOfRange(preorder, 1, midIdx + 1);
    int[] rightPreorder = Arrays.copyOfRange(preorder, midIdx + 1, preorder.length);

    int[] leftInorder = Arrays.copyOfRange(inorder, 0, midIdx);
    int[] rightInorder = Arrays.copyOfRange(inorder, midIdx + 1, inorder.length);
    root.left = buildTree(leftPreorder, leftInorder);
    root.right = buildTree(rightPreorder, rightInorder);
    return root;
  }

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

  public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
      if (root == null) {
        return "null,";
      }
      String res = root.val + ",";
      res += serialize(root.left);
      res += serialize(root.right);
      return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      String[] split = data.split(",");
      Queue<String> queue = new LinkedList<>(Arrays.asList(split));

      return build(queue);
    }

    private TreeNode build(Queue<String> queue) {
      if (queue.isEmpty()) {
        return null;
      }
      String str = queue.poll();
      if ("null".equals(str)) {
        return null;
      }
      int val = Integer.parseInt(str);
      TreeNode node = new TreeNode(val);
      node.left = build(queue);
      node.right = build(queue);
      return node;
    }
  }


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

  public int goodNodes(TreeNode root) {
    return goodNodesDfs(root, root.val);
  }

  private int goodNodesDfs(TreeNode root, int maxVal) {
    if (root == null) {
      return 0;
    }
    int res = (root.val >= maxVal) ? 1 : 0;
    maxVal = Math.max(maxVal, root.val);
    res += goodNodesDfs(root.left, maxVal);
    res += goodNodesDfs(root.right, maxVal);
    return res;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }
    if (root == p || root == q) {
      return root;
    }

    TreeNode leftAncestor = lowestCommonAncestor(root.left, p, q);
    TreeNode rightAncestor = lowestCommonAncestor(root.right, p, q);
    if (leftAncestor != null && rightAncestor != null) {
      return root;
    }
    if (leftAncestor != null) {
      return leftAncestor;
    }
    return rightAncestor;
  }

  private static int val;
  private static int cnt;

  public static int kthSmallest(TreeNode root, int k) {
    if (root == null) {
      return -1;
    }
    cnt = 0;
    inorderTraversalDfs(root, k);
    return val;
  }

  private static void inorderTraversalDfs(TreeNode root, int k) {
    if (root == null) {
      return;
    }

    inorderTraversalDfs(root.left, k);
    ++cnt;
    if (k == cnt) {
      val = root.val;
      return;
    }
    inorderTraversalDfs(root.right, k);
  }

  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    while (root != null || !stack.isEmpty()) {
      if (root != null) {
        stack.add(root);
        root = root.left;
      } else if (!stack.isEmpty()) {
        TreeNode tmp = stack.pop();
        result.add(tmp.val);
        root = tmp.right;
      }
    }
    return result;
  }

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      for (int i = 0; i < queueSize; i++) {
        TreeNode node = queue.poll();
        if (i == 0) {
          result.add(node.val);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
        if (node.left != null) {
          queue.add(node.left);
        }
      }
    }
    return result;
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      List<Integer> subResult = new ArrayList<>();
      for (int i = 0; i < queueSize; i++) {
        TreeNode node = queue.poll();
        subResult.add(node.val);
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }
      result.add(subResult);
    }
    return result;
  }

  private static class BalancedInfo {

    int depth;
    boolean balanced;

    public BalancedInfo(int depth, boolean balanced) {
      this.depth = depth;
      this.balanced = balanced;
    }
  }

  public boolean isBalanced(TreeNode root) {
    BalancedInfo info = balancedDfs(root);
    return info.balanced;
  }

  private BalancedInfo balancedDfs(TreeNode root) {
    if (root == null) {
      return new BalancedInfo(0, true);
    }
    BalancedInfo leftInfo = balancedDfs(root.left);
    BalancedInfo rightInfo = balancedDfs(root.right);
    int curDepth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
    if (!leftInfo.balanced || !rightInfo.balanced) {
      return new BalancedInfo(curDepth, false);
    }
    boolean curBalanced = Math.abs(leftInfo.depth - rightInfo.depth) <= 1;

    return new BalancedInfo(curDepth, curBalanced);
  }

  private static class DiameterInfo {

    int depth;
    int diameter;

    public DiameterInfo(int depth, int diameter) {
      this.depth = depth;
      this.diameter = diameter;
    }
  }

  public int diameterOfBinaryTree(TreeNode root) {
    DiameterInfo info = dfs(root);
    return info.diameter;
  }

  private DiameterInfo dfs(TreeNode root) {
    if (root == null) {
      return new DiameterInfo(0, 0);
    }
    DiameterInfo leftInfo = dfs(root.left);
    DiameterInfo rightInfo = dfs(root.right);
    int curDepth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
    // 不经过root
    int curDiameter = Math.max(leftInfo.diameter, rightInfo.diameter);
    // 经过root
    curDiameter = Math.max(curDiameter, leftInfo.depth + rightInfo.depth);

    return new DiameterInfo(curDepth, curDiameter);
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

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    int level = 0;
    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      for (int i = 0; i < queueSize; i++) {
        TreeNode node = queue.poll();
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }
      ++level;
    }
    return level;
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

  public int[] maxSlidingWindow(int[] nums, int k) {

    int[] result = new int[nums.length - k + 1];
    Deque<Integer> deque = new LinkedList<>();
    for (int i = 0; i < nums.length; i++) {
      while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
        deque.removeLast();
      }
      deque.addLast(i);

      if (!deque.isEmpty() && i >= k + deque.peekFirst()) {
        deque.removeFirst();
      }

      if (i - k + 1 >= 0) {
        result[i - k + 1] = nums[deque.peekFirst()];
      }
    }

    return result;
  }

  public String minWindow(String s, String t) {
    String result = "";
    int minLength = Integer.MAX_VALUE;
    Map<Character, Integer> needs = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      char ch = t.charAt(i);
      needs.put(ch, needs.getOrDefault(ch, 0) + 1);
    }
    Map<Character, Integer> window = new HashMap<>();
    int left = 0;
    int right = 0;
    int match = 0;
    while (right < s.length()) {
      char r_char = s.charAt(right);
      if (needs.containsKey(r_char)) {
        window.put(r_char, window.getOrDefault(r_char, 0) + 1);
        if (window.get(r_char).equals(needs.get(r_char))) {
          ++match;
        }
      }
      ++right;

      while (match == needs.size()) {
        if (right - left < minLength) {
          minLength = right - left;
          result = s.substring(left, right);
        }

        char l_char = s.charAt(left);
        if (needs.containsKey(l_char)) {
          window.put(l_char, window.getOrDefault(l_char, 0) - 1);
          if (window.get(l_char) < needs.get(l_char)) {
            --match;
          }
        }
        ++left;
      }
    }
    return result;
  }

  public boolean checkInclusion(String s1, String s2) {
    Map<Character, Integer> needs = new HashMap<>();
    for (int i = 0; i < s1.length(); i++) {
      char ch = s1.charAt(i);
      needs.put(ch, needs.getOrDefault(ch, 0) + 1);
    }

    Map<Character, Integer> window = new HashMap<>();
    int left = 0;
    int right = 0;
    int match = 0;
    while (right < s2.length()) {
      char r_char = s2.charAt(right);
      if (needs.containsKey(r_char)) {
        window.put(r_char, window.getOrDefault(r_char, 0) + 1);
        if (window.get(r_char).equals(needs.get(r_char))) {
          ++match;
        }
      }
      ++right;

      while (match == needs.size()) {
        if (right - left == s1.length()) {
          return true;
        }
        char l_char = s2.charAt(left);
        if (needs.containsKey(l_char)) {
          window.put(l_char, window.getOrDefault(l_char, 0) - 1);
          if (window.get(l_char) < needs.get(l_char)) {
            --match;
          }
        }
        ++left;
      }
    }
    return false;
  }

  public static int characterReplacement(String s, int k) {
    int result = 0;
    int left = 0;
    int right = 0;
    int historyMaxCnt = 0;
    Map<Character, Integer> window = new HashMap<>();
    while (right < s.length()) {
      char r_char = s.charAt(right);
      int r_cnt = window.getOrDefault(r_char, 0) + 1;
      window.put(r_char, r_cnt);
      historyMaxCnt = Math.max(historyMaxCnt, r_cnt);
      ++right;

      // 为什么只需要维护historyMaxCnt就可以了？因为我希望right - left尽可能大，也就是希望historyMaxCnt尽可能大。因此只需要记录历史上的historyMaxCnt即可
      if (right - left <= historyMaxCnt + k) {
        result = Math.max(result, right - left);
      } else {
        char l_char = s.charAt(left);
        window.put(l_char, window.get(l_char) - 1);
        ++left;
      }
    }
    return result;
  }

  public int lengthOfLongestSubstring(String s) {
    int result = 0;
    int left = 0;
    int right = 0;
    Map<Character, Integer> window = new HashMap<>();
    while (right < s.length()) {
      char r_char = s.charAt(right);
      window.put(r_char, window.getOrDefault(r_char, 0) + 1);
      ++right;

      while (window.get(r_char) > 1) {
        char l_char = s.charAt(left);
        window.put(l_char, window.get(l_char) - 1);
        ++left;
      }
      result = Math.max(result, right - left);
    }
    return result;
  }

  private static class LruNode {

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

    private Map<Integer, LruNode> cache;
    private LruNode head;
    private LruNode tail;
    private int capacity;

    public LRUCache(int capacity) {
      this.cache = new HashMap<>();
      this.head = new LruNode(0, 0);
      this.tail = new LruNode(0, 0);
      this.head.next = this.tail;
      this.tail.prev = this.head;
      this.capacity = capacity;
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

      if (cache.size() > capacity) {
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
      node.prev = prev;
      node.next = this.tail;
      this.tail.prev = node;
    }
  }


  public int findDuplicate(int[] nums) {
    int slow = 0;
    int fast = 0;
    slow = nums[slow];
    fast = nums[nums[fast]];
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[nums[fast]];
    }

    int slow2 = 0;
    while (slow != slow2) {
      slow = nums[slow];
      slow2 = nums[slow2];
    }
    return slow;
  }

  public ListNode detectCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (true) {
      if (fast == null || fast.next == null) {
        return null;
      }
      fast = fast.next.next;
      slow = slow.next;

      if (fast == slow) {
        break;
      }
    }
    ListNode slow2 = head;
    while (slow2 != slow) {
      slow = slow.next;
      slow2 = slow2.next;
    }
    return slow;
  }

  public static ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    ListNode left = dummy;
    ListNode right = left;
    while (true) {
      int countDown = k;
      while (right != null && countDown > 0) {
        right = right.next;
        countDown--;
      }
      if (right == null) {
        break;
      }
      ListNode rightNext = right.next;
      ListNode leftNext = left.next;
      right.next = null;
      left.next = reverseList(leftNext);
      leftNext.next = rightNext;
      left = leftNext;
      right = left;
    }
    return dummy.next;
  }

  public static ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode next = head.next;
    head.next = null;
    ListNode reversedNode = reverseList(next);
    next.next = head;
    return reversedNode;
  }

  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    return merge(lists, 0, lists.length - 1);
  }

  public ListNode merge(ListNode[] lists, int left, int right) {
    if (left == right) {
      return lists[left];
    }
    int mid = left + (right - left) / 2;
    ListNode l = merge(lists, left, mid);
    ListNode r = merge(lists, mid + 1, right);
    return mergeTwoLists(l, r);
  }

  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;
    while (list1 != null && list2 != null) {
      if (list1.val < list2.val) {
        cur.next = list1;
        list1 = list1.next;
      } else {
        cur.next = list2;
        list2 = list2.next;
      }

      cur = cur.next;
    }
    if (list1 != null) {
      cur.next = list1;
    }
    if (list2 != null) {
      cur.next = list2;
    }

    return dummy.next;
  }

  class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }

  public Node copyRandomList(Node head) {
    Map<Node, Node> map = new HashMap<>();
    Node cur = head;
    while (cur != null) {
      Node copy = new Node(cur.val);
      map.put(cur, copy);
      cur = cur.next;
    }

    cur = head;
    while (cur != null) {
      Node copy = map.get(cur);
      copy.next = map.get(cur.next);
      copy.random = map.get(cur.random);
      cur = cur.next;
    }

    return map.get(head);
  }

  public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    }
    return false;
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;

    int carry = 0;
    while (l1 != null || l2 != null) {
      int val1 = l1 == null ? 0 : l1.val;
      int val2 = l2 == null ? 0 : l2.val;
      int sum = val1 + val2 + carry;
      cur.next = new ListNode(sum % 10);
      carry = sum / 10;

      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null) {
        l2 = l2.next;
      }
      cur = cur.next;
    }
    if (carry > 0) {
      cur.next = new ListNode(carry);
    }
    return dummy.next;
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    ListNode left = dummy;
    ListNode right = head;
    while (n > 0) {
      right = right.next;
      n--;
    }
    while (right != null) {
      right = right.next;
      left = left.next;
    }
    left.next = left.next.next;

    return head;
  }

  public void reorderList(ListNode head) {
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    // 旋转second
    ListNode second = slow.next;
    slow.next = null;
    ListNode prev = null;
    while (second != null) {
      ListNode tmp = second.next;
      second.next = prev;
      prev = second;
      second = tmp;
    }

    second = prev;
    ListNode first = head;
    while (second != null) {
      ListNode firstNext = first.next;
      ListNode secondNext = second.next;
      first.next = second;
      second.next = firstNext;
      first = firstNext;
      second = secondNext;
    }
  }


  public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }
    if (list2 == null) {
      return list1;
    }

    if (list1.val < list2.val) {
      list1.next = mergeTwoLists2(list1.next, list2);
      return list1;
    } else {
      list2.next = mergeTwoLists2(list1, list2.next);
      return list2;
    }
  }


  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  class TimeMap {

    private Map<String, List<Pair<Integer, String>>> keyStore;

    public TimeMap() {
      keyStore = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
      List<Pair<Integer, String>> pairList = keyStore.getOrDefault(key, new ArrayList<>());
      pairList.add(new Pair<>(timestamp, value));
      keyStore.put(key, pairList);
    }

    public String get(String key, int timestamp) {
      List<Pair<Integer, String>> pairList = keyStore.get(key);
      int l = 0;
      int r = pairList.size() - 1;
      while (l <= r) {
        int c = l + (r - l) / 2;
        if (pairList.get(c).getKey() <= timestamp) {
          l = c + 1; // l 左边时间戳 必然满足 <= timestamp
        } else {
          r = c - 1;
        }
      }
      if (r == -1) {
        return "";
      }
      return pairList.get(r).getValue();
    }

    private static class Pair<K, V> {

      private final K key;
      private final V value;

      public Pair(K key, V value) {
        this.key = key;
        this.value = value;
      }

      public K getKey() {
        return key;
      }

      public V getValue() {
        return value;
      }
    }
  }

  public int search(int[] nums, int target) {
    int n = nums.length;
    int l = 0;
    int r = n - 1;
    while (l <= r) {
      int c = l + (r - l) / 2;
      if (nums[c] == target) {
        return c;
      }

      if (nums[c] >= nums[0]) {
        // target < nums[0] 说明target在第二个区间
        if (nums[c] < target || target < nums[0]) {
          l = c + 1;
        } else {
          r = c - 1;
        }
      } else {
        if (nums[c] > target || target >= nums[0]) {
          r = c - 1;
        } else {
          l = c + 1;
        }
      }
    }
    return -1;
  }

  public static int findMin(int[] nums) {
    int l = 0;
    int r = nums.length - 1;
    if (nums[l] <= nums[r]) {
      return nums[l];
    }
    while (l <= r) {
      int c = (l + r) / 2;
      if (nums[c] >= nums[0]) {
        l = c + 1;
      } else {
        r = c - 1;
      }
    }
    return nums[l];
  }

  public static int minEatingSpeed(int[] piles, int h) {
    int max = 0;
    for (int pile : piles) {
      max = Math.max(max, pile);
    }
    // max speed is max; min speed is 1
    int l = 1;
    int r = max;

    while (l <= r) {
      int c = l + (r - l) / 2;
      if (calculateTime(piles, c) <= h) {
        r = c - 1; // 更新后，r右边必然满足时间 <= h
      } else {
        l = c + 1; // 更新后，l左边必然满足时间 > h
      }
    }
    return l;
  }

  private static int calculateTime(int[] piles, int speed) {
    int sumTime = 0;
    for (int pile : piles) {
      if (pile % speed > 0) {
        sumTime += (pile / speed + 1);
      } else {
        sumTime += (pile / speed);
      }
    }
    return sumTime;
  }

  public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length; // m rows
    int n = matrix[0].length; // n cols

    int row = m - 1;
    int col = 0;
    while (row >= 0 && col < n) {
      if (matrix[row][col] == target) {
        return true;
      } else if (matrix[row][col] > target) {
        row--;
      } else {
        col++;
      }
    }
    return false;
  }

//  public int search(int[] nums, int target) {
//    int l = 0;
//    int r = nums.length - 1;
//    while (l <= r) {
//      int c = l + (r - l) / 2;
//      if (nums[c] == target) {
//        return c;
//      } else if (nums[c] > target) {
//        r = c - 1;
//      } else {
//        l = c + 1;
//      }
//    }
//    return -1;
//  }

  public static int trap(int[] heights) {
    // 看一下和carFleet的联系是什么？
    int len = heights.length;
    int[] leftMaxHeight = new int[len];
    int[] rightMaxHeight = new int[len];

    for (int i = 0; i < len; i++) {
      if (i == 0) {
        leftMaxHeight[i] = heights[i];
      } else {
        leftMaxHeight[i] = Math.max(heights[i], leftMaxHeight[i - 1]);
      }
    }

    for (int i = len - 1; i >= 0; i--) {
      if (i == len - 1) {
        rightMaxHeight[i] = heights[i];
      } else {
        rightMaxHeight[i] = Math.max(heights[i], rightMaxHeight[i + 1]);
      }
    }

    int result = 0;
    for (int i = 0; i < len; i++) {
      int curHeight = heights[i];
      result += (Math.min(leftMaxHeight[i], rightMaxHeight[i]) - curHeight);
    }
    return result;
  }


  public static int maxArea1(int[] heights) {
    int l = 0;
    int r = heights.length - 1;
    int maxArea = 0;
    while (l < r) {
      int minHeight = Math.min(heights[l], heights[r]);
      int area = (r - l) * minHeight;
      maxArea = Math.max(maxArea, area);
      if (heights[l] < heights[r]) {
        ++l;
      } else {
        --r;
      }
    }
    return maxArea;
  }

  public static int maxArea(int[] heights) {
    // 两个stack，分别找左右两边，距离当面位置 最远的 >= 当面高度的位置 -> 分别找左右两边，距离当面位置 最近的 < 当面高度的位置
    int[] rightMostCloseIdx = new int[heights.length];
    int[] leftMostCloseIdx = new int[heights.length];
    for (int i = 0; i < heights.length; i++) {
      leftMostCloseIdx[i] = -1;
      rightMostCloseIdx[i] = heights.length;
    }
    Stack<Integer> stack1 = new Stack<>();
    for (int i = 0; i < heights.length; i++) {
      int h = heights[i];
      while (!stack1.isEmpty() && h < heights[stack1.peek()]) {
        int idx = stack1.pop();
        rightMostCloseIdx[idx] = i;
      }
      stack1.push(i);
    }

    Stack<Integer> stack2 = new Stack<>();
    for (int i = heights.length - 1; i >= 0; i--) {
      int h = heights[i];
      while (!stack2.isEmpty() && h < heights[stack2.peek()]) {
        int idx = stack2.pop();
        leftMostCloseIdx[idx] = i;
      }
      stack2.push(i);
    }

    int result = 0;
    for (int i = 0; i < heights.length; i++) {
      int leftIdx = leftMostCloseIdx[i] + 1;
      int rightIdx = rightMostCloseIdx[i] - 1;
      int minH = Math.min(heights[leftIdx], heights[rightIdx]);
      result = Math.max(result, minH * (rightIdx - leftIdx));
    }

    return result;
  }

  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    int len = nums.length;
    for (int i = 0; i < len - 2; i++) {
      if (nums[i] > 0) {
        break;
      }
      if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
        break;
      }
      //去重
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int l = i + 1;
      int r = len - 1;
      while (l < r) {
        int sum = nums[i] + nums[l] + nums[r];
        if (sum == 0) {
          List<Integer> subRes = new ArrayList<>();
          subRes.add(nums[i]);
          subRes.add(nums[l]);
          subRes.add(nums[r]);
          result.add(subRes);
          while (l < r && nums[r] == nums[--r])
            ;
          while (l < r && nums[l] == nums[++l])
            ;
        } else if (sum > 0) {
          while (l < r && nums[r] == nums[--r])
            ;
        } else {
          while (l < r && nums[l] == nums[++l])
            ;
        }
      }
    }
    return result;
  }

  public int[] twoSum(int[] numbers, int target) {
    int l = 0;
    int r = numbers.length - 1;
    while (l < r) {
      int sum = numbers[l] + numbers[r];
      if (sum == target) {
        return new int[]{l + 1, r + 1};
      } else if (sum > target) {
        r--;
      } else {
        l++;
      }
    }
    return new int[]{};
  }

  public static boolean isPalindrome(String s) {
    int i = 0;
    int j = s.length() - 1;
    while (i < j) {
      while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
        i++;
      }
      while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
        j--;
      }
      if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  public static int carFleet(int target, int[] position, int[] speed) {
    int length = position.length;
    double[][] cars = new double[length][2];
    for (int i = 0; i < length; i++) {
      cars[i][0] = position[i];
      cars[i][1] = speed[i];
    }
    // 按照position由大到小排序
    Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));
    int result = 1;
    double curMax = (target - cars[0][0]) / cars[0][1];

    for (int i = 1; i < length; i++) {
      double curTime = (target - cars[i][0]) / cars[i][1];
      if (curTime > curMax) {
        ++result;
        curMax = curTime;
      } else {
        curMax = Math.max(curMax, curTime);
      }
    }

    return result;
  }

  public static int carFleet1(int target, int[] position, int[] speed) {
    int length = position.length;
    double[][] cars = new double[length][2];
    for (int i = 0; i < length; i++) {
      cars[i][0] = position[i];
      cars[i][1] = speed[i];
    }
    // 按照position由大到小排序
    Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));
    int result = 1;
    double[] timeToReach = new double[length];
    timeToReach[0] = (target - cars[0][0]) / cars[0][1];
    double curMax = timeToReach[0];

    for (int i = 1; i < length; i++) {
      timeToReach[i] = (target - cars[i][0]) / cars[i][1];
      if (timeToReach[i] > curMax) {
        ++result;
        curMax = timeToReach[i];
      } else {
        curMax = Math.max(curMax, timeToReach[i]);
      }
    }

    return result;
  }


  private static List<String> result;

  public static List<String> generateParenthesis(int n) {
    result = new ArrayList<>();
    String string = "";
    dfs(string, n, n);
    return result;
  }

  private static void dfs(String curString, int left, int right) {
    if (left == 0 && right == 0) {
      result.add(curString);
      return;
    }
    if (left > right) {
      return;
    }
    if (left > 0) {
      dfs(curString + "(", left - 1, right);
    }
    if (right > 0) {
      dfs(curString + ")", left, right - 1);
    }
  }

  public static int largestRectangleArea(int[] heights) {
    // 两个stack，分别找左右两边，距离当面位置 最远的 >= 当面高度的位置 -> 分别找左右两边，距离当面位置 最近的 < 当面高度的位置
    int[] rightMostCloseIdx = new int[heights.length];
    int[] leftMostCloseIdx = new int[heights.length];
    for (int i = 0; i < heights.length; i++) {
      leftMostCloseIdx[i] = -1;
      rightMostCloseIdx[i] = heights.length;
    }
    Stack<Integer> stack1 = new Stack<>();
    for (int i = 0; i < heights.length; i++) {
      int h = heights[i];
      while (!stack1.isEmpty() && h < heights[stack1.peek()]) {
        int idx = stack1.pop();
        rightMostCloseIdx[idx] = i;
      }
      stack1.push(i);
    }

    Stack<Integer> stack2 = new Stack<>();
    for (int i = heights.length - 1; i >= 0; i--) {
      int h = heights[i];
      while (!stack2.isEmpty() && h < heights[stack2.peek()]) {
        int idx = stack2.pop();
        leftMostCloseIdx[idx] = i;
      }
      stack2.push(i);
    }

    int result = 0;
    for (int i = 0; i < heights.length; i++) {
      int h = heights[i];
      result = Math.max(result, h * (rightMostCloseIdx[i] - leftMostCloseIdx[i] - 1));
    }

    return result;
  }

  public int[] dailyTemperatures(int[] temperatures) {
    int[] result = new int[temperatures.length];
    Stack<Integer> stack = new Stack<>(); // idx stack
    for (int i = 0; i < temperatures.length; i++) {
      int temp = temperatures[i];
      while (!stack.isEmpty() && temp > temperatures[stack.peek()]) {
        int index = stack.pop();
        result[index] = i - index;
      }
      stack.add(i);
    }
    return result;
  }

  public int evalRPN(String[] tokens) {
    Set<String> set = new HashSet<>();
    set.add("+");
    set.add("-");
    set.add("*");
    set.add("/");

    Stack<Integer> stack = new Stack<>();
    for (String token : tokens) {
      if (set.contains(token)) {
        int num1 = stack.pop();
        int num2 = stack.pop();
        if (token.equals("+")) {
          stack.add(num2 + num1);
        } else if (token.equals("-")) {
          stack.add(num2 - num1);
        } else if (token.equals("*")) {
          stack.add(num2 * num1);
        } else if (token.equals("/")) {
          stack.add(num2 / num1);
        }
      } else {
        stack.add(Integer.parseInt(token));
      }
    }
    return stack.pop();
  }

  class MinStack {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MinStack() {
      stack1 = new Stack<>();
      stack2 = new Stack<>();
    }

    public void push(int val) {
      stack1.add(val);
      if (stack2.isEmpty() || val <= stack2.peek()) {
        stack2.add(val);
      }
    }

    public void pop() {
      if (stack1.isEmpty()) {
        return;
      }
      int val = stack1.pop();
      if (!stack2.isEmpty() && val == stack2.peek()) {
        stack2.pop();
      }
    }

    public int top() {
      if (stack1.isEmpty()) {
        return -1;
      }
      return stack1.peek();
    }

    public int getMin() {
      if (stack2.isEmpty()) {
        return -1;
      }
      return stack2.peek();
    }
  }

  public boolean isValid(String s) {
    if (s.length() % 2 == 1) {
      return false;
    }
    HashMap<Character, Character> hashMap = new HashMap<>();
    hashMap.put(')', '(');
    hashMap.put('}', '{');
    hashMap.put(']', '[');

    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (hashMap.containsKey(ch)) {
        if (!stack.isEmpty() && stack.peek() == hashMap.get(ch)) {
          stack.pop();
        } else {
          return false;
        }
      } else {
        stack.add(ch);
      }
    }
    return stack.isEmpty();
  }

  public boolean isValidSudoku(char[][] board) {
    boolean[][] row = new boolean[9][9];
    boolean[][] col = new boolean[9][9];
    boolean[][] mat = new boolean[9][9];

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] != '.') {
          int blockIndex = i / 3 * 3 + j / 3;
          int num = board[i][j] - '1';
          if (row[i][num] || col[j][num] || mat[blockIndex][num]) {
            return false;
          }
          row[i][num] = col[j][num] = mat[blockIndex][num] = true;
        }
      }
    }
    return true;
  }

  public int longestConsecutive(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }

    int result = 0;
    for (int num : nums) {
      // sequence start point
      if (!set.contains(num - 1)) {
        int curNum = num;
        while (true) {
          if (set.contains(curNum + 1)) {
            curNum = curNum + 1;
          } else {
            break;
          }
        }
        result = Math.max(result, curNum - num + 1);
      }
    }
    return result;
  }

  public String encode(List<String> strs) {
    StringBuilder stringBuilder = new StringBuilder();
    for (String str : strs) {
      stringBuilder.append(str.length()).append("#").append(str);
    }
    return stringBuilder.toString();
  }

  public List<String> decode(String str) {
    List<String> result = new ArrayList<>();
    int i = 0;
    while (i < str.length()) {
      int j = i;
      while (j < str.length() && str.charAt(j) != '#') {
        j++;
      }
      int length = Integer.parseInt(str.substring(i, j));
      i = j + 1 + length;
      String subStr = str.substring(j + 1, i);
      result.add(subStr);
    }
    return result;
  }

  public int[] productExceptSelf(int[] nums) {
    int[] left = new int[nums.length];
    left[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      left[i] = nums[i] * left[i - 1];
    }

    int[] right = new int[nums.length];
    right[nums.length - 1] = nums[nums.length - 1];
    for (int i = nums.length - 2; i >= 0; i--) {
      right[i] = nums[i] * right[i + 1];
    }

    int[] result = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      int leftIdx = i - 1;
      int rightIdx = i + 1;
      int leftProduct = leftIdx < 0 ? 1 : left[leftIdx];
      int rightProduct = rightIdx >= nums.length ? 1 : right[rightIdx];
      result[i] = leftProduct * rightProduct;
    }
    return result;
  }

//  [1,2,4,6]
//  [1,2,8,48]
//  [48,48,24,6]
//  [48,24,12,8]

  public int[] topKFrequent(int[] nums, int k) {
    if (nums == null) {
      return new int[]{};
    }

    Map<Integer, Integer> cnt = new HashMap<>();
    for (int num : nums) {
      cnt.put(num, cnt.getOrDefault(num, 0) + 1);
    }

    List<Integer>[] freq = new List[nums.length + 1];
    for (int i = 0; i < freq.length; i++) {
      freq[i] = new ArrayList<>();
    }
    for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
      freq[entry.getValue()].add(entry.getKey());
    }
    int[] result = new int[k];
    int idx = 0;
    outer:
    for (int i = nums.length; i >= 0; i--) {
      List<Integer> list = freq[i];
      for (Integer integer : list) {
        result[idx] = integer;
        idx++;
        if (idx == k) {
          break outer;
        }
      }
    }
    return result;
  }

  public List<List<String>> groupAnagrams(String[] strs) {

    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      int[] store = new int[26];
      for (int i = 0; i < str.length(); i++) {
        store[str.charAt(i) - 'a']++;
      }
      String storeStr = Arrays.toString(store);

      List<String> list = map.getOrDefault(storeStr, new ArrayList<>());
      list.add(str);
      map.put(storeStr, list);
    }
    return new ArrayList<>(map.values());
  }

  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    int[] store = new int[26];
    for (int i = 0; i < s.length(); i++) {
      store[s.charAt(i) - 'a']++;
      store[t.charAt(i) - 'a']--;
    }

    for (int i = 0; i < 26; i++) {
      if (store[i] != 0) {
        return false;
      }
    }
    return true;

  }


}
