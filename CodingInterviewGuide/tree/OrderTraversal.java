package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class OrderTraversal {
    public class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public void preOrderRecur(Node head){
        if (head == null) {
            return;
        }
        System.out.println(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }


    public void preOrderUnRecur(Node head){
        System.out.print("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                Node temp = stack.pop();
                System.out.print(temp.value + " ");
                if (temp.right != null) {
                    stack.push(temp.right);
                }
                if (temp.left != null) {
                    stack.push(temp.left);
                }
            }
        }
        System.out.println();
    }

    public void inOrderUnRecur(Node head){
        System.out.print("in-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                }else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    //前序遍历是中、左、右
    //后序遍历是左、右、中
    //因此，后续遍历可以看成是前序遍历转化过来的：左、右在stack中顺序相反，然后在逆序打印
    //即：中、左、右 -> 中、右、左 -> 左、右、中
    public List<Integer> postOrderUnRecur(Node head){
        System.out.print("after-order: ");
        /**值得注意的数据结构，而且也可以用另一个stack来代替*/
        LinkedList<Integer> result = new LinkedList<>();
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                Node temp = stack.pop();
                result.addFirst(temp.value);
                if (temp.left != null) {
                    stack.push(temp.left);
                }
                if (temp.right != null) {
                    stack.push(temp.right);
                }
            }
        }
        return result;
    }

}
