package alibaba.March_23;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个地图n*m，包含1个起点，1个终点，其他点包括可达点和不可达点。
 * 每一次可以：上下左右移动，或使用1点能量从（i,j)瞬间移动到（n-1-i, m-1-j)，最多可以使用5点能量。
 *
 * 输入：
 * 4 4
 * #S..
 * E#..
 * ....
 * ....
 * 输出：4
 * 解释:S(0,1)先瞬移到(3, 2)，然后往上一步，往右一步，瞬移到E，一共4步
 */
public class Test_2 {
    public static void main(String[] args) {
        String s1 = new String("abc");

        String s2 = new String("abc");
        System.out.println(s1.equals(s2));


        Object o1 = s1;
//        o1 = 1234;
        Object o2 = s2;
//        o2 = 1234;
        System.out.println(o1.equals(o2));
    }
}
