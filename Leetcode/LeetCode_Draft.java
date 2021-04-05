import java.util.*;

public class LeetCode_Draft {
    static class GraphNode {
        int value;
        int in;
        int out;
        ArrayList<Edge> edges;
        ArrayList<GraphNode> nexts;

        public GraphNode(int value) {
            this.value = value;
            this.in = 0;
            this.out = 0;
            this.edges = new ArrayList<>();
            this.nexts = new ArrayList<>();
        }
    }

    static class Edge {
        int weight;
        GraphNode fromNode;
        GraphNode toNode;

        public Edge(int weight, GraphNode fromNode, GraphNode toNode) {
            this.weight = weight;
            this.fromNode = fromNode;
            this.toNode = toNode;
        }
    }

    static class Graph {
        HashMap<Integer, GraphNode> nodes;
        HashSet<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    static class GraphGenerator {
        public static Graph createGraph(Integer[][] matrix) {
            Graph graph = new Graph();
            for (int i = 0; i < matrix.length; i++) {
                int weight = matrix[i][0];
                int from = matrix[i][1];
                int to = matrix[i][2];

                if (!graph.nodes.containsKey(from)) {
                    graph.nodes.put(from, new GraphNode(from));
                }
                if (!graph.nodes.containsKey(to)) {
                    graph.nodes.put(to, new GraphNode(to));
                }

                GraphNode fromNode = graph.nodes.get(from);
                GraphNode toNode = graph.nodes.get(to);
                fromNode.out++;
                fromNode.nexts.add(toNode);
                toNode.in++;
                Edge edge = new Edge(weight, fromNode, toNode);
                fromNode.edges.add(edge);
                graph.edges.add(edge);
            }
            return graph;
        }
    }

    public static void bfs(GraphNode node) {
        if (node == null) {
            return;
        }

        Queue<GraphNode> queue = new LinkedList<>();
        HashSet<GraphNode> visited = new HashSet<>();
        queue.add(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            GraphNode tmp = queue.poll();
            System.out.print(tmp.value + " ");
            for (GraphNode nextNode : tmp.nexts) {
                if (!visited.contains(nextNode)) {
                    visited.add(nextNode);
                    queue.add(nextNode);
                }
            }
        }
    }

    public static void dfs(GraphNode node) {
        HashSet<GraphNode> visited = new HashSet<>();
        visited.add(node);
        process(node, visited);
    }

    private static void process(GraphNode node, HashSet<GraphNode> visited) {
        if (node == null) {
            return;
        }

        System.out.print(node.value + " ");
        ArrayList<GraphNode> nexts = node.nexts;
        for (GraphNode next : nexts) {
            if (!visited.contains(next)) {
                visited.add(next);
                process(next, visited);
            }
        }
    }

    public static List<GraphNode> sortedTopology(Graph graph) {
        List<GraphNode> result = new ArrayList<>();

        HashMap<GraphNode, Integer> nodeToIn = new HashMap<>();
        Queue<GraphNode> zeroQueue = new LinkedList<>();
        for (GraphNode node : graph.nodes.values()) {
            nodeToIn.put(node, node.in);
            if (node.in == 0) {
                zeroQueue.add(node);
            }
        }

        while (!zeroQueue.isEmpty()) {
            int cnt = zeroQueue.size();
            for (int i = 0; i < cnt; i++) {
                GraphNode tmp = zeroQueue.poll();
                result.add(tmp);
                for (GraphNode next : tmp.nexts) {
                    nodeToIn.put(next, nodeToIn.get(next) - 1);
                    if (nodeToIn.get(next) == 0) {
                        zeroQueue.add(next);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[][] matrix = {{7, 0, 1}, {3, 1, 2}, {5, 0, 2},
                {3, 2, 3}, {4, 2, 4}, {1, 4, 5}, {2, 4, 6}, {3, 5, 7}};
        Graph graph = GraphGenerator.createGraph(matrix);
        dfs(graph.nodes.get(2));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }


        int[] in = new int[numCourses];
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            in[edge[0]]++;
        }

        Queue<Integer> zeroQueue = new LinkedList<>();
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                zeroQueue.add(i);
            }
        }

        int index = 0;
        while (!zeroQueue.isEmpty()) {
            int cnt = zeroQueue.size();
            for (int i = 0; i < cnt; i++) {
                int cur = zeroQueue.poll();
                index++;
                for (int next : graph.get(cur)) {
                    in[next]--;
                    if (in[next] == 0) {
                        zeroQueue.add(next);
                    }
                }
            }
        }
        return index == numCourses;
    }


}