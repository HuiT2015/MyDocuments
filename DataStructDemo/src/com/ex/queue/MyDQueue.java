package com.ex.queue;

import java.util.Random;

public class MyDQueue<T> {

	//定义双端队列的大小
	private static int nQueueSize=100;
	
	private static final class DQueue<M>
	{
		int end1;
		int end2;
		M []queue;
	}
	
	/**
	 * 定义一个枚举结构来表示左右队列
	 * @author lenovo
	 *
	 */
	static enum QType
	{
		Left{
			void tellDirection(boolean nFlag)
			{
				if (nFlag==true) {
					System.out.println("元素从左边入队列:");
				}
				else {
					System.out.println("元素从左边出队列：");
				}
			
			}
		},
		Right
		{
			void tellDirection(boolean nFlag)
			{
				if (nFlag==true) {
					System.out.println("元素从右边入队列:");
				}
				else {
					System.out.println("元素从右边出队列：");
				}
			
			}
		};
		abstract void tellDirection(boolean nFlag);
	}
	
	//定义一个双端队列对象
	private DQueue<T> DQ;
	
	private void initDQ() {
		DQ=new DQueue<T>();
		DQ.end2=nQueueSize/2;
		DQ.end1=DQ.end2-1;
		DQ.queue=(T [])new Object[nQueueSize];
	}
	
	/**
	 * 元素入队
	 * @param e
	 * @param nFlag
	 */
	private void enterQueue(T e,QType eFlag) {
		eFlag.tellDirection(true);
		switch (eFlag) {
		case Left:
			if (DQ.end1!=DQ.end2) {
				DQ.queue[DQ.end1]=e;
				DQ.end1=(DQ.end1-1)%nQueueSize;
			}
			break;
		case Right:
			if (DQ.end1!=DQ.end2) {
				DQ.queue[DQ.end2]=e;
				DQ.end2=(DQ.end2+1)%nQueueSize;
			}
			break;
		default:
			System.out.println("非法进入队列！");
			break;
		}
	}
	/**
	 * 出队列
	 * @param nFlag
	 * @return
	 */
	private T deleteQueue(QType eFlag) {
		T ret = null;
		eFlag.tellDirection(false);
		switch (eFlag) {
		case Left:
			if (DQ.end1+1!=DQ.end2) {
				
				DQ.end1=(DQ.end1+1)%nQueueSize;
				ret=DQ.queue[DQ.end1];
			}
			break;
		case Right:
			if (DQ.end1+1!=DQ.end2) {
				DQ.end2=(DQ.end2-1)%nQueueSize;
				ret=DQ.queue[DQ.end2];
			}
			break;
		default:
			System.out.println("出队列非法！");
			break;
		}
		return ret;
	}
	

	/**
	 * 打印双端队列
	 */
	private void printDQ() {
		int i=0;
		int nEnd1=DQ.end1,nEnd2=DQ.end2;
		while (DQ.end1+1!=DQ.end2) {
			DQ.end1=(DQ.end1+1)%nQueueSize;
			System.out.println("左边对列中的第"+(++i)+"个元素为:"+DQ.queue[DQ.end1]);
			DQ.end2=(DQ.end2-1)%nQueueSize;
			System.out.println("右边对列中的第"+(++i)+"个元素为:"+DQ.queue[DQ.end2]);
		}
		DQ.end1=nEnd1;
		DQ.end2=nEnd2;
	}
	
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyDQueue<Double> dQueue=new MyDQueue<Double>();
		dQueue.initDQ();
		Random random=new Random();
		for (int i = 0; i < 12; i++) {
			dQueue.enterQueue(i*1.0,QType.values()[random.nextInt(QType.values().length)]);
		}
		System.out.println("出队列前队列中的元素为:");
		dQueue.printDQ();
		System.out.println("左边队列对头元素出队列，右边队列队尾元素出队列后的队列为:");
		dQueue.deleteQueue(QType.Left);
		dQueue.deleteQueue(QType.Right);
		dQueue.printDQ();
	}

}
