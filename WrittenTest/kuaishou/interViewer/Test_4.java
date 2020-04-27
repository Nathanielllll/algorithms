package kuaishou.interViewer;///*
//[[*,.,*,*,.],
// [*,.,*,*,*],
// [*,.,*,*,.]]
//
//4
//
// */
//public class Test_4 {
//    public static void main(String[] args) {
//        char[][] pos = {{'*','.','*','*','.'},{'*','.','*','*','*'},{'*','.','*','*','.'}};
//        GetMaxStaffs(pos);
//    }
//
//    public static void GetMaxStaffs(char[][] pos) {
//        // write code here
//        int rows = pos.length;
//        int cols = pos[0].length;
//        boolean[][] used = new boolean[rows][cols];
//
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                int count = backTracking(pos, i, j, rows, cols, used);
//                System.out.println(count);
//            }
//        }
//
//
//    }
//
//    //从每个row，col都走一遍程序
//    public static int backTracking(char[][] pos, int row, int col, int rows, int cols,
//                             boolean[][] used){
//
//        int count = 0;
////        if (pos[row][col] == '*' && row >= 0 && row < rows && col >= 0 && col < cols) {
////            count = backTracking(pos, row + 1, col, rows, cols, used) +
////                    backTracking(pos, row - 1, col, rows, cols, used) +
////                    backTracking(pos, row, col + 1, rows, cols, used) +
////                    backTracking(pos, row, col - 1, rows, cols, used);
////        }else
//        if(row == )
//        if(row >= 0 && row < rows && col >= 0 && col < cols && !used[row][col] &&
//                //四周都没有坐人的情况
//            !used[row + 1][col] && !used[row - 1][col] && !used[row][col + 1] && !used[row + 1][col - 1]){
//
//            used[row][col] = true;
//            count = 1 +
//                    backTracking(pos, row + 1, col, rows, cols, used) +
//                    backTracking(pos, row - 1, col, rows, cols, used) +
//                    backTracking(pos, row, col + 1, rows, cols, used) +
//                    backTracking(pos, row, col - 1, rows, cols, used);
//        }
//        return count;
//    }
//}
