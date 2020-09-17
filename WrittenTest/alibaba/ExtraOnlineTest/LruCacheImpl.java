package alibaba.ExtraOnlineTest;

import java.util.HashMap;

public class LruCacheImpl implements LruCache {
    //Test
    public static void main(String[] args) {
        LruCache cache = new LruCacheImpl();
        cache.setCapacity(2);

        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));//打印1
        cache.put(3,3);//2作废
        System.out.println(cache.get(2));//打印null
        cache.put(4,4);//1作废
        System.out.println(cache.get(1));//打印null
        System.out.println(cache.get(3));//打印3
        System.out.println(cache.get(4));//打印4
    }

    // (key, Node(key, val))
    private HashMap<Object, Node> cache = new HashMap<>();

    private DoubleLinkedList linkedList = new DoubleLinkedList();
    //最大容量
    private int capacity;

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void put(Object cacheKey, Object cacheValue) {
        Node node = new Node(cacheKey, cacheValue);

        //包含这个key
        if(cache.containsKey(cacheKey)){
            //删除list中旧节点，新的插到头部
            linkedList.remove(cache.get(cacheKey));
        //不包含这个key
        }else {
            //如果list满了
            if (capacity == linkedList.getSize()) {
                //删除list最后一个节点
                Node last_node = linkedList.removeLast();
                //cache中删除最后一个节点对应的key、node
                cache.remove(last_node.key);
            }
        }

        //直接添加到头部
        linkedList.addFirst(node);
        //更新cache中的数据
        cache.put(cacheKey, node);
    }

    @Override
    public Object get(Object cacheKey) {
        if (!cache.containsKey(cacheKey)) {
            return null;
        }
        Object cacheValue = cache.get(cacheKey).val;
        //调用put方法更新
        put(cacheKey, cacheValue);
        return cacheValue;
    }
}

class Node{
    public Object key, val;
    public Node next, prev;
    public Node(Object key, Object val){
        this.key = key;
        this.val = val;
    }
}

class DoubleLinkedList{
    private Node head, tail;//头尾虚节点
    private int size;//链表的元素数

    public DoubleLinkedList() {
        head = new Node(new Object(), new Object());
        tail = new Node(new Object(), new Object());
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    //在链表头部插入节点
    public void addFirst(Node node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        size++;
    }

    //删除链表中的某个节点，假设节点一定存在
    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    //删除最后一个节点
    public Node removeLast(){
        if(tail.prev == head){
            return null;
        }
        Node last_node = tail.prev;
        remove(last_node);
        return last_node;
    }

    //返回链表长度
    public int getSize(){
        return size;
    }
}
