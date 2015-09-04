package com.ex.list;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MyLinkedList<M> {

	// 定义单链表的数据结构
	private static class LinkedNode<T> {
		T data;
		LinkedNode<T> next;

		public LinkedNode() {
			this(null, null);
		}

		public LinkedNode(T data) {
			this(data, null);
		}

		public LinkedNode(T data, LinkedNode<T> next) {
			this.data = data;
			this.next = next;
		}
	}

	// 定义一个单链表的头指针
	private LinkedNode<M> head;

	/**
	 * 初始化链表
	 */
	private void initList() {
		head = null;
	}

	/**
	 * 创建链表
	 */
	private void createLinkedList() {
		if (head == null) {
			head = new LinkedNode<M>();
		}
		BufferedReader bufferedReader;
		String[] strArr = null;
		try {
			bufferedReader = new BufferedReader(new FileReader("list.txt"));
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
		int nodeNum = Integer.parseInt(strArr[0]);
		LinkedNode<M> newNode = null;
		for (int i = 0; i < nodeNum; i++) {
			Character inputCh = strArr[i + 1].charAt(0);
			newNode = new LinkedNode<M>((M) inputCh);
			newNode.next = head.next;
			head.next = newNode;
		}
	}

	/**
	 * 打印链表
	 */
	private void printLinkedList() {
		LinkedNode<M> p = head.next;
		while (p != null) {
			System.out.print(p.data + "\t");
			p = p.next;
		}
	}

	/**
	 * n指第几个节点（1,2,3...),nFlag={-1,0,1},分别返回前、中、后节点
	 * 
	 * @param n
	 * @param nFlag
	 * @return
	 */
	private LinkedNode<M> locateNode(int n, int nFlag) {
		LinkedNode<M> p = head;
		int nCnt = 0;
		while (p.next != null && nCnt < n + nFlag) {
			p = p.next;
			nCnt++;
		}
		return p;
	}

	/**
	 * 插入节点操作
	 * 
	 * @param e
	 * @param i
	 */
	private void insertNode(M e, int i) {
		LinkedNode<M> pre = locateNode(i, -1);
		LinkedNode<M> newNode = new LinkedNode<M>(e);
		newNode.next = pre.next;
		pre.next = newNode;
	}

	/**
	 * 删除节点操作
	 * 
	 * @param i
	 */
	private void eraseNode(int i) {
		LinkedNode<M> pre = locateNode(i, -1);
		LinkedNode<M> cur = locateNode(i, 0);
		pre.next = cur.next;
		cur.next = null;
		cur.data = null;
		cur = null;
	}

	/**
	 * 主函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyLinkedList<Character> myLinkedList = new MyLinkedList<Character>();
		myLinkedList.initList();
		System.out.println("建立单链表！");
		myLinkedList.createLinkedList();
		System.out.println("打印单链表！");
		myLinkedList.printLinkedList();
	}
}
