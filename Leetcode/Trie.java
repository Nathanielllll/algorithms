public class Trie {
    /*
    实现一个 Trie (前缀树)，包含insert,search, 和startsWith这三个操作。

示例:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");
trie.search("app");     // 返回 true
说明:

你可以假设所有的输入都是由小写字母a-z构成的。
保证所有输入均为非空字符串。
     */

    private boolean isEnd;
    private Trie[] children;

    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        children = new Trie[26];
    }

    /** Inserts a word into the trie. */
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

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie root = this;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (root.children[c - 'a'] == null) {
                return false;
            }
            root = root.children[c - 'a'];
        }
        return root.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie root = this;
        char[] chars = prefix.toCharArray();
        for (char c : chars) {
            if (root.children[c - 'a'] == null) {
                return false;
            }
            root = root.children[c - 'a'];
        }
        return true;
    }


}
