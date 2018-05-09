package common.exercise;

import java.util.*;

public class MatchRemove {

	// 规则列表
	private static final Integer[][] rules = {
			{0, 0, 0}, {1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 4}, {5, 5, 5}, {6, 6, 6}, {7, 7, 7}, {8, 8, 8}, {9, 9, 9},
			{10, 10, 10}, {11, 11, 11}, {12, 12, 12}, {13, 13, 13}, {14, 14, 14}, {15, 15, 15}, {16, 16, 16}, {17, 17, 17},
			{18, 18, 18}, {19, 19, 19}, {20, 20, 20}, {21, 21, 21}, {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {9, 10, 11}, {12, 13, 14},
			{15, 16, 17}, {4, 8, 10}, {10, 18, 19}, {10, 19, 20}, {19, 20, 21}, {12, 20, 21}, {12, 15, 21}, {12, 15, 16}, {13, 15, 16}};

	// 按第一个元素分类，减少匹配次数
	private static final int[][][] rebuildRules = {
			{{0, 0, 0}, {0, 1, 2}},
			{{1, 1, 1}},
			{{2, 2, 2}},
			{{3, 3, 3}, {3, 4, 5}},
			{{4, 4, 4}, {4, 8, 10}},
			{{5, 5, 5}},
			{{6, 6, 6}, {6, 7, 8}},
			{{7, 7, 7}},
			{{8, 8, 8}},
			{{9, 9, 9}, {9, 10, 11}},
			{{10, 10, 10}, {10, 18, 19}, {10, 19, 20}},
			{{11, 11, 11}},
			{{12, 12, 12}, {12, 13, 14}, {12, 20, 21}, {12, 15, 21}, {12, 15, 16}},
			{{13, 13, 13}, {13, 15, 16}},
			{{14, 14, 14}},
			{{15, 15, 15}, {15, 16, 17}},
			{{16, 16, 16}},
			{{17, 17, 17}},
			{{18, 18, 18}},
			{{19, 19, 19}, {19, 20, 21}},
			{{20, 20, 20}},
			{{21, 21, 21}}
	};

	// 储存已匹配上的数组
	private static List<int[]> successArr = new LinkedList<>();
	// 匹配不上的元素
	private static List<Integer> failArr = new LinkedList<>();
	// 每次匹配上后记录相关参数用于回退
	private static Stack<Object[]> params = new Stack<>();

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for(int[] testArr: arrArr) {
			init(testArr);
			try {
				check(testArr);
			} catch (Exception e) {
				// 跳出递归
			}
			if (failArr.size() == 2) {
				int[] remainArr = list2Arr(failArr);
				Integer res = diffBetweenRuleAndExample(remainArr);
				if(null == res) {
					findAgain(testArr);
				} else {
					printResult(remainArr, res);
				}
			} else if (failArr.size() > 2) {
				System.out.println("无解");
			}
			System.out.println();
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
	}

	/**
	 * 初始化参数并打印初始数组
	 * @param testArr
	 */
	private static void init(int[] testArr) {
		failArr.clear();
		successArr.clear();
		params = new Stack<>();
		tmpArr.clear();
		System.out.println(Arrays.toString(testArr));
	}

	/**
	 * 检查是否有符合的数字
	 * @param testArr
	 * @throws Exception
	 */
	private static void check(int[] testArr) throws Exception {
		int len = testArr.length;
		if (len < 2) {
			if (len == 1) {
				failArr.add(testArr[0]);
			}
			throw new Exception();
		}
		if (failArr.size() > 2) { // 匹配失败的元素数量大于2了则回退再查找
			findAgain(testArr);
			throw new Exception();
		}
		if (len == 2) {
			Integer res = diffBetweenRuleAndExample(testArr);
			if (null == res) {
				findAgain(testArr);
			} else {
				printResult(testArr, res);
				throw new Exception();
			}
		}
		if(failArr.contains(testArr[0])) {
			addFail(testArr);
		} else {
			tryToFind(testArr, len);
		}
	}

	private static void findAgain(int[] testArr) {
		try {
			Object[] param = params.pop();
			if (null != param) {
				testArr = (int[]) param[0];
				int failArrLen = (int) param[3];
				if (failArrLen == 0) {
					failArr.clear();
				} else {
					failArr = failArr.subList(0, failArrLen);
				}
				successArr.remove(successArr.size() - 1);
				tryToFind(testArr, testArr.length, (int)param[1], (int)param[2] + 1);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 组合算法查找符合规则的数组
	 * @param testArr
	 * @param len
	 * @throws Exception
	 */
	private static void tryToFind(int[] testArr, int len) throws Exception {
		boolean success = false;
		for (int i = 0, index = len - 2; i < len - 2; i++) {
			for (int j = i; j < i + index; j++) {
				combine(0, i + 1, j, 3, testArr);
				int[] result2Arr = list2Arr(resultArr);
				List<Integer> rule = ifInRule(result2Arr, rebuildRules[resultArr.get(0).intValue()]);
				if (null != rule) {
					success = true;
					successArr.add(result2Arr);
					Object[] param = {testArr, i, j, failArr.size()};
					params.push(param);
					testArr = afterRemove(testArr, rule);
					check(testArr);
				} else {
					continue;
				}
			}
			index--;
			if (i == len - 3 && !success) {
				addFail(testArr);
			}
		}
	}

	/**
	 * 组合算法查找符合规则的数组 -- 回退后从某一个点继续找
	 * @param testArr
	 * @param len
	 * @throws Exception
	 */
	private static void tryToFind(int[] testArr, int len, int initI, int initJ) throws Exception {
		boolean success = false;
		for (int i = initI, index = len - 2 - initI; i < len - 2; i++) {
			int j = i;
			if (i == initI)
				j = initJ;
			for (; j < i + index; j++) {
				combine(0, i + 1, j, 3, testArr);
				int[] result2Arr = list2Arr(resultArr);
				List<Integer> rule = ifInRule(result2Arr, rebuildRules[resultArr.get(0).intValue()]);
				if (null != rule) {
					success = true;
					successArr.add(result2Arr);
					Object[] param = {testArr, i, j, failArr.size()};
					params.push(param);
					testArr = afterRemove(testArr, rule);
					check(testArr);
				} else {
					continue;
				}
			}
			index--;
			if (i == len - 3 && !success) {
				addFail(testArr);
			}
		}
	}

	private static void addFail(int[] testArr) throws Exception {
		failArr.add(testArr[0]);
		testArr = removeFirstElement(testArr);
		check(testArr);
	}

	private static void printResult(int[] remainArr, Integer res) {
		System.out.println("添加数字" + res + "可全部消除, 所有组合如下：");
		printSuccess();
		// 打印剩余的
		System.out.print("[");
		print(remainArr);
		System.out.print(res);
		System.out.print("]");
		System.out.println();
	}

	private static void printSuccess() {
		if (null != successArr && successArr.size() > 0) {
			for (int[] item : successArr) {
				System.out.print("[");
				print(item);
				System.out.print("]");
				System.out.println();
			}
		}
	}

	/**
	 * 只剩两个元素时，获取能和这两个元素补足的数字
	 *
	 * @param list
	 * @return
	 */
	private static Integer diffBetweenRuleAndExample(int[] arr) {
		List<Integer> list = arr2List(arr);
		for (Integer[] item : rules) {
			List<Integer> rule = new LinkedList<>(Arrays.asList(item));
			if (rule.containsAll(list)) {
				for (int i = 0; i < 2; i++) {
					if (!list.get(i).equals(rule.get(i))) {
						return rule.get(i);
					}
				}
				return rule.get(2);
			}
		}
		return null;
	}

	private static int[] afterRemove(int[] arr, List<Integer> singleRule) {
		List<Integer> originalList = arr2List(arr);
		for (Integer item : singleRule) {
			originalList.remove(item);
		}
		return list2Arr(originalList);
	}

	private static int[] removeFirstElement(int[] arr) {
		List<Integer> originalList = arr2List(arr);
		;
		originalList.remove(0);
		return list2Arr(originalList);
	}

	private static List<Integer> ifInRule(int[] arr, int[][] rule) {
		for (int i = 0; i < rule.length; i++) {
			if (Arrays.equals(arr, rule[i])) {
				return arr2List(rule[i]);
			}
		}
		return null;
	}

	private static ArrayList<Integer> tmpArr = new ArrayList<>();
	private static ArrayList<Integer> resultArr = new ArrayList<>();

	// 组合算法
	@SuppressWarnings("unchecked")
	private static void combine(int index, int step, int key, int k, int[] arr) {
		if (k == 1) {
			tmpArr.add(arr[key]);
			resultArr = (ArrayList<Integer>) tmpArr.clone();
			tmpArr.remove((Object) arr[key]);
		} else if (k > 1) {
			tmpArr.add(arr[index]); // tmpArr都是临时性存储一下
			combine(index + step, step, key + 1, k - 1, arr); // 索引右移，内部循环，自然排除已经选择的元素
			tmpArr.remove((Object) arr[index]); // tmpArr因为是临时存储的，上一个组合找出后就该释放空间，存储下一个元素继续拼接组合了
		} else {
			return;
		}
	}

	private static void print(int[] arr) {
		for (int a : arr)
			System.out.print(a + " ");
	}

	private static int[] list2Arr(List<Integer> list) {
		int count = list.size();
		int[] res = new int[count];
		for(int i=0; i<count; i++) {
			res[i] = list.get(i).intValue();
		}
		return res;
	}

	private static List<Integer> arr2List(int[] arr) {
		int count = arr.length;
		List<Integer> res = new LinkedList<>();
		for(int i=0; i<count; i++) {
			res.add(Integer.valueOf(arr[i]));
		}
		return res;
	}

	// 测试数据
	private static final int[][] arrArr = {
			// 存在答案的数据
			{4, 5,6,7,8,10,18,19},
			{ 0, 0, 0, 18, 18 },
			{ 4, 4, 4, 10, 19 },
			{ 2, 2, 2, 17, 17 },
			{ 15, 15, 19, 20, 21 },
			{ 10, 10, 10, 15, 15 },
			{ 7, 7, 7, 10, 10 },
			{ 8, 8, 15, 15, 15 },
			{ 19, 19, 19, 19, 19 },
			{ 8, 8, 8, 12, 15 },
			{ 10, 10, 10, 19, 20 },
			{ 12, 12, 12, 13, 13 },
			{ 4, 8, 10, 12, 12 },
			{ 12, 15, 15, 15, 16 },
			{ 5, 5, 15, 16, 17 },
			{ 0, 0, 11, 11, 11 },
			{ 15, 16, 19, 19, 19 },
			{ 11, 11, 11, 12, 20 },
			{ 12, 12, 15, 15, 16 },
			{ 0, 1, 1, 1, 2, 12, 12, 12 },
			{ 8, 8, 12, 12, 13, 14, 15, 16 },
			{ 9, 9, 9, 9, 9, 10, 10, 11 },
			{ 0, 1, 2, 2, 2, 19, 20, 21 },
			{ 4, 4, 4, 6, 6, 12, 12, 12 },
			{ 15, 16, 18, 18, 18, 19, 20, 21 },
			{ 3, 3, 3, 6, 6, 6, 12, 12 },
			{ 4, 4, 4, 5, 5, 5, 5, 5 },
			{ 10, 10, 12, 13, 13, 13, 15, 21 },
			{ 6, 6, 7, 7, 8, 18, 18, 18 },
			{ 5, 5, 12, 12, 13, 14, 20, 21 },
			{ 3, 4, 5, 13, 13, 13, 20, 20 },
			{ 4, 4, 4, 5, 5, 12, 20, 21 },
			{ 12, 13, 14, 15, 16, 17, 21, 21 },
			{ 0, 0, 0, 12, 13, 13, 13, 20 },
			{ 2, 2, 13, 13, 13, 13, 15, 16 },
			{ 0, 0, 1, 1, 1, 13, 15, 16 },
			{ 8, 8, 8, 13, 13, 19, 20, 21 },
			{ 4, 4, 4, 10, 13, 15, 16, 18 },
			{ 3, 3, 3, 3, 3, 3, 14, 14 },
			{ 4, 4, 4, 12, 20, 21, 21, 21 },
			{ 2, 2, 2, 5, 5, 5, 9, 10 },
			{ 0, 0, 0, 4, 8, 8, 8, 8, 10, 13, 15 },
			{ 6, 7, 10, 10, 10, 12, 15, 15, 15, 15, 16 },
			{ 4, 5, 5, 8, 10, 15, 15, 16, 16, 17, 17 },
			{ 2, 2, 2, 3, 3, 3, 5, 5, 5, 21, 21 },
			{ 1, 1, 1, 10, 10, 12, 15, 19, 19, 19, 21 },
			{ 0, 0, 0, 12, 12, 15, 19, 19, 19, 20, 21 },
			{ 0, 0, 0, 3, 3, 3, 6, 7, 19, 19, 19 },
			{ 0, 0, 0, 9, 10, 10, 10, 11, 12, 13, 14, 19, 19, 20 },
			{ 0, 0, 2, 2, 2, 10, 10, 10, 12, 13, 14, 17, 17, 17 },
			{ 0, 1, 4, 5, 5, 5, 8, 10, 13, 13, 13, 17, 17, 17 },
			{ 0, 0, 0, 4, 6, 7, 8, 8, 10, 12, 15, 16, 19, 20 },
			{ 3, 4, 5, 7, 7, 7, 10, 12, 12, 18, 18, 18, 18, 19 },
			{ 4, 4, 4, 4, 8, 10, 12, 13, 13, 13, 15, 18, 18, 18 },
			{ 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 8, 9, 9, 9 },
			{ 0, 1, 2, 8, 8, 8, 9, 10, 18, 18, 18, 20, 20, 20 },
			{ 4, 4, 6, 7, 8, 10, 12, 15, 19, 19, 19, 19, 20, 21 },
			{ 0, 0, 3, 4, 5, 10, 14, 14, 14, 18, 19, 19, 20, 21 },
			{ 3, 3, 3, 7, 7, 7, 9, 10, 10, 10, 10, 18, 18, 18 },
			{ 5, 5, 5, 12, 15, 16, 16, 16, 16, 16, 16, 16, 20, 20 },
			{ 0, 0, 0, 9, 9, 9, 9, 9, 12, 17, 17, 17, 20, 21 },
			{ 1, 1, 1, 4, 4, 4, 4, 8, 10, 10, 10, 15, 15, 15 },
			{ 2, 2, 2, 4, 4, 4, 5, 5, 5, 12, 13, 15, 15, 16, 19, 20, 21 },
			{ 2, 2, 2, 3, 3, 6, 6, 6, 8, 8, 8, 12, 17, 17, 17, 20, 21 },
			{ 0, 1, 1, 1, 1, 2, 6, 7, 8, 13, 13, 18, 18, 18, 19, 19, 19 },
			{ 7, 7, 9, 9, 9, 9, 9, 9, 12, 12, 14, 14, 14, 15, 20, 21, 21 },
			{ 1, 1, 2, 2, 2, 4, 4, 4, 11, 11, 11, 15, 16, 17, 20, 20, 20 },
			{ 0, 0, 0, 4, 8, 9, 9, 9, 9, 9, 10, 19, 20, 20, 20, 20, 21 },
			{ 0, 1, 2, 5, 5, 5, 6, 6, 13, 15, 16, 17, 17, 17, 19, 20, 21 },
			{ 3, 4, 9, 10, 11, 14, 14, 14, 15, 15, 15, 20, 20, 20, 21, 21, 21 },
			{ 3, 3, 3, 9, 10, 11, 12, 14, 14, 14, 15, 16, 19, 19, 19, 20, 21 },
			{ 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 11, 11, 11, 11, 15, 16, 17 },
			{ 3, 4, 4, 4, 5, 5, 5, 5, 9, 9, 9, 11, 11, 11, 12, 14, 14, 14, 20, 21 },
			{ 2, 2, 2, 9, 9, 9, 10, 10, 18, 18, 18, 18, 18, 19, 19, 20, 21, 21, 21, 21 },
			{ 5, 5, 5, 6, 6, 6, 6, 6, 6, 11, 11, 11, 15, 16, 16, 16, 17, 17, 17, 17 },
			{ 4, 4, 4, 5, 5, 5, 10, 10, 14, 14, 14, 19, 19, 19, 19, 19, 20, 21, 21, 21 },
			{ 3, 3, 4, 4, 4, 5, 5, 8, 9, 9, 9, 10, 10, 12, 16, 16, 16, 19, 20, 21 },
			{ 3, 4, 5, 6, 7, 10, 10, 10, 12, 12, 12, 18, 18, 18, 19, 20, 21, 21, 21, 21 },
			{ 3, 4, 5, 5, 5, 5, 7, 7, 7, 9, 10, 10, 10, 10, 11, 21, 21, 21, 21, 21 },
			{ 0, 1, 2, 3, 3, 3, 4, 4, 4, 12, 15, 15, 15, 15, 16, 20, 20, 20, 20, 20 },
			{ 3, 3, 3, 4, 4, 4, 8, 8, 8, 9, 10, 10, 10, 11, 12, 12, 12, 12, 15, 16 },
			{ 4, 4, 4, 7, 7, 8, 8, 8, 9, 10, 10, 11, 12, 12, 12, 12, 12, 15, 16, 18, 19, 20, 21 },
			{ 2, 2, 2, 5, 5, 5, 12, 12, 15, 15, 15, 15, 15, 15, 16, 16, 16, 17, 17, 17, 21, 21, 21 },
			{ 1, 1, 1, 1, 1, 1, 10, 10, 10, 12, 13, 13, 13, 15, 16, 16, 16, 16, 19, 19, 20, 20, 21 },
			{ 0, 0, 0, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 8, 9, 9, 9, 10, 11, 11, 16, 16, 16 },
			{ 1, 1, 1, 3, 3, 3, 6, 7, 8, 8, 8, 8, 11, 11, 11, 12, 13, 13, 15, 16, 17, 17, 17 },
			{ 2, 2, 4, 6, 6, 6, 7, 7, 7, 8, 10, 13, 13, 15, 15, 15, 16, 16, 16, 17, 19, 19, 19 },
			{ 11, 11, 11, 11, 11, 12, 12, 13, 13, 13, 14, 14, 14, 15, 18, 18, 18, 20, 20, 20, 20, 21, 21 },
			{ 4, 4, 4, 6, 6, 6, 7, 8, 9, 10, 11, 11, 11, 11, 13, 13, 13, 13, 15, 16, 17, 17, 17 },
			{ 0, 1, 2, 2, 2, 13, 15, 16, 18, 18, 18, 19, 19, 19, 20, 20, 20, 21, 21, 21, 21, 21, 21 },
			{ 0, 0, 1, 1, 2, 4, 5, 5, 5, 8, 9, 9, 9, 10, 11, 11, 11, 13, 13, 13, 21, 21, 21 },
			{ 0, 0, 0, 1, 2, 3, 3, 3, 6, 6, 6, 9, 9, 9, 10, 11, 11, 11, 12, 18, 19, 19, 19, 19, 20, 21 },
			{ 4, 6, 6, 6, 6, 6, 6, 8, 9, 10, 10, 10, 11, 12, 12, 13, 13, 13, 15, 15, 16, 16, 16, 18, 19, 21 },
			{ 3, 4, 4, 4, 4, 5, 7, 7, 7, 8, 8, 8, 8, 8, 8, 9, 9, 9, 11, 11, 12, 12, 12, 12, 20, 21 },
			{ 2, 2, 2, 3, 3, 3, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 15, 17, 17, 19, 19, 19, 21 },
			{ 3, 3, 4, 4, 4, 5, 5, 5, 6, 7, 7, 7, 7, 7, 7, 7, 8, 15, 15, 15, 16, 16, 16, 19, 20, 21 },
			{ 1, 1, 1, 3, 4, 5, 6, 6, 6, 10, 12, 12, 12, 12, 13, 13, 13, 13, 14, 19, 20, 20, 21, 21, 21, 21 },
			{ 0, 1, 2, 7, 7, 7, 9, 9, 9, 10, 10, 10, 12, 12, 12, 12, 12, 15, 15, 15, 15, 15, 16, 16, 19, 19 },
			{ 1, 1, 1, 5, 5, 5, 5, 5, 5, 7, 7, 7, 9, 9, 9, 10, 15, 15, 16, 16, 17, 19, 19, 20, 20, 21 },
			{ 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 6, 7, 8, 10, 12, 12, 12, 16, 16, 19, 20, 21, 21, 21 },
			{ 12, 12, 12, 15, 15, 16, 21, 21 },

			// 不存在答案的数据
			{ 10, 11, 12, 14, 15, 16, 17, 18 },
			{ 0, 0, 1, 1, 2, 2, 3, 4, 5, 6, 6, 6, 7, 8, 18, 19, 19 },
			{ 0, 1, 2, 4, 4, 4, 8, 8, 8, 10, 10, 11, 11, 16, 16, 17, 18, 18, 19, 19 }
	};

}

