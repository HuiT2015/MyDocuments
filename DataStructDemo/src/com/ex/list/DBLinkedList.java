package com.ex.list;

public class DBLinkedList<T> {

	/**
	 * ����˫����������ݽṹ
	 * 
	 * @author lenovo
	 * @param <M>
	 */
	static final class DBLinkNode<M> {
		M data;
		DBLinkNode<M> prior;
		DBLinkNode<M> next;

		public DBLinkNode(M data, DBLinkNode<M> prior, DBLinkNode<M> next) {
			this.data = data;
			this.prior = prior;
			this.next = next;
		}

	}

	// ����һ��˫������ͷָ��
	private DBLinkNode<T> head;

	/**
	 * ˫�������ʼ������
	 */
	private void init() {
		head = new DBLinkNode<T>(null, null, null);
		head.prior = head;
		head.next = head;
	}

	/**
	 * ��ָ��λ�ò���Ԫ��
	 * 
	 * @param e
	 * @param nPos
	 */
	private void insertElement(T e, int nPos) {
		DBLinkNode<T> cur = getLocation(nPos);
		DBLinkNode<T> newNode = new DBLinkNode<T>(e, cur.prior, cur);
		cur.prior.next = newNode;
		cur.prior = newNode;
	}

	/**
	 * ��ȡָ��λ�õ�Ԫ��
	 * 
	 * @param nPos
	 * @return
	 */
	private DBLinkNode<T> getLocation(int nPos) {
		int i = 0;
		DBLinkNode<T> cur = head.next;
		while (i < nPos && cur != head) {
			cur = cur.next;
			i++;
		}
		return cur;
	}

	/**
	 * ɾ��ָ��λ�õ�Ԫ�أ���������
	 * 
	 * @param nPos
	 * @return
	 */
	private T deleteElement(int nPos) {
		DBLinkNode<T> cur = getLocation(nPos);
		cur.next.prior = cur.prior;
		cur.prior.next = cur.next;
		return cur.data;
	}

	/**
	 * ��ӡ˫������Ԫ��
	 */
	private void printDBLinkedList() {
		DBLinkNode<T> cur = head.next;
		int i = 0;
		while (cur != head) {
			System.out.println("��" + (++i) + "��Ԫ��Ϊ:" + cur.data);
			cur = cur.next;
		}
	}

	/**
	 * ������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBLinkedList<Integer> dLinkedList = new DBLinkedList<Integer>();
		dLinkedList.init();
		for (int i = 0; i < 10; i++) {
			dLinkedList.insertElement(2 * i + 1, i);
		}
		System.out.println("��ʼ��ʱ˫�������е�Ԫ��Ϊ:");
		dLinkedList.printDBLinkedList();
		System.out.println("ɾ����4��Ԫ��" + dLinkedList.deleteElement(3) + "���˫������Ϊ:");
		dLinkedList.printDBLinkedList();
	}

}
