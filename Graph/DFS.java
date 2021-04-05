import commonStructures.Graph;
import commonStructures.GraphGenerator;
import commonStructures.GraphNode;

import java.util.*;

/**
 * dfs 其实和树的前序遍历，非常的类似！
 */
public class DFS {
    public static void main(String[] args) {
        Integer[][] matrix= {{7,0,1}, {3,1,2},{5,0,2},
                {3,2,3},{4,2,4},{1,4,5},{2,4,6},{3,5,7}};
        Graph graph = GraphGenerator.createGraph(matrix);
        dfs(graph.nodes.get(2));
        System.out.println("============>");
        dfs_1(graph.nodes.get(2));
    }

    //两种都没毛病啊，只是遍历的次序不一样而已
    public static void dfs(GraphNode node) {
        if (node == null) {
            return;
        }
        Stack<GraphNode> stack = new Stack<>();
        HashSet<GraphNode> visited = new HashSet<>();
        stack.push(node);
        visited.add(node);

        while (!stack.isEmpty()) {
            GraphNode cur = stack.pop();
            System.out.println(cur.value);

            for (GraphNode next : cur.nexts) {
                if (!visited.contains(next)) {
                    stack.push(next);
                    visited.add(next);
                }
            }
        }
    }


    static HashSet<GraphNode> visited;
    public static void dfs_1(GraphNode node) {
        if (node == null) {
            return;
        }
        visited = new HashSet<>();

        helper(node);
    }

    private static void helper(GraphNode node){
        if (node == null) {
            return;
        }

        visited.add(node);
        System.out.println(node.value);
        for(GraphNode next : node.nexts){
            if (!visited.contains(next)) {
                helper(next);
            }
        }
    }
}
