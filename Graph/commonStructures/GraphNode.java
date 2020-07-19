package commonStructures;

import java.util.ArrayList;

public class GraphNode {
    public int value;
    public int in;// 入度，有多少边指向这个点
    public int out;// 出度
    public ArrayList<GraphNode> nexts;//从我发散出去的点的集合
    public ArrayList<Edge> edges;//从我发散出去的边的集合

    public GraphNode(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
