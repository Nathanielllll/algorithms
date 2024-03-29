import commonStructures.Graph;
import commonStructures.GraphGenerator;
import commonStructures.GraphNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    /**
     * 图和树的最大区别在于图的下一个节点可能指向已访问过的节点。
     * 因此在使用BFS及DFS遍历时，应维护一个Set，Set中存放已被访问过的节点，在遍历时先判断节点未被访问过再遍历即可。
     *
     * 要说框架的话，我们先举例一下 BFS 出现的常见场景好吧，问题的本质就是让你在一幅「图」中找到从起点start到终点target的最近距离，
     * 这个例子听起来很枯燥，但是 BFS 算法问题其实都是在干这个事儿。
     *
     * BFS就是求最短距离的！！！！
     */

    public static void main(String[] args) {
        Integer[][] matrix= {{7,0,1}, {3,1,2},{5,0,2},
                {3,2,3},{4,2,4},{1,4,5},{2,4,6},{3,5,7}};
        Graph graph = GraphGenerator.createGraph(matrix);
        bfs(graph.nodes.get(2));
    }

    public static void bfs(GraphNode node) {
        if (node == null) {
            return;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        HashSet<GraphNode> visited = new HashSet<>();//不让一个节点重复进队列的检查机制
        queue.add(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            System.out.println(cur.value);

            for (GraphNode next : cur.nexts) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
