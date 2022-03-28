package tree;

import java.util.LinkedList;
import java.util.Queue;

/*
实现一个 MapSum 类里的两个方法，insert和sum。

对于方法insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。

对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。

示例 1:

输入: insert("apple", 3), 输出: Null
输入: sum("ap"), 输出: 3
输入: insert("app", 2), 输出: Null
输入: sum("ap"), 输出: 5
 */
public class Test_677 {
    class MapSum {
        private boolean isEnd;
        private int value;
        private MapSum[] next;

        /** Initialize your data structure here. */
        public MapSum() {
            isEnd = false;
            value = 0;
            next = new MapSum[26];
        }

        public void insert(String key, int val) {
            MapSum node = this;
            char[] chars = key.toCharArray();
            for(char c : chars){
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new MapSum();
                }
                node = node.next[c - 'a'];
            }
            node.isEnd = true;
            node.value = val;
        }

        public int sum(String prefix) {
            MapSum node = this;
            char[] chars = prefix.toCharArray();
            for(char c : chars){
                if (node.next[c - 'a'] == null) {
                    return 0;
                }
                node = node.next[c - 'a'];
            }

            // 对子节点进行加和
            return bfs(node);
        }
        
        private int bfs(MapSum node){
            int sum = 0;
            Queue<MapSum> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()){
                int cnt = queue.size();
                for (int i = 0; i < cnt; i++) {
                    MapSum temp = queue.poll();
                    for (int j = 0; j < 26; j++) {
                        if (temp.next[j] != null) {
                            queue.add(temp.next[j]);
                        }
                    }
                    if(temp.isEnd){
                        sum += temp.value;
                    }
                }
            }
            return sum;
        }
    }
}
class MapSum {
    private boolean isEnd;
    private int value;
    private MapSum[] next;

    /** Initialize your data structure here. */
    public MapSum() {
        isEnd = false;
        value = 0;
        next = new MapSum[26];
    }

    public void insert(String key, int val) {
        MapSum node = this;
        char[] chars = key.toCharArray();
        for(char c : chars){
            if (node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new MapSum();
            }
            node = node.next[c - 'a'];
        }
        node.isEnd = true;
        node.value = val;
    }

    public int sum(String prefix) {
        MapSum node = this;
        char[] chars = prefix.toCharArray();
        for(char c : chars){
            if (node.next[c - 'a'] == null) {
                return 0;
            }
            node = node.next[c - 'a'];
        }

        // 对子节点进行加和
        return bfs(node);
    }

    private int bfs(MapSum node){
        int sum = 0;
        Queue<MapSum> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                MapSum temp = queue.poll();
                for (int j = 0; j < 26; j++) {
                    if (temp.next[j] != null) {
                        queue.add(temp.next[j]);
                    }
                }
                if(temp.isEnd){
                    sum += temp.value;
                }
            }
        }
        return sum;
    }
}
