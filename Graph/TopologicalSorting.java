import commonStructures.Graph;
import commonStructures.GraphNode;

import java.util.*;

/*
    B->
A->     D->E->F->G  ----->打印出ABCDEFG
    C->

    适用范围：要求是有向图，且有入度为0的节点，且没有环
    无向图没有拓扑排序
    循环依赖：两个包互相依赖。只有可能E2.0依赖A1.0，A1.0依赖E1.0
 */
public class TopologicalSorting {
    // directed graph and no loop
    public static List<GraphNode> sortedTopology(Graph graph) {
        // key：某一个node
        // value：剩余的入度
        HashMap<GraphNode, Integer> inMap = new HashMap<>();
        // 入度为0的点，才能进这个队列
        Queue<GraphNode> zeroInQueue = new LinkedList<>();
        for (GraphNode node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {//首先要找入度为0的节点！
                zeroInQueue.add(node);
            }
        }
        // 拓扑排序的结果，依次加入result
        List<GraphNode> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            GraphNode cur = zeroInQueue.poll();
            result.add(cur);
            for (GraphNode next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);//把当前节点的next节点的入度减1
                if (inMap.get(next) == 0) {//如果当前节点的next节点入度为0，就加入zeroInQueue
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
