package common.str;

/**
 * 求最小数和最大数的和
 * @author yjy
 * @date 2017年4月5日下午5:45:34
 */
public class MinAndMax {

	public static int findSum(int[] a) {
		if (a != null && a.length > 1) {
			sort(a);
			System.out.println("排序完成》" + toString(a));
			return a[0] + a[a.length - 1];
		}
		return 0;
	}
	
	/**
	 * [sort 数组排序 - 从小到大]
	 * 
	 * @param a
	 *            [数组]
	 */
	public static void sort(int[] a) {
		createMaxHeap(a);

		for (int length = a.length, len = length, i = length - 1; i > 0; i--) {
			swap(a, 0, i);
			maxHeapify(a, 0, --len);
		}
	}

	/**
	 * [createMaxHeap 创建相应数组的最大堆]
	 * 
	 * @param a
	 *            [数组]
	 */
	private static void createMaxHeap(int[] a) {
		for (int i = a.length / 2 + 1; i >= 0; i--) {
			maxHeapify(a, i, a.length);
		}
	}

	/**
	 * [maxHeapify 最大堆节点维护]
	 * 
	 * @param a
	 *            [最大堆数组]
	 * @param i
	 *            [维护节点]
	 * @param len
	 *            [description]
	 */
	private static void maxHeapify(int[] a, int i, int len) {
		int l = left(i), r = right(i), max = i;
		if (l < len && a[l] > a[i]) {
			max = l;
		}
		if (r < len && a[r] > a[max]) {
			max = r;
		}
		if (max != i) {
			swap(a, i, max);
			maxHeapify(a, max, len);
		}
	}

	/**
	 * [swap 数组中位置互换]
	 * 
	 * @param int[]a
	 *            [数组]
	 * @param x
	 *            [位置1]
	 * @param y
	 *            [位置2]
	 */
	private static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

	/**
	 * [left 获取左孩子下标]
	 * 
	 * @param i
	 *            [节点下标]
	 * @return [description]
	 */
	private static int left(int i) {
		return i * 2 + 1;
	}

	/**
	 * [right 获取右孩子下标]
	 * 
	 * @param i
	 *            [节点下标]
	 * @return [description]
	 */
	private static int right(int i) {
		return i * 2 + 2;
	}
	
	/**
	 * [toString 工具方法 帮助打印数组]
	 * 
	 * @param a
	 *            [description]
	 * @return [description]
	 */
	private static String toString(int[] a) {
		StringBuilder sb = new StringBuilder("[]");
		for (int x = 0; x < a.length; x++) {
			sb.insert(sb.length() - 1, a[x] + ",");
		}
		return "length: " + a.length + ",content: " + sb.toString();
	}
	
	public static void main(String[] at) {
		int[] a = {1,34,6,3,234,5,3,23254,53,332,-4};
		System.out.println(findSum(a));
	}
}
