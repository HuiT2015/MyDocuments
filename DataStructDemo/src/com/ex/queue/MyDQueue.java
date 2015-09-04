package com.ex.queue;

import java.util.Random;

public class MyDQueue<T> {

	// ����˫�˶��еĴ�С
	private static int nQueueSize = 100;

	private static final class DQueue<M> {
		int end1;
		int end2;
		M[] queue;
	}

	/**
	 * ����һ��ö�ٽṹ����ʾ���Ҷ���
	 * 
	 * @author lenovo
	 *
	 */
	static enum QType {
		Left {
			void tellDirection(boolean nFlag) {
				if (nFlag == true) {
					System.out.println("Ԫ�ش���������:");
				} else {
					System.out.println("Ԫ�ش���߳����У�");
				}

			}
		},
		Right {
			void tellDirection(boolean nFlag) {
				if (nFlag == true) {
					System.out.println("Ԫ�ش��ұ������:");
				} else {
					System.out.println("Ԫ�ش��ұ߳����У�");
				}

			}
		};
		abstract void tellDirection(boolean nFlag);
	}

	// ����һ��˫�˶��ж���
	private DQueue<T> DQ;

	private void initDQ() {
		DQ = new DQueue<T>();
		DQ.end2 = nQueueSize / 2;
		DQ.end1 = DQ.end2 - 1;
		DQ.queue = (T[]) new Object[nQueueSize];
	}

	/**
	 * Ԫ�����
	 * 
	 * @param e
	 * @param nFlag
	 */
	private void enterQueue(T e, QType eFlag) {
		eFlag.tellDirection(true);
		switch (eFlag) {
		case Left:
			if (DQ.end1 != DQ.end2) {
				DQ.queue[DQ.end1] = e;
				DQ.end1 = (DQ.end1 - 1) % nQueueSize;
			}
			break;
		case Right:
			if (DQ.end1 != DQ.end2) {
				DQ.queue[DQ.end2] = e;
				DQ.end2 = (DQ.end2 + 1) % nQueueSize;
			}
			break;
		default:
			System.out.println("�Ƿ�������У�");
			break;
		}
	}

	/**
	 * ������
	 * 
	 * @param nFlag
	 * @return
	 */
	private T deleteQueue(QType eFlag) {
		T ret = null;
		eFlag.tellDirection(false);
		switch (eFlag) {
		case Left:
			if (DQ.end1 + 1 != DQ.end2) {

				DQ.end1 = (DQ.end1 + 1) % nQueueSize;
				ret = DQ.queue[DQ.end1];
			}
			break;
		case Right:
			if (DQ.end1 + 1 != DQ.end2) {
				DQ.end2 = (DQ.end2 - 1) % nQueueSize;
				ret = DQ.queue[DQ.end2];
			}
			break;
		default:
			System.out.println("�����зǷ���");
			break;
		}
		return ret;
	}

	/**
	 * ��ӡ˫�˶���
	 */
	private void printDQ() {
		int i = 0;
		int nEnd1 = DQ.end1, nEnd2 = DQ.end2;
		while (DQ.end1 + 1 != DQ.end2) {
			DQ.end1 = (DQ.end1 + 1) % nQueueSize;
			System.out.println("��߶����еĵ�" + (++i) + "��Ԫ��Ϊ:" + DQ.queue[DQ.end1]);
			DQ.end2 = (DQ.end2 - 1) % nQueueSize;
			System.out.println("�ұ߶����еĵ�" + (++i) + "��Ԫ��Ϊ:" + DQ.queue[DQ.end2]);
		}
		DQ.end1 = nEnd1;
		DQ.end2 = nEnd2;
	}

	/**
	 * ������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyDQueue<Double> dQueue = new MyDQueue<Double>();
		dQueue.initDQ();
		Random random = new Random();
		for (int i = 0; i < 12; i++) {
			dQueue.enterQueue(i * 1.0, QType.values()[random.nextInt(QType.values().length)]);
		}
		System.out.println("������ǰ�����е�Ԫ��Ϊ:");
		dQueue.printDQ();
		System.out.println("��߶��ж�ͷԪ�س����У��ұ߶��ж�βԪ�س����к�Ķ���Ϊ:");
		dQueue.deleteQueue(QType.Left);
		dQueue.deleteQueue(QType.Right);
		dQueue.printDQ();
	}

}
