package trie;

public class Test_208_Trie {

  class PrefixTreeNode {

    PrefixTreeNode[] children;
    boolean isEnd;

    public PrefixTreeNode() {
      this.children = new PrefixTreeNode[26];
      this.isEnd = false;
    }
  }

  class Trie {

    private final PrefixTreeNode root;
    public Trie() {
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
      for (char ch : chars) {
        int idx = ch - 'a';
        if (tmp.children[idx] == null) {
          return false;
        }
        tmp = tmp.children[idx];
      }
      return tmp.isEnd;
    }

    public boolean startsWith(String prefix) {
      char[] chars = prefix.toCharArray();
      PrefixTreeNode tmp = root;
      for (char ch : chars) {
        int idx = ch - 'a';
        if (tmp.children[idx] == null) {
          return false;
        }
        tmp = tmp.children[idx];
      }
      return true;
    }
  }

}
