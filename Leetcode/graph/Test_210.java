package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test_210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();

        int[] res = new int[numCourses];
        int index = 0;

        int[] in = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            //入度。如0->1，则0的入度为0，1的入度为1
            in[edge[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        //加入入度为0的
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                queue.add(i);
                res[index++] = i;
            }
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int next : graph.get(cur)) {
                    //减去入度
                    in[next]--;
                    if (in[next] == 0){
                        queue.add(next);
                        res[index++] = next;
                    }
                }
            }
        }

        if (index == numCourses) {
            return res;
        }else {
            return new int[] {};
        }
    }
}
