package introductoryCourse.Chapter_4.AfterClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
对于一个由0..n的所有数按升序组成的序列，我们要进行一些筛选，每次我们取当前所有数字中从小到大的第奇数位个的数，并将其丢弃。重复这一过程直到最后剩下一个数。请求出最后剩下的数字。

输入描述:
每组数据一行一个数字，为题目中的n(n小于等于1000)。


输出描述:
一行输出最后剩下的数字。
示例1
输入
500
输出
255
 */
public class Test_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (br.ready()) {
            int target = Integer.parseInt(br.readLine());
            int num = 1;
            while (target > (num << 1)) {
                num <<= 1;
            }
            System.out.println(num - 1);
        }
    }
}
