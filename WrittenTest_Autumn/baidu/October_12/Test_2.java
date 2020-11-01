package baidu.October_12;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Test_2 {


    public static int N; // 多少个队伍
    public static int M; // 多少场比赛
    public static int[] degree = new int[501];
    public static int[][] vis = new int[501][501];
    public static PriorityQueue<Integer> priorityQueue;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();

        Arrays.fill(degree, 0);
        for (int i = 0; i < vis.length; i++) {
            Arrays.fill(vis[i], 0);
        }

        int first = 0;
        int second = 0;
        for (int i = 0; i < M; i++) {
            first = scanner.nextInt();
            second = scanner.nextInt();
            if(vis[first][second] == 0){
                vis[first][second] = 1;
                // 是输了的队伍++
                degree[second]++;
            }
        }

        topologicalSort();
    }

    private static void topologicalSort(){
        int num = 0; // 人员的数量
        priorityQueue = new PriorityQueue<>();

        // 把没有输的人的编号放进去
        for (int i = 1; i <= N; i++) {
            if(degree[i] == 0){
                priorityQueue.add(i);
            }
        }

        while(!priorityQueue.isEmpty()){
            int u = priorityQueue.poll();
            System.out.print(u);
            num++;
            if(num < N){
                System.out.print(" ");
            }else {
                System.out.println();
            }
            for (int i = 0; i < vis[u].length; i++) {
                if(vis[u][i] > 0){
                    degree[i]--;
                    if(degree[i] == 0){
                        priorityQueue.add(i);
                    }
                }
            }
        }
    }
}
