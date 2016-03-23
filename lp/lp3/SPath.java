import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;


/*
* @author: Gaurav
* Team: G10
* Gaurav Ketkar
* Madhuri Abnave
* Vijay Mungara
* Malav Shah
*/

class SPath {
	/*
	 * Accept graph input, either from a file or the console.
	 */
	public static Graph takeInput(String[] args) throws IOException {
		// Input from file
		if (args.length != 0) {
			FileReader fr = new FileReader(args[0]);
			BufferedReader br = new BufferedReader(fr);

			// create a graph instance
			int n, m;
			n = br.read();
			m = br.read();
			Graph graphFromFile = new Graph(n);
			for (int i = 0; i < m; i++) {
				int u = br.read();
				int v = br.read();
				int w = br.read();
				graphFromFile.addDirectedEdge(u, v, w);
			}
			// Close br
			br.close();
			return graphFromFile;
		}
		// Input from Console
		else {
			Scanner in = new Scanner(System.in);
			Graph graphFromConsole = Graph.readGraph(in, true);
			return graphFromConsole;
		}
	}

	/*
	 * Print results in the format "Solution by BFS/DAG/Dijikstra's/BF Vertex :
	 * Distance from Source"
	 */
	public static void printResults(Graph myG, String method) {
		System.out.print("Solving by " + method);
		for (int k = 1; k < myG.verts.size() - 1; k++) {
			Vertex v = myG.verts.get(k);
			System.out.println("Vertex: " + v.name + ". Distance from S: " + v.distance);
		}
	}

	/*
	 * Main method
	 */
	public static void main(String[] args) throws IOException {
		Graph myGraph = takeInput(args);

		// Solve by BFS - all edge weights are uniform
		runBFS(myGraph);

		// Solve by DAG
		runDAG(myGraph);

	}// Main ends

	/*
	 * Runs BFS on the input graph
	 */
	public static void runBFS(Graph myG) {
		// Initialize graph.
		Vertex source = myG.verts.get(1);
		source.distance = 0;
		int uniformWeight = myG.verts.get(1).Adj.get(0).Weight;
		int m = myG.verts.size();
		for (int i = 2; i < m - 1; i++) {
			Vertex node = myG.verts.get(i);
			node.seen = false;
			node.parent = null;
		}

		// Run BFS
		Queue<Vertex> ipQ = new LinkedList<>();
		ipQ.add(source);
		while (!ipQ.isEmpty()) {
			Vertex current = ipQ.remove();
			// If current node is not seen, see it and add its adj verts to the
			// queue.
			if (!current.seen) {
				current.seen = true;
				for (Edge e : current.Adj) {
					if (!ipQ.contains(e.otherEnd(current))) {
						ipQ.add(e.otherEnd(current));
						e.otherEnd(current).parent = current;
					}
				}
				if (current.parent != null)
					current.distance = current.parent.distance + uniformWeight;
			}
		}

		// Output the results - "Vertex : Distance from S"
		printResults(myG, "BFS\n");

	}// runBFS ends

	/*
	 * Runs DAG shortest path on the input graph
	 */
	public static void runDAG(Graph myG) {
		// Initialize
		int m = myG.verts.size();
		for (int i = 1; i < m - 1; i++) {
			Vertex node = myG.verts.get(i);
			node.distance = 0;
			node.seen = false;
			node.inDegree = 0;
			for (Edge incoming : node.revAdj) {
				node.inDegree++;
			}
		}
		Vertex source = myG.verts.get(1);
		if (source.inDegree != 0) {
			System.out.println("Error! No source node in input");
			return;
		}

		// Run DAG
		Queue<Vertex> myQ = new LinkedList<>();
		myQ.add(source);
		while (!myQ.isEmpty()) {
			Vertex myV = myQ.remove();
			for (Edge e : myV.Adj) {
				Vertex target = e.otherEnd(myV);
				target.inDegree--;
				if (null == target.parent) {
					target.parent = myV;
					target.distance = e.Weight + target.parent.distance;
				} else {
					if (target.distance > (e.Weight + myV.distance)) {
						target.parent = myV;
						target.distance = e.Weight + target.parent.distance;
					}
				}
				if (0 == target.inDegree) {
					myQ.add(target);
				}
			}
		}
		// Print results
		printResults(myG, "DAG\n");
	}

	/*
	 * Runs Dijikstra's algo on the input graph
	 */
	public static void runDi(Graph myG) {
		// Initialize
		int m = myG.verts.size();
		for (int i = 1; i < m-1; i++) {
			Vertex node = myG.verts.get(i);
			node.distance = 0;
			node.seen = false;
		}
		
		// Run Dij's
		Vertex source = myG.verts.get(1);
		for(int i = 2; i<m-1; i++) {
			Vertex candidate = myG.verts.get(i);
			
		}
		
		
		
		
		
	}
}// Class ends