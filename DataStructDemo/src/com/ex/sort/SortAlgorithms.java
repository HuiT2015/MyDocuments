package com.ex.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * �����㷨���ܣ�Ĭ�ϰ�������������� ����������Ϊ��{2,16,9,8,11,33,5,4} ���������㷨��ע�Ͳ���
 * 
 * @author lenovo write:Sep 3,2015 21:05 in School of Remote Sensing and
 *         Information Technology in Wuhan University
 */

public class SortAlgorithms {

	static enum ESort {
		DI {
			void print(int[] nArray) {
				System.out.println("ֱ�Ӳ�������Ľ��Ϊ:" + Arrays.toString(nArray));
			}
		},
		HF {
			void print(int[] nArray) {
				System.out.println("�۰���Ҳ�������Ľ��Ϊ:" + Arrays.toString(nArray));
			}
		},
		SH {
			void print(int[] nArray) {
				System.out.println("ϣ������Ľ��Ϊ:" + Arrays.toString(nArray));
			}
		},
		SS {
			void print(int[] nArray) {
				System.out.println("��ѡ������Ľ��Ϊ:" + Arrays.toString(nArray));
			}
		},
		BS {
			void print(int[] nArray) {
				System.out.println("ð������Ľ��Ϊ:" + Arrays.toString(nArray));
			}
		};
		abstract void print(int[] nArray);
	}

	/**
	 * ����������
	 */
	private static int[] nArr = { 2, 16, 9, 8, 11, 33, 5, 4 };

	/**
	 * ��ں���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("����������ʽ:DI��ʾֱ�Ӳ�������HF��ʾ�۰��������SH��ʾϣ������SS��ʾ��ѡ������BS��ʾð������.");
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
	 * ֱ�Ӳ�������
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
	 * �۰��������
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
	 * ϣ������
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
				System.out.println("���һ������");
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
	 * ��ѡ������
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
	 * ð������
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
