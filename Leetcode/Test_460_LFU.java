import java.util.HashMap;

public class Test_460_LFU {

    /*
    https://leetcode-cn.com/problems/lfu-cache/solution/chao-xiang-xi-tu-jie-dong-tu-yan-shi-460-lfuhuan-c/

    LFU有个兄弟LRU，他们两都是常用的缓存淘汰算法。

    LRU(Least Recently Used) 最近最少使用算法，它是根据时间维度来选择将要淘汰的元素，即删除掉最长时间没被访问的元素。
    LFU(Least Frequently Used) 最近最不常用算法，它是根据频率维度来选择将要淘汰的元素，即删除访问频率最低的元素。如果两个元素的访问频率相同，则淘汰最久没被访问的元素。
    也就是说LFU淘汰的时候会选择两个维度，先比较频率，选择访问频率最小的元素；如果频率相同，则按时间维度淘汰掉最久远的那个元素。
     */
    static class LFUCache {

        class Node {
            int key;
            int value;
            int freq;
            Node prev;
            Node next;

            public Node(int key, int value, int freq) {
                this.key = key;
                this.value = value;
                this.freq = freq;
                this.prev = null;
                this.next = null;
            }
        }

        class DoubleList {
            Node head;
            Node tail;
            int size;

            public DoubleList() {
                this.size = 0;
                head = new Node(0, 0, 0);
                tail = new Node(0, 0, 0);
                head.next = tail;
                tail.prev = head;
            }

            public void remove(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
            }

            public Node removeLast() {
                if (tail.prev == head) {
                    return null;
                }

                Node lastNode = tail.prev;
                remove(lastNode);
                return lastNode;
            }

            public void addFirst(Node node) {
                node.next = head.next;
                node.prev = head;
                head.next.prev = node;
                head.next = node;
                size++;
            }

            public int getSize() {
                return size;
            }
        }

        // key -> Node
        private HashMap<Integer, Node> cache;
        // freq -> DoubleList
        private HashMap<Integer, DoubleList> freqMap;
        private int capacity;

        public LFUCache(int capacity) {
            cache = new HashMap<>();
            freqMap = new HashMap<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            int value = cache.get(key).value;
            put(key, value);
            return value;
        }

        public void put(int key, int value) {
            if (capacity <= 0) {
                return;
            }

            Node node;
            // cache中已经有了key
            if (cache.containsKey(key)) {
                Node original_node = cache.get(key);
                int original_freq = original_node.freq;
                DoubleList original_list = freqMap.get(original_freq);
                original_list.remove(original_node);

                node = new Node(key, value, original_freq + 1);
            } else {
                // 一个值得注意的细节
                if (cache.size() >= capacity) {
                    int i = 1;
                    for (; i <= capacity; i++) {
                        if (freqMap.containsKey(i) && freqMap.get(i).getSize() > 0) {
                            break;
                        }
                    }
                    DoubleList freq_i_list = freqMap.get(i);
                    Node freq_i_last_node = freq_i_list.removeLast();
                    cache.remove(freq_i_last_node.key);
                }

                node = new Node(key, value, 1);
            }

            cache.put(key, node);
            DoubleList list = freqMap.containsKey(node.freq) ? freqMap.get(node.freq) : new DoubleList();
            list.addFirst(node);
            freqMap.put(node.freq, list);
        }
    }

    public static void main(String[] args) {
        // ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
        //[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]

//        LFUCache lfuCache = new LFUCache(2);
//        lfuCache.put(1,1);
//        lfuCache.put(2,2);
//        System.out.println(lfuCache.get(1));
//        lfuCache.put(3,3);
//        System.out.println(lfuCache.get(2));
//        System.out.println(lfuCache.get(3));
//        lfuCache.put(4,4);
//        System.out.println(lfuCache.get(1));
//        System.out.println(lfuCache.get(3));
//        System.out.println(lfuCache.get(4));


        LFUCache lfuCache = new LFUCache(0);
        lfuCache.put(0,0);
        System.out.println(lfuCache.get(0));


    }

}
