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
public class Test {
    public class Node{
        int key, value;
        Node next, prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public class DoubleLinkedList{
        Node head, tail;
        int size;

        public DoubleLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            size = 0;
            head.next = tail;
            tail.prev = head;
        }

        //添加首节点
        public void addFirst(Node node){
            Node next = head.next;

            head.next = node;
            node.prev = head;
            node.next = next;
            next.prev = node;

            size++;
        }

        //删除某节点
        public void delete(Node node){
            Node prev = node.prev;
            Node next = node.next;

            prev.next = next;
            next.prev = prev;
            size--;
        }

        //删除尾节点
        public Node deleteLast(){
            if(head.next == tail){
                return null;
            }
            Node node = tail.prev;
            delete(node);
            return node;
        }

        //获得linkedList大小
        public int getSize(){
            return size;
        }
    }

    public class LRUCache{
        HashMap<Integer, Node> cache;
        DoubleLinkedList list;
        int capacity;

        public LRUCache(int capacity) {
            this.cache = new HashMap<>();
            this.list = new DoubleLinkedList();
            this.capacity = capacity;
        }

        public void put(int key, int value){
            Node node = new Node(key, value);
            //不包括这个key
            if (!cache.containsKey(key)) {
                if (list.size == capacity) {
                    Node last = list.deleteLast();
                    cache.remove(last.key);
                }
            }else {
                Node original_node = cache.get(key);
                list.delete(original_node);
            }
            cache.put(key, node);
            list.addFirst(node);
        }

        public int get(int key){
            if (!cache.containsKey(key)) {
                return -1;
            }else {
                int value = cache.get(key).value;
                put(key, value);
                return value;
            }
        }
    }

}
