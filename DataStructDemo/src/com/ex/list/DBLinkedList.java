package com.ex.list;


public class DBLinkedList<T> {

	/**
	 * 定义双端链表的数据结构
	 * @author lenovo
	 * @param <M>
	 */
	static final class DBLinkNode<M>
	{
		M data;
		DBLinkNode<M> prior;
		DBLinkNode<M> next;
		public DBLinkNode(M data, DBLinkNode<M> prior, DBLinkNode<M> next) {
			this.data = data;
			this.prior = prior;
			this.next = next;
		}
		
	}
	
	//定义一个双端链表头指针
	private DBLinkNode<T> head;
	
	/**
	 * 双端链表初始化函数
	 */
	private void init() {
		head=new DBLinkNode<T>(null,null,null);
		head.prior=head;
		head.next=head;
	}
	
	/**
	 * 在指定位置插入元素
	 * @param e
	 * @param nPos
	 */
	private void insertElement(T e,int nPos) {
		DBLinkNode<T> cur=getLocation(nPos);
		DBLinkNode<T> newNode=new DBLinkNode<T>(e, cur.prior, cur);
		cur.prior.next=newNode;
		cur.prior=newNode;
	}
	
	/**
	 * 获取指定位置的元素
	 * @param nPos
	 * @return
	 */
	private DBLinkNode<T> getLocation(int nPos) {
		int i=0;
		DBLinkNode<T> cur=head.next;
		while (i<nPos&&cur!=head) {
			cur=cur.next;
			i++;
		}
		return cur;
	}
	
	/**
	 * 删除指定位置的元素，并返回它
	 * @param nPos
	 * @return
	 */
	private T deleteElement(int nPos) {
		DBLinkNode<T> cur=getLocation(nPos);
		cur.next.prior=cur.prior;
		cur.prior.next=cur.next;
		return cur.data;
	}
	
	/**
	 * 打印双端链表元素
	 */
	private void printDBLinkedList() {
		DBLinkNode<T> cur=head.next;
		int i=0;
		while (cur!=head) {
			System.out.println("第"+(++i)+"个元素为:"+cur.data);
			cur=cur.next;
		}
	}
	
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBLinkedList<Integer> dLinkedList=new DBLinkedList<Integer>();
		dLinkedList.init();
		for (int i = 0; i < 10; i++) {
			dLinkedList.insertElement(2*i+1, i);
		}
		System.out.println("初始化时双端链表中的元素为:");
		dLinkedList.printDBLinkedList();
		System.out.println("删除第4个元素"+dLinkedList.deleteElement(3)+"后的双端链表为:");
		dLinkedList.printDBLinkedList();
	}

}
