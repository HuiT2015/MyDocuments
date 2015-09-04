package com.ex.stack;

public class LinkedStack<T> {
	
	/**
	 * ����һ����ʽջ�����ݽṹ
	 * @author lenovo
	 *
	 * @param <T>
	 */
	static class LinkedNode<T>
	{
		T data;
		LinkedNode<T> next;
		public LinkedNode(T data,LinkedNode<T> next) {
			this.data = data;
			this.next = next;
		}
		
	}
	
	//��һ��ջ��ָ��
	public LinkedNode<T> top=null;
	
	/**
	 * ��ջ
	 * @param e
	 */
	public void push(T e) {
		if (top==null) {
			top=new LinkedNode<T>(null, null);
		}
		LinkedNode<T> newNode=new LinkedNode<T>(e, top.next);
		top.next=newNode;
	}
	
	/**
	 * ��ջ
	 * @return
	 */
	public T pop() {
		T result=null;
		LinkedNode<T> p=top.next;
		if (p!=null) {
			top.next=p.next;
			result=p.data;
		}
		return result;
	}
	
	/**
	 * ����ջ��Ԫ�ض���ɾ��
	 * @return
	 */
	public T peekTop() {
		return top.next.data;
	}
	
	/**
	 * ���ջ�е�Ԫ��
	 */
	public void clear() {
		LinkedNode<T> p=top;
		LinkedNode<T> q=null;
		while (p!=null) {
			q=p;
			p.data=null;
			p.next=null;
			p=null;
			p=q.next;
		}
	}
	
	/**
	 * �ж�ջ�Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty() {
		return top.next==null;
	}
}
