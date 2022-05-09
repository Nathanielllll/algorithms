import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test_1233 {

    /**
     * 你是一位系统管理员，手里有一份文件夹列表 folder，你的任务是要删除该列表中的所有 子文件夹，并以 任意顺序 返回剩下的文件夹。
     * <p>
     * 如果文件夹folder[i]位于另一个文件夹folder[j]下，那么folder[i]就是folder[j]的 子文件夹 。
     * <p>
     * 文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：'/'后跟一个或者多个小写英文字母。
     * <p>
     * 例如，"/leetcode"和"/leetcode/problems"都是有效的路径，而空字符串和"/"不是。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
     * 输出：["/a","/c/d","/c/f"]
     * 解释："/a/b/" 是 "/a" 的子文件夹，而 "/c/d/e" 是 "/c/d" 的子文件夹。
     * 示例 2：
     * <p>
     * 输入：folder = ["/a","/a/b/c","/a/b/d"]
     * 输出：["/a"]
     * 解释：文件夹 "/a/b/c" 和 "/a/b/d/" 都会被删除，因为它们都是 "/a" 的子文件夹。
     * 示例 3：
     * <p>
     * 输入: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
     * 输出: ["/a/b/c","/a/b/ca","/a/b/d"]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-sub-folders-from-the-filesystem
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param folder
     * @return
     */
    public List<String> removeSubfolders(String[] folder) {
//        Arrays.sort(folder);
//        List<String> fathers = new ArrayList<>();
//        int i = 0;
//        while (i < folder.length) {
//            fathers.add(folder[i]);
//            int k = i + 1;
//            String dir = folder[i] + "/"; // 这个很重要："/a/b"和"/a/bc"没关系，"/a/b"和"/a/b/c"才有关系
//            while (k < folder.length && folder[k].startsWith(dir)) {
//                k++;
//            }
//            i = k;
//        }
//        return fathers;

        Arrays.sort(folder);
        List<String> fathers = new ArrayList<>();
        int i = 0;
        int length = folder.length;
        while (i < length) {
            fathers.add(folder[i]);
            String dir = folder[i] + "/";
            int j = i + 1;
            while (j < length && folder[j].startsWith(dir)) {
                ++j;
            }
            i = j;
        }
        return fathers;
    }
}
