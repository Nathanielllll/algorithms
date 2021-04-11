package graph.topologicalSort;

import java.util.*;

/*
对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。

格式

该图包含n个节点，标记为0到n - 1。给定数字n和一个无向边edges列表（每一个边都是一对标签）。

你可以假设没有重复的边会出现在edges中。由于所有的边都是无向边， [0, 1]和[1, 0]是相同的，因此不会同时出现在edges里。

示例 1:

输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3

输出: [1]
 */
public class Test_310 {

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};

//        int n = 6;
//        int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        System.out.println(findMinHeightTrees(n, edges));
    }

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //      1.条件判断（边界判断，其他要求的判断）
        if (n == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            return list;
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] degree = new int[n];
        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];

            graph.get(node1).add(node2);//跟edge[0]相邻的节点
            graph.get(node2).add(node1);//跟edge[1]相邻的节点
            degree[node1]++;//计算edge[0]节点的度数
            degree[node2]++;//计算edge[1]节点的度数
        }
        
        //      2.创建队列
        Queue<Integer> queue = new LinkedList<>();
        //      3.在队列中加入第一个满足条件的元素
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                degree[i]--;
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result = new ArrayList<>();
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                int from = queue.poll();
                result.add(from);
                List<Integer> tos = graph.get(from);
                for(int to : tos){
                    degree[to]--;//删除叶子节点后，跟其相邻的节点的度数要减少
                    if (degree[to] == 1) {
                        queue.add(to);
                    }
                }
            }
        }
        return result;

    }
//    作者：ming-cai
//    链接：https://leetcode-cn.com/problems/minimum-height-trees/solution/tu-jie-zui-xiao-gao-du-shu-bsf-by-ming-cai/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

}
