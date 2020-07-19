/**
 * 打印从1到最大的n位数
 * 不能使用int/long类型会导致溢出，用数组/字符串来表示大数问题
 *
 * 两个考点：如何打印？怎么进位？
 */
public class Code_17 {
    //使用数组实现对数进行+1操作
    public static boolean increment(int[] number) {
        if (number.length < 1)
            throw new RuntimeException("invalid length of array");
        //最高位产生进位标志，则数组中的数为最大的n位整数
        boolean isOverFlow = false;
        //进位位
        int carry = 0;
        //没有产生进位的+1，循环只运行1次，产生一个进位，循环多运行一次
        //要从低位开始
        for (int i = number.length - 1; i >= 0; i--) {
            int sum = number[i] + carry;
            if (i == number.length - 1)
                sum++;//最低位+1

            //产生了进位，要分两种情况看
            if (sum >= 10) { //在此题当中即为sum == 10
                //最高位产生进位
                if (i == 0)
                    return true;
//                    isOverFlow = true;
                //普通位产生进位
                else {
                    carry = 1;
                    number[i] = 0;
                }
            //没有产生进位，直接break
            } else {
                //普通位+1的结果保存到数组中，程序退出循环
                number[i] = sum;
                break;
            }
        }
//        return isOverFlow;
        return false;
    }

    //自己写出一个打印字符串表示数字的函数。如038要打印成38
    public static void printNumber(int[] number) {
        boolean isBeginning = false;
        //前面为0时，不执行程序
        for (int i = 0; i < number.length; i++) {
            //在遇到第一个非0的char的时候
            if (!isBeginning && number[i] != 0) {
                isBeginning = true;
            }
            //isBeginning为true时，开始输出
            if (isBeginning) {
                System.out.print(number[i]);
            }
        }
        System.out.print(" ");
    }

    public static void main(String[] args) {
        int[] number = new int[2];
        //第二个循环
        while (!increment(number)) {
            printNumber(number);
        }
    }
}
