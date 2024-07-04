package graph.topologicalSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//拓扑排序，可以判断有没有环
public class Test_207 {

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] in = new int[numCourses];
        for (int[] edge : prerequisites) {
            int prev = edge[1];
            int next = edge[0];
            in[next]++;
            graph.computeIfAbsent(prev, k -> new HashSet<>()).add(next);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                int course = queue.poll();
                Set<Integer> nexts = graph.get(course);
                if (nexts != null) {
                    for (Integer next : nexts) {
                        in[next]--;
                        if (in[next] == 0) {
                            queue.add(next);
                        }
                    }
                }
            }
        }
        //如果还有入度>0的节点，则不存在拓扑排序，则存在环。
        for (int i = 0; i < numCourses; i++) {
            if (in[i] != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int numCourses=4;
        int[][] prerequisites = {{0,1},{1,2},{2,3},{3,0}};
        System.out.println(canFinish_1(numCourses, prerequisites));
    }

    /**
     * 判断有向图是否有环，首先可以想到DFS。
     * 分为三个状态，0,1，-1；
     * 0:未扫描过。
     * 1:上一轮扫描过。
     * -1:当前轮扫描过。
     * 某一轮扫描中，遇到了-1，代表成环。
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites){
        int[] isVisited = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }
        // 表示a->b的边
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
        }
        for(int i = 0; i < numCourses; i++){
            if(!dfsFinish(graph, i, isVisited))
                return false;
        }
        return true;
    }

    /**
     * 0表示还未访问过，1表示已经访问了(被之前的dfs访问)，-1表示有冲突(当前dfs过程中再次进入)
     */
    private static boolean dfsFinish(List<List<Integer>> graph, int visit, int[] isVisited){
        if(1 == isVisited[visit])
            return true;
        if(-1 == isVisited[visit])
            return false;
        isVisited[visit] = -1;
        for(int next : graph.get(visit)){
            if(!dfsFinish(graph, next, isVisited))
                return false;
        }
        isVisited[visit] = 1;
        return true;
    }


    /**
     * 拓扑排序/bfs
     *
     * 上面方法虽然可以高效地解决问题，但该题的本质是考察拓扑排序。
     * 下一题就会要求规划处一个可行的课程安排，DFS就无法办到了。
     * 拓扑排序：
     * 有向图，那么每个节点的连线有进有出。
     * 我们先记录每个节点的进入的线，称为“入度”。
     * 然后本质我们要做的就是每次从图中剥离无“入度”的点，看最后能否把图剥完。
     * 在代码中，以BFS的方式，每轮干两件事。
     * 1.剥离“入度”为0的点。（剥节点）
     * 2.去掉这些点所指向其他点的“入度”。（剥节点连线）
     * 如果期间产生了某个“入度”为0的点，再次加入队列。
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish_1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
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
        for (int i = 0; i < numCourses; i++) {
            //入度为0的点
            if (in[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                //next是进入的，因此对next的入度减1
                for (int next : graph.get(cur)) {
                    in[next]--;
                    if (in[next] == 0) queue.add(next);
                }
            }
        }

        //如果还有入度>0的节点，则不存在拓扑排序，则存在环。
        for (int i = 0; i < numCourses; i++)
            if (in[i] != 0) return false;
        return true;
    }
}
