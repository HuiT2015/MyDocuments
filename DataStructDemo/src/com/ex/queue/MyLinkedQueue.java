package com.ex.queue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyLinkedQueue<M> {

	/**
	 * 定义链式队列结点数据结构
	 * 
	 * @author lenovo
	 * 
	 * @param <T>
	 */
	private final static class QueueNode<T> {
		T data;
		QueueNode<T> next;

		public QueueNode() {
			this(null, null);
		}

		public QueueNode(T data) {
			this(data, null);
		}

		public QueueNode(T data, QueueNode<T> next) {
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * 定义链式队列数据结构
	 * 
	 * @author lenovo
	 * 
	 * @param <M>
	 */
	private final static class LinkQueue<M> {
		QueueNode<M> front;
		QueueNode<M> rear;
	}

	/**
	 * 定义链式队列
	 */
	private LinkQueue<Integer> LQ;

	/**
	 * 初始化队列操作
	 */
	private void initLinkedQueue() {
		if (LQ == null) {
			LQ = new LinkQueue<Integer>();
		}
		LQ.front = LQ.rear = new QueueNode<Integer>();
	}

	/**
	 * 入队列操作
	 * 
	 * @param elem
	 */
	private void enterQueue(int elem) {
		QueueNode<Integer> newNode = new QueueNode<Integer>(elem);
		LQ.rear.next = newNode;
		LQ.rear = newNode;
	}

	/**
	 * 出队列操作
	 * 
	 * @return
	 */
	private int deleteQueue() {
		if (LQ.front == LQ.rear) {
			System.out.println("队列为空！");
			return -1;
		}
		QueueNode<Integer> newNode = LQ.front.next;
		int result = newNode.data;
		LQ.front.next = newNode.next;
		if (LQ.rear == newNode) {
			LQ.rear = LQ.front;
		}
		return result;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyLinkedQueue<Integer> myLinkedQueuey = new MyLinkedQueue<Integer>();
		System.out.println("建立链式队列...");
		myLinkedQueuey.initLinkedQueue();
		int queueSize = -1;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					"queue.txt"));
			try {
				String[] strArr = bufferedReader.readLine().split(" ");
				queueSize = Integer.parseInt(strArr[0]);
				for (int i = 0; i < queueSize; i++) {
					myLinkedQueuey.enterQueue(Integer.parseInt(strArr[i + 1]));
				}
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("打印链式队列...");
		while (myLinkedQueuey.LQ.front != myLinkedQueuey.LQ.rear) {
			System.out.print(myLinkedQueuey.deleteQueue() + " ");
		}
	}
}
