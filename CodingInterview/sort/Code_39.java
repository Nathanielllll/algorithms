package sort;

/**
 * 数组中出现超过一半的数字
 * <p>
 * 使用partition找出中位数，如果下标大于n/2，那么中位数在右边；如果==n/2，就是中位数；。。。
 */
public class Code_39 {

    //使用投票法。
    //https://leetcode-cn.com/problems/majority-element/solution/mo-er-tou-piao-ha-xi-tong-by-javalangruntimeexcept/
    public static int majorityElement(int[] nums) {
        int count = 1;
        int number = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                number = nums[i];
                count = 1;
                continue;
            }

            if (nums[i] == number) {
                count++;
            } else {
                count--;
            }
        }
        return number;
    }

    //使用partition的方法找出中位数
    public static int moreThanHalfNum(int[] arr) {
        if (checkInvalidArray(arr)) {
            return 0;
        }
        int middle_index = arr.length >> 1;
        int start = 0;
        int end = arr.length - 1;
        int index = partition(arr, start, end);
        while (index != middle_index) {
            //下标大于n/2，则中位数在左边
            if (index > middle_index) {
                end = index - 1;
            } else {
                start = index + 1;
            }
            index = partition(arr, start, end);
        }

        //找出中位数
        int mid_num = arr[middle_index];
        if (!checkMoreThanHalf(arr, mid_num)) {
            return 0;
        }
        return mid_num;
    }

    //用partition找出下标
    public static int partition(int[] arr, int left, int right) {
        int less = left - 1;
        int more = right;
        //arr[right]是target值
        while (left != more) {
            if (arr[left] < arr[right]) {
                swap(arr, ++less, left++);
            } else if (arr[left] > arr[right]) {
                swap(arr, --more, left);
            } else {
                left++;
            }
        }
        swap(arr, left, right);
        return left;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean checkInvalidArray(int[] arr) {
        //如果确实无效，返回true
        if (arr == null || arr.length <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkMoreThanHalf(int[] arr, int number) {
        int times = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                times++;
            }
        }
        if (times * 2 <= arr.length) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        // 存在出现次数超过数组长度一半的数字
        int numbers[] = {1, 2, 3};
        System.out.println(moreThanHalfNum(numbers));

        // 出现次数超过数组长度一半的数字都出现在数组的前半部分
        int numbers2[] = {2, 2, 2, 2, 2, 1, 3, 4, 5};
        System.out.println(moreThanHalfNum(numbers2));

        // 出现次数超过数组长度一半的数字都出现在数组的后半部分
        int numbers3[] = {1, 3, 4, 5, 2, 2, 2, 2, 2};
        System.out.println(moreThanHalfNum(numbers3));

        // 只有一个数
        int numbers4[] = {1};
        System.out.println(moreThanHalfNum(numbers4));

        // 输入空指针
        System.out.println(moreThanHalfNum(null));

        // 不存在出现次数超过数组长度一半的数字
        int numbers5[] = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        System.out.println(moreThanHalfNum(numbers5));
    }
}
