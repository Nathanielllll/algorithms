import commonStructures.Graph;
import commonStructures.GraphGenerator;
import commonStructures.GraphNode;

import java.util.*;

public class DFS {
    public static void main(String[] args) {
        Integer[][] matrix= {{7,0,1}, {3,1,2},{5,0,2},
                {3,2,3},{4,2,4},{1,4,5},{2,4,6},{3,5,7}};
        Graph graph = GraphGenerator.createGraph(matrix);
        dfs(graph.nodes.get(2));
    }

    //两种都没毛病啊，只是遍历的次序不一样而已
    public static void dfs(GraphNode node) {
        if (node == null) {
            return;
        }
        Stack<GraphNode> stack = new Stack<>();
        HashSet<GraphNode> set = new HashSet<>();
        stack.push(node);
        set.add(node);

        while (!stack.isEmpty()) {
            GraphNode cur = stack.pop();
            System.out.println(cur.value);

            for (GraphNode next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(next);
                    set.add(next);
                }
            }
        }
    }

//    public static void dfs(GraphNode node) {
//        if (node == null) {
//            return;
//        }
//        Stack<GraphNode> stack = new Stack<>();
//        HashSet<GraphNode> set = new HashSet<>();
//        stack.add(node);
//        set.add(node);
//        System.out.println(node.value);
//        while (!stack.isEmpty()) {
//            GraphNode cur = stack.pop();
//            for (GraphNode next : cur.nexts) {
//                if (!set.contains(next)) {
//                    stack.push(cur);
//                    stack.push(next);
//                    set.add(next);
//                    System.out.println(next.value);
//                    break;
//                }
//            }
//        }
//    }



}
