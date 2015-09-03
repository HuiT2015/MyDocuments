package com.ex.graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DGraph {

	private static final int INFINITY = (int) 1e9;
	private AdjGraph<Character> graph;


	/**
	 * 创建图
	 */
	private void createGraph() {
		if (graph == null)
			graph = new AdjGraph<Character>();
		graph.graphKind = GraphKind.DG;
		BufferedReader bufferedReader=null;
		try {
		    bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File("data.txt").getAbsoluteFile())));
		    try {
		    	if(bufferedReader.ready()==true)
		    	{
		    		String []graphInfoArr=bufferedReader.readLine().split(" ");
		    		graph.vexNum = Integer.parseInt(graphInfoArr[0]);
					graph.arcNum = Integer.parseInt(graphInfoArr[1]);
					String []vertexArr=bufferedReader.readLine().split(" ");
					for (int i = 0; i < graph.vexNum; i++) {
						graph.vertexNodes[i] = new VNode<Character>();
						graph.vertexNodes[i].data = Character.valueOf(vertexArr[i].charAt(0));
						graph.vertexNodes[i].firstArcNode = null;
						graph.vertexNodes[i].know = false;
						graph.vertexNodes[i].dist = INFINITY;
						graph.vertexNodes[i].nearNode = null;
					}
					Character ch1, ch2;
					ArcNode arcNode = null;
					for (int i = 0; i < graph.arcNum; i++) {
						String []arcInfoArr=bufferedReader.readLine().split(" ");
						ch1 = Character.valueOf(arcInfoArr[0].charAt(0));
						ch2 = Character.valueOf(arcInfoArr[1].charAt(0));
						int u = locateVertex(ch1);
						int v = locateVertex(ch2);
						arcNode = new ArcNode(v, graph.vertexNodes[u].firstArcNode,
								Integer.parseInt(arcInfoArr[2]));
						graph.vertexNodes[u].firstArcNode = arcNode;
					}
		    	}
				bufferedReader.close();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Scanner scanner = new Scanner(System.in);
		System.out.println("请输入有向图的顶点数目和边的数目！");
		graph.vexNum = scanner.nextInt();
		graph.arcNum = scanner.nextInt();
		System.out.println("请输入顶点的值！");
		for (int i = 0; i < graph.vexNum; i++) {
			graph.vertexNodes[i] = new VNode<Character>();
			graph.vertexNodes[i].data = Character.valueOf(scanner.next()
					.charAt(0));
			graph.vertexNodes[i].firstArcNode = null;
			graph.vertexNodes[i].know = false;
			graph.vertexNodes[i].dist = INFINITY;
			graph.vertexNodes[i].nearNode = null;
		}
		System.out.println("请输入有向图中的各条弧！");
		Character ch1, ch2;
		ArcNode arcNode = null;
		for (int i = 0; i < graph.arcNum; i++) {
			ch1 = Character.valueOf(scanner.next().charAt(0));
			ch2 = Character.valueOf(scanner.next().charAt(0));
			int u = locateVertex(ch1);
			int v = locateVertex(ch2);
			arcNode = new ArcNode(v, graph.vertexNodes[u].firstArcNode,
					scanner.nextInt());
			graph.vertexNodes[u].firstArcNode = arcNode;
		}*/
	}

	/**
	 * 获取某个节点在图中的位置
	 * 
	 * @param v
	 * @return
	 */
	private int locateVertex(Character v) {
		for (int i = 0; i < graph.vexNum; i++) {
			if (graph.vertexNodes[i].data.equals(v)) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * 打印图
	 */
	private void displayGraph() {
		ArcNode p;
		System.out.printf("总共有%d个顶点!\n", graph.vexNum);
		for (int i = 0; i < graph.vexNum; i++) {
			System.out.println(graph.vertexNodes[i].data);
		}
		System.out.printf("总共有%d条边!\n", graph.arcNum);
		for (int i = 0; i < graph.vexNum; i++) {
			p = graph.vertexNodes[i].firstArcNode;
			while (p != null) {
				System.out.printf("%s->%s\t", graph.vertexNodes[i].data,
						graph.vertexNodes[p.adjvex].data);
				p = p.nextArcNode;
			}
			System.out.println();
		}
	}

	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		DGraph dGraph = new DGraph();
		//System.out.println("创建有向图...");
		dGraph.createGraph();
		//System.out.println("输出有向图...");
		dGraph.displayGraph();
		System.out.println("请输入起点节点...");
		Scanner scanner = new Scanner(System.in);
		Character s = Character.valueOf(scanner.next().charAt(0));
		int s0 = dGraph.locateVertex(s);
		dGraph.dijkstra(s0);
		for (int i = 0; i < dGraph.graph.vexNum; i++) {
			if (i!=s0) {
				System.out.printf("%s->%s的最短路径为:%d\t",dGraph.graph.vertexNodes[s0].data,dGraph.graph.vertexNodes[i].data,dGraph.graph.vertexNodes[i].dist);
				dGraph.showPath(s0, i);
			}
		}
	}

	/**
	 * 单源最短路径
	 * @param s
	 */
	private void dijkstra(int s0) {
		ArcNode arcNode = null;
		//初始化各个节点距离初始源节点的路径长度
		for (int i = 0; i < graph.vexNum; i++) {
			arcNode=graph.vertexNodes[s0].firstArcNode;
			while (arcNode != null ) {
				if (arcNode.adjvex==i) {
					graph.vertexNodes[i].dist = arcNode.weight;
					graph.vertexNodes[i].nearNode = graph.vertexNodes[s0];
					break;
				}
				arcNode = arcNode.nextArcNode;
			}
			if(arcNode==null)
			{
				graph.vertexNodes[i].dist = INFINITY;
				graph.vertexNodes[i].nearNode = null;
			}
		}
		graph.vertexNodes[s0].dist = 0;
		for (int i = 0; i < graph.vexNum; i++) {
			int m = -1;
			int min_dist = INFINITY;
			// 寻找出还未访问过的最短路径点
			for (int j = 0; j < graph.vexNum; j++) {
				if (graph.vertexNodes[j].know == false
						&& graph.vertexNodes[j].dist < min_dist) {
					min_dist = graph.vertexNodes[j].dist;
					m = j;
				}
			}
			if (m==-1) continue;
			graph.vertexNodes[m].know = true;
			//根据已找出最短路径的节点修正
			for (int j = 0; j < graph.vexNum; j++) {
				arcNode = graph.vertexNodes[m].firstArcNode;
				if (graph.vertexNodes[j].know == false) {
					while (arcNode != null) {
						if (arcNode.adjvex == j) {
							if (arcNode.weight > 0
									&& graph.vertexNodes[j].dist > graph.vertexNodes[m].dist
											+ arcNode.weight) {
								graph.vertexNodes[j].dist = graph.vertexNodes[m].dist
										+ arcNode.weight;
								graph.vertexNodes[j].nearNode=graph.vertexNodes[m];
							}
						}
						arcNode = arcNode.nextArcNode;
					}
				}
			}
		}
	}
	
	/**
	 * 显示最短路径
	 * @param v1
	 * @param v2
	 */
	private void showPath(int v0,int v) {
		LinkedStack<Character> linkedStack=new LinkedStack<Character>();
		boolean bCanReach=true;
		while (v!=v0) {
			linkedStack.push(graph.vertexNodes[v].data);
			if(graph.vertexNodes[v].dist==INFINITY)
			{
				bCanReach=false;
				break;
			}
			if(graph.vertexNodes[v].nearNode!=null)
			  v=locateVertex(graph.vertexNodes[v].nearNode.data);
		}
		linkedStack.push(graph.vertexNodes[v0].data);
		Character e=null;
		while ((e=linkedStack.pop())!=null) {
			System.out.print(e+" ");
		}
		if(bCanReach==false)
			System.out.print("终点不可达！");
		System.out.println();
	}
}
