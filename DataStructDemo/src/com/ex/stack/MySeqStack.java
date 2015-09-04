package com.ex.stack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MySeqStack<M> {

	// ����һ������ջ��������ֵ
	private static int STACK_SIZE = 100;

	// ��������ջ�����ݽṹ
	private static class SeqStack<T> {
		T[] stack = (T[]) new Object[STACK_SIZE];
		int top;
	}

	// ����һ������ջ
	private SeqStack<M> seqStack;

	// ��ʼ������ջ
	private void initSeqStack() {
		if (seqStack == null) {
			seqStack = new SeqStack<M>();
		}
	}

	/**
	 * ��ջ����
	 * 
	 * @param e
	 */
	private void push(M e) {
		if (seqStack.top == STACK_SIZE) {
			System.out.println("����ջ����Ա����Ҫ����!");
			STACK_SIZE *= 2;
			seqStack.stack = (M[]) new Object[STACK_SIZE];
		}
		seqStack.stack[seqStack.top++] = e;
	}

	/**
	 * ջ��Ԫ�س�ջ
	 * 
	 * @return
	 */
	private M pop() {
		return seqStack.stack[--seqStack.top];
	}

	/**
	 * ������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MySeqStack<Integer> mySeqStack = new MySeqStack<Integer>();
		System.out.println("��������ջ...");
		mySeqStack.initSeqStack();
		int stackCapacity = -1;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("stack.txt"));
			try {
				String[] strArr = bufferedReader.readLine().split(" ");
				stackCapacity = Integer.parseInt(strArr[0]);
				for (int i = 0; i < stackCapacity; i++) {
					mySeqStack.push(Integer.parseInt(strArr[i + 1]));
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
		System.out.println("��ӡ����ջ...");
		for (int i = 0; i < stackCapacity; i++) {
			System.out.print(mySeqStack.pop() + " ");
		}
	}
}
