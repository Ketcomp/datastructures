import java.util.Scanner;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

/**
 * @author: G10
 */

public class TopoPrint<T> {

	public static List<Vertex> toplogicalOrder1(Graph g) {
		/*
		 * Algorithm 1. Remove vertices with no incoming edges, one at a time,
		 * along with their incident edges, and add them to a list.
		 */
		List<Vertex> result = new ArrayList<>();
		//Keep iterating while there are more unseen vertices in the graph.
		do {
			Stack temp = new Stack();
			for (Vertex every : g) {
				//List<Edge> po = every.revAdj;
				if (every.revAdj.size() == 0) {
					// Remove the incoming edges for vertices 'every' goes to
					for (Edge removeThisEdge : every.Adj) {

						// Visit each vertex that 'every' points to
						Vertex candidateVertex = removeThisEdge.To;
						//System.out.println("This edge goes to: " + candidateVertex.name);
						Stack S = new Stack();
						for (Edge incomingEdgeToRemove : candidateVertex.revAdj) {
							if (incomingEdgeToRemove != null)
								if (incomingEdgeToRemove.From != null && incomingEdgeToRemove.From.equals(every)) {
									//System.out.println("Need to remove" + incomingEdgeToRemove.From);
									// incomingEdgeToRemove.From=null;
									//Push edges that need to be removed to stack S and remove them later
									S.push(incomingEdgeToRemove);
									// System.out.println("The From vertex for
									// the Edge
									// "+incomingEdgeToRemove.toString()+" is
									// now :"+incomingEdgeToRemove.From);
								}
						}
						while (!S.isEmpty()) {
							candidateVertex.revAdj.remove(S.pop());
						}

					}

					every.Adj = null;
					//Visit the said vertex and add it to the List 'result'
					result.add(every);
					temp.push(every);

				}
			}
			g.numNodes = g.numNodes - temp.size();
			//Remove the vertices that we have visited from the Graph
			while (!temp.isEmpty()) {
				g.verts.remove(temp.pop());
			}

		} while (g.numNodes > 0);

		return result;
	}

	public static void dfsVisit(Vertex u, Stack<Vertex> S) {
		u.seen = true;
		Vertex v;
		for (Edge e : u.Adj) {
			v = e.otherEnd(u);
			if (!v.seen) {
				v.parent = u;
				dfsVisit(v, S);
			}
		}
		S.push(u);
	}

	public static Stack<Vertex> toplogicalOrder2(Graph g) {
		/*
		 * Algorithm 2. Run DFS on g and push nodes to a stack in the order in
		 * which they finish. Write code without using global variables.
		 */
		for (Vertex u : g) {
			u.seen = false;
			u.parent = null;
		}
		Stack<Vertex> S = new Stack<>();
		for (Vertex u : g) {
			if (!u.seen)
				dfsVisit(u, S);
		}
		return S;
	}

	public static void main(String[] args) {
		Graph myGraph;
		Scanner in = new Scanner(System.in);
		boolean isDirected = true;

		myGraph = Graph.readGraph(in, isDirected);

		Stack<Vertex> opAlgo2 = new Stack<>();
		opAlgo2 = toplogicalOrder2(myGraph);
		int sizeofstack2 = opAlgo2.size();
		System.out.print("Topo ordering by algo 2:\n[");
		
		for (int i = 0; i < sizeofstack2; i++) {
			System.out.print(opAlgo2.pop().toString() +", ");
		}
		System.out.print("]\n");
		List<Vertex> opAlgo1 = new ArrayList<>();
		opAlgo1 = toplogicalOrder1(myGraph);
		//int sizeofstack1 = opAlgo1.size();
		System.out.println("Topo ordering by algo 1:");
		System.out.print(opAlgo1.toString());

	}
}// Class ends