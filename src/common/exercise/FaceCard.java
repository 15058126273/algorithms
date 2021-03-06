package common.exercise;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 花牌肖肖的方式
 * @author wdy
 * @version ：2018年4月16日 下午3:51:47
 */
public class FaceCard {

	int[][] rule = {{0, 0, 0}, {0, 1, 2}, {1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {3, 4, 5}, {4, 4, 4}, {4, 8, 10}, {5, 5, 5}, {6, 6, 6}, {6, 7, 8}, {7, 7, 7}, {8, 8, 8}, {9, 9, 9}, {9, 10, 11}, {10, 10, 10}, {10, 18, 19}, {10, 19, 20}, {11, 11, 11}, {12, 12, 12}, {12, 13, 14}, {12, 15, 16}, {12, 15, 21}, {12, 20, 21}, {13, 13, 13}, {13, 15, 16}, {14, 14, 14}, {15, 15, 15}, {15, 16, 17}, {16, 16, 16}, {17, 17, 17}, {18, 18, 18}, {19, 19, 19}, {19, 20, 21}, {20, 20, 20}, {21, 21, 21}};
	Queue<int[]> queue = null;
	int[][][] rules = null;
	int max = 0;

	public void seek(int[] atts) {
		//规则整理，相同的放一起
		rules = new int[22][10][3];
		int ruleIndex = 0;
		int k = 0;
		for (int i = 0; i < rule.length; i++) {
			int index = rule[i][0];
			if (index == ruleIndex) {
				rules[ruleIndex][k] = rule[i];
				k++;
			} else {
				ruleIndex = index;
				k = 0;
				rules[ruleIndex][k] = rule[i];
				k++;
			}
		}

		//数组先进行排序处理
		Arrays.sort(atts);
		int len = atts.length;
		len = len / 3 + 1;
		int[] remains = new int[0];
		Queue<int[]> relation = new LinkedList<int[]>();
		max = 0;
		int luck = canGo(atts, remains, relation);
		System.out.println("我总共模拟了：" + max + " 次。");
		if (-1 == luck) {
			System.out.println("没有找到");
		}

	}

	private int canGo(int[] attIndex, int[] remains, Queue<int[]> relation) {
		int remainsLen = remains.length;
		int len = attIndex.length;
		if (len < 1) {
			//查看是否存在n
			int num = findLuckyNum(remains);
			if (-1 != num) {
				System.out.print("找到幸运者是：" + num);
				for (int[] nums : relation) {
					System.out.print(" , [" + nums[0] + "," + nums[1] + "," + nums[2] + "]");
				}
				System.out.println(" , [" + remains[0] + "," + remains[1] + "," + num + "]");
			}
			return num;
		}
		int luckyNum = -1;
		if (remains.length > 3)
			return luckyNum;

		int first = attIndex[0];
		int luck = -1;
		if (attIndex.length >= 3) {
			int[][] ruleSelf = rules[first];
			for (int i = 0; i < ruleSelf.length; i++) {
				max++;
				luck = -1;
				int[] ruleOne = ruleSelf[i];
				luck = findByRule(ruleOne, attIndex, remains, relation);
				if (-1 != luck) {
					return luck;
				}
			}
		}
		//不使用规则或者没有匹配到的统一处理
		int[] remain = new int[remainsLen + 1];
		System.arraycopy(remains, 0, remain, 0, remainsLen);
		remain[remainsLen] = first;
		int[] newAttIndex = new int[len - 1];
		System.arraycopy(attIndex, 1, newAttIndex, 0, len - 1);

		luckyNum = canGo(newAttIndex, remain, relation);

		return luckyNum;
	}


	//根据规则去查询是否可以肖肖
	private int findByRule(int[] rules, int[] atts, int[] remains, Queue<int[]> relation) {
		int luck = -1;
		int first = atts[0];
		if (first != rules[0])
			return luck;

		int ruleNumIndex = 1;//当前规则序号
		int ruleNum = rules[ruleNumIndex];//当前规则的值
		for (int k = 1; k < atts.length; k++) {
			int attNum = atts[k];
			if (ruleNum < attNum)
				break;

			//匹配到到了
			if (ruleNum == attNum) {
				if (ruleNumIndex == 2) {
					//规则最后一个数字，消肖数字
					int[] newAttIndex = sweep(rules, atts);
					//继续下一个数字
					relation.add(rules);
					luck = canGo(newAttIndex, remains, relation);
					break;
				}

				if (ruleNumIndex < 2) {
					//继续匹配下一个数字
					ruleNumIndex++;
					ruleNum = rules[ruleNumIndex];
				}
				continue;
			}
		}

		return luck;
	}

	/**
	 * 已有符合的规则进行消除
	 *
	 * @param rule
	 * @param original
	 * @return
	 */
	private int[] sweep(int[] rule, int[] original) {
		int len = original.length - rule.length;
		int[] remain = new int[len];
		int k = 0;
		int ruleNum = rule[k];
		int r = 0;
		for (int i = 0; i < original.length; i++) {
			int num = original[i];
			if (num == ruleNum && k < 3) {
				k++;
				if (k < 3)
					ruleNum = rule[k];

			} else {
				remain[r] = num;
				r++;
			}
		}
		return remain;
	}

	/**
	 * 寻找幸运数字
	 *
	 * @param index
	 * @return
	 */
	private int findLuckyNum(int[] index) {
		int luckyNum = -1;
		if (index.length > 2) {
			return luckyNum;
		}
		int num = index[0];
		int other = index[1];
		for (int i = 0; i < rule.length; i++) {
			int[] att = rule[i];
			if (num < att[0] && num < att[1]) {
				break;//不存在
			}
			if (num == att[0] || num == att[1]) {
				for (int k = 0; k < 2; k++) {
					if (num == att[k]) {
						if (other == att[2]) {
							luckyNum = att[1 - k];
							break;
						}
						if (other == att[1 - k]) {
							luckyNum = att[2];
							break;
						}
					}
				}

				if (luckyNum != -1)
					break;
			}
		}

		return luckyNum;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		FaceCard faceCard = new FaceCard();
		for (int i = 0; i < arrArr.length; i++) {
			int[] arr = arrArr[i];
			faceCard.seek(arr);
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
//		int[] arr = {0, 1, 2, 7, 7, 7, 9, 9, 9, 10, 10, 10, 12, 12, 12, 12, 12, 15, 15, 15, 15, 15, 16, 16, 19, 19};
//		faceCard.seek(arr);
	}

	private static final int[][] arrArr = {
			// 存在答案的数据
			{0, 0, 0, 18, 18},
			{4, 4, 4, 10, 19},
			{2, 2, 2, 17, 17},
			{15, 15, 19, 20, 21},
			{10, 10, 10, 15, 15},
			{7, 7, 7, 10, 10},
			{8, 8, 15, 15, 15},
			{19, 19, 19, 19, 19},
			{8, 8, 8, 12, 15},
			{10, 10, 10, 19, 20},
			{12, 12, 12, 13, 13},
			{4, 8, 10, 12, 12},
			{12, 15, 15, 15, 16},
			{5, 5, 15, 16, 17},
			{0, 0, 11, 11, 11},
			{15, 16, 19, 19, 19},
			{11, 11, 11, 12, 20},
			{12, 12, 15, 15, 16},
			{0, 1, 1, 1, 2, 12, 12, 12},
			{8, 8, 12, 12, 13, 14, 15, 16},
			{9, 9, 9, 9, 9, 10, 10, 11},
			{0, 1, 2, 2, 2, 19, 20, 21},
			{4, 4, 4, 6, 6, 12, 12, 12},
			{15, 16, 18, 18, 18, 19, 20, 21},
			{3, 3, 3, 6, 6, 6, 12, 12},
			{4, 4, 4, 5, 5, 5, 5, 5},
			{10, 10, 12, 13, 13, 13, 15, 21},
			{6, 6, 7, 7, 8, 18, 18, 18},
			{5, 5, 12, 12, 13, 14, 20, 21},
			{3, 4, 5, 13, 13, 13, 20, 20},
			{4, 4, 4, 5, 5, 12, 20, 21},
			{12, 13, 14, 15, 16, 17, 21, 21},
			{0, 0, 0, 12, 13, 13, 13, 20},
			{2, 2, 13, 13, 13, 13, 15, 16},
			{0, 0, 1, 1, 1, 13, 15, 16},
			{8, 8, 8, 13, 13, 19, 20, 21},
			{4, 4, 4, 10, 13, 15, 16, 18},
			{3, 3, 3, 3, 3, 3, 14, 14},
			{4, 4, 4, 12, 20, 21, 21, 21},
			{2, 2, 2, 5, 5, 5, 9, 10},
			{0, 0, 0, 4, 8, 8, 8, 8, 10, 13, 15},
			{6, 7, 10, 10, 10, 12, 15, 15, 15, 15, 16},
			{4, 5, 5, 8, 10, 15, 15, 16, 16, 17, 17},
			{2, 2, 2, 3, 3, 3, 5, 5, 5, 21, 21},
			{1, 1, 1, 10, 10, 12, 15, 19, 19, 19, 21},
			{0, 0, 0, 12, 12, 15, 19, 19, 19, 20, 21},
			{0, 0, 0, 3, 3, 3, 6, 7, 19, 19, 19},
			{0, 0, 0, 9, 10, 10, 10, 11, 12, 13, 14, 19, 19, 20},
			{0, 0, 2, 2, 2, 10, 10, 10, 12, 13, 14, 17, 17, 17},
			{0, 1, 4, 5, 5, 5, 8, 10, 13, 13, 13, 17, 17, 17},
			{0, 0, 0, 4, 6, 7, 8, 8, 10, 12, 15, 16, 19, 20},
			{3, 4, 5, 7, 7, 7, 10, 12, 12, 18, 18, 18, 18, 19},
			{4, 4, 4, 4, 8, 10, 12, 13, 13, 13, 15, 18, 18, 18},
			{3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 8, 9, 9, 9},
			{0, 1, 2, 8, 8, 8, 9, 10, 18, 18, 18, 20, 20, 20},
			{4, 4, 6, 7, 8, 10, 12, 15, 19, 19, 19, 19, 20, 21},
			{0, 0, 3, 4, 5, 10, 14, 14, 14, 18, 19, 19, 20, 21},
			{3, 3, 3, 7, 7, 7, 9, 10, 10, 10, 10, 18, 18, 18},
			{5, 5, 5, 12, 15, 16, 16, 16, 16, 16, 16, 16, 20, 20},
			{0, 0, 0, 9, 9, 9, 9, 9, 12, 17, 17, 17, 20, 21},
			{1, 1, 1, 4, 4, 4, 4, 8, 10, 10, 10, 15, 15, 15},
			{2, 2, 2, 4, 4, 4, 5, 5, 5, 12, 13, 15, 15, 16, 19, 20, 21},
			{2, 2, 2, 3, 3, 6, 6, 6, 8, 8, 8, 12, 17, 17, 17, 20, 21},
			{0, 1, 1, 1, 1, 2, 6, 7, 8, 13, 13, 18, 18, 18, 19, 19, 19},
			{7, 7, 9, 9, 9, 9, 9, 9, 12, 12, 14, 14, 14, 15, 20, 21, 21},
			{1, 1, 2, 2, 2, 4, 4, 4, 11, 11, 11, 15, 16, 17, 20, 20, 20},
			{0, 0, 0, 4, 8, 9, 9, 9, 9, 9, 10, 19, 20, 20, 20, 20, 21},
			{0, 1, 2, 5, 5, 5, 6, 6, 13, 15, 16, 17, 17, 17, 19, 20, 21},
			{3, 4, 9, 10, 11, 14, 14, 14, 15, 15, 15, 20, 20, 20, 21, 21, 21},
			{3, 3, 3, 9, 10, 11, 12, 14, 14, 14, 15, 16, 19, 19, 19, 20, 21},
			{6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 11, 11, 11, 11, 15, 16, 17},
			{3, 4, 4, 4, 5, 5, 5, 5, 9, 9, 9, 11, 11, 11, 12, 14, 14, 14, 20, 21},
			{2, 2, 2, 9, 9, 9, 10, 10, 18, 18, 18, 18, 18, 19, 19, 20, 21, 21, 21, 21},
			{5, 5, 5, 6, 6, 6, 6, 6, 6, 11, 11, 11, 15, 16, 16, 16, 17, 17, 17, 17},
			{4, 4, 4, 5, 5, 5, 10, 10, 14, 14, 14, 19, 19, 19, 19, 19, 20, 21, 21, 21},
			{3, 3, 4, 4, 4, 5, 5, 8, 9, 9, 9, 10, 10, 12, 16, 16, 16, 19, 20, 21},
			{3, 4, 5, 6, 7, 10, 10, 10, 12, 12, 12, 18, 18, 18, 19, 20, 21, 21, 21, 21},
			{3, 4, 5, 5, 5, 5, 7, 7, 7, 9, 10, 10, 10, 10, 11, 21, 21, 21, 21, 21},
			{0, 1, 2, 3, 3, 3, 4, 4, 4, 12, 15, 15, 15, 15, 16, 20, 20, 20, 20, 20},
			{3, 3, 3, 4, 4, 4, 8, 8, 8, 9, 10, 10, 10, 11, 12, 12, 12, 12, 15, 16},
			{4, 4, 4, 7, 7, 8, 8, 8, 9, 10, 10, 11, 12, 12, 12, 12, 12, 15, 16, 18, 19, 20, 21},
			{2, 2, 2, 5, 5, 5, 12, 12, 15, 15, 15, 15, 15, 15, 16, 16, 16, 17, 17, 17, 21, 21, 21},
			{1, 1, 1, 1, 1, 1, 10, 10, 10, 12, 13, 13, 13, 15, 16, 16, 16, 16, 19, 19, 20, 20, 21},
			{0, 0, 0, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 8, 9, 9, 9, 10, 11, 11, 16, 16, 16},
			{1, 1, 1, 3, 3, 3, 6, 7, 8, 8, 8, 8, 11, 11, 11, 12, 13, 13, 15, 16, 17, 17, 17},
			{2, 2, 4, 6, 6, 6, 7, 7, 7, 8, 10, 13, 13, 15, 15, 15, 16, 16, 16, 17, 19, 19, 19},
			{11, 11, 11, 11, 11, 12, 12, 13, 13, 13, 14, 14, 14, 15, 18, 18, 18, 20, 20, 20, 20, 21, 21},
			{4, 6, 6, 6, 8, 8, 8, 8, 9, 10, 10, 10, 10, 10, 11, 12, 12, 12, 15, 15, 15, 20, 21},
			{4, 4, 4, 6, 6, 6, 7, 8, 9, 10, 11, 11, 11, 11, 13, 13, 13, 13, 15, 16, 17, 17, 17},
			{0, 1, 2, 2, 2, 13, 15, 16, 18, 18, 18, 19, 19, 19, 20, 20, 20, 21, 21, 21, 21, 21, 21},
			{0, 0, 1, 1, 2, 4, 5, 5, 5, 8, 9, 9, 9, 10, 11, 11, 11, 13, 13, 13, 21, 21, 21},
			{0, 0, 0, 1, 2, 3, 3, 3, 6, 6, 6, 9, 9, 9, 10, 11, 11, 11, 12, 18, 19, 19, 19, 19, 20, 21},
			{4, 6, 6, 6, 6, 6, 6, 8, 9, 10, 10, 10, 11, 12, 12, 13, 13, 13, 15, 15, 16, 16, 16, 18, 19, 21},
			{3, 4, 4, 4, 4, 5, 7, 7, 7, 8, 8, 8, 8, 8, 8, 9, 9, 9, 11, 11, 12, 12, 12, 12, 20, 21},
			{2, 2, 2, 3, 3, 3, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 15, 17, 17, 19, 19, 19, 21},
			{3, 3, 4, 4, 4, 5, 5, 5, 6, 7, 7, 7, 7, 7, 7, 7, 8, 15, 15, 15, 16, 16, 16, 19, 20, 21},
			{1, 1, 1, 3, 4, 5, 6, 6, 6, 10, 12, 12, 12, 12, 13, 13, 13, 13, 14, 19, 20, 20, 21, 21, 21, 21},
			{0, 1, 2, 7, 7, 7, 9, 9, 9, 10, 10, 10, 12, 12, 12, 12, 12, 15, 15, 15, 15, 15, 16, 16, 19, 19},
			{1, 1, 1, 5, 5, 5, 5, 5, 5, 7, 7, 7, 9, 9, 9, 10, 15, 15, 16, 16, 17, 19, 19, 20, 20, 21},
			{2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 6, 7, 8, 10, 12, 12, 12, 16, 16, 19, 20, 21, 21, 21},

			// 不存在答案的数据
			{10, 11, 12, 14, 15, 16, 17, 18},
			{0, 0, 1, 1, 2, 2, 3, 4, 5, 6, 6, 6, 7, 8, 18, 19, 19},
			{0, 1, 2, 4, 4, 4, 8, 8, 8, 10, 10, 11, 11, 16, 16, 17, 18, 18, 19, 19},
	};
}