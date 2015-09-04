package com.ex.tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.ex.graph.LinkedStack;

public class BinaryTree {

	// ������ڵ�
	private BinaryNode<Character> root;
	// ����һ����ʽջ�����洢���ʹ��Ľڵ�
	private LinkedStack<BinaryNode<Character>> linkedStack = null;

	/**
	 * ��ʼ��
	 */
	private void initBinaryTree() {
		this.root = null;
		linkedStack = new LinkedStack<BinaryNode<Character>>();
	}

	/**
	 * ����������
	 * 
	 * @param treeNode
	 * @return
	 */
	private BinaryNode<Character> createBinaryTree(BinaryNode<Character> treeNode, BufferedReader bufReader)
			throws IOException {
		Character inputCh = bufReader.readLine().charAt(0);
		if (inputCh.equals('#'))
			return null;
		else {
			if (treeNode == null)
				treeNode = new BinaryNode<Character>(inputCh);
			treeNode.left = createBinaryTree(treeNode.left, bufReader);
			treeNode.right = createBinaryTree(treeNode.right, bufReader);
			return treeNode;
		}
	}

	/**
	 * ����������
	 * 
	 * @param p
	 * @param newEle
	 */
	private void insertLeftChild(BinaryNode<Character> p, Character newEle) {
		if (p != null) {
			BinaryNode<Character> newNode = new BinaryNode<Character>(newEle);
			newNode.right = p.left;
			p.left = newNode;
		}
	}

	/**
	 * ����������
	 * 
	 * @param p
	 * @param newEle
	 */
	private void insertRightChild(BinaryNode<Character> p, Character newEle) {
		if (p != null) {
			BinaryNode<Character> newNode = new BinaryNode<Character>(newEle);
			newNode.right = p.right;
			p.right = newNode;
		}
	}

	/**
	 * ǰ�����
	 */
	private void preOrderTraverse() {
		linkedStack.clear();
		BinaryNode<Character> p = root;
		while (p != null || linkedStack.isEmpty() == false) {
			while (p != null) {
				System.out.print(p.element + " ");
				linkedStack.push(p);
				p = p.left;
			}
			if (linkedStack.isEmpty() != true) {
				p = linkedStack.pop();
				p = p.right;
			}
		}
		System.out.print("\n");
	}

	/**
	 * �������
	 */
	private void inOrderTraverse() {
		linkedStack.clear();
		BinaryNode<Character> p = root;
		while (p != null || linkedStack.isEmpty() == false) {
			while (p != null) {
				linkedStack.push(p);
				p = p.left;
			}
			if (linkedStack.isEmpty() != true) {
				p = linkedStack.pop();
				System.out.print(p.element + " ");
				p = p.right;
			}
		}
		System.out.print("\n");
	}

	/**
	 * �������
	 */
	private void postOrderTraverse() {
		linkedStack.clear();
		BinaryNode<Character> p = root;
		BinaryNode<Character> q = null;
		while (p != null || linkedStack.isEmpty() == false) {
			while (p != null) {
				linkedStack.push(p);
				p = p.left;
			}
			if (linkedStack.isEmpty() != true) {
				p = linkedStack.peekTop();
				if (p.right == null || p.right == q) {
					System.out.print(p.element + " ");
					q = p;
					p = null;
					linkedStack.pop();
				} else {
					p = p.right;
				}
			}
		}
		System.out.print("\n");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree binaryTree = new BinaryTree();
		binaryTree.initBinaryTree();
		System.out.println("����������...");
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("tree.txt"));
			try {
				binaryTree.root = binaryTree.createBinaryTree(binaryTree.root, bufferedReader);
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("������ǰ�����Ϊ...");
		binaryTree.preOrderTraverse();
		System.out.println("�������������Ϊ...");
		binaryTree.inOrderTraverse();
		System.out.println("�������������Ϊ...");
		binaryTree.postOrderTraverse();
		System.out.println("������ʾ�����!");
	}

}
