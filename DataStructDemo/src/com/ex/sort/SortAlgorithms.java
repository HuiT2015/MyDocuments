package com.ex.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 排序算法汇总，默认按照升序进行排序 待排序数组为：{2,16,9,8,11,33,5,4} 具体排序算法见注释部分
 * 
 * @author lenovo write:Sep 3,2015 21:05 in School of Remote Sensing and
 *         Information Technology in Wuhan University
 */

public class SortAlgorithms {

	static enum ESort {
		DI {
			void print(int[] nArray) {
				System.out.println("直接插入排序的结果为:" + Arrays.toString(nArray));
			}
		},
		HF {
			void print(int[] nArray) {
				System.out.println("折半查找插入排序的结果为:" + Arrays.toString(nArray));
			}
		},
		SH {
			void print(int[] nArray) {
				System.out.println("希尔排序的结果为:" + Arrays.toString(nArray));
			}
		},
		SS {
			void print(int[] nArray) {
				System.out.println("简单选择排序的结果为:" + Arrays.toString(nArray));
			}
		},
		BS {
			void print(int[] nArray) {
				System.out.println("冒泡排序的结果为:" + Arrays.toString(nArray));
			}
		};
		abstract void print(int[] nArray);
	}

	/**
	 * 待排序数组
	 */
	private static int[] nArr = { 2, 16, 9, 8, 11, 33, 5, 4 };

	/**
	 * 入口函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("请输入排序方式:DI表示直接插入排序，HF表示折半插入排序，SH表示希尔排序，SS表示简单选择排序，BS表示冒泡排序.");
		ESort sortType;
		Scanner scanner = new Scanner(System.in);
		sortType = ESort.valueOf(scanner.next());
		switch (sortType) {
		case DI:
			insertSort();
			break;
		case HF:
			halfFindSort();
			break;
		case SH:
			shellInsertSort();
			break;
		case SS:
			simpleSelectSort();
			break;
		case BS:
			bubbleSort();
			break;
		default:
			break;
		}
		sortType.print(nArr);
	}

	/**
	 * 直接插入排序
	 */
	private static void insertSort() {
		int nTemp = 0;
		for (int i = 0; i < nArr.length - 1; i++) {
			nTemp = nArr[i + 1];
			int j = i;
			while (j > -1 && nTemp < nArr[j]) {
				nArr[j + 1] = nArr[j];
				j--;
			}
			nArr[j + 1] = nTemp;
		}
	}

	/**
	 * 折半插入排序
	 */
	private static void halfFindSort() {
		int temp, low, high, mid;
		int i, j;
		for (i = 0; i < nArr.length - 1; i++) {
			temp = nArr[i + 1];
			low = 0;
			high = i;
			while (low <= high) {
				mid = (low + high) / 2;
				if (nArr[mid] > temp) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}
			for (j = i; j >= low; j--) {
				nArr[j + 1] = nArr[j];
			}
			nArr[low] = temp;
		}

	}

	/**
	 * 希尔排序
	 */
	private static void shellInsertSort() {
		for (int i = nArr.length / 2 - 1; i >= 0; i--) {
			shellInsert(2 * i + 1);
		}
	}

	private static void shellInsert(int delta) {
		int nTemp, j;
		for (int i = delta; i < nArr.length; i++) {
			if (i == 1) {
				System.out.println("最后一趟排序！");
			}
			if (nArr[i] < nArr[i - delta]) {
				nTemp = nArr[i];
				for (j = i - delta; j > 0 && nArr[j] > nTemp; j -= delta) {
					nArr[j + delta] = nArr[j];
				}
				nArr[j + delta] = nTemp;
			}
		}
	}

	/**
	 * 简单选择排序
	 */
	private static void simpleSelectSort() {
		int nTemp = 0;
		for (int i = 0; i < nArr.length - 1; i++) {
			for (int j = i + 1; j < nArr.length; j++) {
				if (nArr[i] > nArr[j]) {
					nTemp = nArr[i];
					nArr[i] = nArr[j];
					nArr[j] = nTemp;
				}
			}
		}
	}

	/**
	 * 冒泡排序
	 */
	private static void bubbleSort() {
		int nTemp = 0;
		for (int i = 0; i < nArr.length - 1; i++) {
			for (int j = 0; j < nArr.length - i - 1; j++) {
				if (nArr[j] > nArr[j + 1]) {
					nTemp = nArr[j];
					nArr[j] = nArr[j + 1];
					nArr[j + 1] = nTemp;
				}
			}
		}
	}

}
