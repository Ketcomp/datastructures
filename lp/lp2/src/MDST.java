import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author vmungara
 *
 */
public class MDST {
	
	/* 1. Transform weights so that every node except r has an incoming edge of weight 0:
for u ∈ V − {r} do
Let du be the weight of a minimum weight edge into u.
for (p, u) ∈ E do
w(p, u) ← w(p, u) − du
Reduction in weight of MST by above transformation = Pdu, where u ∈ V − r.
	 */
	private static int create0WeightGraph(Graph g) {
		
		return 0;
	}
	
	/*2. Let G0 = (V, Z) be the subgraph of G containing all edges of 0-weight, i.e., Z = {e ∈ E :
		w(e) = 0}. Run DFS/BFS in G0, from r. Note that we are using only edges of G with
		0-weight. If all nodes of V are reached from r, then return this DFS/BFS tree as MST.*/
	private static boolean runBFS(Graph g0){
		//return true if BFS can traverse through all nodes in g0, else false
		
		//reset graph vertex nodes
		for(Vertex v : g0.verts) {
			if(v != null) {
				v.seen = false;
			}
		}
		
		int bfsCount = 0;
		Queue<Vertex> queue = new LinkedList<>();
		Vertex u = g0.verts.get(1);	// start from root node always
		u.seen = true;
		Vertex v= null;
		queue.offer(u);
		while(!queue.isEmpty()) {
			u = queue.poll();
			bfsCount++;
			for(Edge e: u.Adj) {
				// check if edge is already deleted when removing 0 cycle. run only on 0 weight edges
				if(!e.isDeleted() && e.Weight == 0) {
					v = e.otherEnd(u);
					if(!v.seen) {
						queue.offer(v);
						v.seen = true;
					}
				}
			}
		}
		
		return bfsCount == g0.numNodes ? true : false;
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
	private static int findAndShrink0WeightGraph(Graph g) {
		
		return 0;
	}
	
	public static int findMST(Graph g){
		int spanningWeight = 0;
		// create first 0 weight graph for the input graph
		spanningWeight = create0WeightGraph(g);
		
		//run BFS and if BFS is true, then return valMDST or else find and shrink 0 weight graph
		while(!runBFS(g)){
			spanningWeight += findAndShrink0WeightGraph(g);
		}
		
		return spanningWeight;
	}
	
	public static void main(String[] args) {
		
	}
	
}
