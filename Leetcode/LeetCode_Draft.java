import linkedList.Test_109;

import java.util.*;
import java.util.concurrent.DelayQueue;
import java.util.jar.JarEntry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeetCode_Draft {

    public static void main(String[] args) {
//        int n = 2;
//        int[][] edges = {{0,1}};
//        System.out.println(findMinHeightTrees(n, edges));
        int n = 15;
        System.out.println((n & (n-1)) == 0);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        Collections.synchronizedMap(hashMap);
    }

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            return list;
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] degree = new int[n];
        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
            degree[node1]++;
            degree[node2]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                degree[i]--;
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result = new ArrayList<>();
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                int from = queue.poll();
                result.add(from);
                List<Integer> tos = graph.get(from);
                for(int to : tos){
                    degree[to]--;
                    if (degree[to] == 1) {
                        queue.add(to);
                    }
                }
            }
        }
        return result;

    }


}