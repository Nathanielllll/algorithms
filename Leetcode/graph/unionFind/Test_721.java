package graph.unionFind;

import java.util.*;

/*
给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。

现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。

合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按字符 ASCII 顺序排列的邮箱地址。账户本身可以以任意顺序返回。

 

示例 1：

输入：
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
输出：
[["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
解释：
第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 */
public class Test_721 {
    class Djset {
        private int[] parent;
        private int[] rank;

        public Djset(int length) {
            this.parent = new int[length];
            for (int i = 0; i < length; i++) {
                parent[i] = i;
            }
            this.rank = new int[length];
        }

        public int findRoot(int x) {
            if (x != parent[x]) {
                parent[x] = findRoot(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int x_root = findRoot(x);
            int y_root = findRoot(y);
            if (rank[x_root] < rank[y_root]) {
                parent[x_root] = y_root;
            } else if (rank[y_root] < rank[x_root]) {
                parent[y_root] = x_root;
            } else {
                parent[x_root] = y_root;
                rank[y_root]++;
            }
        }
    }

    /**
     * 使用并查集
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int length = accounts.size();
        Djset djset = new Djset(length);

        HashMap<String, Integer> emailToId = new HashMap<>();
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String curEmail = accounts.get(i).get(j);
                if (!emailToId.containsKey(curEmail)) {
                    emailToId.put(curEmail, i);
                }else {
                    djset.union(i, emailToId.get(curEmail));
                }
            }
        }

        HashMap<Integer, List<String>> idToEmailList = new HashMap<>();
        for(Map.Entry<String, Integer> entry : emailToId.entrySet()){
            int id = djset.findRoot(entry.getValue());

            String curEmail = entry.getKey();
            List<String> emailList = idToEmailList.getOrDefault(id, new ArrayList<>());
            emailList.add(curEmail);
            idToEmailList.put(id, emailList);
        }

        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<Integer, List<String>> entry : idToEmailList.entrySet()){
            List<String> list = new ArrayList<>();
            String name = accounts.get(entry.getKey()).get(0);
            list.add(name);
            List<String> emailList = entry.getValue();
            Collections.sort(emailList);
            list.addAll(emailList);
            result.add(list);
        }
        return result;
    }


    /**
     * 使用dfs
     * @param accounts
     * @return
     */
//    public List<List<String>> accountsMerge_1(List<List<String>> accounts) {
//
//    }
}




