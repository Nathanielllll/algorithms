public class offer062 {
    class Trie {

        private boolean isEnd;
        private Trie[] children;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            this.isEnd = false;
            this.children = new Trie[26];
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie node = this;
            char[] chars = word.toCharArray();
            for (char ch : chars) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new Trie();
                }
                node = node.children[ch - 'a'];
            }
            node.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie node = this;
            char[] chars = word.toCharArray();
            for (char ch : chars) {
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            return node.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Trie node = this;
            char[] chars = prefix.toCharArray();
            for (char ch : chars) {
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            return true;
        }
    }
}
