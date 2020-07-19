package introductoryCourse.Chapter_1.OnClass;

public class Code07_EvenTimesOddTimes {
	//一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到这一个数
	public static void printOddTimesNum1(int[] arr) {
		int eO = 0;
		for (int cur : arr) {
			eO ^= cur;
		}
		System.out.println(eO);
	}

	//一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到这两个数
	public static void printOddTimesNum2(int[] arr) {
		int eO = 0, eOhasOne = 0, eOhasZero = 0;
		for (int curNum : arr) {
			eO ^= curNum;
		}
		//提取出最右边的1
		//~eO+1是取反加1，和原数&，就可以提取出来
		//技巧，记住即可！
		int rightOne = eO & (~eO + 1);
		for (int cur : arr) {
			if ((cur & rightOne) != 0) {
				eOhasOne ^= cur;
			}else {
				eOhasZero ^= cur;
			}
		}
		System.out.println(eOhasOne + " " + eOhasZero);
	}

	public static void main(String[] args) {
		int a = 5;
		int b = 7;

		//可以不用另外申请变量，实现两数数值的交换
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;

		System.out.println(a);
		System.out.println(b);

		int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		printOddTimesNum1(arr1);

		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOddTimesNum2(arr2);

	}

}
