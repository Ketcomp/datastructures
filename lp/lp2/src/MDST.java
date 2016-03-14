import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * @author vmungara
 *
 */
public class MDST {
	

	static int vertexCount = 0;
	static List<Vertex> verticies;
	

	/* 1. Transform weights so that every node except r has an incoming edge of weight 0:
for u ∈ V − {r} do
Let du be the weight of a minimum weight edge into u.
for (p, u) ∈ E do
w(p, u) ← w(p, u) − du
Reduction in weight of MST by above transformation = Pdu, where u ∈ V − r.
	 */
	private static long create0WeightGraph(Graph g) {
		long Pdu = 0;
		for(int i = 2; i < verticies.size(); i++) {
			Vertex v = getParent(verticies.get(i));
			Edge minEdge = getEdge(v);
			if(minEdge == null || minEdge.deleted) continue;
			Vertex u = getParent(minEdge.From);
			int du = minEdge.reducedWeight;
			if(u != v) {
				v.previous = u;
				u.outgoing0s.add(v);
				if(du == 0) continue;
				//reduce weights of all incoming edges
				for(Edge e: v.revAdj) {
					e.reducedWeight -= du;
				}
				Pdu += du;
			}
		}
		return Pdu;
	}
	
	private static long create0WeightGraph2(Graph g) {
		long Pdu = 0;
		for(int i = 2; i < verticies.size(); i++) {
			Vertex v = verticies.get(i);
			Edge minEdge = getEdge(v);
			Vertex u = minEdge.From;
			int du = minEdge.reducedWeight;
			if(du == 0) continue;
			if(u != v) {
				v.previous = u;
				//reduce weights of all incoming edges
				for(Edge e: v.revAdj) {
					e.reducedWeight -= du;
				}
				Pdu += du;
			}
		}
		return Pdu;
	}
	
	static Edge getEdge(Vertex v) {
		Edge minEdge = v.incomingEdgesPQ.peek();
		return minEdge;
	}
	
	/*2. Let G0 = (V, Z) be the subgraph of G containing all edges of 0-weight, i.e., Z = {e ∈ E :
		w(e) = 0}. Run DFS/BFS in G0, from r. Note that we are using only edges of G with
		0-weight. If all nodes of V are reached from r, then return this DFS/BFS tree as MST.*/
	private static Vertex runBFS(Graph g0, boolean print){
		//return null if BFS can traverse through all nodes in g0, else unreachable node
		
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
					if(print) System.out.println("(" + u + "," + v +")");
					v.seen = true;
				}
			}
			
		}
		
		// get unvisited node
		if(bfsCount == g0.numNodes) {
			return null;
		}
		//System.out.println("counts "+bfsCount+" : "+g0.numNodes);
		
		for(Vertex vertex: verticies) {
			if(vertex != null && !getParent(vertex).seen) {
				return getParent(vertex);
			}
		}

		return null;
	}
	
	private static void printMDST(Graph g0){
		//return null if BFS can traverse through all nodes in g0, else unreachable node
		
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
		Vertex v = null;
		while(!queue.isEmpty()) {
			u = queue.poll();
			bfsCount += u.children;
			
			for(Edge e: u.Adj) {
				v = e.To;
				// check if edge is already deleted when removing 0 cycle. run only on 0 weight edges
				if(e.reducedWeight == 0) {
					if(!v.seen) {
						queue.offer(v);
						System.out.println(e);
						v.seen = true;
					}
				}
			}	
		}
	}
	
	/*3. If there is no spanning tree rooted at r in G0, then there is a 0-weight cycle. Find a 0-weight
cycle as follows:
(a) Find a node z that is not reachable from r in G0, in the above search.
(b) Walk backward from z in G0, using incoming edges at each node visited. Every node
except r has a 0-edge coming into it, and so this walk can keep going forever. Since r
has no path to z using 0-edges, this walk will never get to r. There are only a finite
number of nodes. So, some node x will be repeated on this walk. The path from x to
itself on this walk is composed of 0-weight edges, and this gives a 0-weight cycle C.
4. Shrink cycle C into a single node c. There may be many edges from the nodes of C to a
node u outside the cycle. These are replaced by a single edge. For each edge (a, u) in G, with
a ∈ C and u 6∈ C, introduce the edge (c, u) of weight w(a, u).
Similarly, for edges of G that are going into C, do the following. For each edge (u, a) in G,
with u 6∈ C and a ∈ C, introduce the edge (u, c) of weight w(u, a).
If there are multiple edges (c, u), keep just one edge with minimum weight, and record the
corresponding edge of G. Similarly, process multiple edges (u, c) by replacing each multi-edge
by a single edge of minimum weight.
The new graph has fewer nodes than the original graph, and the MSTs of the two graphs
have equal weight.
	 */
	private static long findAndShrink0WeightGraph(Graph g, Vertex z) {
		//get parent of z (cycle in which z is present)
		Vertex pnode = getParent(z);
		//walk backward and get all nodes and add to list 
		Set<Vertex> nodeList = new HashSet<Vertex>();
		Stack<Vertex> stack = new Stack<>();
		while(!pnode.seen) {
			stack.push(pnode);
			pnode.seen = true;
			pnode = getParent(pnode.previous);
		}
		
		while(pnode != stack.peek()) {
			nodeList.add(stack.pop());
		}
		nodeList.add(stack.pop());
		
		if(!nodeList.isEmpty()) {
			HashMap<Vertex, Edge> revAdjMap = populaterevAdjMap(nodeList);
			HashMap<Vertex, Edge> adjMap = populateAdjMap(nodeList);
			
			Vertex cycleNode = new Vertex(vertexCount++);
			cycleNode.revAdj.addAll(revAdjMap.values());
			cycleNode.Adj.addAll(adjMap.values());
			cycleNode.incomingEdgesPQ.addAll(cycleNode.revAdj);
			cycleNode.children = nodeList.size();
			verticies.add(cycleNode);
			
			for(Vertex node: nodeList) {
				node.parent = cycleNode;
				cycleNode.childrens += node.name+" ";
			}
			
			//for all incoming edges mark other incoming edge from cycle as deleted
			for(Vertex node: nodeList) {
				Edge otherEdge = node.incomingEdgesPQ.peek();
				otherEdge.deleted = true;
			}
		}
		
		return create0WeightGraph(g);
	}

	private static HashMap<Vertex, Edge> populaterevAdjMap(Set<Vertex> nodeList) {
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
	
	private static HashMap<Vertex, Edge> populateAdjMap(Set<Vertex> nodeList) {
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
	
	
	private static Vertex getParent(Vertex v) {
		while(v.parent != null) {
			v = v.parent;
		}
		return v;
	}
	
	public static void findMST(Graph g){
		vertexCount = g.verts.size();
		boolean printMDST = g.verts.size() <= 51 ? true: false;
		verticies = g.verts;
		long spanningWeight = 0;
		
		initialize(g);
		
		// create first 0 weight graph for the input graph
		spanningWeight = create0WeightGraph(g);
		
		//run BFS and if BFS is true, then return valMDST or else find and shrink 0 weight graph
		for(Vertex v = runBFS(g, false); v != null; v = runBFS(g, false)){
			spanningWeight += findAndShrink0WeightGraph(g, v);
		}
		
		//print edges
		System.out.println(spanningWeight);
		if(printMDST) {
			printMDST(g);
		}
	}
	
	/*
	 * creating priority queue of all incoming edges
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
		
		if(args.length == 0){
			File input = new File("1-lp2.txt");
			in = new Scanner(input);
		} else {
			in = new Scanner(System.in);
			System.out.println("Enter graph:");
		}
		
		//System.out.println("reading graph start");
		Graph graph = Graph.readGraph(in, directed);
		System.out.println("reading graph complete");
		//System.out.println("vertices: " + graph.numNodes + ", edges: " + graph.edgeCount);
		long start = System.currentTimeMillis();
		findMST(graph);
		System.out.println("time = "+ (System.currentTimeMillis() - start) + " ms");

	}
	
}
