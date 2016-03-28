import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @authors: Gaurav, Malav Team: 
 * 
 * G10 
 * Gaurav Ketkar 
 * Madhuri Abnave 
 * Vijay Mungara
 * Malav Shah
 */

class SPath {
	public static int whichMethod = 1; // 1-BFS, 2-DAG, 3-Dijikstra's, 4-BellmanFord
	public static boolean notDij = false;
	public static boolean notBFS = false;
	private static boolean enablePrint = true;

	/**
	 * Main method
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		driver(args, true);
	}// Main ends

	/**
	 * Driver class
	 * 
	 * @param args
	 * @param print
	 * @return
	 * @throws IOException
	 */
	public static Graph driver(String[] args, boolean print) throws IOException {
		Graph myGraph = takeInput(args);
		enablePrint = print;
		//Timer time = new Timer();
		//time.start();
		// whichMethod decides which method is run on the graph.
		if (notBFS) {
			if (!checkCycle(myGraph)) {
				whichMethod = 2; // DAG is selected
			} else if (!notDij) {
				whichMethod = 3; // Dij is selected
			} else
				whichMethod = 4; // Defaults to Bellman-Ford
		}

		// Run appropriate algorithm
		switch (whichMethod) {
		case (0):
			System.out.println("Some error");
			break;
		case (1):
			// Solve by BFS - all edge weights are uniform
			runBFS(myGraph);
			break;
		case (2):
			// Solve by DAG - there are no cycles
			runDAG(myGraph);
			break;
		case (3):
			// Solve by Dijikstra's algorithm
			runDijikstras(myGraph);
			break;
		case (4):
			// Solve by Bellman-Ford algorithm
			runBellmanFord(myGraph, myGraph.verts.get(1));
			break;
		}
		//System.out.println(time.end());
		return myGraph;
	}

	// Setter for int whichMethod
	public boolean setWhichMethod(int i) {
		whichMethod = i;
		return true;
	}

	/**
	 * Accept graph input, either from a file or the console.
	 * 
	 * @param args
	 * @return
	 * @throws IOException
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

	/**
	 * Determine which algorithm to run on given graph. Returns True if the
	 * graph has a cycle otherwise False.
	 * 
	 * @param g
	 * @return
	 */
	public static boolean checkCycle(Graph g) {
		for (Vertex v : g.verts) {
			if (v == null)
				continue;
			v.color = 1;
			v.parent = null;
		}
		for (Vertex v : g.verts) {
			if (v == null)
				continue;
			if (v.color == 1)
				if (DFSVisit(v) == true)
					return true;
		}
		return false;
	}

	/**
	 * DFS Visit is called by checkCycle method
	 * 
	 * @param v
	 * @return
	 */
	public static boolean DFSVisit(Vertex v) {
		Vertex u;
		v.color = 2;
		for (Edge e : v.Adj) {
			u = e.otherEnd(v);
			if (u.color == 2)
				return true;
			if (u.color == 1)
				if (DFSVisit(u) == true)
					return true;
		}
		v.color = 3;
		return false;
	}

	/**
	 * Runs BFS on the input graph
	 * 
	 * @param myG
	 */
	public static void runBFS(Graph myG) {
		// Initialize graph.
		int weight = myG.verts.get(1).Adj.get(0).Weight;
		for (Vertex node : myG.verts) {
			if(node == null) continue;
			node.seen = false;
			node.parent = null;
		}
		Vertex source = myG.verts.get(1);
		source.distance = 0;
		
		// Run BFS
		Queue<Vertex> queue = new LinkedList<>();
		queue.add(source);
		source.seen = true;
		Vertex v = null;
		while (!queue.isEmpty()) {
			Vertex current = queue.remove();
			// If current node is not seen, see it and add its adj verts to the queue.
			for (Edge e : current.Adj) {
				v = e.otherEnd(current);
				if(!v.seen) {
					v.parent = current;
					v.distance = current.distance + weight;
					queue.add(v);
					v.seen = true;
				}
			}
		}

		// Output the results - "Vertex : Distance from S"
		printResults(myG, "BFS");

	}// runBFS ends

	public static void runDAG(Graph myG) {
		// get topological order
		Stack<Vertex> top = TotalShortestPaths.getTopologicalOrder(myG);
		// Initialize
		for (Vertex node : myG.verts) {
			if (node != null) {
				node.distance = Integer.MAX_VALUE;
				node.parent = null;
			}
		}
		myG.verts.get(1).distance = 0;

		Vertex v = null;
		Vertex u = null;
		while (!top.isEmpty()) {
			u = top.pop();
			for (Edge e : u.Adj) {
				v = e.otherEnd(u);
				if (v.distance > e.Weight + u.distance && u.distance != Integer.MAX_VALUE) {
					v.distance = e.Weight + u.distance;
					v.parent = u;
				}
			}
		}

		// Print results
		printResults(myG, "DAG");
	}

	/**
	 * BellmanFord Algo
	 * 
	 * @param g
	 * @param source
	 * @return
	 */
	public static boolean runBellmanFord(Graph g, Vertex source) {
		Queue<Vertex> q = new LinkedList<Vertex>();
		for (Vertex v : g.verts) {
			if (v == null)
				continue;
			v.distance = Integer.MAX_VALUE;
			v.parent = null;
			v.count = 0;
			v.seen = false;
		}

		source.distance = 0;
		source.seen = true;
		q.add(source);
		Vertex u;
		Vertex v;
		while (!q.isEmpty()) {
			u = q.remove();
			u.seen = false;
			u.count = u.count + 1;
			if (u.count >= g.numNodes)
				return false;
			for (Edge e : u.Adj) {
				v = e.otherEnd(u);
				if (v.distance > u.distance + e.Weight) {
					v.distance = u.distance + e.Weight;
					v.parent = u;
					if (!v.seen) {
						q.add(v);
						v.seen = true;
					}
				}
			}
		}

		printResults(g, "B-F");
		return true;
	}

	/**
	 * Runs Dijikstra's algo on the input graph
	 * 
	 * @param myG
	 */
	public static void runDijikstras(Graph myG) {
		// Initialize
		myG.verts.get(1).distance = 0;
		int graphSize = myG.verts.size();
		for (int i = 2; i <= graphSize - 1; i++) {
			Vertex node = myG.verts.get(i);
			node.distance = Integer.MAX_VALUE;
			node.seen = false;
			node.parent = null;
		}

		// Run Dijikstra's
		// Indexed PQ of vertices using vertex.distance as priority.
		Vertex[] array = myG.verts.toArray(new Vertex[myG.verts.size()]);
		IndexedHeap<Vertex> heap = new IndexedHeap<>(array, myG.verts.get(1));
		while (!heap.isEmpty()) {
			Vertex vert = heap.deleteMin();
			vert.seen = true;
			for (Edge edj : vert.Adj) {
				Vertex otherEnd = edj.To;
				if (otherEnd.distance > vert.distance + edj.Weight && !otherEnd.seen) {
					otherEnd.distance = vert.distance + edj.Weight;
					otherEnd.parent = vert;
					heap.decreaseKey(otherEnd);
				}
			}
		}
		// Output the results - "Vertex : Distance from S"
		printResults(myG, "Dij");
	}

	/**
	 * Print output
	 * 
	 * @param myG
	 * @param method
	 */
	public static void printResults(Graph myG, String method) {
		if (!enablePrint)
			return; // for level 2

		long sum = 0;
		for (int k = 1; k <= myG.verts.size() - 1; k++) {
			if (myG.verts.get(k).parent != null)
				sum += myG.verts.get(k).distance;
		}
		
		System.out.println(method + " " + sum);
		if (myG.verts.size() <= 100 + 1) {
			for (int k = 1; k <= myG.verts.size() - 1; k++) {
				Vertex v = myG.verts.get(k);
				if (v.distance == Integer.MAX_VALUE || v.parent == null) {
					System.out.println(v.name + " " + "INF" + " " + "-");
				} else if (v.distance == 0) {
					System.out.println(v.name + " " + "0" + " " + "-");
				} else {
					System.out.println(v.name + " " + v.distance + " " + v.parent.name);
				}

			}
		}
	}

}// Class ends