import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestEulerian {

	static void testEulerian(Graph g) {
		// check if the graph is connected
		// check number of vertices with odd number of nodes

		// reset graph
		for (Vertex v : g.verts) {
			if(v != null)
				v.seen = false;
		}
		
		List<Vertex> oddEdgeVerticies = new ArrayList<>();
		int cn = 0;
		for (Vertex v : g.verts) {
			if (v != null && !v.seen) {
				cn++;
				DFSVisit(v, oddEdgeVerticies);
			}
		}

		if (cn > 1) { // multiple components 
			System.out.println("Graph is not connected.");
		
		} else if (oddEdgeVerticies.isEmpty()) { // no vertices with odd edges 
			System.out.println("Graph is Eulerian.");

		} else if (oddEdgeVerticies.size() == 2) { // exactly 2 odd vertices with odd edges
			System.out.println("Graph has an Eulerian Path between vertices " + oddEdgeVerticies.get(0) + " and "
					+ oddEdgeVerticies.get(1));

		} else { // multiple vertices with odd edges 
			System.out
					.println("Graph is not Eulerian.  It has " + oddEdgeVerticies.size() + " vertices of odd degree.");
		}

	}

	private static void DFSVisit(Vertex u, List<Vertex> oddEdgeVerticies) {
		u.seen = true;
		
		if (u.Adj.size() % 2 != 0) {
			oddEdgeVerticies.add(u);
		}

		for (Edge e : u.Adj) {
			Vertex v = e.otherEnd(u);
			if (!v.seen)
				DFSVisit(v, oddEdgeVerticies);
		}

	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean directed = false;
		Graph graph = Graph.readGraph(in, directed);
		TestEulerian.testEulerian(graph);
	}

}
