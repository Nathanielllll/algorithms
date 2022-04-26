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
//    239. 滑动窗口最大值 字节hard top10欢迎你


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

    public String simplifyPath(String path) {
        String[] strings = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String str : strings) {
            if (str.equals("") || str.equals(".")) {
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


    public static int findNthDigit(int n) {
        int digit = 1;
        while (n > getCountOfCurDigit(digit)) {
            n -= getCountOfCurDigit(digit);
            ++digit;
        }

        long curNum = (long) Math.pow(10, digit - 1);
        curNum = curNum + n / digit;
        if (curNum >= Integer.MAX_VALUE) {
            curNum = Integer.MAX_VALUE;
        }

        if (n % digit == 0) {
            return (int) curNum % 10;
        } else {
            return String.valueOf(curNum + 1).charAt(n % digit - 1) - '0';
        }
    }

    private static int getCountOfCurDigit(int digit) {
        return (int) Math.pow(10, digit - 1) * 9 * digit;
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

//    public static int myAtoi(String str) {
//
//
//
//
//    }


    public int subarraySum(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);

        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;

            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }

            // 然后维护 preSumFreq 的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        int mid = length / 2;
        for (int i = mid; i >= 1; i--) {
            if (length % i == 0) {
                int count = 0;
                int j = 0;
                String segment = s.substring(0, i);
                while (j <= length - i) {
                    if (segment.equals(s.substring(j, j + i))) {
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


}