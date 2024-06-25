package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Test_332_ATTENTION {

    public static void main(String[] args) {
//        ["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(List.of("MUC","LHR"));
        tickets.add(List.of("JFK","MUC"));
        tickets.add(List.of("SFO","SJC"));
        tickets.add(List.of("LHR","SFO"));
        Test_332_ATTENTION test = new Test_332_ATTENTION();
        test.findItinerary(tickets);

    }

    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new ArrayList<>();
        if (tickets == null || tickets.size() == 0) {
            return result;
        }

        HashMap<String, PriorityQueue<String>> graph = new HashMap<>();
        for (int i = 0; i < tickets.size(); i++) {
            String from = tickets.get(i).get(0);
            String to = tickets.get(i).get(1);

            PriorityQueue<String> children = graph.getOrDefault(from, new PriorityQueue<>());
            children.add(to);
            graph.put(from, children);
        }

        dfs(result, "JFK", graph);
        return result;
    }

    private void dfs(List<String> result, String cur, HashMap<String, PriorityQueue<String>> graph){
        PriorityQueue<String> children = graph.get(cur);
        while (children != null && !children.isEmpty()) {
            String to = children.poll();
            dfs(result, to, graph);
        }
        //如果第一个访问的节点是 “孤岛节点”，他会出现在结果集的最后。当我们顺序读取结果集时，这种 “孤岛节点” 是最后遇到的，是图遍历的终点，这样就没有问题了。
        result.add(0, cur);
    }


}