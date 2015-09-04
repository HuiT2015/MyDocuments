package com.ex.queue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MySeqQueue<M> {

	// 定义队列的容量大小
	private static final int QUEUE_SIZE = 100;

	// 定义单端队列的数据结构
	private static class SeqQueue<T> {
		T[] queue = (T[]) new Object[QUEUE_SIZE];
		int front, rear;
	}

	// 定义单端队列
	private SeqQueue<M> seqQueue;

	/**
	 * 初始化单端队列
	 */
	private void initSeqQueue() {
		if (seqQueue == null) {
			seqQueue = new SeqQueue<M>();
		}
		seqQueue.front = seqQueue.rear = 0;
	}

	/**
	 * 向队尾添加元素
	 * 
	 * @param e
	 */
	private void enterQueue(M e) {
		if (seqQueue.rear == QUEUE_SIZE) {
			System.out.println("队列满员！");
			return;
		}
		seqQueue.queue[seqQueue.rear++] = e;
	}

	/**
	 * 从对头删除元素
	 * 
	 * @return
	 */
	private M deleteQueue() {
		if (seqQueue.front == seqQueue.rear) {
			System.out.println("队列已经为空！");
			return null;
		}
		return seqQueue.queue[seqQueue.front++];
	}

	/**
	 * 从文件中创建队列
	 */
	private void createSeqQueue() {
		BufferedReader bufferedReader;
		String[] strArr = null;
		try {
			bufferedReader = new BufferedReader(new FileReader("queue.txt"));
			try {
				strArr = bufferedReader.readLine().split(" ");
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int queueSize = Integer.parseInt(strArr[0]);
		for (int i = 0; i < queueSize; i++) {
			Object elem = (Object) Integer.parseInt(strArr[i + 1]);
			enterQueue((M) elem);
		}
	}

	/**
	 * 主函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MySeqQueue<Integer> mySeqQueue = new MySeqQueue<Integer>();
		mySeqQueue.initSeqQueue();
		System.out.println("建立线性队列！");
		mySeqQueue.createSeqQueue();
		System.out.println("打印线性队列！");
		for (int i = 0; i < mySeqQueue.seqQueue.rear; i++) {
			System.out.print(mySeqQueue.deleteQueue() + " ");
		}
	}
}
