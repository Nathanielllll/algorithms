import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test2 {

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
