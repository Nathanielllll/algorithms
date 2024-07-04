package greedy;

/**
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表，用字母 A 到 Z 表示，以及一个冷却时间 n。每个周期或时间间隔允许完成一项任务。任务可以按任何顺序完成，但有一个限制：两个
 * 相同种类 的任务之间必须有长度为 n 的冷却时间。
 * <p>
 * 返回完成所有任务所需要的 最短时间间隔 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2 输出：8 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 示例 2：
 * <p>
 * 输入：tasks = ["A","A","A","B","B","B"], n = 0 输出：6 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 * ["A","A","A","B","B","B"] ["A","B","A","B","A","B"] ["B","B","B","A","A","A"] ... 诸如此类 示例 3：
 * <p>
 * 输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2 输出：16 解释：一种可能的解决方案是： A -> B
 * -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 */
public class Test_621 {
  public int leastInterval(char[] tasks, int n) {
    int[] taskCnt = new int[26];
    for (char task : tasks) {
      taskCnt[task - 'A']++;
    }

    int maxCnt = 0;
    for (int i = 0; i < 26; i++) {
      maxCnt = Math.max(maxCnt, taskCnt[i]);
    }
    int p = 0;
    for (int i = 0; i < 26; i++) {
      if (taskCnt[i] == maxCnt) {
        ++p;
      }
    }
    // 贪心策略。最大次数为maxCnt，前面部分必须(maxCnt - 1) * (n + 1)时间，后面还有p个最大次数的任务
    int ans = (maxCnt - 1) * (n + 1) + p;
    // 因为无论如何，最少肯定也要tasks.length
    return Math.max(ans, tasks.length);
  }

}
