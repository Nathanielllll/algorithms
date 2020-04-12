/***
 * 数字序列中某一位的数字【记住规律】
 */
public class Code_44 {

    public static void main(String[] args) {
        System.out.println(digitAtIndex(11));
    }

    public static int digitAtIndex(int index) {
        if (index < 0) {
            return -1;
        }
        int digits = 1;
        while (true) {
            int digitNumbers = countOfIntegers(digits) * digits;
            if (index >= digitNumbers) {
                index = index - digitNumbers;
                digits++;
            } else {
                return digitAtIndex(index, digits);
            }
        }
    }

    /**
     *
     * @param index 这个index是指：在digits位上的index，而不是原始的index
     * @param digits
     * @return
     */
    public static int digitAtIndex(int index, int digits) {
        //对应的数值
        int number = beginNumberFor(digits) + index / digits;
        //从数值右边开始算的位置
        int indexFromRight = digits - index % digits;
        //去除右边的indexFromRight-1个数字
        for (int i = 1; i < indexFromRight; i++)
            number /= 10;
        //求个位数字
        return number % 10;
    }

    /**
     * 得到m位的数字总共有多少个
     *
     * @param digits
     * @return
     */
    public static int countOfIntegers(int digits) {
        if (digits == 1) {
            return 10;
        }
        int count = (int) Math.pow(10, digits - 1);
        return 9 * count;
    }

    /**
     * digits位数的第一个数字，两位数从10开始，三位数从100开始
     *
     * @param digits
     * @return
     */
    public static int beginNumberFor(int digits) {
        if (digits == 1) {
            return 0;
        }
        return (int) Math.pow(10, digits - 1);
    }


}
