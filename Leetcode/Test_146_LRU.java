import java.util.HashMap;
// 很容易犯错的一点是：处理链表节点的同时不要忘了更新哈希表中对节点的映射。
public class Test_146_LRU {

    static class Node{
        int k, v;
        Node prev, next;

        public Node(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

    static class DoubleList{
        int size;
        Node head, tail;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addFirst(Node node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        public void remove(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        public Node removeLast(){
            if (tail.prev == head) {
                return null;
            }
            Node last = tail.prev;
            remove(last);
            return last;
        }

        public int getSize(){
            return size;
        }
    }

    static class  LRUCache{
        HashMap<Integer, Node> cache;
        DoubleList list;
        int cap;

        public LRUCache(int cap) {
            this.cap = cap;
            cache = new HashMap<>();
            list = new DoubleList();
        }

        //先写put，再写get
        public void put(int key, int value){
            Node node = new Node(key, value);
            if(cache.containsKey(key)){
                //删除list当中的key对应的原来的node
                Node original_node = cache.get(key);
                list.remove(original_node);
            }else {
                if(list.getSize() == this.cap){
                    Node last = list.removeLast();
                    //如果没有这个key，为啥还要删除？？？
                    //因为删除的是原来的list的最后节点！！
                    cache.remove(last.k);
                }
            }

            cache.put(key, node);
            list.addFirst(node);
        }

        public int get(int key){
            if (!cache.containsKey(key)) {
                return -1;
            }
            int value = cache.get(key).v;
            put(key, value);
            return value;
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
