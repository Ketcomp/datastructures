import java.util.Scanner;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

/**
 * @author: Gaurav Ketkar
 */

public class TopoPrint<T>{
	

	public static List<Vertex> toplogicalOrder1(Graph g) { 
      /* Algorithm 1. Remove vertices with no incoming edges, one at a
       time, along with their incident edges, and add them to a list.
       */
	List<Vertex> result = new ArrayList<>();
	for(Vertex every : g) {
		if(every.revAdj.size()==0) {
			//Remove the incoming edges for vertices 'every' goes to
			for(Edge removeThisEdge : every.Adj) {
				Vertex candidateVertex = removeThisEdge.To;
				for(Edge e2 : candidateVertex.revAdj) {
					if(e2.From.equals(every)){
						candidateVertex.revAdj.remove(index)
					}
				}
			}
			result.add(every);
		}
	}
		return result;
	}

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
		int sizeofstack2 = opAlgo2.size();
		System.out.println("Topo ordering by algo 2:");
		for(int i =0; i<sizeofstack2;i++) {
			System.out.print(opAlgo2.pop().toString() +", ");
		}
		
		List<Vertex> opAlgo1 = new ArrayList<>();
		opAlgo1 = toplogicalOrder1(myGraph);
		int sizeofstack1 = opAlgo1.size();		
		System.out.println("Topo ordering by algo 1:");
		for(int i =0; i<sizeofstack1;i++) {
			System.out.print(opAlgo1.toString()+", ");
		}
	}
}//Class ends