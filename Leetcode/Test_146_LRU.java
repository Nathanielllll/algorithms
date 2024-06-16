import java.util.HashMap;
import java.util.Map;

// 很容易犯错的一点是：处理链表节点的同时不要忘了更新哈希表中对节点的映射。
public class Test_146_LRU {

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
}
