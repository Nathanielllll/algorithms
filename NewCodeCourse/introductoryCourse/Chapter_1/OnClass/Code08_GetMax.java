package introductoryCourse.Chapter_1.OnClass;

public class Code08_GetMax {

	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}

	public static int process(int[] arr, int L, int R) {
		if (L == R) {//arr[L..R]范围只有一个数，直接返回，base case
			return arr[L];
		}
		//(L+R)/2可能会溢出，所以使用L + ((R - L) >> 1);
		int mid = L + ((R - L) >> 1);
		int leftMax = process(arr, L, mid);
		int rightMax = process(arr, mid + 1, R);
		return Math.max(leftMax, rightMax);
	}

}
