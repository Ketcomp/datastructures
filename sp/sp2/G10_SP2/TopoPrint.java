import java.util.Scanner;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

/**
 * @author: G10
 * Gaurav Ketkar
 * Madhuri Abnave
 * Malav Shah
 * Vijay Kumar Mungara 
 *
 * A program to output the topological order for a DAG by two algorithms:
 * 1. Visit vertices with no incoming edges and remove their outgoing edges. Repeat
 * 2. Run DFS on g and push nodes to a stack in the order in which
 * they finish. Write code without using global variables.
 */

public class TopoPrint {

	/**
	 * Algorithm 1: Remove vertices with no incoming edges, one at a time, along
	 * with their incident edges, and add them to a list.
	 *
	 * @param g
	 *            : The graph of which's topological order must be printed.
	 * @return result: An array-list of vertices in the order in which they were
	 *         visited for topological ordering.
	 */
	public static List<Vertex> toplogicalOrder1(Graph g) {
		List<Vertex> result = new ArrayList<>();
		// Keep iterating while there are more unseen vertices in the graph.
		do {
			Stack<Vertex> temp = new Stack<>(); // temp stores vertices to be
												// removed, once they are
												// visited.
			for (Vertex every : g) {
				if (every.revAdj.size() == 0) {
					for (Edge removeThisEdge : every.Adj) {
						Vertex candidateVertex = removeThisEdge.To;
						Stack<Edge> S = new Stack<>(); // S stores edges to be
														// removed, once
														// determined to
														// originate from
														// 'every' Vertex
						for (Edge incomingEdgeToRemove : candidateVertex.revAdj) {
							if (incomingEdgeToRemove != null)
								if (incomingEdgeToRemove.From != null && incomingEdgeToRemove.From.equals(every)) {
									S.push(incomingEdgeToRemove);
								}
						}
						while (!S.isEmpty()) {
							candidateVertex.revAdj.remove(S.pop());
						}
					}
					every.Adj = null;
					// Add the said vertex to the List 'result'
					result.add(every);
					temp.push(every);
				}
			}
			g.numNodes = g.numNodes - temp.size();
			while (!temp.isEmpty()) {
				g.verts.remove(temp.pop());
			}

		} while (g.numNodes > 0);
		return result;
	}

	/**
	 * Method to recursively visit every vertex in the graph in a depth-first
	 * manner.
	 * 
	 * @param u
	 *            : The Vertex where we start from
	 * @param S
	 *            : The stack onto which the vertexes are pushed in the order in
	 *            which they appear.
	 */
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

	/**
	 * Algorithm 2. Run DFS on g and push nodes to a stack in the order in which
	 * they finish. Write code without using global variables.
	 * 
	 * @param g
	 *            : The graph of which's topological order must be printed.
	 * @return : A stack containing the vertices of the graph in the order in
	 *         which they are visited.
	 */

	public static Stack<Vertex> toplogicalOrder2(Graph g) {
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

		// Topological ordering by Algo 2
		Stack<Vertex> opAlgo2 = new Stack<>();
		opAlgo2 = toplogicalOrder2(myGraph);

		int sizeofstack2 = opAlgo2.size();
		System.out.print("Topo ordering by algo 2:\n[");
		for (int i = 0; i < sizeofstack2; i++) {
			System.out.print(opAlgo2.pop().toString() + ", ");
		}
		System.out.print("]\n");

		// Topological ordering by Algo 1
		List<Vertex> opAlgo1 = new ArrayList<>();
		opAlgo1 = toplogicalOrder1(myGraph);

		System.out.println("Topo ordering by algo 1:");
		System.out.println(opAlgo1.toString());
	}
}// Class ends