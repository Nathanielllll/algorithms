package baidu.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author luzhi
 * @date 2021/4/28
 */
public class Test_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int personNum = scanner.nextInt();
        int competitionNum = scanner.nextInt();
        int niuRealNum = scanner.nextInt();
        Graph graph = new Graph(personNum);
        for (int i = 0; i < competitionNum; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            graph.addEdge(start - 1, end - 1);
        }

        process(graph, niuRealNum);
    }

    static int winNiuNum = 0; // 赢过牛牛的人数
    static int loseNiuNum = 0;// 输过牛牛的人数

    public static void process(Graph graph, int niuRealNum) {
        boolean[] visited = new boolean[graph.points.size()];
        for (int i = 0; i < graph.points.size(); i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, niuRealNum - 1);
            }
        }

        for (int i = winNiuNum; i < graph.points.size() - loseNiuNum; i++) {
            System.out.print((i + 1) + " ");
        }
    }


    public static void dfs(Graph graph, int i, boolean[] visited, int niuNum) {
        if (visited[i]) return;

        visited[i] = true;

        if (i == niuNum) {
            loseNiuNum += graph.points.get(i).size();
        }

        List<Integer> neighbors = graph.points.get(i);
        for (int j = 0; j < neighbors.size(); j++) {
            int neighbor = neighbors.get(j);
            if (neighbor == niuNum) {
                winNiuNum++;
            }
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited, niuNum);
            }
        }
    }

    static class Graph {
        List<List<Integer>> points;

        public Graph(int n) {
            this.points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                points.add(new ArrayList<>());
            }
        }

        public void addEdge(int start, int end) {
            points.get(start).add(end);
        }
    }
}
