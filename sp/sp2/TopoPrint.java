import java.util.Scanner;
import java.util.List;
import java.util.Stack;

/**
 * @author: Gaurav Ketkar
 */

public class TopoPrint<T>{
	

	//List<Vertex> toplogicalOrder1(Graph g) { 
      /* Algorithm 1. Remove vertices with no incoming edges, one at a
       time, along with their incident edges, and add them to a list.
       */
   //}

	public static void dfsVisit(Vertex u, Stack<Vertex> S) {
		u.seen = true;
		Vertex v;
		for(Edge e: u.Adj) {
			v = e.otherEnd(u);
			if(!v.seen){
				v.parent = u;
				dfsVisit(v, S);
			}
		}
		S.push(u);
	}

	public static Stack<Vertex> toplogicalOrder2(Graph g) {
      /* Algorithm 2. Run DFS on g and push nodes to a stack in the
       order in which they finish.  Write code without using global variables.
       */
		for(Vertex u : g) {
			u.seen = false;
			u.parent = null;
		}
		Stack<Vertex> S = new Stack<>();
		for (Vertex u : g) {
			if(!u.seen) dfsVisit(u, S);
		}
		return S;
	}


	public static void main(String[] args){
		Graph myGraph;
		Scanner in = new Scanner(System.in);
		boolean isDirected = true;
		
		myGraph = Graph.readGraph(in, isDirected);
		
		Stack<Vertex> opAlgo2 = new Stack<>();
		
		opAlgo2 = toplogicalOrder2(myGraph);
		//opAlgo1 = toplogicalOrder1(myGraph);
		int sizeofstack = opAlgo2.size();
		System.out.println("Topo ordering by algo 2:");
		for(int i =0; i<sizeofstack;i++) {
			System.out.print(opAlgo2.pop().toString() +", ");
		}
	}
}//Class ends