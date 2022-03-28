public class offer064 {


    static class MagicDictionary {
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
                    int idx = ch - 'a';
                    if (node.children[idx] == null) {
                        node.children[idx] = new Trie();
                    }
                    node = node.children[idx];
                }
                node.isEnd = true;
            }
        }

        Trie root;

        /**
         * Initialize your data structure here.
         */
        public MagicDictionary() {
            root = new Trie();
        }

        public void buildDict(String[] dictionary) {
            for (String word : dictionary) {
                root.insert(word);
            }
        }

        public boolean search(String searchWord) {
            return search(searchWord, 0, root, false);
        }

        private boolean search(String word, int depth, Trie root, boolean hasChanged) {
            // changedLetter 用于标识当前搜索路径中是否已经有字母进行过替换
            if (depth == word.length()) {
                return root.isEnd && hasChanged;
            }
            char targetLetter = word.charAt(depth++);
            int idx = targetLetter - 'a';
            Trie node = root.children[idx];
            if (node != null && search(word, depth, node, hasChanged)) {
                return true;
            }
            if (!hasChanged) {
                for (char letter = 'a'; letter <= 'z'; letter++) {
                    if (letter == targetLetter) {
                        continue;
                    }
                    node = root.children[idx];
                    if (node != null && search(word, depth, node, true)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
//        ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
//[[], [["hello","hallo","leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
        MagicDictionary magicDictionary = new MagicDictionary();
        String[] dictionary = {"hello","hallo","leetcode"};
//        String[] dictionary = {"hello"};
        magicDictionary.buildDict(dictionary);
        String[] words = {"hello", "hhllo", "hell", "leetcoded"};
//        String[] words = {"hhllo"};
        for (String word : words) {
            System.out.println(magicDictionary.search(word));
        }

//        System.out.println(false | true);
    }
}
