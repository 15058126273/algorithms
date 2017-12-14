package common.heap;

/**
 * 最大堆 相关
 * @author yjy
 * @date 2017年4月5日下午5:13:51
 */
public class MaxHeap {

	private static int[] a = { 2, 129, 4, 9, 212, 66, 999, 10, 43, 8, 12, 33, 432, 87, 5, 11, 85, 7, 6, 54, 121, 45,
			4545, 1, 4562, 84, 3 };

	// 1、maximum()：返回堆的最大值。
	// 2、extractMax()：返回堆的最大值并从堆中删除。
	// 3、heapIncreaseKey(i, key)：将下标为i的元素增大为key。
	// 4、maxHeapInsert(key)：将元素key插入到堆中。

	public static int maximum() {
		return a[0];
	}

	public static int extractMax() {
		int max = a[0];
		a[0] = a[a.length - 1];
		int[] acopy = new int[a.length - 1];
		System.arraycopy(a, 0, acopy, 0, acopy.length);
		a = acopy;
		maxHeapify(0, acopy.length);
		return max;
	}

	public static void heapIncreaseKey(int i, int key) {
		if (key > a[i]) {
			a[i] = key;
			int parent;
			while ((parent = (i + 1) / 2 - 1) >= 0 && key > a[parent]) {
				swap(i, parent);
				i = parent;
			}
		}
	}

	public static void maxHeapInsert(int key) {
		int[] newA = new int[a.length + 1];
		System.arraycopy(a, 0, newA, 0, a.length);
		newA[newA.length - 1] = Integer.MIN_VALUE;
		a = newA;
		heapIncreaseKey(newA.length - 1, key);
	}

	/**
	 * [sort 数组排序 - 从小到大]
	 * 
	 * @param a
	 *            [数组]
	 */
	public static void sort() {
		createMaxHeap();

		for (int length = a.length, len = length, i = length - 1; i > 0; i--) {
			swap(0, i);
			maxHeapify(0, --len);
		}
	}

	/**
	 * [createMaxHeap 创建相应数组的最大堆]
	 * 
	 * @param a
	 *            [数组]
	 */
	private static void createMaxHeap() {
		for (int i = a.length / 2 + 1; i >= 0; i--) {
			maxHeapify(i, a.length);
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
	private static void maxHeapify(int i, int len) {
		int l = left(i), r = right(i), max = i;
		if (l < len && a[l] > a[i]) {
			max = l;
		}
		if (r < len && a[r] > a[max]) {
			max = r;
		}
		if (max != i) {
			swap(i, max);
			maxHeapify(max, len);
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
	private static void swap(int x, int y) {
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

	/**
	 * [printHeap 打印最大堆，以树节点 格式]
	 * 
	 * @param a
	 *            [最大堆数组]
	 */
	private static void printHeap(int[] a) {
		for (int r = 0, b = 1, d = 0; d < a.length; d++) {
			if (d < b) {
				System.out.print(a[d] + (d % 2 == 0 ? " ; " : ":"));
			} else {
				r++;
				b += Math.pow(2, r);
				System.out.println();
				System.out.print(a[d] + (d % 2 == 0 ? " ; " : ":"));
			}
		}
		System.out.println();
	}

	private static boolean checkMaxHeap() {
		for (int i = 0; i < a.length / 2 + 1; i++) {
			int lefti = left(i), righti = right(i);
			if ((lefti < a.length && a[i] < a[lefti]) || (righti < a.length && a[i] < a[righti])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * [main 主函数]
	 * 
	 * @param arg
	 *            [description]
	 */
	public static void main(String[] arg) {
		createMaxHeap();

		System.out.println(checkMaxHeap() + "创建最大堆完成》" + toString(a));
		printHeap(a);
		// sort();
		// System.out.println("排序完成》" + toString(a));
		System.out.println(checkMaxHeap() + "最大堆中的最大值为》" + maximum());
		int max = extractMax();

		System.out.println(checkMaxHeap() + "移除最大值》" + max + ",堆结果》" + toString(a));
		printHeap(a);
		heapIncreaseKey(10, 4555);

		System.out.println(checkMaxHeap() + "增大10的值以后的堆结果》" + toString(a));
		printHeap(a);
		int loop = 0;
		while (loop < 100) {
			int insertkey = (int) Math.floor(Math.random() * 10000);
			maxHeapInsert(insertkey);
			loop++;
		}

		System.out.println(checkMaxHeap() + "插入值64以后的堆结果》" + toString(a));
		printHeap(a);
	}

}