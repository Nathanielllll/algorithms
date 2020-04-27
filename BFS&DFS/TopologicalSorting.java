import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    B->
A->     D->E->F->G  ----->打印出ABCDEFG
    C->
 */
public class TopologicalSorting {
    class Node{
        String val;
        List<Node> children;
    }

    static Queue<String> queue = new LinkedList<>();
    static HashSet<String> set = new HashSet<>();

    //难道不是前序遍历?
    public static void printTopological(Node head){
        if (head != null) {
            queue.add(head.val);
            set.add(head.val);

            for(Node child : head.children){
                if (set.contains(child.val)) continue;
                printTopological(child);
            }
        }
    }

    public static void main(String[] args) {

    }
}
