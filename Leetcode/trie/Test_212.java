package trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luzhi
 * @date 2021/3/27
 */
public class Test_212 {

    public static void main(String[] args) {
        Test_212 test = new Test_212();
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        System.out.println(test.findWords(board, words));
    }

    // 改造了Trie，直接把word放进去
    class Trie {
        String word;
        Trie[] children;

        public Trie() {
            this.word = null;
            this.children = new Trie[26];
        }

        void insert(String word) {
            Trie root = this;
            char[] chars = word.toCharArray();
            for (char ch : chars) {
                if (root.children[ch - 'a'] == null) {
                    root.children[ch - 'a'] = new Trie();
                }
                root = root.children[ch - 'a'];
            }
            root.word = word;
        }
    }

    List<String> result;

    public List<String> findWords(char[][] board, String[] words) {
        result = new ArrayList<>();

        Trie root = new Trie();
        for (String word : words) {
            root.insert(word);
        }

        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(board, i, j, visited, root);
            }
        }
        return result;
    }

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    private void dfs(char[][] board, int row, int col, boolean[][] visited, Trie root) {
        if (root.word != null) {
            result.add(root.word);
            // 防止重复
            root.word = null;
        }

        if (row >= 0 && row < board.length && col >= 0 && col < board[0].length
                && !visited[row][col] && root.children[board[row][col] - 'a'] != null) {
            visited[row][col] = true;
            root = root.children[board[row][col] - 'a'];
            for (int k = 0; k < 4; k++) {
                int nextRow = dx[k] + row;
                int nextCol = dy[k] + col;
                dfs(board, nextRow, nextCol, visited, root);
            }
            visited[row][col] = false;
        }
    }
}
