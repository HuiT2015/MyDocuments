package com.ex.stack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class MySeqStack<M>{

	private static int STACK_SIZE=100;
	
	private static class SeqStack<T>
	{
		T []stack=(T[]) new Object[STACK_SIZE];
		int top;
	}
	private SeqStack<M> seqStack;
	
	private void initSeqStack() {
		if (seqStack==null) {
			seqStack=new SeqStack<M>();
		}
	}
	
	/**
	 * 进栈操作
	 * @param e
	 */
	private void push(M e) {
		if (seqStack.top==STACK_SIZE) {
			System.out.println("线性栈已满员，需要扩容!");
			STACK_SIZE*=2;
			seqStack.stack=(M[]) new Object[STACK_SIZE];
		}
		seqStack.stack[seqStack.top++]=e;
	}
	
	/**
	 * 栈顶元素出栈
	 * @return
	 */
	private M pop() {
		return seqStack.stack[--seqStack.top];
	}
	
	public static void main(String []args) {
		MySeqStack<Integer> mySeqStack=new MySeqStack<Integer>();
		System.out.println("建立线性栈...");
		mySeqStack.initSeqStack();
		int stackCapacity=-1;
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader("stack.txt"));
			try {
				String []strArr=bufferedReader.readLine().split(" ");
				stackCapacity=Integer.parseInt(strArr[0]);
				for (int i = 0; i < stackCapacity; i++) {
					mySeqStack.push(Integer.parseInt(strArr[i+1]));
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
		System.out.println("打印线性栈...");
		for (int i = 0; i < stackCapacity; i++) {
			System.out.print(mySeqStack.pop()+" ");
		}
	}
}
