package graph.unionFind;

import java.util.HashMap;
import java.util.HashSet;

/*
如果交换字符串X 中的两个不同位置的字母，使得它和字符串Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。

例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)；"rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。

总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。

给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？



示例 1：

输入：strs = ["tars","rats","arts","star"]
输出：2
示例 2：

输入：strs = ["omv","ovm"]
输出：1
 */
public class Test_839 {
    int[] parents;

    public int numSimilarGroups(String[] strs) {
        int length = strs.length;
        parents = new int[length];
        for (int i = 0; i < length; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                int fi = find(i), fj = find(j);
                if (fi == fj) {
                    continue;
                }
                if (check(strs[i], strs[j])) {
                    parents[fi] = fj;
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < length; i++) {
            if (parents[i] == i) {
                ret++;
            }
        }
        return ret;
    }

    public int find(int x) {
        return parents[x] == x ? x : (parents[x] = find(parents[x]));
    }

    public boolean check(String a, String b) {
        int len = a.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                num++;
                if (num > 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
