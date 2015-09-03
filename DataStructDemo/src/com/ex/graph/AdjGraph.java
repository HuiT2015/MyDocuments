package com.ex.graph;

import java.util.List;

/**
 * 
 * @author lenovo
 * 定义图的邻接链表数据结构实现
 * @param <T>
 */
public class AdjGraph<T> {
	private static final int MAX_SIZE=1000;
	VNode<T> []vertexNodes;
	int vexNum,arcNum;
	GraphKind graphKind;
	
	@SuppressWarnings("unchecked")
	public AdjGraph() {
		vertexNodes=(VNode<T> [])new VNode[MAX_SIZE];
		vexNum=arcNum=0;
		graphKind=GraphKind.DG;
	}
	
}


/**
 * 定义弧节点
 */
class ArcNode
{
	int adjvex;
	ArcNode nextArcNode;
	int weight;
	public ArcNode(int adjvex, ArcNode nextArcNode,int weight) {
		this.adjvex = adjvex;
		this.nextArcNode = nextArcNode;
		this.weight=weight;
	}
}

/**
 * 定义顶点结点
 */
class VNode<T>
{
	T data;
	ArcNode firstArcNode;
	
	boolean know;
	int dist;
	VNode<T> nearNode;
}

/**
 * 定义图 类型
 */
enum GraphKind
{
 DG,
 DN,
 UG,
 UN
}