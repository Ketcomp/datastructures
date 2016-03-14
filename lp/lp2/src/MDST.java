import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
public class MDST {
	static int vertexCount = 0;
	static List<Vertex> verticies;
	static Iterator<Vertex> vertexItr = null;
	static long time1, time2, time3;

	/**
	 *  Transform weights so that every node except r has an incoming edge of weight 0
	 *  
	 *  @input 	Graph g
	 *  @return long : sum of minimum weight edges  
	 */
	private static long create0WeightGraph(Graph g) {
		//long start = System.currentTimeMillis();
		long Pdu = 0;
		for(int i = 2; i < verticies.size(); i++) {
			Pdu += create0WeightEdge(g, verticies.get(i));
		}
		//time1 += (System.currentTimeMillis()-start);
		return Pdu;
	}
	
	/**
	 * Helper function for create0WeightGraph, runs on each vertex.
	 * This is also used create find 0 weight edge for cycle node
	 * 
	 * @param g
	 * @param v
	 * @return long: weight of minimum weight edge 
	 */
	private static long create0WeightEdge(Graph g, Vertex v) {
		long du = 0;
		Edge minEdge = v.incomingEdgesPQ.peek();
		Vertex u = getParent(minEdge.From);
		du = minEdge.reducedWeight;
		if(u != v) {
			v.previous = u;
			u.outgoing0s.add(v);
			if(du == 0) return du;
			//reduce weights of all incoming edges
			for(Edge e: v.revAdj) {
				e.reducedWeight -= du;
			}
		}
		return du;
	}
	
	/**
	 * Runs BFS on the graph on 0 weight edges
	 * 
	 * @param g0 o weighted graph
	 * @return unreachable vertex
	 * also returns null if BFS can traverse through all nodes in g0
	 */
	private static Vertex runBFS(Graph g0){
		//long start = System.currentTimeMillis();
		
		//reset graph vertex nodes
		for(Vertex v : verticies) {
			if(v != null) {
				v.seen = false;
			}
		}
		
		int bfsCount = 0;
		Queue<Vertex> queue = new LinkedList<>();
		Vertex u = verticies.get(1); u.seen = true; // start from root node always
		queue.offer(u);
		
		while(!queue.isEmpty()) {
			u = getParent(queue.poll());
			bfsCount += u.children;
			
			for(Vertex v: u.outgoing0s) {
				if(!v.seen) {
					queue.offer(v);
					v.seen = true;
				}
			}
			
		}
		
		// bfsCount is same as vertices count then return null
		if(bfsCount == g0.numNodes) {
			//time2 += (System.currentTimeMillis() - start);
			return null;
		}
		
		// iterate over all vertices to find unreachable vertex
		Vertex unreachable;
		while(vertexItr.hasNext()) {
			unreachable = vertexItr.next();
			if(unreachable != null && !getParent(unreachable).seen) { 
				//time2 += (System.currentTimeMillis() - start);
				return getParent(unreachable);
			}
		}
		
		//time2 += (System.currentTimeMillis() - start);
		return null;
	}
	
	/**
	 * Print MDST
	 * 
	 * @param g0
	 */
	private static void printMDST(Graph g0){
		//reset graph vertex nodes
		for(Vertex v : verticies) {
			if(v != null) {
				v.seen = false;
			}
		}
		Queue<Vertex> queue = new LinkedList<>();
		Vertex u = verticies.get(1); u.seen = true; // start from root node always
		queue.offer(u);
		Vertex v = null;
		// BFS on Graph g0
		while(!queue.isEmpty()) {
			u = queue.poll();
			for(Edge e: u.Adj) {
				v = e.To;
				// check if edge is already deleted when removing 0 cycle. run only on 0 weight edges
				if(e.reducedWeight == 0 && !v.seen) {
					queue.offer(v);
					System.out.println(e);
					v.seen = true;
				}
			}	
		}
	}
	

	/**
	 * Find and shrink the unreachable 0 weighted cycle
	 * Iterate over all previous nodes and match the node which occurs twice. 
	 * Replace entire cycle with a single node.
	 * 
	 * @param g
	 * @param z
	 * @return
	 */
	private static long findAndShrink0WeightGraph(Graph g, Vertex z) {
		//long start = System.currentTimeMillis();
		//get parent of z (cycle in which z is present)
		Vertex pnode = getParent(z);
		//walk backward and get all nodes and add to list 
		List<Vertex> nodeList = new ArrayList<Vertex>(); //contains the cycle
		Stack<Vertex> stack = new Stack<>(); // stack to find the cycle
		while(!pnode.seen) {
			stack.push(pnode);
			pnode.seen = true;
			pnode = getParent(pnode.previous);
		}
		
		while(!stack.isEmpty() && pnode != stack.peek()) {
			nodeList.add(stack.pop());
		}
		if(!stack.isEmpty()) {
			nodeList.add(stack.pop());
		}
		Vertex cycleNode = null;
		if(!nodeList.isEmpty()) {
			HashMap<Vertex, Edge> revAdjMap = populaterevAdjMap(nodeList);
			HashMap<Vertex, Edge> adjMap = populateAdjMap(nodeList);
			// create cycle vertex
			cycleNode = new Vertex(vertexCount++);
			cycleNode.revAdj.addAll(revAdjMap.values());
			cycleNode.Adj.addAll(adjMap.values());
			cycleNode.incomingEdgesPQ.addAll(cycleNode.revAdj);
			cycleNode.children = nodeList.size();
			verticies.add(cycleNode);
			// add children
			for(Vertex node: nodeList) {
				node.parent = cycleNode;
			}
			
			//for all incoming edges, mark other incoming edge from cycle as deleted
			for(Vertex node: nodeList) {
				Edge otherEdge = node.incomingEdgesPQ.peek();
				otherEdge.deleted = true;
			}
			//time3 += (System.currentTimeMillis()-start);
			return create0WeightEdge(g, cycleNode);
		}
		//time3 += (System.currentTimeMillis()-start);
		return 0;
	}

	/**
	 * If vertex v that doesn't belong to cycle has multiple edges coming into the cycle, 
	 * we consider only one edge with minimum weight  
	 * 
	 * @param nodeList
	 * @return
	 */
	private static HashMap<Vertex, Edge> populaterevAdjMap(List<Vertex> nodeList) {
		HashMap<Vertex, Edge> revAdjMap = new HashMap<>();
		for(Vertex node : nodeList) {
			for(Edge e: node.revAdj) {
				Vertex otherEnd = getParent(e.From);
				if(nodeList.contains(otherEnd)) continue;
				if(revAdjMap.containsKey(otherEnd)) {
					if(revAdjMap.get(otherEnd).reducedWeight > e.reducedWeight) {
						revAdjMap.get(otherEnd).deleted = true;
						revAdjMap.put(otherEnd, e);
					} else {
						e.deleted = true;
					}
				} else {
					revAdjMap.put(otherEnd, e);
				}
			}
		}
		return revAdjMap;
	}
	
	/**
	 * same as above method but for outgoing edges.
	 * If vertex v that doesn't belong to cycle has multiple edges going into the cycle, 
	 * we consider only one edge with minimum weight
	 * 
	 * @param nodeList
	 * @return Map containing <Vertex, corresponding outgoing edge>
	 */
	private static HashMap<Vertex, Edge> populateAdjMap(List<Vertex> nodeList) {
		HashMap<Vertex, Edge> adjMap = new HashMap<>();
		for(Vertex node : nodeList) {
			for(Edge e: node.Adj) {
				Vertex otherEnd = getParent(e.To);
				if(nodeList.contains(otherEnd)) continue;
				if(adjMap.containsKey(otherEnd)) {
					if(adjMap.get(otherEnd).reducedWeight > e.reducedWeight) {
						adjMap.get(otherEnd).deleted = true;
						adjMap.put(otherEnd, e);
					} else {
						e.deleted = true;
					}
				} else {
					adjMap.put(otherEnd, e);
				}
			}
		}
		return adjMap;
	}
	
	
	/**
	 * Get parent (super/ outer most vertex) of the vertex
	 * 
	 * @param v
	 * @return parent (super/ outer most vertex) of the vertex
	 */
	private static Vertex getParent(Vertex v) {
		while(v.parent != null) {
			v = v.parent;
		}
		return v;
	}
	
	/**
	 * Find MSDT of the input graph
	 * 
	 * @param g
	 */
	public static void findMST(Graph g){
		vertexCount = g.verts.size();
		verticies = new ArrayList<>(g.verts);
		vertexItr = g.verts.iterator();
		long spanningWeight = 0;
		boolean printMDST = g.verts.size() <= 51 ? true: false;
		// create priority queue for all vertices 
		initialize(g);
		// create first 0 weight graph for the input graph
		spanningWeight = create0WeightGraph(g);
		//run BFS and if BFS returns null, then return valMDST or else find and shrink 0 weight cycles in the graph
		for(Vertex v = runBFS(g); v != null; v = runBFS(g)){
			spanningWeight += findAndShrink0WeightGraph(g, v);
		}
		
		//print MDST
		System.out.println(spanningWeight);
		if(printMDST) {
			printMDST(g);
		}
	}

	/**
	 * creating priority queue of all incoming edges
	 * 
	 * @param g
	 */
	private static void initialize(Graph g) {
		for(Vertex v: g.verts) {
			if(v != null)
				v.incomingEdgesPQ.addAll(v.revAdj);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = null;
		boolean directed = true;
		
		if(args.length == 1){
			File input = new File(args[0]);
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
		findMST(graph);
		System.out.println("time = "+ (System.currentTimeMillis() - start) + " ms");
		//System.out.println("time break down: " + time1 + " " + time2 + " " + time3);

	}
	
}
