import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1 {

  class Node {

    public int val;
    public List<Node> neighbors;

    public Node() {
      val = 0;
      neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
      val = _val;
      neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
      val = _val;
      neighbors = _neighbors;
    }
  }

  public Node cloneGraph(Node node) {
    Map<Node, Node> map = new HashMap<>();
    return cloneGraphDfs(node, map);
  }

  public Node cloneGraphDfs(Node node, Map<Node, Node> map) {
    if (node == null) {
      return null;
    }
    if (map.containsKey(node)) {
      return map.get(node);
    }
    Node copy = new Node(node.val);
    map.put(node, copy);
    for (Node neighbor : node.neighbors) {
      copy.neighbors.add(cloneGraphDfs(neighbor, map));
    }
    return copy;
  }


}
