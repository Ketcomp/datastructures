import java.io.IOException;
import java.util.Stack;

/**
 * 
 * @author vmungara
 *
 */
public class TotalShortestPaths {

	public static void main(String[] args) throws IOException {
		Graph graph = SPath.driver(args, false);
		//Timer time = new Timer();
		//time.start();
		Graph dag = generateDAG(graph);
		countShortestPaths(dag);
		//System.out.println(time.end());
	}
	
	/**
	 * Generate DAG containing all shortest paths of graph g
	 * @param g
	 * @return
	 */
	static Graph generateDAG(Graph g) {
		Graph dag = new Graph(g.numNodes);
		Vertex v = null;
		for(Vertex u: g.verts) {
			if(u != null) {
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
	
	/**
	 * Count shortest paths for each vertex from source.
	 * @param dag
	 */
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
			for(Edge incomingEdge : u.revAdj) {
				tail = incomingEdge.otherEnd(u);
				pathCount[u.name] += pathCount[tail.name];
			}
			count += pathCount[u.name];
		}

		System.out.println(count);

		if(dag.numNodes > 100) {
			//do not print paths
			return;
		}
		
		for(Vertex node : dag.verts) {
			if(node == null) continue;
			System.out.println(node.name + " " + node.distance + " " + pathCount[node.name]);
		}
	}

	/**
	 * Get Topological order of vertices in DAG
	 * @param dag
	 * @return
	 */
	public static Stack<Vertex> getTopologicalOrder(Graph dag) {
		Stack<Vertex> top = new Stack<Vertex>();
		// initialize 
		for(Vertex u : dag.verts) {
			if( u != null ) {
				u.seen = false;
				u.active = false;
			}
		}
		// Run DFS visit
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

	/**
	 * Modified DFS Visit, throws exception if DAG has a cycle.
	 * @param u
	 * @param top
	 * @throws Exception
	 */
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
