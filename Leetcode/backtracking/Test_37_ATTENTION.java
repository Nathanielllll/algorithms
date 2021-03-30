package backtracking;
/*
37. 解数独
编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
空白格用 '.' 表示。

https://leetcode-cn.com/problems/sudoku-solver/solution/37-jie-shu-du-hui-su-sou-suo-suan-fa-xiang-jie-by-/

 */
public class Test_37_ATTENTION {
    public void solveSudoku(char[][] board) {
        backtracking(board);
    }

    private boolean backtracking(char[][] board) {
        for (int i = 0; i < board.length; i++) { // 遍历行
            for (int j = 0; j < board[0].length; j++) { // 遍历列
                if (board[i][j] != '.') continue;
                for (char k = '1'; k <= '9'; k++) { // (i, j) 这个位置放k是否合适
                    if (isValid(i, j, k, board)) {
                        board[i][j] = k;
                        if (backtracking(board)) return true; // 如果找到合适一组立刻返回
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true; // 遍历完没有返回false，说明找到了合适棋盘位置了
    }

    private boolean isValid(int row, int col, char val, char[][] board) {
        for (int i = 0; i < 9; i++) { // 判断行里是否重复
            if (board[row][i] == val) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) { // 判断列里是否重复
            if (board[j][col] == val) {
                return false;
            }
        }
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) { // 判断9方格里是否重复
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == val ) {
                    return false;
                }
            }
        }
        return true;
    }

}
