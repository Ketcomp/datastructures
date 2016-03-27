import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class TotalShortestPaths {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = null;
		boolean directed = true;
		
		if(args.length == 0){
			File input = new File("2.txt");
			in = new Scanner(input);
		} else {
			in = new Scanner(System.in);
			System.out.println("Enter graph:");
		}
		
		//System.out.println("reading graph start");
		Graph graph = Graph.readGraph(in, directed);
		SPath.runDi(graph);
		//assuming each vertex has u.distance populated
		Graph dag = generateDAG(graph);
		countShortestPaths(dag);
	}
	
	static Graph generateDAG(Graph g) {
		Graph dag = new Graph(g.numNodes);
		Vertex v = null;
		for(Vertex u: g.verts) {
			if(u != null) {
				if(u.name == 50) System.out.println(u.revAdj.get(1)+" "+u.revAdj.get(0));
				dag.verts.get(u.name).distance = u.distance;
				for(Edge e: u.Adj) {
					v = e.otherEnd(u);
					if(u.distance + e.Weight == v.distance) {
						dag.addDirectedEdge(u.name, v.name, e.Weight);
					}
				}
			}
		}
		return dag;
	}
	
	static void countShortestPaths(Graph dag) {
		Stack<Vertex> top = getTopologicalOrder(dag);
		if(top == null) {
			System.out.println("Non-positive cycle in graph. DAC is not applicable");
			return;
		}
		
		int pathCount[] = new int[dag.numNodes + 1];
		// set 1 for source node. i.e index 1
		pathCount[1] = 1; 
		
		Vertex u = null;
		int count = 0;
		while(!top.isEmpty()) {
			u = top.pop();
			Vertex tail = null;
			if(u.name == 50) {
				System.out.println();
			}
			for(Edge incomingEdge : u.revAdj) {
				tail = incomingEdge.otherEnd(u);
				pathCount[u.name] += pathCount[tail.name];
			}
			count += pathCount[u.name];
		}

		System.out.println(count);
		for(Vertex node : dag.verts) {
			if(node == null) continue;
			System.out.println(node.name + " " + node.distance + " " + pathCount[node.name]);
		}
	}

	private static Stack<Vertex> getTopologicalOrder(Graph dag) {
		Stack<Vertex> top = new Stack<Vertex>();
		// initialize 
		for(Vertex u : dag.verts) {
			if( u != null ) {
				u.seen = false;
				u.active = false;
			}
		}
		
		for(Vertex u : dag.verts) {
			if(u != null && !u.seen) {
				try {
					DFSVisit(u, top);
					
				} catch (Exception e) {
					return null;
				}
			}
			
		}
		
		return top;
	}

	private static void DFSVisit(Vertex u, Stack<Vertex> top) throws Exception {
		u.seen = true;
		u.active = true;
		
		Vertex v = null;
		for(Edge e : u.Adj) {
			v = e.otherEnd(u);
			if(!v.seen) {
				DFSVisit(v, top);
			} else if(v.active) {
				throw new Exception("Graph is not a DAG");
			}
		}
		
		top.push(u);
		u.active = false;
	}
}
