import java.util.*;

public class LeetCode_Draft {

    class UnionFind {
        int[] rank;
        int[] parent;

        public boolean isSameRoot(int x, int y) {
            return findRoot(x) == findRoot(y);
        }

        public UnionFind(int length) {
            this.parent = new int[length];
            for (int i = 0; i < parent.length; i++) {
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
            if (x_root == y_root) {
                return;
            }
            if (rank[x_root] < rank[y_root]) {
                parent[x_root] = y_root;
            } else if (rank[x_root] > rank[y_root]) {
                parent[y_root] = x_root;
            } else {
                parent[x_root] = y_root;
                rank[y_root]++;
            }
        }
    }

    // 输入：
    //accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
    //输出：
    //[["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
    //解释：
    //第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
    //第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
    //可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
    //['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind unionFind = new UnionFind(accounts.size());

        HashMap<String, Integer> emailToId = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (!emailToId.containsKey(email)) {
                    emailToId.put(email, i);
                }else {
                    unionFind.union(emailToId.get(email), i);
                }
            }
        }

        HashMap<Integer, List<String>> idToEmails = new HashMap<>();
        for(Map.Entry<String, Integer> entry : emailToId.entrySet()){
            int id = unionFind.findRoot(entry.getValue());
            String email = entry.getKey();
            List<String> emails = idToEmails.getOrDefault(id, new ArrayList<>());
            emails.add(email);
            idToEmails.put(id, emails);
        }

        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<Integer, List<String>> entry : idToEmails.entrySet()){
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

}