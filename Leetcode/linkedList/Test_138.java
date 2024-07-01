package linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 要求返回这个链表的深拷贝。
 * <p>
 * 我们用一个由n个节点组成的链表来表示输入/输出中的链表。每个节点用一个[val, random_index]表示：
 * <p>
 * val：一个表示Node.val的整数。 random_index：随机指针指向的节点索引（范围从0到n-1）；如果不指向任何节点，则为null。
 */
public class Test_138 {

  class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }

  public Node copyRandomList(Node head) {
    Map<Node, Node> map = new HashMap<>();
    Node cur = head;
    while (cur != null) {
      Node copy = new Node(cur.val);
      map.put(cur, copy);
      cur = cur.next;
    }

    cur = head;
    while (cur != null) {
      Node copy = map.get(cur);
      copy.next = map.get(cur.next);
      copy.random = map.get(cur.random);
      cur = cur.next;
    }

    return map.get(head);
  }
}
