package trie;

public class Test_208_Trie {
    class Trie {
        public boolean isEnd;
        public Trie[] next;

        /** Initialize your data structure here. */
        public Trie() {
            isEnd = false;
            next = new Trie[26];
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            char[] words = word.toCharArray();
            Trie node = this;
            for (char c : words) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new Trie();
                }
                node = node.next[c - 'a'];
            }
            node.isEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            char[] words = word.toCharArray();
            Trie node = this;
            for (char c : words){
                if (node.next[c - 'a'] == null) {
                    return false;
                }
                node = node.next[c - 'a'];
            }
            return node.isEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            char[] words = prefix.toCharArray();
            Trie node = this;
            for (char c : words){
                if (node.next[c - 'a'] == null) {
                    return false;
                }
                node = node.next[c - 'a'];
            }
            return true;
        }
    }
}
