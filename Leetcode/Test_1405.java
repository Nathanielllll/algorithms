import java.util.PriorityQueue;

public class Test_1405 {

    /**
     * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
     *
     * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
     *
     * s 是一个尽可能长的快乐字符串。
     * s 中 最多 有a 个字母 'a'、b个字母 'b'、c 个字母 'c' 。
     * s 中只含有 'a'、'b' 、'c' 三种字母。
     * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
     *
     * 示例 1：
     *
     * 输入：a = 1, b = 1, c = 7
     * 输出："ccaccbcc"
     * 解释："ccbccacc" 也是一种正确答案。
     * 示例 2：
     *
     * 输入：a = 2, b = 2, c = 1
     * 输出："aabbc"
     * 示例 3：
     *
     * 输入：a = 7, b = 1, c = 0
     * 输出："aabaa"
     * 解释：这是该测试用例的唯一正确答案。
     */
    public String longestDiverseString(int a, int b, int c) {
        //[0,1] 表示a有1个
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        if (a > 0) queue.add(new int[]{0, a});
        if (b > 0) queue.add(new int[]{1, b});
        if (c > 0) queue.add(new int[]{2, c});

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int length = sb.length();
            // sb的倒数第1、2个，都是cur[0]
            if (length >= 2 && sb.charAt(length - 1) == cur[0] + 'a' && sb.charAt(length - 2) == cur[0] + 'a') {
                if (queue.isEmpty()) {
                    break;
                }

                int[] next = queue.poll();
                sb.append((char) (next[0] + 'a'));
                if (--next[1] != 0) {
                    queue.add(next);
                }
                queue.add(cur);
            } else {
                sb.append((char) (cur[0] + 'a'));
                if (--cur[1] != 0) {
                    queue.add(cur);
                }
            }
        }
        return sb.toString();
    }
}
