import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    /**
     * 图和树的最大区别在于图的下一个节点可能指向已访问过的节点。
     * 因此在使用BFS及DFS遍历时，应维护一个Set，Set中存放已被访问过的节点，在遍历时先判断节点未被访问过再遍历即可。
     *
     * 要说框架的话，我们先举例一下 BFS 出现的常见场景好吧，问题的本质就是让你在一幅「图」中找到从起点start到终点target的最近距离，
     * 这个例子听起来很枯燥，但是 BFS 算法问题其实都是在干这个事儿。
     *
     * BFS就是求最短距离的！！！！
     */

    public static LinkedList<Character> bfs(HashMap<Character, LinkedList<Character>> graph, char start) {
        LinkedList<Character> res = new LinkedList<>();

        Queue<Character> queue = new LinkedList<>();
        HashSet<Character> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            char temp = queue.poll();
            //加入结果
            res.add(temp);

            //然后child不为null，就加入进去，步骤和tree的一样
            LinkedList<Character> children = graph.get(temp);
            for(char child : children){
                if(!visited.contains(child)) {
                    queue.add(child);
                    visited.add(child);
                }

            }
        }
        return res;
    }


    static HashSet<Character> set = new HashSet<>();
    public static void bfs_recv(HashMap<Character, LinkedList<Character>> graph, char start){
        System.out.print(start + " ");
        set.add(start);

        LinkedList<Character> children = graph.get(start);
        for(char child : children){
            if (!set.contains(child)) {
                bfs_recv(graph, child);
            }
        }
    }

    public static void main(String[] args) {

        //构造各顶点
        LinkedList<Character> list_a = new LinkedList<Character>();
        list_a.add('b');
        list_a.add('c');
        LinkedList<Character> list_b = new LinkedList<Character>();
        list_b.add('a');
        list_b.add('c');
        list_b.add('d');
        LinkedList<Character> list_c = new LinkedList<Character>();
        list_c.add('a');
        list_c.add('b');
        list_c.add('d');
        list_c.add('e');
        LinkedList<Character> list_d = new LinkedList<Character>();
        list_d.add('b');
        list_d.add('c');
        list_d.add('e');
        list_d.add('f');
        LinkedList<Character> list_e = new LinkedList<Character>();
        list_e.add('c');
        list_e.add('d');
        LinkedList<Character> list_f = new LinkedList<Character>();
        list_f.add('d');

        //构造图
        HashMap<Character, LinkedList<Character>> graph = new HashMap<Character, LinkedList<Character>>();
        graph.put('a', list_a);
        graph.put('b', list_b);
        graph.put('c', list_c);
        graph.put('d', list_d);
        graph.put('e', list_e);
        graph.put('f', list_f);
        //记录每个顶点离起始点的距离，也即最短距离
//        HashMap<Character, Integer> dist = new HashMap<Character, Integer>();
        //遍历的起始点
        char start = 'a';
        //调用广度优先方法1
        LinkedList<Character> res = bfs(graph, start);
        System.out.println(res);
        //调用广度优先方法2
        bfs_recv(graph, start);
    }
}
