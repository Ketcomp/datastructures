import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author vmungara
 *
 */
public class HierholzerAlgorithm {

	/*
	 *  to track Edge and its start vertex in the eular path
	 */
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
	
	/*
	 * find Euler tour
	 * @input Graph g
	 * @return List tour - Euler tour 
	 */
	static List<Edge> findEulerTour(Graph g) {
		List<Edge> tour = new LinkedList<Edge>();
		Stack<EdgeObject> stack = new Stack<>();
		// check graph and get start vertex  
		Vertex u = getStartVertex(g);
		if(u == null) return null;
		// run algo
		Edge e = getUnvisitedEdge(u);
		EdgeObject edgeObject = new EdgeObject(e, u);
		stack.push(edgeObject);	
		while(!stack.isEmpty()) {
			edgeObject = stack.peek();
			u = edgeObject.edge.otherEnd(edgeObject.start);
			e = getUnvisitedEdge(u);
			if(e != null) {
				edgeObject = new EdgeObject(e, u);
				stack.push(edgeObject);
				
			} else {
				edgeObject = stack.pop();
				tour.add(0, edgeObject.edge);
			}
			 
		}
		
		return tour;
	}

	/*
	 * get next unvisited edge from adjacency list
	 */
	private static Edge getUnvisitedEdge(Vertex u) {
		/*Edge e = u.unseenEdges.iterator().next();
		u.unseenEdges.remove(e);
		Vertex v = e.otherEnd(u);
		v.unseenEdges.remove(e);*/
		Edge e = null;
		while(u.itr().hasNext()) {
			e = u.itr().next();
			if(!e.visited) {
				e.visited = true;
				return e;
			}
		}
		
		return null;
	}
	
	/*
	 * verify if the tour is valid 
	 */
	static boolean verifyTour(Graph g, List<Edge> tour) {
		//check edge count
		if(tour == null || tour.size() != g.edgeCount) {
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
		
		// get common node from edge 1 and 2,
		// other node of edge 1 is the start of the path/tour
		Vertex u = tour.get(0).From;
		Vertex v = tour.get(0).To;
		Vertex start = null;
		if (tour.get(1).From.equals(v) || tour.get(1).To.equals(v)) {
			start = u;
		} else {
			start = v;
		}
		
		Vertex temp = start;
		Vertex other = null;
		// traverse the tour from start node
		for(Edge e: tour){
			if(e.visited)
				return false;
			
			e.visited = true;
			other = e.otherEnd(start);
			start = other;
		}
		// check begin and end vertex of the tour
		// for eular tour both start and end vertices are same, for eular path both are of odd degree
		return temp.equals(other) || ( temp.Adj.size() % 2 == 1 && other.Adj.size() % 2 == 1 );
	}
	
	/*  
	 *  If the graph has eular tour then start vertex is node 1. 
	 *  If the graph has eular path then start vertex is the smaller numbered node of odd degree.
	 *  @input Graph g
	 *  @return Vertex
	 */
	static Vertex getStartVertex(Graph g) {
		//check if graph is connected
		if(!isGraphConnected(g)) {
			return null;
		}
		//System.out.println("graph is connected");
		int oddDegreeVertices = 0;
		Vertex start = null;
		// check odd degree count and set start to smaller numbered node of odd degree
		for(Vertex v: g.verts) {
			if(v != null) {
				if(v.Adj.size() % 2 == 1) {
					oddDegreeVertices++;
					if(start != null) {
						start = start.Adj.size() > v.Adj.size() ? v : start;  
					} else {
						start = v;
					}
				}
			}
		}
		
		// check to see if graph has more than 2 vertices with odd degree
		if(oddDegreeVertices > 2) return null;
		
		if(start == null) { 
			System.out.println("graph has a tour");
		} else {
			System.out.println("graph has a path with start node :"+start);
		}
		
		// if start is null then return node 1 of graph
		return start != null? start : g.verts.get(1);	
	}
	
	
	/*
	 * check if graph is connected by running BFS
	 * compare vertices traversed to total vertices in graph
	 * 
	 */
	static boolean isGraphConnected(Graph g) {
		//reset graph vertex nodes
		for(Vertex v : g.verts) {
			if(v != null) {
				v.seen = false;
			}
		}
		int bfsCount = 0;
		Queue<Vertex> queue = new LinkedList<>();
		Vertex u = g.verts.get(1);
		u.seen = true;
		Vertex v= null;
		queue.offer(u);
		while(!queue.isEmpty()) {
			u = queue.poll();
			bfsCount++;
			for(Edge e: u.Adj) {
				v = e.otherEnd(u);
				if(!v.seen) {
					queue.offer(v);
					v.seen = true;
				}
			}
		}
		
		return bfsCount == g.numNodes ? true : false;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = null;
		boolean directed = false;
		
		if(args.length == 0){
			File input = new File("lp0-big.txt");
			in = new Scanner(input);
		} else {
			in = new Scanner(System.in);
			System.out.println("Enter graph:");
		}
		
		//System.out.println("reading graph start");
		Graph graph = Graph.readGraph(in, directed);
		//System.out.println("reading graph complete");
		//System.out.println("vertices: " + graph.numNodes + ", edges: " + graph.edgeCount);
		long start = System.currentTimeMillis();
		List<Edge> tour = findEulerTour(graph);
		System.out.println("time = "+ (System.currentTimeMillis() - start) + " ms");
		
		if( tour == null ) {
			System.out.println("Graph is not Eulerian");

		} else if(!verifyTour(graph, tour)) {
			System.out.println("not a valid tour");
			
		} else {
			System.out.println("printing graph");
			//for(Edge e: tour) { System.out.println(e); }
		}
	}
}
