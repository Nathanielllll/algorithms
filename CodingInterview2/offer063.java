import java.util.Arrays;
import java.util.List;

public class offer063 {
    static class Trie {

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
         * 看word的前缀是否包含在trie中
         * @param word
         * @return
         */
        public int contains(String word) {
            Trie node = this;
            for (int i = 0, n = word.length(); i < n; i++) {
                int idx = word.charAt(i) - 'a';
                if (node.children[idx] == null) {
                    return -1;
                }
                if (node.children[idx].isEnd) {
                    // 如果到达了一个词根单词的尽头，那么此时得到的一定是最短词根，这也符合题目要求
                    return i + 1;
                }
                node = node.children[idx];
            }
            return -1;
        }
    }

    public static String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }

        StringBuilder resultBuilder = new StringBuilder();

        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int idx = trie.contains(word);
            if (idx > 0) {
                resultBuilder.append(word, 0, idx);
            } else {
                resultBuilder.append(word);
            }
            if (i < words.length - 1) {
                resultBuilder.append(" ");
            }
        }
        return resultBuilder.toString();
    }

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList("a", "aa", "aaa", "aaaa");
        String sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa";

        System.out.println(replaceWords(dictionary, sentence));
    }
}
