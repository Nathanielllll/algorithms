import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    /**
     * 图和树的最大区别在于图的下一个节点可能指向已访问过的节点。
     * 因此在使用BFS及DFS遍历时，应维护一个Set，Set中存放已被访问过的节点，在遍历时先判断节点未被访问过再遍历即可。
     */
    public void BFSWithQueue(Node root){
        Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }

        HashSet<Node> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            //Set中存放已被访问过的节点
            visited.add(temp);

            //然后child不为null，就加入进去，步骤和tree的一样


        }

    }
}
