package com.ex.tree;
import java.io.*;
import java.util.*;

import com.sun.org.apache.bcel.internal.generic.NEW;


public final class MyBiTreeDemo<T>{

	private static final class BiTree<M>
	{
		M data;
		BiTree<M> lchild,rchild;
		public BiTree() {
			data=null;
			lchild=null;
			rchild=null;
		}
		public BiTree(M e,BiTree<M> lc,BiTree<M> rc) {
			data=e;
			lchild=lc;
			rchild=rc;
		}
	}
	
	private static final int MAX_SIZE=100;
	private BiTree<T> bitTree;
	
	private void InitBitTree() {
		bitTree=null;
	}
	
	private void DestroyBitTree(BiTree<T> bTree) {
		if (bTree!=null) {
			if (bTree.lchild!=null) {
				DestroyBitTree(bTree.lchild);
			}
			if (bTree.rchild!=null) {
				DestroyBitTree(bTree.rchild);
			}
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void CreateBitTree() {
		Scanner scanner=new Scanner(System.in);
		String s;
		BiTree<T> []stack=(BiTree<T>[])new BiTree[MAX_SIZE];
		BiTree<T> p;
		BiTree<T> node;
		int top=0;
		bitTree=new BiTree('A',null,null);
		p=bitTree;
		while (p!=null||top>0) {
			while (p!=null) {
				stack[top++]=p;
				s=scanner.next();
				if(s.equals("#"))
					break;
				node=new BiTree(s,null,null);
				InsertLeftChild(p,node);
				p=p.lchild;
			}
			if (top>0) {
				p=stack[--top];
				s=scanner.next();
				if(s.equals("#")!=true)
				{
					node=new BiTree(s,null,null);
					InsertRightChild(p,node);
					p=p.rchild;
				}
				else {
					p=null;
				}
			}
		}
    
	}
	private BiTree<Character> bTree;
	private BiTree<Character> prepreNode=new BiTree<Character>(),preNode=new BiTree<Character>();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private BiTree<Character> CreateBitTree2(BiTree<Character> Tree,int nFlag) {
		Scanner scanner=new Scanner(System.in);
		String s;
		s=scanner.next();
		if(s=="#"&&nFlag==0)
			Tree=null;
		else if (s=="#"&&nFlag==1) {
			Tree=null;
			return null;
		}
		else if (s=="#"&&nFlag==2) {
			preNode=prepreNode;
			Tree=preNode;
			return null;
		}
		
		Tree=new BiTree<Character>(s.charAt(0),null,null);
		if (nFlag==0) {
			preNode=Tree;
			bTree=Tree;
		}
		else if (nFlag==1) {
			prepreNode=preNode;
			preNode.lchild=Tree;
			preNode=Tree;
		}
		else if(nFlag==2){
			prepreNode=preNode;
			preNode.rchild=Tree;
			preNode=Tree;
		}
		if(Tree!=null)
		{
			CreateBitTree2(Tree.lchild,1);
			CreateBitTree2(Tree.rchild,2);
		}
		return Tree;
	}
	private boolean InsertLeftChild(BiTree<T> p,BiTree<T> c) {
		if (p!=null) {
			c.rchild=p.lchild;
			p.lchild=c;
			return true;
		}
		return false;
	}
	
	private boolean InsertRightChild(BiTree<T> p,BiTree<T> c) {
		if (p!=null) {
			c.rchild=p.rchild;
			p.rchild=c;
			return true;
		}
		return false;
	}
	
	private BiTree<T> Point(T e) {
		@SuppressWarnings("unchecked")
		BiTree<T> []Queue=(BiTree<T> [])new Object[MAX_SIZE];
		int front=0,rear=0;
		BiTree<T> p;
		if (bitTree!=null) {
			Queue[rear++]=bitTree;
			while(front!=rear)
			{
				p=Queue[front++];
				if(p.data==e)
					return p;
				if (p.lchild!=null) {
					Queue[rear++]=p.lchild;
				}
				if (p.rchild!=null) {
					Queue[rear++]=p.rchild;
				}
			}
		}
		return null;
	}
	
	private T GetLeftChild(T e) {
		BiTree<T> p;
		if (bitTree!=null) {
			p=Point(e);
			if(p!=null&&p.lchild!=null)
				return p.lchild.data;
		}
		return null;
	}
	
	private T GetRightChild(T e) {
		BiTree<T> p;
		if (bitTree!=null) {
			p=Point(e);
			if(p!=null&&p.rchild!=null)
				return p.rchild.data;
		}
		return null;
	}
	
	private boolean DeleteLeftChild(BiTree<T> p) {
		if (p!=null) {
			DestroyBitTree(p.lchild);
			return true;
		}
		return false;
	}
	
	private boolean DeleteRightChild(BiTree<T> p) {
		if (p!=null) {
			DestroyBitTree(p.rchild);
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	private void PreOrderTraverse() throws ClassCastException {
		
		try {
			BiTree<T> []stack;
			@SuppressWarnings("rawtypes")
			BiTree []s=new BiTree[MAX_SIZE];
			stack=(BiTree<T>[])s;
			int top=0;
			BiTree<T> p=bitTree;
			while (p!=null||top>0) {
				while (p!=null) {
					System.out.printf("当前节点的元素值为:%s\n", p.data);
					stack[top++]=p;
					p=p.lchild;
				}
				if (top>0) {
					p=stack[--top];
					p=p.rchild;
				}
			}
		} catch (ClassCastException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void InOrderTraverse() {
		
		BiTree<T> []stack;
		@SuppressWarnings("rawtypes")
		BiTree []s=new BiTree[MAX_SIZE];
		stack=(BiTree<T>[])s;
		int top=0;
		BiTree<T> p=bitTree;
		while (p!=null||top>0) {
			while (p!=null) {
				stack[top++]=p;
				p=p.lchild;
			}
			if (top>0) {
				p=stack[--top];
				System.out.printf("当前节点的元素值为:%s\n", p.data);
				p=p.rchild;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void PostOrderTraverse() {
		
		BiTree<T> []stack;
		@SuppressWarnings("rawtypes")
		BiTree []s=new BiTree[MAX_SIZE];
		stack=(BiTree<T>[])s;
		int top=0;
		BiTree<T> p=bitTree;
		BiTree<T> q=null;
		while (p!=null||top>0) {
			while (p!=null) {
				stack[top++]=p;
				p=p.lchild;
			}
			if (top>0) {
				p=stack[top-1];
				if (p.rchild!=null&&p.rchild==q) {
					System.out.printf("当前节点的元素值为:%s\n", p.data);
					q=p;
					p=null;
					top-=1;
				}
				else
				   p=p.rchild;
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws ClassCastException {
		// TODO Auto-generated method stub
		MyBiTreeDemo<Character> myBTreeDemo=new MyBiTreeDemo<Character>();
		myBTreeDemo.InitBitTree();
		myBTreeDemo.CreateBitTree();
		try {
			System.out.println("前序遍历为:");
			myBTreeDemo.PreOrderTraverse();
			System.out.println("中序遍历为:");
			myBTreeDemo.InOrderTraverse();
			System.out.println("后序遍历为:");
			myBTreeDemo.PostOrderTraverse();
			
		} catch (ClassCastException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
