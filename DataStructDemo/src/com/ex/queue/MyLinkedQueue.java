package com.ex.queue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyLinkedQueue<M> {

	/**
	 * ������ʽ���н�����ݽṹ
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
	 * ������ʽ�������ݽṹ
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
	 * ������ʽ����
	 */
	private LinkQueue<Integer> LQ;

	/**
	 * ��ʼ�����в���
	 */
	private void initLinkedQueue() {
		if (LQ == null) {
			LQ = new LinkQueue<Integer>();
		}
		LQ.front = LQ.rear = new QueueNode<Integer>();
	}

	/**
	 * ����в���
	 * 
	 * @param elem
	 */
	private void enterQueue(int elem) {
		QueueNode<Integer> newNode = new QueueNode<Integer>(elem);
		LQ.rear.next = newNode;
		LQ.rear = newNode;
	}

	/**
	 * �����в���
	 * 
	 * @return
	 */
	private int deleteQueue() {
		if (LQ.front == LQ.rear) {
			System.out.println("����Ϊ�գ�");
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
	 * ����
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyLinkedQueue<Integer> myLinkedQueuey = new MyLinkedQueue<Integer>();
		System.out.println("������ʽ����...");
		myLinkedQueuey.initLinkedQueue();
		int queueSize = -1;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("queue.txt"));
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
		System.out.println("��ӡ��ʽ����...");
		while (myLinkedQueuey.LQ.front != myLinkedQueuey.LQ.rear) {
			System.out.print(myLinkedQueuey.deleteQueue() + " ");
		}
	}
}
