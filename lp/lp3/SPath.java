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
	 * Accept graph input either from file or console input.
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

	public static void main(String[] args) throws IOException {
		Graph myGraph = takeInput(args);

		// Solve by BFS
		runBFS(myGraph);

	}// Main ends

	/*
	 * Runs BFS on the input graph
	 */
	public static void runBFS(Graph ipGraph) {
		// Initialize graph.
		Vertex source = ipGraph.verts.get(1);
		source.distance = 0;
		int m = ipGraph.verts.size();
		for (int i = 2; i < m - 1; i++) {
			Vertex node = ipGraph.verts.get(i);
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
					current.distance = current.parent.distance + 1;
			}
		}

		// Output the results - "Vertex : Distance from S"
		System.out.print("Solving by BFS\n");
		for (int k = 1; k < m - 1; k++) {
			Vertex v = ipGraph.verts.get(k);
			System.out.println("Vertex: " + v.name + ". Distance from S: " + v.distance);
		}
	}// runBFS ends
}