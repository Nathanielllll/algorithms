package bit;

/**
 * 数组中数字出现的次数
 * <p>
 * 数组中只出现一次的两个数字。除了两个数字外，其他数字都出现了两次。
 * 要求时间复杂度为O(n), 空间复杂度为O(1)。
 * <p>
 * 把全部数字异或得到一个数字。根据此数字找到第一个为1的位的位置，把原数组分成两个子数组。再进行异或
 */
public class Code_56 {

    public static void main(String[] args) {
        int[] data = new int[]{3, 4, 5, 6, 4, 5, 6, 7};
        System.out.println(findNumsAppearOnce(data)[0]);
        System.out.println(findNumsAppearOnce(data)[1]);
    }

    public static int[] findNumsAppearOnce(int[] data) {
        if (data == null || data.length < 2) {
            throw new RuntimeException("invalid input");
        }

        // 3: 011
        // 5: 101
        //    110
        int resultExclusiveOR = 0;
        for (int i = 0; i < data.length; i++) {
            resultExclusiveOR ^= data[i];
        }

        int indexOf1 = findFirstBitIs1(resultExclusiveOR);

        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < data.length; i++) {
            if (isBit1(data[i], indexOf1)) {
                num1 ^= data[i];
            } else {
                num2 ^= data[i];
            }
        }

        return new int[]{num1, num2};
    }

    public static int findFirstBitIs1(int num) {
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((num & mask) != 0) {
                return i;
            }
            mask <<= 1;
        }
        return -1;
    }

    public static boolean isBit1(int num, int indexBit) {
        int mask = 1;
        mask <<= indexBit;
        return (num & mask) != 0;
    }
//    public static int findFirstBitIs1(int num) {
//        int indexBit = 0;
//        while (((num & 1) == 0) && indexBit < 32) {
//            num = num >> 1;
//            indexBit++;
//        }
//        return indexBit;
//    }
//
//    public static boolean isBit1(int num, int indexBit) {
//        num >>= indexBit;
//        //＆同为１时为１，否则为０
//        return (num & 1) == 1;
//    }
}
