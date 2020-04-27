import java.util.*;

public class DFS {

    public static LinkedList<Character> dfs(HashMap<Character, LinkedList<Character>> graph, char start) {
        LinkedList<Character> res = new LinkedList<>();

        Stack<Character> stack = new Stack<>();
        HashSet<Character> visited = new HashSet<>();
        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            char temp = stack.pop();
            res.add(temp);

            LinkedList<Character> children = graph.get(temp);
            for(char child : children){
                if(!visited.contains(child)) {
                    stack.push(child);
                    visited.add(child);
                }
            }
        }
        return res;
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


        LinkedList<Character> res = dfs(graph, 'a');
        System.out.println(res);

    }
}
