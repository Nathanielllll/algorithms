package netEase.April_11;

import java.util.*;

/**
 * 轮数T
 * 输入变成M，刀长L
 * <p>
 * 2
 * 10 3
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 2 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 1 0 2 0 0 0
 * 0 0 0 0 0 0 0 0 0 2
 * 0 0 0 1 0 0 0 0 0 1
 * 0 0 0 1 0 0 2 0 2 0
 * 8 8
 * 10 2
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0
 * 0 0 2 0 0 1 0 0 0 0
 * 0 0 0 1 2 0 2 0 0 0
 * 0 0 0 0 2 1 2 0 2 0
 * 0 0 2 0 0 0 0 0 2 0
 * 0 0 0 0 0 0 0 2 0 0
 * 0 0 0 1 0 0 0 0 0 0
 * 2 5
 */
public class Test_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        for (int t = 0; t < total; t++) {
            int M = scanner.nextInt();
            int L = scanner.nextInt();

            int[][] nums = new int[M][M];

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    nums[i][j] = scanner.nextInt();
                }
            }

            int x = scanner.nextInt();
            int y = scanner.nextInt();

            double[][] Distance = new double[M][M];
            int result = subProcess(nums, x, y, L, M, Distance);

            System.out.println(result);
        }


//        int[][] nums = {{0,1,0,0},{0,0,0,0},{0,2,0,1},{0,0,3,0}};
//        int x = 0, y=0;
//        int L = 1;
//        int M = 4;
//        double[][] Distance = new double[M][M];
//
//        System.out.println(subProcess(nums, x, y, L, M, Distance));

    }

    public static int subProcess(int[][] nums, int x, int y, int L, int M, double[][] Distance) {
        //计算出距离
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (nums[i][j] != 0) {
                    double distance = calculateDistance(x, y, i, j);
                    Distance[i][j] = distance;
                }
            }
        }

        //距离，对应的补给的大顶堆
        HashMap<Double, PriorityQueue<Integer>> hashMap = new HashMap<>();
        //将对应的坐标放入minHeap，条件是
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (Distance[i][j] > 0.0) {
                    if (hashMap.get(Distance[i][j]) == null) {
                        //距离相同的情况，让补给多的排在前面
                        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
                        maxHeap.add(nums[i][j]);
                        hashMap.put(Distance[i][j], maxHeap);
                    } else {
                        PriorityQueue<Integer> maxHeap = hashMap.get(Distance[i][j]);
                        maxHeap.add(nums[i][j]);
                        hashMap.put(Distance[i][j], maxHeap);
                    }
                }
            }
        }

        //刀的长度

        //由小到大排列的distances
        List<Double> sort_distance = new LinkedList<>();
        for (double distance : hashMap.keySet()) {
            sort_distance.add(distance);
        }
        Collections.sort(sort_distance);

        outer:
        for (int i = 0; i < sort_distance.size(); i++) {
            double distance = sort_distance.get(i);
            PriorityQueue<Integer> maxHeap = hashMap.get(distance);
            while (!maxHeap.isEmpty()) {
                if (L >= distance) {
                    L += maxHeap.poll();
                }else {
                    break outer;
                }
            }
        }
        return L;
    }

    public static double calculateDistance(int x1, int y1, int x2, int y2) {
        double dx = Math.pow(x1 - x2, 2);
        double dy = Math.pow(y1 - y2, 2);
        double res = Math.pow(dx + dy, 0.5);
        return res;
    }
}
