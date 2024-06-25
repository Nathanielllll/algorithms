package graph.unionFind;

import java.util.HashMap;

/*
N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。

人和座位用0到2N-1的整数表示，情侣们按顺序编号，第一对是(0, 1)，第二对是(2, 3)，以此类推，最后一对是(2N-2, 2N-1)。

这些情侣的初始座位row[i]是由最初始坐在第 i 个座位上的人决定的。

示例 1:

输入: row = [0, 2, 1, 3]
输出: 1
解释: 我们只需要交换row[1]和row[2]的位置即可。
示例 2:

输入: row = [3, 2, 0, 1]
输出: 0
解释: 无需交换座位，所有的情侣都已经可以手牵手了。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/couples-holding-hands
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test_765 {
    public static void main(String[] args) {
        int[] row = {0,2,4,6,7,1,3,5};
        System.out.println(minSwapsCouples_2(row));
    }

    // 并查集
    public static int minSwapsCouples(int[] row) {
        int length = row.length / 2;
        UnionFind unionFind = new UnionFind(length);
        for (int i = 0; i < row.length; i += 2) {
            // 如果row[i] / 2, row[i + 1] / 2可以计算出row[i]和row[i + 1]的root
            // 如果他们不是属于同一个root，就会union到一起，也就是会交换一次位置
            unionFind.union(row[i] / 2, row[i + 1] / 2);
        }
        return unionFind.getCount();
    }

    static class UnionFind {
        private int[] parent;
        // 用于记录并查集的数量,某些情况下该成员非必要
        private int count;
        private int[] rank;

        public UnionFind(int length) {
            this.parent = new int[length];
            this.count = 0;
            this.rank = new int[length];

            for (int i = 0; i < length; i++) {
                parent[i] = i;
            }
        }

        public int getCount() {
            return count;
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int x_root = find(x);
            int y_root = find(y);
            if (x_root == y_root) {
                return;
            }

            if (rank[x_root] < rank[y_root]) {
                parent[x_root] = y_root;
            } else if (rank[y_root] < rank[x_root]) {
                parent[y_root] = x_root;
            } else {
                parent[x_root] = y_root;
                rank[y_root]++;
            }
            count++;
        }
    }










    /**
     * 贪心算法
     *
     * @param row
     * @return 这题使用贪心是最简单的方法。该策略是说，我们遍历每个偶数位置 2 * i ，把它的对象安排到它右边的奇数位置 2 * i + 1。
     */
    // 时间复杂度O(N)，空间复杂度O(N)
    public static int minSwapsCouples_2(int[] row) {
        int result = 0;
        // num -> index
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < row.length; i++) {
            hashMap.put(row[i], i);
        }
        for (int i = 0; i < row.length; i+=2) {
            int p1 = row[i];
            int p2 = (p1 & 1) == 0 ? p1 + 1 : p1 - 1; // p1的情侣
            if (row[i + 1] == p2) {
                continue;
            }
            int p2Index = hashMap.get(p2);
            swap(row, i + 1, p2Index);
            // 更新hashMap
            hashMap.put(row[i + 1], i + 1);
            hashMap.put(row[p2Index], p2Index);
            result++;
        }
        return result;
    }

    // 时间复杂度O(N*N)，空间复杂度O(1)
    public static int minSwapsCouples_1(int[] row) {
        int result = 0;
        for (int i = 0; i < row.length; i += 2) {
            if (row[i] / 2 == row[i + 1] / 2) {
                continue;
            }
            for (int j = i + 2; j < row.length; j++) {
                if (row[i] / 2 == row[j] / 2) {
                    swap(row, i + 1, j);
                    result++;
                }
            }
        }
        return result;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
