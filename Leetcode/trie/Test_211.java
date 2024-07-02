package trie;

/**
 * @author luzhi
 * @date 2021/3/27
 */
public class Test_211 {

  class WordDictionary {

    PrefixTree trie;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
      trie = new PrefixTree();
    }

    public void addWord(String word) {
      trie.insert(word);
    }

    public boolean search(String word) {
      return trie.search(word);
    }
  }

  class PrefixTree {

    private PrefixTreeNode root;

    private class PrefixTreeNode {

      PrefixTreeNode[] children;
      boolean isEnd;

      public PrefixTreeNode() {
        this.children = new PrefixTreeNode[26];
        this.isEnd = false;
      }
    }

    public PrefixTree() {
      this.root = new PrefixTreeNode();
    }

    public void insert(String word) {
      char[] chars = word.toCharArray();
      PrefixTreeNode tmp = root;
      for (char ch : chars) {
        int idx = ch - 'a';
        if (tmp.children[idx] == null) {
          tmp.children[idx] = new PrefixTreeNode();
        }
        tmp = tmp.children[idx];
      }
      tmp.isEnd = true;
    }

    public boolean search(String word) {
      char[] chars = word.toCharArray();
      PrefixTreeNode tmp = root;
      return dfs(chars, tmp, 0);
    }

    private boolean dfs(char[] chars, PrefixTreeNode root, int pos) {
      if (pos == chars.length && root.isEnd) {
        return true;
      }
      boolean result = false;
      if (pos < chars.length) {
        char ch = chars[pos];
        PrefixTreeNode[] children = root.children;
        if (ch == '.') {
          for (int i = 0; i < 26; i++) {
            if (children[i] != null) {
              result = result || dfs(chars, children[i], pos + 1);
            }
          }
        } else if (children[ch - 'a'] != null) {
          result = dfs(chars, children[ch - 'a'], pos + 1);
        }
      }
      return result;
    }
  }
}
