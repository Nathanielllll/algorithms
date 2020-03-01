import java.util.HashMap;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 */
class Node {
    public int key, val;
    public Node next, prev;
    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}
class DoubleList {
    private Node head, tail; // 头尾虚节点
    private int size; // 链表元素数

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // 在链表头部添加节点 x
    public void addFirst(Node x) {
        x.next = head.next;
        x.prev = head;
        head.next.prev = x;
        head.next = x;
        size++;
    }

    // 删除链表中的 x 节点（x 一定存在）
    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    // 删除链表中最后一个节点，并返回该节点
    public Node removeLast() {
        if (tail.prev == head)
            return null;
        Node last = tail.prev;
        remove(last);
        return last;
    }

    // 返回链表长度
    public int size() {
        return size;
    }
}

class LRUCache {
    // key -> Node(key, val)
    private HashMap<Integer, Node> cache;
    // Node(k1, v1) <-> Node(k2, v2)...
    private DoubleList list;
    // 最大容量
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        cache = new HashMap<>();
        list = new DoubleList();
    }

    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;
        int val = cache.get(key).val;
        // 利用 put 方法把该数据提前
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        // 先把新节点 x 做出来
        Node x = new Node(key, val);

        if (cache.containsKey(key)) {
            // 删除旧的节点，新的插到头部
            list.remove(cache.get(key));

            // 直接添加到头部
            list.addFirst(x);
            // 更新 map 中对应的数据
            cache.put(key, x);
        } else {
            //cache中本来没有这个节点且已经满了
            if (cap == list.size()) {
                // 删除链表最后一个数据
                Node last = list.removeLast();
                cache.remove(last.key);
            }

            // 直接添加到头部
            list.addFirst(x);
            // 更新 map 中对应的数据
            cache.put(key, x);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));;       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4

    }
}



