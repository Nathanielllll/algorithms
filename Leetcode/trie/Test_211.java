package trie;

/**
 * @author luzhi
 * @date 2021/3/27
 */
public class Test_211 {
    public static void main(String[] args) {
        //["WordDictionary","addWord","addWord","addWord","addWord","search","search","addWord","search","search","search","search","search","search"]
        //[[],["at"],["and"],["an"],["add"],["a"],[".at"],["bat"],[".at"],["an."],["a.d."],["b."],["a.d"],["."]]
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");

        System.out.println(wordDictionary.search("a"));
        System.out.println(wordDictionary.search(".at"));

        wordDictionary.addWord("bat");
//
        System.out.println(wordDictionary.search(".at"));
        System.out.println(wordDictionary.search("an."));
        System.out.println(wordDictionary.search("a.d."));
        System.out.println(wordDictionary.search("b."));
        System.out.println(wordDictionary.search("a.d"));
        System.out.println(wordDictionary.search("."));
    }
    static class Trie {
        private boolean isEnd;
        private Trie[] children;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            isEnd = false;
            children = new Trie[26];
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie root = this;
            char[] chars = word.toCharArray();
            for (char c : chars) {
                if (root.children[c - 'a'] == null) {
                    root.children[c - 'a'] = new Trie();
                }
                root = root.children[c - 'a'];
            }
            root.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie root = this;
            char[] chars = word.toCharArray();
            return dfs(chars, root, 0);
        }

        private boolean dfs(char[] chars, Trie root, int pos) {
            if (pos == chars.length && root.isEnd) {
                return true;
            }


            boolean result = false;
            if (pos < chars.length) {
                int ch = chars[pos];
                if (ch == '.') {
                    for (int i = 0; i < 26; i++) {
                        if (root.children[i] != null) {
                            result = result || dfs(chars, root.children[i], pos + 1);
                        }
                    }
                } else if (root.children[ch - 'a'] != null) {
                    result = dfs(chars, root.children[ch - 'a'], pos + 1);
                }
            }
            return result;
        }
    }

    static class WordDictionary {
        Trie trie;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            trie = new Trie();
        }

        public void addWord(String word) {
            trie.insert(word);
        }

        public boolean search(String word) {
            return trie.search(word);
        }
    }
}
