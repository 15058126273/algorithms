package common.exercise;

public class HuCombination {

	/**
	 * 查找组合
	 * @author wdy
	 * @version ：2018年4月9日 下午1:48:44
	 * @param atts
	 * @param min
	 * @param minScore
	 */
	public static void seek(int[] atts , int min,  int max) {
		if(atts.length <= 0)
			return;
		
		//统计对应数字出现的次数
		int[] nums = new int[11];
		for (int att : atts) {
			nums[att] = nums[att] + 1;
		}
		
		//统计指定次数的包含的数字，通过二维数组对应的维度保存数据
		int[][] scores = new int[11][11];
		for(int i = 1;i <= 10; i++) {
			int cur = nums[i];
			if(cur >= min && cur <= max) {
				int curNum = scores[cur][0] + 1;
				scores[cur][0] = curNum;
				scores[cur][curNum] = i;
			}
		}
		
		//计算有多少种方案
		int total = 0;
		int num = scores[min][0] + scores[min + 1][0];
		total += (num * (num -1 ) * (num - 2) ) / 3 / 2;
		
		total += ( (num - 1)* scores[min + 1][0] ) ;
		
		System.out.println("总计有：" + total + "种方案！");
		System.out.println("我来罗列下：");
		
		int[] newNum = new int[11];
		
		num = scores[min][0];
		int i = 0;
		for(;i < num;i++) {
			newNum[i] = scores[min][i+1];
		}
		
		num = scores[min][0] + scores[min + 1][0];
		int j = 0;
		for(;i < num;i++) {
			newNum[i] = scores[min + 1][j+1];
			j++;
		}
		
		//情况一，1+1+1
		int k = 1;
		for(int k1 = 0;k1 < num; k1 ++) {
			for(int k2 = k1 + 1;k2 < num; k2 ++) {
				for(int k3 = k2 + 1;k3 < num; k3 ++) {
					int num1 = newNum[k1];
					int num2 = newNum[k2];
					int num3 = newNum[k3];
					System.out.println("方案" + k + "：[" + num1 + num1 + num1 + num1 + "," + num2 + num2 + num2 + num2 + ","+ num3 + num3 + num3 + num3 + "]");
					k++;
				}
			}
		}
		
		//情况二，1+2
		int numMax = scores[min + 1][0];
		for(int k1 = 1;k1 <= numMax; k1 ++) {
			for(int k2 = 0;k2 < num; k2 ++) {
				int num1 = scores[min + 1][k1];
				int num2 = newNum[k2];
				if(num1 == num2) {
					continue;
				}
				System.out.println("方案" + k + "：[" + num1 + num1 + num1 + num1 + num1 + "," + num2 + num2 + num2 + num2 + "]");
				k++;
			}
		}
		
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,2,2,2,2,3,3,4,4,4,5,5,5,5,6,6,6,6,6,7,7,7,7,8,8,9,9,9,9,9};
		long start = System.currentTimeMillis();
//		int[][] testArr = {
//				{1,1,1,2,3,3,4,4,5,6,7,7,7,8,8,8,9,9,10},
//				{1,1,1,1,1,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5,6,6,6,6,7,7,7,8,8,8,8,8,9,9,9,9,9,10,10,10,10},
//				{1,1,1,1,2,2,2,3,4,4,5,5,5,6,6,6,6,6,7,7,7,8,8,8,8,9,9,10,10,10,10},
//				{1,2,3,3,4,4,6,7,8,9,9},
//				{1,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,6,6,6,6,7,7,7,7,8,8,9,9,9,9,10},
//				{1,1,1,1,1,2,2,2,2,2,3,4,4,4,4,4,5,5,5,5,6,6,6,6,7,7,8,8,8,8,8,9,9,9,10,10},
//				{1,1,4,4,4,6},
//				{1,1,1,1,2,2,3,3,3,4,4,4,4,4,5,6,6,6,6,7,7,7,7,7,8,8,8,8,8,9,9,9,10,10,10},
//				{3,4,5,5,5,6,6,7},
//				{1,2,2,2,2,2,3,3,4,4,5,5,6,6,7,7,8,8,8,9,9,10,10,10,10,10},
//				{1,1,1,2,2,2,2,3,3,4,4,4,5,5,5,5,5,6,6,6,6,6,7,7,8,8,9,10,10,10,10,10},
//				{1,3,3,4,5,5,6,6,10},
//				{1},
//				{1,1,1,1,1,2,2,2,2,2,3,3,4,4,4,4,4,6,6,6,6,6,7,7,7,7,8,8,8,8,8,9,9,9,10,10,10},
//				{1,1,1,1,1,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5,6,6,6,6,6,7,7,7,7,7,8,8,9,9,10,10,10,10,10},
//				{2,3,5},
//				{3,3,3,3,6,7,7},
//				{1,1,2,3,3,3,4,5,5,6,6,6,7,7,7,7,7,9,9,9,10,10,10},
//				{1,1,1,1,2,2,2,3,3,3,3,4,4,4,4,4,5,5,6,6,6,6,6,7,7,8,8,8,8,8,9,9,9,9,10},
//				{2,3,3,6,7,9}
//		};
//		for(int i = 0;i < testArr.length;i++) {
//			int[] arr = testArr[i];
			seek(arr, 4,  5);
//		}

		System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
	}

}
