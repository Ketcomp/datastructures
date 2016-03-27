import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.io.File;

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
			String location = args[0];
			File input = new File(location);
			Scanner in = new Scanner(input);
			Graph graphFromFile = Graph.readGraph(in, true);
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
	 * Print output
	 */
	public static void printResults(Graph myG, String method) {
		long sum = 0;
		for (int k = 1; k <= myG.verts.size() - 1; k++) {
			sum += myG.verts.get(k).distance;
		}
		System.out.print(method+" "+sum);
		if(myG.verts.size() <= 100){
			for (int k = 1; k <= myG.verts.size() - 1; k++) {
				Vertex v = myG.verts.get(k);
				System.out.println(v.name+" "+v.parent.name+" "+v.distance);
			} 
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
		// runDAG(myGraph);

		// Solve by Dijikstra's algorithm
		//runDi(myGraph);

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
		myG.verts.get(1).distance = 0;
		int graphSize = myG.verts.size();
		for (int i = 2; i < graphSize - 1; i++) {
			Vertex node = myG.verts.get(i);
			node.distance = Integer.MAX_VALUE;
			node.seen = false;
			node.parent = null;
		}

		// Run Dijikstra's
		// Indexed PQ of vertices using vertex.distance as priority.
		Vertex[] array = myG.verts.toArray(new Vertex[myG.verts.size()]);
		IndexedHeap<Vertex> heap = new IndexedHeap<>(array, myG.verts.get(1));
		while(!heap.isEmpty()){
			Vertex vert = heap.deleteMin();
			vert.seen = true;
			for(Edge edj : vert.Adj){		
				Vertex otherEnd = edj.To;
				if(otherEnd.distance > vert.distance + edj.Weight && !otherEnd.seen){
					otherEnd.distance = vert.distance + edj.Weight;
					otherEnd.parent = vert;
					heap.decreaseKey(otherEnd);
				}
			}
		}
		System.out.println("");
		// Output the results - "Vertex : Distance from S"
		printResults(myG, "Dijikstra's\n");
	}
}// Class ends