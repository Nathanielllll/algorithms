public class Test_440 {
    /*
    给定整数n和k，返回[1, n]中字典序第k小的数字。

示例 1:

输入: n = 13, k = 2
输出: 10
解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
示例 2:

输入: n = 1, k = 1
输出: 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/solution/java-zi-dian-xu-si-lu-qing-xi-dai-ma-jia-2ugh/
     */

    public int findKthNumber(int n, int k) {
        long cur = 1;
        k -= 1;

        while (k > 0) {
            int nodes = getNode(n, cur);
            if (k >= nodes) {
                k -= nodes;
                ++cur;
            } else {
                k -= 1;
                cur *= 10;
            }
        }
        return (int) cur;
    }

    private int getNode(int n, long cur) {
        long next = cur + 1;
        long totalNodes = 0;
        // 必然是全部<=n
        while (cur <= n) {
            totalNodes += Math.min(n - cur + 1, next - cur);
            cur *= 10;
            next *= 10;
        }
        return (int) totalNodes;
    }


}
