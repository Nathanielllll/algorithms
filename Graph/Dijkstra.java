import commonStructures.Edge;
import commonStructures.GraphNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
适用范围:没有权值为负数的边
 */
public class Dijkstra {
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

    public static GraphNode getMinDistanceAndUnselectedNode(HashMap<GraphNode, Integer> distanceMap, HashSet<GraphNode> touchedNodes) {
        GraphNode minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<GraphNode, Integer> entry : distanceMap.entrySet()) {
            GraphNode node = entry.getKey();
            int distance = entry.getValue();
            if (!touchedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }
}
