package trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luzhi
 * @date 2021/3/27
 */
public class Test_212 {

    public List<String> findWords(char[][] board, String[] words) {
    PrefixTree2 tree = new PrefixTree2();
    for (String word : words) {
      tree.insert(word);
    }

    int m = board.length;
    int n = board[0].length;
    List<String> result = new ArrayList<>();
    int[][] used = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        findWordsDfs(board, used, i, j, m, n, result, tree);
      }
    }
    return result;
  }

  private void findWordsDfs(char[][] board, int[][] used, int i, int j,
      int m, int n, List<String> result, PrefixTree2 tree) {
    if (tree.word != null) {
      result.add(tree.word);
      tree.word = null;
    }

    if (i >= 0 && i < m && j >= 0 && j < n && used[i][j] == 0
        && tree.children[board[i][j] - 'a'] != null) {
      used[i][j] = 1;
      tree = tree.children[board[i][j] - 'a'];
      findWordsDfs(board, used, i - 1, j, m, n, result, tree);
      findWordsDfs(board, used, i + 1, j, m, n, result, tree);
      findWordsDfs(board, used, i, j - 1, m, n, result, tree);
      findWordsDfs(board, used, i, j + 1, m, n, result, tree);
      used[i][j] = 0;
    }
  }

  class PrefixTree2 {

    String word;
    PrefixTree2[] children;

    public PrefixTree2() {
      this.word = null;
      this.children = new PrefixTree2[26];
    }

    void insert(String word) {
      PrefixTree2 root = this;
      char[] chars = word.toCharArray();
      for (char ch : chars) {
        if (root.children[ch - 'a'] == null) {
          root.children[ch - 'a'] = new PrefixTree2();
        }
        root = root.children[ch - 'a'];
      }
      root.word = word;
    }
  }
}
