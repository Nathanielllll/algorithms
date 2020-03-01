import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class Test_460_LFU {
    class LFUCache {
        Map<Integer, Node> cache;  // 存储缓存的内容
        Map<Integer, LinkedHashSet<Node>> freqMap; // 存储每个频次对应的双向链表
        int size;
        int capacity;
        int min; // 存储当前最小频次

        public LFUCache(int capacity) {
            cache = new HashMap<>(capacity);
            freqMap = new HashMap<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            // 该key访问频次+1
            freqInc(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            Node node = cache.get(key);
            // 若key存在，则更新value，访问频次+1
            if (node != null) {
                node.value = value;
                freqInc(node);

            // 若key不存在
            } else {
                // 如果缓存满了，删除 表示最小频次的链表 中的tail.pre这个Node（即最小频次链表中最先访问的Node），如果该链表中的元素删空了，则删掉该链表。
                if (size == capacity) {
                    Node deadNode = removeNode();
                    cache.remove(deadNode.key);
                    size--;
                }
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addNode(newNode);
                size++;
            }
        }

        /**
         * node的访问频次 + 1
         */
        void freqInc(Node node) {
            // 从原freq对应的链表里移除, 并更新min
            int freq = node.freq;
            LinkedHashSet<Node> set = freqMap.get(freq);
            set.remove(node);
            if (freq == min && set.size() == 0) {
                min = freq + 1;
            }
            // 将node加入新freq对应的链表，若该链表不存在，则先创建该链表。
            node.freq++;
            LinkedHashSet<Node> newSet = freqMap.get(freq + 1);
            if (newSet == null) {
                newSet = new LinkedHashSet<>();
                freqMap.put(freq + 1, newSet);
            }
            newSet.add(node);
        }

        /**
         * 增加代表某一频次对应的链表
         */
        void addNode(Node node) {
            LinkedHashSet<Node> set = freqMap.get(1);
            if (set == null) {
                set = new LinkedHashSet<>();
                freqMap.put(1, set);
            }
            set.add(node);
            min = 1;
        }

        /**
         * 删除代表某一频次对应的链表
         */
        Node removeNode() {
            LinkedHashSet<Node> set = freqMap.get(min);
            Node deadNode = set.iterator().next();
            set.remove(deadNode);
            return deadNode;
        }
    }

    class Node {
        int key;
        int value;
        int freq = 1;

        public Node() {}

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}
