import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class HierholzerAlgorithm {

	private static class EdgeObject {
		Edge edge;
		Vertex start;
		
		public EdgeObject(Edge e, Vertex s) {
			edge = e;
			start = s;
		}
		
		@Override
		public String toString() {
			return edge.toString() + ":" + start;
		}
	}
	
	static List<Edge> findEulerTour(Graph g) {  // Return an Euler tour of g
		// get source
		List<Edge> tour = new LinkedList<Edge>();
		Stack<EdgeObject> stack = new Stack<>();
		
		Vertex u = testEulerian(g);
		//Vertex u = g.verts.get(1);
		if(u == null) return null; 
		Edge e = getUnvisitedEdge(u);		
		
		EdgeObject edgeObject = new EdgeObject(e, u);
		stack.push(edgeObject);
		
		while(!stack.isEmpty()) {
			edgeObject = stack.peek();
			u = edgeObject.edge.otherEnd(edgeObject.start);
			
			if(!u.unseenEdges.isEmpty()) {
				e = getUnvisitedEdge(u);
				edgeObject = new EdgeObject(e, u);
				stack.push(edgeObject);
				
			} else {
				edgeObject = stack.pop();
				tour.add(edgeObject.edge);
			}
			 
		}
		
		return tour;
	}

	private static Edge getUnvisitedEdge(Vertex u) {
		Edge e = u.unseenEdges.iterator().next();
		u.unseenEdges.remove(e);
		Vertex v = e.otherEnd(u);
		v.unseenEdges.remove(e);
		return e;
	}
	
	
	static boolean verifyTour(Graph g, List<Edge> tour) { // verify tour is a valid Euler tour
		//check edge count
		if(tour.size() != g.edgeCount) {
			return false;
		}
		
		//reset all edge.visited to false
		for(Vertex u : g.verts) {
			if(u != null) {
				for(Edge e: u.Adj) {
					e.visited = false;
				}
			}
		}
		
		// get common node from edge 1 and 2
		Vertex start = getStartVertex(tour);
		Vertex temp = start;
		Vertex other = null;
		for(Edge e: tour){
			if(e.visited)
				return false;
			
			e.visited = true;
			other = e.otherEnd(start);
			start = other;
		}
		
		return temp.equals(other) || ( temp.Adj.size() % 2 == 1 && other.Adj.size() % 2 == 1 );
	}

	private static Vertex getStartVertex(List<Edge> tour) {
		Vertex u = tour.get(0).From;
		Vertex v = tour.get(0).To;
		Vertex start = null;
		if (tour.get(1).From.equals(v) || tour.get(1).To.equals(v)) {
			start = u;
		} else {
			start = v;
		}
		return start;
	}
	
	static Vertex testEulerian(Graph g) {
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
			return null;
		
		}
		
		if (oddEdgeVerticies.isEmpty()) { // no vertices with odd edges 
			System.out.println("Graph is Eulerian.");
			return g.verts.get(1);

		} 
		
		if (oddEdgeVerticies.size() == 2) { // exactly 2 odd vertices with odd edges
			System.out.println("Graph has an Eulerian Path between vertices " + oddEdgeVerticies.get(0) + " and "
					+ oddEdgeVerticies.get(1));
			return oddEdgeVerticies.get(0);

		} 
		
		// multiple vertices with odd edges 
		System.out.println("Graph is not Eulerian.  It has " + oddEdgeVerticies.size() + " vertices of odd degree.");
		return null;

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
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = null;
		
		if(args.length == 0){
			File input = new File("lp0-big.txt");
			in = new Scanner(input);
		} else {
			in = new Scanner(System.in);
		}
		
		boolean directed = false;
		
		Graph graph = Graph.readGraph(in, directed);
		
		long start = System.currentTimeMillis();
		List<Edge> tour = findEulerTour(graph);
		System.out.println("time = "+ (System.currentTimeMillis() - start) + " ms");
		
		if( tour != null ){
			System.out.println(verifyTour(graph, tour));
			//for(Edge e: tour) { System.out.println(e); }
		}
	}
}
