package tree;

/**
 * 二叉树的下一个节点(中序遍历)
 * 除了有指向左右子节点的指针，还有指向父节点的指针
 * <p>
 * 规律：记住即可
 * 1、如果此节点有右子树，那么下个节点就是右子树的最左节点
 * 2、如果此节点没有右子树，并且它是它父节点的左子节点，那么下个节点就是它的父节点
 * 3、如果此节点没有右子树，并且它是它父节点的右子节点，那么情况复杂：沿着父节点的指针
 * 一直向上遍历，直到找到一个是它父节点的左子节点的节点。(即回到了第二种情况)
 */
public class Code_8 {
    public static class Node {
        Object value;
        Node left;
        Node right;
        Node parent;

        public Node(Object value) {
            this.value = value;
        }
    }

    public static Node getMostLeft(Node root) {
        if (root == null) {
            return null;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static Node getSuccessorNode(Node root) {
        if (root == null) {
            return null;
        }
        if (root.right != null) {
            return getMostLeft(root.right);
        } else {
            Node parent = root.parent;
            //它是它父节点的左子节点，那么下个节点就是它的父节点
            while (parent != null && root != parent.left) {
                root = parent;
                parent = root.parent;
            }
            return parent;
        }
    }

    public static void main(String[] args) {
        Node node = new Node("a");
        node.parent = node;
        //左子树
        node.left = new Node("b");
        node.left.parent = node;
        node.left.left = new Node("d");
        node.left.left.parent = node.left;
        node.left.right = new Node("e");
        node.left.right.parent = node.left;
        node.left.right.left = new Node("h");
        node.left.right.left.parent = node.left.right;
        node.left.right.right = new Node("i");
        node.left.right.right.parent = node.left.right;
        //右子树
        node.right=new Node("c");
        node.right.parent = node;
        node.right.left = new Node("f");
        node.right.left.parent = node.right;
        node.right.right = new Node("g");
        node.right.right.parent = node.right;

        System.out.println(getSuccessorNode(node).value);//a->f
        System.out.println(getSuccessorNode(node.left).value);//b->h
        System.out.println(getSuccessorNode(node.right).value);//c->g
        System.out.println(getSuccessorNode(node.left.right).value);//e->i
    }
}
