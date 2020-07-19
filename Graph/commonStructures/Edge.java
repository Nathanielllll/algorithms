package commonStructures;

public class Edge {
    public int weight;//权重
    public GraphNode from;//从哪个点发散出去
    public GraphNode to;

    public Edge(int weight, GraphNode from, GraphNode to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
