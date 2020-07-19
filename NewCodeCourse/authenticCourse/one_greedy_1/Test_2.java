package authenticCourse.one_greedy_1;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
容器装水，地形盛水问题
使用优先队列（堆）
1.四边入队
2.从堆中pop出num，看num能不能更新max。
3.num周围没有进过堆的数x入队，产生水量max-x（如果max > x）
 */
public class Test_2 {
    public static class Node {
        int value;//可以用来更新max
        int row;//row & col可以知道临近位置
        int col;

        public Node(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
            return 0;
        }

        int N = heightMap.length;
        int M = heightMap[0].length;
        boolean[][] isEnter = new boolean[N][M];
        PriorityQueue<Node> heap = new PriorityQueue<Node>(new NodeComparator());

        //1.将四边入堆
        for (int col = 0; col < M - 1; col++) {
            isEnter[0][col] = true;
            heap.add(new Node(heightMap[0][col], 0, col));
        }
        for (int row = 0; row < N - 1; row++) {
            isEnter[row][M - 1] = true;
            heap.add(new Node(heightMap[row][M - 1], row, M - 1));
        }
        for (int col = M - 1; col > 0; col--) {
            isEnter[N - 1][col] = true;
            heap.add(new Node(heightMap[N - 1][col], N - 1, col));
        }
        for (int row = N - 1; row > 0; row--) {
            isEnter[row][0] = true;
            heap.add(new Node(heightMap[row][0], row, 0));
        }

        int water = 0;
        int max = 0;
        while (!heap.isEmpty()) {
            //2.从堆中pop出num，看num能不能更新max。
            Node cur = heap.poll();

            //(1)cur更新max。因为弹出来的是cur，则下次一定是以cur（新的max）作为盛水瓶颈
            //(2)cur不更新max。只能是因为cur进去的时候，是以max作为瓶颈的
            max = Math.max(max, cur.value);//此时的盛水瓶颈
            int r = cur.row;
            int c = cur.col;
            int value = 0;
            //3.num周围没有进过堆的数x入队，产生水量max-x（如果max > x）
            if (r > 0 && !isEnter[r - 1][c]) {
                value = heightMap[r - 1][c];
                water += Math.max(0, max - value);
                isEnter[r - 1][c] = true;
                heap.add(new Node(heightMap[r - 1][c], r - 1, c));
            }
            if (r < N - 1 && !isEnter[r + 1][c]) {
                value = heightMap[r + 1][c];
                water += Math.max(0, max - value);
                isEnter[r + 1][c] = true;
                heap.add(new Node(heightMap[r + 1][c], r + 1, c));
            }
            if (c > 0 && !isEnter[r][c - 1]) {
                value = heightMap[r][c - 1];
                water += Math.max(0, max - value);
                isEnter[r][c - 1] = true;
                heap.add(new Node(heightMap[r][c - 1], r, c - 1));
            }
            if (c < M - 1 && !isEnter[r][c + 1]) {
                value = heightMap[r][c + 1];
                water += Math.max(0, max - value);
                isEnter[r][c + 1] = true;
                heap.add(new Node(heightMap[r][c + 1], r, c + 1));
            }
        }
        return water;
    }
}
