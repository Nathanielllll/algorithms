import commonStructures.Edge;
import commonStructures.Graph;
import commonStructures.GraphGenerator;
import commonStructures.GraphNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
适用范围:没有权值为负数的边
 */
public class Dijkstra {
    public static void main(String[] args) {
        Integer[][] matrix= {{7,0,1}, {3,1,2},{5,0,2},
                {3,2,3},{4,2,4},{1,4,5},{2,4,6},{3,5,7}};
        Graph graph = GraphGenerator.createGraph(matrix);
        HashMap<GraphNode, Integer> result = dijkstra1(graph.nodes.get(0));
    }

    public static HashMap<GraphNode, Integer> dijkstra1(GraphNode from) {
        // 从head出发到所有点的最小距离
        // key : 从from出发到达key
        // value : 从from出发到达key的最小距离
        // 如果在表中，没有T的记录，含义是从from出发到T这个点的距离为正无穷
        HashMap<GraphNode, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        // 已经求过距离的节点，存在selectedNodes中，以后再也不碰
        HashSet<GraphNode> selectedNodes = new HashSet<>();
        GraphNode minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            // 此时选的最小记录，是从from -> minNode (distance)
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                GraphNode toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min(
                            distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static GraphNode getMinDistanceAndUnselectedNode(HashMap<GraphNode, Integer> distanceMap, HashSet<GraphNode> selectedNodes) {
        GraphNode minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<GraphNode, Integer> entry : distanceMap.entrySet()) {
            GraphNode node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }
}
