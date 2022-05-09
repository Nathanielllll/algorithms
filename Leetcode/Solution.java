import java.util.*;

class Solution {

//    25. K 个一组翻转链表
//    42. 接雨水
//    23. 合并K个排序链表
//    41. 缺失的第一个正数
//    124. 二叉树中的最大路径和
//    76. 最小覆盖子串
//    32. 最长有效括号
//    72. 编辑距离
//    4. 寻找两个正序数组的中位数
//    239. 滑动窗口最大值


    public int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        int idx = 0;

        outer:
        while (idx < length) {
            int cur_gas = 0;
            for (int i = idx; i <= idx + length; i++) {
                cur_gas += gas[i % length] - cost[i % length];
                if (cur_gas < 0) {
                    idx = i;
                    continue outer;
                } else if (i == idx + length) {
                    return idx % length;
                }
            }
            ++idx;
        }
        return -1;
    }

    public static int shortestSubarray(int[] nums, int k) {
        int length = nums.length;
        int[] preSum = new int[length];
        preSum[0] = nums[0];
        for (int i = 1; i < length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }

        int result = Integer.MAX_VALUE;

        Stack<Integer> stack = new Stack<>();// 单调递增栈
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && preSum[stack.peek()] > preSum[i]) {
                int curIdx = stack.pop();
                if (!stack.isEmpty()) {
                    int leftIdx = stack.peek();
                    if (preSum[curIdx] - preSum[leftIdx] >= k) {
                        result = Math.min(result, curIdx - leftIdx);
                    }
                } else {
                    if (preSum[curIdx] >= k) {
                        result = Math.min(result, curIdx + 1);
                    }
                }
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int curIdx = stack.pop();
            if (!stack.isEmpty()) {
                int leftIdx = stack.peek();
                if (preSum[curIdx] - preSum[leftIdx] >= k) {
                    result = Math.min(result, curIdx - leftIdx);
                }
            } else {
                if (preSum[curIdx] >= k) {
                    result = Math.min(result, curIdx + 1);
                }
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public int[] nextGreaterElements(int[] nums) {
        int length = nums.length;
        int[] cycleNums = new int[length * 2];
        for (int i = 0; i < length * 2; i++) {
            cycleNums[i] = nums[i % length];
        }

        int[] tempResult = new int[length * 2];
        Arrays.fill(tempResult, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * length; i++) {
            while (!stack.isEmpty() && cycleNums[stack.peek()] < cycleNums[i]) {
                tempResult[stack.pop()] = cycleNums[i];
            }
            stack.push(i);
        }
        int[] result = new int[nums.length];
        System.arraycopy(tempResult, 0, result, 0, nums.length);
        return result;
    }


    public int findNthDigit(int n) {
        int len = 1;
        while (len * 9 * Math.pow(10, len - 1) < n) {
            n -= len * 9 * Math.pow(10, len - 1);
            len++;
        }
        long s = (long) Math.pow(10, len - 1);
        s += n / len - 1;
        n -= len * (n / len);
        return n == 0 ? (int) (s % 10) : (int) ((s + 1) / Math.pow(10, len - n) % 10);
    }


    public static int[] getMaxMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] result = new int[4];
        int resultMaxSum = Integer.MIN_VALUE;

        int[][] preSum = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }


        for (int i = 0; i < rows; i++) {
            for (int j = i; j < rows; j++) {

                // 计算出当前i~j的每列的和
                int[] cur = new int[cols];
                for (int k = 0; k < cols; k++) {
                    cur[k] = preSum[j + 1][k + 1] - preSum[i][k + 1] - preSum[j + 1][k] + preSum[i][k];
                }

                int left = 0;
                int right = 0;

                int maxSum = cur[0];
                int curSum = cur[0];
                for (int k = 1; k < cur.length; k++) {
                    if (curSum < 0) {
                        curSum = cur[k];
                        left = right = k;
                    } else {
                        curSum += cur[k];
                        right = k;
                    }
                    if (curSum > maxSum) {
                        maxSum = curSum;
                        result[0] = i;
                        result[1] = left;
                        result[2] = j;
                        result[3] = right;
                    }
                }
            }
        }
        return result;
    }

    public int[] maxSubArray(int[] nums) {
        int[] result = new int[2];
        int left = 0;
        int right = 0;
        int maxSum = nums[0];
        int curSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (curSum < 0) {
                curSum = nums[i];
                left = right = i;
            } else {
                curSum += nums[i];
                right = i;
            }
            if (curSum > maxSum) {
                maxSum = curSum;
                result[0] = left;
                result[1] = right;
            }
        }
        return result;
    }


    class NumMatrix {
        int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            preSum = new int[rows + 1][cols + 1];
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];
        }
    }


    public static int strStr(String string, String pattern) {
        if (pattern == null || pattern.length() < 1) {
            return 0;
        }

        if (string == null || string.length() < 1 || string.length() < pattern.length()) {
            return -1;
        }

        int stringLen = string.length();
        int patternLen = pattern.length();

        Map<Character, Integer> shift = new HashMap<>();
        for (int i = 0; i < patternLen; i++) {
            shift.put(pattern.charAt(i), patternLen - i);
        }

        int startIdx = 0;
        while (startIdx + patternLen <= stringLen) {
            int nextIdx = startIdx + patternLen;
            String segment = string.substring(startIdx, nextIdx);
            if (pattern.equals(segment)) {
                return startIdx;
            } else {
                if (startIdx >= stringLen - patternLen) {
                    return -1;
                }

                if (shift.containsKey(string.charAt(nextIdx))) {
                    startIdx += shift.get(string.charAt(nextIdx));
                } else {
                    startIdx += patternLen + 1;
                }
            }
        }
        return -1;
    }

    public static int myAtoi(String str) {
        boolean isPositive = false;
        boolean isBeginning = false;
        int idx = 0;
        int length = str.length();
        int result = 0;

        while (idx < length) {
            char ch = str.charAt(idx);
            if (!isBeginning) {
                if (ch == '+') {
                    isPositive = true;
                    isBeginning = true;
                    ++idx;
                } else if (ch == '-') {
                    isBeginning = true;
                    ++idx;
                } else if (ch >= '0' && ch <= '9') {
                    isPositive = true;
                    isBeginning = true;
                    result += ch - '0';
                    ++idx;
                } else if (ch == ' ') {
                    ++idx;
                } else {
                    return 0;
                }
            } else {
                if (ch >= '0' && ch <= '9') {
                    if (isPositive && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && ch > '7'))) {
                        return Integer.MAX_VALUE;
                    }
                    if (!isPositive && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && ch > '8'))) {
                        return Integer.MIN_VALUE;
                    }
                    result = result * 10 + ch - '0';
                    ++idx;
                } else {
                    break;
                }
            }
        }
        if (!isPositive) {
            result = -result;
        }

        return result;
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char preSign = '+';
        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            }
            if (!Character.isDigit(ch) && ch != ' ' || i == s.length() - 1) {
                if (preSign == '+') {
                    stack.push(num);
                } else if (preSign == '-') {
                    stack.push(-num);
                } else if (preSign == '*') {
                    stack.push(stack.pop() * num);
                } else if (preSign == '/') {
                    stack.push(stack.pop() / num);
                }
                preSign = ch;
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }


    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> preSumCnt = new HashMap<>();
        preSumCnt.put(0, 1);
        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;

            if (preSumCnt.containsKey(preSum - k)) {
                count += preSumCnt.get(preSum - k);
            }

            preSumCnt.put(preSum, preSumCnt.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    public static int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        // 以idx为结尾时，0：最大长度；1：出现次数
        int[][] dp = new int[length][2];
        int maxLength = 1;
        // 初始化
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = 1;
            }
        }

        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i][0] < dp[j][0] + 1) {
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = dp[j][1];
                    } else if (dp[i][0] == dp[j][0] + 1) {
                        dp[i][1] += dp[j][1];
                    }
                }
            }
            maxLength = Math.max(maxLength, dp[i][0]);
        }

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i][0] == maxLength) {
                result += dp[i][1];
            }
        }
        return result;
    }

    public String simplifyPath(String path) {
        String[] strings = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String str : strings) {
            if (str.equals(".") || str.equals("")) {
                continue;
            } else {
                if (str.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else {
                    stack.push(str);
                }
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder resultBuffer = new StringBuilder();
        for (String str : stack) {
            resultBuffer.append("/");
            resultBuffer.append(str);
        }
        return resultBuffer.toString();
    }


    public static int largestRectangleArea(int[] heights) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int curIdx = stack.pop();
                int leftIdx = stack.isEmpty() ? -1 : stack.peek();
                int rightIdx = i;
                res = Math.max(res, heights[curIdx] * (rightIdx - leftIdx - 1));
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int curIdx = stack.pop();
            int leftIdx = stack.isEmpty() ? -1 : stack.peek();
            int rightIdx = heights.length;
            res = Math.max(res, heights[curIdx] * (rightIdx - leftIdx - 1));
        }

        return res;
    }

    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        int mid = length / 2;

        for (int i = mid; i >= 1; i--) {
            if (length % i == 0) {
                String segment = s.substring(0, i);
                int count = 0;
                int j = 0;
                while (j <= length - i) {
                    if (s.substring(j, j + i).equals(segment)) {
                        ++count;
                        j += i;
                    } else {
                        break;
                    }
                }

                if (count == length / i) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int length = chars.length;

        // 找到某个数字出现的最后一个位置
        int[] lastIdxs = new int[10];
        Arrays.fill(lastIdxs, -1);
        for (int i = 0; i < length; i++) {
            lastIdxs[chars[i] - '0'] = i;
        }
        for (int i = 0; i < length; i++) {
            // 找比当前大的数字的位置，
            for (int j = 9; j > chars[i] - '0'; j--) {
                if (lastIdxs[j] > i) {
                    char tmp = chars[i];
                    chars[i] = chars[lastIdxs[j]];
                    chars[lastIdxs[j]] = tmp;
                    return Integer.parseInt(String.valueOf(chars));
                }
            }
        }

        return Integer.parseInt(String.valueOf(chars));
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null) {
            s1 = "";
        }
        if (s2 == null) {
            s2 = "";
        }
        if (s3 == null) {
            s3 = "";
        }

        Boolean[][] memo = new Boolean[s1.length() + 1][s2.length() + 1];
        return process(s1, s2, s3, 0, 0, 0, memo);
    }


    private boolean process(String s1, String s2, String s3, int i, int j, int k, Boolean[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        if (i == s1.length() && j == s2.length() && k == s3.length()) {
            return true;
        }

        if (k >= s3.length()) {
            return memo[i][j] = false;
        }

        if (i < s1.length() && s1.charAt(i) == s3.charAt(k) && process(s1, s2, s3, i + 1, j, k + 1, memo)) {
            return true;
        }
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k) && process(s1, s2, s3, i, j + 1, k + 1, memo)) {
            return true;
        }
        return memo[i][j] = false;
    }


    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums.length == 1 || nums[left] < nums[right]) {
            return nums[0];
        }

        if (nums[left] == nums[right]) {
            int result = nums[left];
            for (int i = left + 1; i <= right; i++) {
                if (result > nums[i]) {
                    result = nums[i];
                }
            }
            return result;
        }

//        while (left < right) {
//            int mid = (left + right + 1) >> 1;
//            if (mid + 1 < nums.length && nums[mid] > nums[mid + 1]) {
//                return nums[mid + 1];
//            }
//            if (nums[mid - 1] > nums[mid]) {
//                return nums[mid];
//            }
//
//            if (nums[mid] >= nums[0]) {
//                left = mid;
//            } else {
//                right = mid - 1;
//            }
//        }

        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (mid + 1 < nums.length && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            if (nums[mid] >= nums[0]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return -1;
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
                    // 假设为：##b , ###a *
                    if (s[i - 1] != p[j - 2] && p[j - 2] != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        // 假设为：##b , ###b *
                        dp[i][j] = dp[i - 1][j] // ## , ###b *
                                || dp[i][j - 1] // ##b , ###b
                                || dp[i][j - 2];// ##b , ###
                    }
                }
            }
        }
        return dp[m][n];
    }


    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }

        if (pNode.right != null) {
            return getMostLeft(pNode.right);
        } else {
            while (pNode.next != null && pNode.next.left != pNode) {
                pNode = pNode.next;
            }
            return pNode.next;
        }
    }

    private TreeLinkNode getMostLeft(TreeLinkNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public class TreeNode {
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

    class BSTIterator {

        TreeNode node;
        Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            node = root;
            stack = new Stack<>();
        }

        public int next() {
            int result = -1;
            if (hasNext()) {
                while (node != null || !stack.isEmpty()) {
                    if (node != null) {
                        stack.push(node);
                        node = node.left;
                    } else if (!stack.isEmpty()) {
                        TreeNode tmp = stack.pop();
                        result = tmp.val;
                        node = tmp.right;
                        break;
                    }
                }
            }
            return result;
        }

        public boolean hasNext() {
            return node != null || !stack.isEmpty();
        }
    }

    public int openLock(String[] deadends, String target) {
        HashSet<String> deadends_set = new HashSet<>(Arrays.asList(deadends));

        HashSet<String> visited = new HashSet<>();
        visited.add("0000");
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");

        int step = 0;
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                String current = queue.poll();
                if (current.equals(target)) {
                    return step;
                }

                if (deadends_set.contains(current)) {
                    continue;
                }

                for (int j = 0; j < 4; j++) {
                    String up = plusOne(current, j);
                    if (!visited.contains(up)) {
                        queue.add(up);
                        visited.add(up);
                    }

                    String down = minusOne(current, j);
                    if (!visited.contains(down)) {
                        queue.add(down);
                        visited.add(down);
                    }
                }
            }
            ++step;
        }
        return -1;
    }

    private String plusOne(String str, int pos) {
        char[] chars = str.toCharArray();
        if (chars[pos] == '9') {
            chars[pos] = '0';
        } else {
            chars[pos]++;
        }
        return String.valueOf(chars);
    }

    private String minusOne(String str, int pos) {
        char[] chars = str.toCharArray();
        if (chars[pos] == '0') {
            chars[pos] = '9';
        } else {
            chars[pos]--;
        }
        return String.valueOf(chars);
    }


//    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
//        int length = heights.length;
//        int i = 0;
//        while (i < length - 1) {
//            if (heights[i] < heights[i + 1]) {
//                if (heights[i + 1] - heights[i] > bricks && ladders == 0) {
//                    break;
//                }
//
//                // 优先使用砖头
//                if (heights[i + 1] - heights[i] <= bricks) {
//                    bricks -= heights[i + 1] - heights[i];
//                    ++i;
//                } else if (ladders > 0) {
//                    --ladders;
//                    ++i;
//                }
//            } else {
//                ++i;
//            }
//        }
//        return i;
//    }


    List<Integer> ans;

    public List<Integer> lexicalOrder(int n) {
        ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) dfs(i, n);
        return ans;
    }

    private void dfs(int cur, int limit) {
        if (cur > limit) {
            return;
        }
        ans.add(cur);
        for (int i = 0; i <= 9; i++) {
            dfs(cur * 10 + i, limit);
        }
    }


    public List<Integer> grayCode(int n) {
        List<Integer> dp = new ArrayList<>();
        dp.add(0);
        if (n == 0) {
            return dp;
        }
        dp.add(1);
        if (n == 1) {
            return dp;
        }

        int mask = 1;
        for (int i = 2; i <= n; i++) {
            mask <<= 1;
            for (int j = dp.size() - 1; j >= 0; j--) {
                dp.add(dp.get(j) + mask);
            }
        }
        return dp;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private TreeNode process(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast == tail || fast.next == tail) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        TreeNode node = new TreeNode(slow.val);
        node.left = process(head, slow);
        node.right = process(slow.next, tail);
        return node;
    }


//    public int subarraysDivByK(int[] nums, int k) {
//
//        Map<Integer, Integer> preSumCnt = new HashMap<>();
//        int preSum = 0;
//        for(int num : nums){
//            preSum += num;
//            preSumCnt.put(preSum, preSumCnt.getOrDefault(preSum, 0) + 1);
//        }
//
//        for(Map.Entry<Integer, Integer> entry : preSumCnt.entrySet()){
//            if(entry.getKey() % k != )
//        }
//    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    class MyHashMap {

        Map<Integer, Integer> map;

        public MyHashMap() {
            map = new HashMap<>();
        }

        public void put(int key, int value) {
            map.put(key, value);
        }

        public int get(int key) {
            return map.getOrDefault(key, -1);
        }

        public void remove(int key) {
            if (map.containsKey(key)) {
                map.remove(key);
            }
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth) {
            return countNodes(root.right) + (1 << leftDepth);
        } else {
            return countNodes(root.left) + (1 << rightDepth);
        }
    }

    private int getDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int left = Integer.MIN_VALUE;
        if (root.left != null) {
            left = getDepth(root.left);
        }

        int right = Integer.MIN_VALUE;
        if (root.right != null) {
            right = getDepth(root.right);
        }

        return Math.max(left, right) + 1;
    }

    public int nthUglyNumber(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2;
            int num3 = dp[p3] * 3;
            int num5 = dp[p5] * 5;

            int res = Math.min(num2, Math.min(num3, num5));
            dp[i] = res;
            if (num2 == res) {
                ++p2;
            }
            if (num3 == res) {
                ++p3;
            }
            if (num5 == res) {
                ++p5;
            }
        }
        return dp[n];
    }


    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) {
            return false;
        }

        int poppedIdx = 0;
        Stack<Integer> stack = new Stack<>();
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[poppedIdx]) {
                stack.pop();
                ++poppedIdx;
            }
        }
        return poppedIdx == popped.length;
    }


    public static int singleNumber(int[] nums) {
        int[] bits = new int[32];
        for (int num : nums) {
            int mask = 1;
            for (int i = 31; i >= 0; i--) {
                if ((num & mask) != 0) {
                    bits[i] += 1;
                }
                mask <<= 1;
            }
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            bits[i] %= 3;
            result = result * 2 + bits[i];
        }
        return result;
    }

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> word2Freq = new HashMap<>();
        for (String word : words) {
            word2Freq.put(word, word2Freq.getOrDefault(word, 0) + 1);
        }

        // 小顶堆。数量相同的情况下，字段顺序靠后的排在前面；数量不同的情况下，数量少的排在前面。
        PriorityQueue<String> heap = new PriorityQueue<>(((o1, o2) -> (
                word2Freq.get(o1).equals(word2Freq.get(o2)) ?
                        o2.compareTo(o1) : word2Freq.get(o1) - word2Freq.get(o2))
        ));

        for (String word : word2Freq.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(heap.poll());
        }
        Collections.reverse(result);
        return result;
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return process(nums, 0, nums.length - 1);
    }

    private TreeNode process(int[] nums, int start, int end) {
        // base case
        if (start > end) {
            return null;
        }

        int mid = (start + end) >> 1;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = process(nums, start, mid - 1);
        node.right = process(nums, mid + 1, end);
        return node;
    }

    public void recoverTree(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode firstNode = null;
        TreeNode secondNode = null;
        TreeNode preNode = new TreeNode(Integer.MIN_VALUE);

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else if (!stack.isEmpty()) {
                TreeNode tmp = stack.pop();

                if (firstNode == null && tmp.val < preNode.val) {
                    firstNode = preNode;
                }
                if (firstNode != null && tmp.val < preNode.val) {
                    secondNode = tmp;
                }

                preNode = tmp;
                root = tmp.right;
            }
        }

        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
    }

    public int rand7() {
        return 1;
    }

    public int rand10() {
        while (true) {
            // 保证数字在1~49之间，并且是等可能的
            int num = (rand7() - 1) * 7 + rand7();
            // case1：
            if (num <= 40) {
                return num % 10 + 1;
            }

            // 41~49-41 = 0~8之间
            // 保证数字在1~63之间，并且是等可能的
            num = (num - 40 - 1) * 7 + rand7();
            if (num <= 60) {
                return num % 10 + 1;
            }

            // 61~63-61 = 0~2之间
            // 保证数字在1~21之间，并且是等可能的
            num = (num - 60 - 1) * 7 * rand7();
            if (num <= 20) {
                return 1 + num % 10;
            }
        }
    }

//    public String fractionToDecimal(int numerator, int denominator) {
//
//    }

    class Info {
        int robYes;
        int robNo;

        public Info(int robYes, int robNo) {
            this.robYes = robYes;
            this.robNo = robNo;
        }
    }

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info info = process(root);
        return Math.max(info.robNo, info.robYes);
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int robYes = root.val + leftInfo.robNo + rightInfo.robNo;
        int robNo = Math.max(leftInfo.robNo, leftInfo.robYes) + Math.max(rightInfo.robNo, rightInfo.robYes);
        return new Info(robYes, robNo);
    }


    public String shiftingLetters(String s, int[] shifts) {
        int length = shifts.length;
        long[] longShifts = new long[length];
        longShifts[length - 1] = shifts[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            longShifts[i] = shifts[i] + longShifts[i + 1];
        }

        for (int i = 0; i < length; i++) {
            longShifts[i] %= 26;
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < length; i++) {
            chars[i] += longShifts[i];
            if (chars[i] > 'z') {
                chars[i] -= 26;
            }
        }
        return String.valueOf(chars);
    }

    public static int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        // 恢复二段性。举个例子：[0,1,2,2,2,3,4,5] 变成 [2,3,4,5,0,1,2,2]就会失去二段性，因此需要恢复二段性
        while (l < r && nums[0] == nums[r]) r--;

        // 第一次「二分」：从中间开始找，找到满足 >=nums[0] 的分割点（旋转点）
        // 查找旋转点的idx。本题中有：nums[idx] >= nums[0]
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] >= nums[0]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        // 旋转点为idx
        int idx = n;
        if (nums[r] >= nums[0] && r + 1 < n) idx = r + 1;

        // 第二次二分，找目标值
        // 从0~idx-1找
        int ans = find(nums, 0, idx - 1, target);
        if (ans != -1) return ans;
        // 从idx~n-1找
        ans = find(nums, idx, n - 1, target);
        return ans;
    }

    static int find(int[] nums, int l, int r, int t) {
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= t) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[r] == t ? r : -1;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 双向队列，记录索引位置。最大为k
        Deque<Integer> deque = new LinkedList<>();

        int length = nums.length;
        int[] result = new int[length - k + 1];
        for (int i = 0; i < length; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.removeLast();
            }
            if (!deque.isEmpty() && i >= deque.peek() + k) {
                deque.removeFirst();
            }
            deque.addLast(i);

            // 开始记录
            if (i + 1 - k >= 0) {
                result[i + 1 - k] = nums[deque.peek()];
            }
        }
        return result;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    class Codec {
        public static final String DIVIDER = ",";
        public static final String IDENTITY = "#";
        private int idx;

        // Encodes a tree to a single string.
        public String serialize(Node root) {
            if (root == null) return IDENTITY;
            StringBuilder res = new StringBuilder();
            res.append(root.val).append(DIVIDER).append(root.children.size()).append(DIVIDER);
            for (Node child : root.children) {
                res.append(serialize(child));
            }
            return res.toString();
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            List<String> list = new ArrayList<>(Arrays.asList(data.split(DIVIDER)));
            idx = 0;
            return dfs(list);
        }

        private Node dfs(List<String> list) {
            if (list.get(idx).equals(IDENTITY)) {
                idx++;
                return null;
            }
            Node root = new Node(Integer.parseInt(list.get(idx++)));
            int len = Integer.parseInt(list.get(idx++));
            List<Node> children = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                children.add(dfs(list));
            }
            root.children = children;
            return root;
        }
    }


    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch == '.') {
                    continue;
                }


            }
        }
        return true;
    }

    private boolean isValid(int row, int col, char val, char[][] board) {
        for (int i = 0; i < 9; i++) { // 判断行里是否重复
            if (board[row][i] == val) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) { // 判断列里是否重复
            if (board[j][col] == val) {
                return false;
            }
        }
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) { // 判断9方格里是否重复
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }

    List<String> result;

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        process("", n, n);
        return result;
    }

    private void process(String str, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(str);
            return;
        }

        if (left > right) {
            return;
        }

        if (left > 0) {
            process(str + "(", left - 1, right);
        }
        if (right > 0) {
            process(str + ")", left, right - 1);
        }
    }

    public static void main(String[] args) {
        String str = "aaaa";
        System.out.println(longestPalindrome(str));
    }

    public static String longestPalindrome(String s) {
        String result = "";
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    result = result.length() > j - i + 1 ? result : s.substring(i, j + 1);
                }
            }
        }
        return result;
    }


}