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

/*
作者：卖报人
链接：https://www.nowcoder.com/discuss/389640
来源：牛客网

空间复杂度O(m*n*6), 时间复杂度O(m*n*6)
考试时写的比较草率，没有好好做函数化，简单加点注释供讨论
简单来说思路是类似于广度优先搜索的，但也写成了偏dp的样子……
dp[i][j][k]：对于位置i,j，用了k次飞行器时，达到这个位置最小需要的步数
用队列来进行访问：
1. 首先把起点坐标放入队列
2. 每一次访问队头元素，观察其1步可以到达的（上、下、左、右、中心对称位）的坐标：
   如果该坐标当前记录的某一种飞行器使用次数k下的值>当前值+1，则更新该值，把这一坐标加入队列
   （注意，上、下、左、右是同k值比较，中心对称位需要使用一次飞行器，所以是k+1和K比较）
3. 队列清空，更新完成
给出终点目前记录的步数，即为结果

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
