package introductoryCourse.Chapter_1.AfterClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
查找和排序

题目：输入任意（用户，成绩）序列，可以获得成绩从高到低或从低到高的排列,相同成绩
都按先录入排列在前的规则处理。

示例：
jack      70
peter     96
Tom       70
smith     67

从高到低  成绩
peter     96
jack      70
Tom       70
smith     67

从低到高

smith     67

jack      70
Tom      70
peter     96

输入描述:
输入多行，先输入要排序的人的个数，然后输入排序方法0（降序）或者1（升序）再分别输入他们的名字和成绩，以一个空格隔开。

输出描述:
按照指定方式输出名字和成绩，名字和成绩之间以一个空格隔开

输入
3
0
ning 70
yang 50
fang 90

输出
fang 90
ning 70
yang 50
 */
public class Test_1 {
    static class Pair {
        String name;
        int score;

        public Pair(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (br.ready()) {
            int total = Integer.parseInt(br.readLine());
            int method = Integer.parseInt(br.readLine());//排序方法0（降序）或者1（升序）

            List<Pair> list = new LinkedList<>();
            for (int i = 0; i < total; i++) {
                String[] strings = br.readLine().split(" ");
                Pair pair = new Pair(strings[0], Integer.parseInt(strings[1]));
                list.add(pair);
            }

            Collections.sort(list, new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    if (method == 0) {
                        return o2.score - o1.score;
                    }
                    return o1.score - o2.score;
                }
            });

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < total; i++) {
                Pair pair = list.get(i);
                sb.append(pair.name + " " + pair.score + "\n");
            }
            System.out.println(sb.substring(0, sb.length() - 1));
        }
    }
}
