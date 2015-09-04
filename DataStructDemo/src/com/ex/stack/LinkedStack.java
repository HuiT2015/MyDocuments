package com.ex.stack;

public class LinkedStack<T> {
	
	/**
	 * 定义一个链式栈的数据结构
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
	
	//顶一个栈顶指针
	public LinkedNode<T> top=null;
	
	/**
	 * 进栈
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
	 * 出栈
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
	 * 返回栈顶元素而不删除
	 * @return
	 */
	public T peekTop() {
		return top.next.data;
	}
	
	/**
	 * 清空栈中的元素
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
	 * 判断栈是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return top.next==null;
	}
}
