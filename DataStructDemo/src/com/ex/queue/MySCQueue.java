package com.ex.queue;

public class MySCQueue<T> {

	// 顺序循环队列大小，可以自动扩容
	private static int nQueueSize = 10;

	/**
	 * 定义顺序循环队列的数据结构
	 * 
	 * @author lenovo
	 *
	 * @param <M>
	 */
	private static final class SCQueue<M> {
		int front;
		int rear;
		M[] queue;

		public SCQueue() {
			// TODO Auto-generated constructor stub
			front = rear = 0;
			queue = (M[]) new Object[nQueueSize];
		}
	}

	/**
	 * 定义一个顺序循环队列的对象
	 */
	private SCQueue<T> SQ;

	/**
	 * 初始化顺序循环队列SQ
	 */
	private void initQueue() {
		SQ = new SCQueue<T>();
		SQ.rear = SQ.front = 0;
	}

	/**
	 * 向顺序循环队列中插入元素，如果队列已满，队列将会自动扩容两倍
	 * 
	 * @param e
	 */
	private void EnterQueue(T e) {
		if (SQ.front == (SQ.rear + 1) % nQueueSize) {
			System.out.println("当前队列已满，队列将扩容为原来的两倍");
			enlargeQueue();
		}
		SQ.queue[SQ.rear] = e;
		SQ.rear = (SQ.rear + 1) % nQueueSize;
	}

	/**
	 * 扩大队列的容量为原来的两倍
	 */
	private void enlargeQueue() {
		T[] tmpQueue = (T[]) new Object[(SQ.rear + nQueueSize - SQ.front) % nQueueSize];
		for (int i = 0; i < tmpQueue.length; i++) {
			tmpQueue[i] = DeleteQueue();
		}
		SQ.front = SQ.rear = 0;
		nQueueSize *= 2;
		SQ.queue = (T[]) new Object[nQueueSize];
		for (int i = 0; i < tmpQueue.length; i++) {
			EnterQueue(tmpQueue[i]);
		}
	}

	/**
	 * 删除队列的对头元素并返回对头元素
	 * 
	 * @return
	 */
	private T DeleteQueue() {
		if (SQ.front == SQ.rear) {
			System.out.println("队列中已经为空:");
			return null;
		}
		T e = SQ.queue[SQ.front];
		SQ.front = (SQ.front + 1) % nQueueSize;
		return e;
	}

	/**
	 * 打印队列元素
	 */
	private void printSCQueue() {
		int i = SQ.front, j = 0;
		while (i != SQ.rear) {
			System.out.println("队列的第" + (++j) + "个的元素为:" + SQ.queue[i]);
			i = (i + 1) % nQueueSize;
		}
	}

	/**
	 * 主函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MySCQueue<Character> queue = new MySCQueue<Character>();
		queue.initQueue();
		for (int i = 0; i < 26; i++) {
			queue.EnterQueue((char) (i + 65));
		}
		System.out.println("出队列前队列中的元素为:");
		queue.printSCQueue();
		queue.DeleteQueue();
		System.out.println("对头元素出队列后队列中的元素为:");
		queue.printSCQueue();
	}

}
