import java.util.*;

public class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

//    public ListNode reverseList(ListNode head) {
//        ListNode pre = null;
//        ListNode next = null;
//        while (head != null) {
//            next = head.next;
//            head.next = pre;
//            pre = head;
//            head = next;
//        }
//        return pre;
//    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        ListNode reversedNode = reverseList(next);
        next.next = head;
        return reversedNode;
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();

        int result = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char ch_right = s.charAt(right);
            window.put(ch_right, window.getOrDefault(ch_right, 0) + 1);
            ++right;

            while (window.get(ch_right) > 1) {
                char ch_left = s.charAt(left);
                window.put(ch_left, window.getOrDefault(ch_left, 0) - 1);
                ++left;
            }
            result = Math.max(result, right - left);
        }
        return result;
    }

    /**
     * 如果允许重复一次字符呢
     * duplicateChar记录遇到的重复值
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring01(String s) {
        Map<Character, Integer> window = new HashMap<>();

        // '1'表示重复值为空
        char duplicateChar = '1';
        int result = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char ch_right = s.charAt(right);
            window.put(ch_right, window.getOrDefault(ch_right, 0) + 1);
            ++right;

            // 遇到重复值
            if (window.get(ch_right) > 1) {
                // 如果duplicateChar为空，则说明第一次遇到重复值。则将它设置为重复值即可
                if (duplicateChar == '1') {
                    duplicateChar = ch_right;
                }
                // 说明不是第一次遇到重复值
                else {
                    // 移动滑动窗口，以去除上一个重复值
                    while (window.get(duplicateChar) > 1) {
                        char ch_left = s.charAt(left);
                        window.put(ch_left, window.getOrDefault(ch_left, 0) - 1);
                        ++left;
                    }

                    // 在滑动后，如果当前滑动窗口下，重复值的个数<=1，则说明duplicateChar为空
                    if (window.get(ch_right) <= 1) {
                        duplicateChar = '1';
                    }
                    // 否则，duplicateChar为ch_right
                    else {
                        duplicateChar = ch_right;
                    }
                }
            }
            result = Math.max(result, right - left);
        }
        return result;
    }

    class LRUCache {

        class Node {
            int key;
            int val;
            Node prev;
            Node next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        class DoubleLinkedList {
            Node head;
            Node tail;
            int size;

            public DoubleLinkedList() {
                this.head = new Node(0, 0);
                this.tail = new Node(0, 0);
                this.head.next = this.tail;
                this.tail.prev = this.head;
                this.size = 0;
            }

            public void addFirst(Node node) {
                Node next = this.head.next;
                next.prev = node;
                node.next = next;
                this.head.next = node;
                node.prev = this.head;
                ++size;
            }

            public void removeNode(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                --size;
            }

            public Node removeLastNode() {
                if (head.next == tail) {
                    return null;
                }

                Node lastNode = tail.prev;
                removeNode(lastNode);
                return lastNode;
            }
        }

        Map<Integer, Node> cache;
        DoubleLinkedList doubleLinkedList;
        int capacity;

        public LRUCache(int capacity) {
            this.cache = new HashMap<>();
            this.doubleLinkedList = new DoubleLinkedList();
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }

            int val = cache.get(key).val;
            put(key, val);
            return val;
        }

        public void put(int key, int value) {
            Node node = new Node(key, value);
            if (cache.containsKey(key)) {
                Node originalNode = cache.get(key);
                doubleLinkedList.removeNode(originalNode);
            } else {
                if (doubleLinkedList.size >= capacity) {
                    Node lastNode = doubleLinkedList.removeLastNode();
                    cache.remove(lastNode.key);
                }
            }
            doubleLinkedList.addFirst(node);
            cache.put(key, node);
        }
    }

    /**
     * 时间复杂度：O(N * 26 * 26)，因为函数最多执行 26 次，for循环遍历一次是26个字符，循环里面对 s 分割时间复杂度是O(N)。超过了 84.40% 的提交。
     * 空间复杂度：O(26 * 26)，函数执行 26 次，每次开辟 26 个字符的set空间。
     *
     * @param s
     * @param k
     * @return
     */
    public static int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }

        Map<Character, Integer> char2Cnt = new HashMap<>();
        for (char ch : s.toCharArray()) {
            char2Cnt.put(ch, char2Cnt.getOrDefault(ch, 0) + 1);
        }

        // 如果一个字符 c 在 s 中出现的次数少于 k 次，那么 s 中所有的包含 c 的子字符串都不能满足题意。
        // 所以，应该在 s 的所有不包含 c 的子字符串中继续寻找结果：把 s 按照 c 分割（分割后每个子串都不包含 c），得到很多子字符串 t；下一步要求 t 作为源字符串的时候，它的最长的满足题意的子字符串长度。
        // （到现在为止，我们把大问题分割为了小问题(s → t)）。此时我们发现，恰好已经定义了函数 longestSubstring(s, k) 就是来解决这个问题的！所以直接把 longestSubstring(s, k) 函数拿来用，于是形成了递归。
        for (char ch : char2Cnt.keySet()) {
            if (char2Cnt.get(ch) < k) {
                int result = 0;
                for (String str : s.split(String.valueOf(ch))) {
                    result = Math.max(result, longestSubstring(str, k));
                }
                return result;
            }
        }

        // 未进入递归时的返回结果：如果 s 中的每个字符出现的次数都大于 k 次，那么 s 就是我们要求的字符串，直接返回该字符串的长度。
        return s.length();
    }

    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int idx = partition(nums, left, right);

        int targetIdx = nums.length - k;
        while (idx != targetIdx) {
            if (idx > targetIdx) {
                right = idx - 1;
            } else {
                left = idx + 1;
            }
            idx = partition(nums, left, right);
        }
        return nums[idx];
    }

    private Random random = new Random(System.currentTimeMillis());

    private int partition(int[] nums, int left, int right) {
        if (right > left) {
            int randomIdx = left + 1 + random.nextInt(right - left);
            swap(nums, left, randomIdx);
        }

        int j = left;
        int pivot = nums[left];
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                ++j;
                swap(nums, i, j);
            }
        }
        swap(nums, left, j);
        return j;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        String s = "aaabbb";
        int k = 3;
        System.out.println(longestSubstring(s, k));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        outer:
        while (fast.next != null) {
            for (int i = 0; i < k; i++) {
                fast = fast.next;
                if (fast == null) {
                    break outer;
                }
            }

            ListNode pre = slow;
            ListNode next = fast.next;
            fast.next = null;
            slow = slow.next;

            pre.next = reverse(slow);
            slow.next = next;
            fast = slow;
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        head.next = null;
        ListNode reversed = reverse(next);
        next.next = head;
        return reversed;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;

        for (int i = 0; i < length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    while (left < right && nums[right] == nums[--right]) ;
                } else if (sum < 0) {
                    while (left < right && nums[left] == nums[++left]) ;
                } else {
                    List<Integer> subResult = new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right]));
                    result.add(subResult);
                    while (left < right && nums[right] == nums[--right]) ;
                    while (left < right && nums[left] == nums[++left]) ;
                }
            }
        }
        return result;
    }


    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int idx = partition(nums, left, right);
            quickSort(nums, left, idx - 1);
            quickSort(nums, idx + 1, right);
        }
    }

    public int maxSubArray(int[] nums) {
        int result = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            result = Math.max(result, sum);
        }
        return result;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int rest = target - nums[i];
            if (map.containsKey(rest)) {
                return new int[]{i, map.get(rest)};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }


}