package introductoryCourse.Chapter_2.AfterClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
输入整型数组和排序标识，对其元素按照升序或降序进行排序（一组测试用例可能会有多组数据）

接口说明

原型：

void sortIntegerArray(Integer[] pIntegerArray, int iSortFlag);

输入参数：

Integer[] pIntegerArray：整型数组

int  iSortFlag：排序标识：0表示按升序，1表示按降序

输出参数：

无

返回值：

void

输入描述:
1、输入需要输入的整型数个数



输出描述:
输出排好序的数字

示例1
输入
8
1 2 4 9 3 55 64 25
0
输出
1 2 3 4 9 25 55 64
 */
public class Test_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (br.ready()) {
            int total = Integer.parseInt(br.readLine());
            String[] strings = br.readLine().split(" ");
            int[] array = new int[total];
            for (int i = 0; i < total; i++) {
                array[i] = Integer.parseInt(strings[i]);
            }
            int method = Integer.parseInt(br.readLine());//排序方法0（降序）或者1（升序）
            Arrays.sort(array);
            if(method == 1) {
                int[] temp = array.clone();
                for (int i = 0; i < array.length; i++) {
                    array[i] = temp[array.length - 1 - i];
                }
            }
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<array.length;i++){
                sb.append(array[i]+" ");
            }
            System.out.println(sb.toString().trim());
        }
    }

}
