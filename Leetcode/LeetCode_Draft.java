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

    static class HeapSort {
        // 大顶堆用来升序排序
        public void heapSort(int[] nums) {
            build_heap(nums, nums.length);

            for (int i = nums.length - 1; i >= 0; i--) {
                swap(nums, 0, i);
                heapify(nums, i, 0);
            }
        }


        // 大顶堆
        public void build_heap(int[] nums, int length) {
            int last_index = length - 1;
            int last_parent_index = (last_index - 1) / 2;
            for (int i = last_parent_index; i >= 0; i--) {
                heapify(nums, length, i);
            }
        }


        private void heapify(int[] nums, int length, int index) {
            if (index > length) {
                return;
            }
            int left_index = index * 2 + 1;
            int right_index = index * 2 + 2;
            int max_index = index;
            if (left_index < length && nums[left_index] > nums[max_index]) {
                max_index = left_index;
            }

            if (right_index < length && nums[right_index] > nums[max_index]) {
                max_index = right_index;
            }

            if (index != max_index) {
                swap(nums, index, max_index);
                heapify(nums, length, max_index);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }


    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(21);
        hashSet.add(3);
        hashSet.add(5);
        hashSet.add(2);
        hashSet.add(6);
        hashSet.add(10);


        for (int num : hashSet) {
            System.out.print((num & 15) + " ");
            System.out.println(num);
        }
    }


}