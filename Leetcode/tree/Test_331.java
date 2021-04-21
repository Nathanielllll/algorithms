package tree;

public class Test_331 {
    /*
    我们可以定义一个概念，叫做槽位，二叉树中任意一个节点或者空孩子节点都要占据一个槽位。二叉树的建立也伴随着槽位数量的变化。开始时只有一个槽位，如果根节点是空节点，就只消耗掉一个槽位，如果根节点不是空节点，除了消耗一个槽位，还要为孩子节点增加两个新的槽位。之后的节点也是同理。

    有了上面的讨论，方法就很简单了。依次遍历前序序列化，根据节点是否为空，按照规则消耗/增加槽位。如果最后可以将所有的槽位消耗完，那么这个前序序列化就是合法的。
    开始时只有一个可用槽位。
    空节点和非空节点都消耗一个槽位。
    空节点不增加槽位，非空节点增加两个槽位。


    算法:
    初始化可用槽位：slots = 1。
    根据逗号分隔前序序列化，将结果数组存储，随后遍历该数组：
        空节点和非空节点都消耗一个槽位：slots = slot - 1.
        如果当前的可用槽位是负数，那么这个前序序列化是非法的，返回 False。
        非空节点（node != '#'）新增两个可用槽位：slots = slots + 2.
    如果所有的槽位都消耗完，那么这个前序序列化就是合法的：返回 slots == 0。
     */
    public boolean isValidSerialization(String preorder) {
        String[] stringArray = preorder.split(",");

        int slots = 1;
        for(String str : stringArray){
            slots--;
            if (slots < 0) {
                return false;
            }
            if (!str.equals("#")) {
                slots += 2;
            }
        }
        return slots == 0;
    }
}
