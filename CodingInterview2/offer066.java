

public class offer066 {
    static class MapSum {
        class Trie {
            private Trie[] children;
            // 如果不为0，则说明这个节点是end
            private int val;

            /**
             * Initialize your data structure here.
             */
            public Trie() {
                this.val = 0;
                this.children = new Trie[26];
            }

            public void insert(String key, int val) {
                Trie node = this;
                char[] chars = key.toCharArray();
                for (char ch : chars) {
                    int idx = ch - 'a';
                    if (node.children[idx] == null) {
                        node.children[idx] = new Trie();
                    }
                    node = node.children[idx];
                }
                node.val = val;
            }

            public int getSum() {
                int sum = val;
                for (int i = 0; i < 26; i++) {
                    if (children[i] != null) {
                        sum += children[i].getSum();
                    }
                }
                return sum;
            }
        }

        Trie root;

        /**
         * Initialize your data structure here.
         */
        public MapSum() {
            root = new Trie();
        }

        public void insert(String key, int val) {
            root.insert(key, val);
        }

        public int sum(String prefix) {
            Trie node = getNode(prefix);
            if (node == null) return 0;

            return node.getSum();
        }

        private Trie getNode(String prefix) {
            Trie node = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    return null;
                }
                node = node.children[idx];
            }
            return node;
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("aa", 2);
        System.out.println(mapSum.sum("aa"));
        mapSum.insert("aaa", 3);
        System.out.println(mapSum.sum("aa"));

    }
}
