package com.ex.list;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MySeqList<M>{

	private static final int MAX_SIZE=100;
	
	private static class SeqList<T>
	{
		T []list=(T[]) new Object[MAX_SIZE];
		int length;
	}
	
	private SeqList<M> seqList;
	
	private void initSeqList() {
		if (seqList==null) {
			seqList=new SeqList<M>();
		}
	}
	
	private M getElem(int i)
	{
		if(i<1||i>seqList.length)
			return null;
		return seqList.list[i-1];
	}
	
	private void insert(int i,M e) {
		if (i<1||i>seqList.length+1) {
			System.out.println("插入位置非法！");
			return ;
		}
		else if (seqList.length>=MAX_SIZE) {
			System.out.println("链表已满员，无法进行插入操作！");
			seqList.list=(M[]) new Object[2*MAX_SIZE];
			return ;
		}
		else {
			for (int j = seqList.length; j >=i; j++) {
				seqList.list[j]=seqList.list[j-1];
			}
			seqList.list[i-1]=e;
			seqList.length+=1;
		}
	}
	
	private M deleteAt(int i) {
		M result=null;
		if (i<1||i>seqList.length) {
			System.out.println("删除位置非法！");
			return result;
		}
		result=seqList.list[i-1];
		for (int j = i-1; j < seqList.length-1; j++) {
			seqList.list[j]=seqList.list[j+1];
		}
		return result;
	}
	
	private void createSeqList() {
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader("list.txt"));
			try {
				String []strArr=bufferedReader.readLine().split(" ");
				int nodeNum=Integer.parseInt(strArr[0]);
				for (int i = 0; i < nodeNum; i++) {
					Character elem=Character.valueOf(strArr[i+1].charAt(0));
					insert(i+1,(M)elem);
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
	}
	
	private void printSeqList() {
		for (int i = 0; i < seqList.length; i++) {
			System.out.print(seqList.list[i]+" ");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MySeqList<Character> mySeqList=new MySeqList<Character>();
		mySeqList.initSeqList();
		System.out.println("创建线性链表... ");
		mySeqList.createSeqList();
		System.out.println("打印线性链表...");
		mySeqList.printSeqList();
	}

}
