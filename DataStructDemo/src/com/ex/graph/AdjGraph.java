package com.ex.graph;

/**
 * 
 * @author lenovo ����ͼ���ڽ��������ݽṹʵ��
 * @param <T>
 */
public class AdjGraph<T> {
	private static final int MAX_SIZE = 1000;
	VNode<T>[] vertexNodes;
	int vexNum, arcNum;
	GraphKind graphKind;

	@SuppressWarnings("unchecked")
	public AdjGraph() {
		vertexNodes = (VNode<T>[]) new VNode[MAX_SIZE];
		vexNum = arcNum = 0;
		graphKind = GraphKind.DG;
	}

}

/**
 * ���廡�ڵ�
 */
class ArcNode {
	int adjvex;
	ArcNode nextArcNode;
	int weight;

	public ArcNode(int adjvex, ArcNode nextArcNode, int weight) {
		this.adjvex = adjvex;
		this.nextArcNode = nextArcNode;
		this.weight = weight;
	}
}

/**
 * ���嶥����
 */
class VNode<T> {
	T data;
	ArcNode firstArcNode;

	boolean know;
	int dist;
	VNode<T> nearNode;
}

/**
 * ����ͼ ����
 */
enum GraphKind {
	DG, DN, UG, UN
}