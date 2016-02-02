/**
 * 
 * @author rbk
 * Example to illustrate an algorithm that tests if a given
 * undirected graph is bipartite.  Nodes are classified into
 * Outer and Inner nodes, the two sides of the bipartition.
 */

import java.io.*;
import java.util.*;
import java.lang.Exception;

public class CheckBipartite {
    /**
     * Class that represents an arc in a Graph
     *
     */
    class Edge {
	public Vertex From; // head vertex
	public Vertex To; // tail vertex
	public int Weight;// weight of the arc

	/**
	 * Constructor for Edge
	 * 
	 * @param u
	 *            : Vertex - The head of the arc
	 * @param v
	 *            : Vertex - The tail of the arc
	 * @param w
	 *            : int - The weight associated with the arc
	 */
	Edge(Vertex u, Vertex v, int w) {
	    From = u;
	    To = v;
	    Weight = w;
	}

	/**
	 * Method to find the other end end of the arc given a vertex reference
	 * 
	 * @param u
	 *            : Vertex
	 * @return
	 */
	public Vertex otherEnd(Vertex u) {
	    // if the vertex u is the head of the arc, then return the tail else return the head
	    if (From == u) {
		return To;
	    } else {
		return From;
	    }
	}

	/**
	 * Method to represent the edge in the form (x,y) where x is the head of
	 * the arc and y is the tail of the arc
	 */
	public String toString() {
	    return "(" + From + "," + To + ")";
	}
    }

    /**
     * Class to represent a vertex of a graph
     * 
     *
     */
    class Vertex {
	public int name; // name of the vertex
	public boolean seen; // flag to check if the vertex has already been visited
	public Vertex parent; // parent of the vertex
	public int distance; // distance to the vertex from the source vertex
	public List<Edge> Adj; // adjacency list; use LinkedList or ArrayList

	/**
	 * Constructor for the vertex
	 * 
	 * @param n
	 *            : int - name of the vertex
	 */
	Vertex(int n) {
	    name = n;
	    seen = false;
	    parent = null;
	    Adj = new ArrayList<Edge>();
	}

	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
	    return Integer.toString(name);
	}
    }

    /**
     * Class to represent a graph
     * 
     *
     */
    class Graph implements Iterable<Vertex> {
	public Vertex[] V; // array of vertices
	public int N; // number of verices in the graph

	/**
	 * Constructor for Graph
	 * 
	 * @param size
	 *            : int - number of vertices
	 */
	Graph(int size) {
	    N = size;
	    V = new Vertex[size + 1];
	    // create an array of Vertex objects
	    for (int i = 1; i <= size; i++)
		V[i] = new Vertex(i);
	}

	/**
	 * Method to add an arc to the graph
	 * 
	 * @param a
	 *            : int - the head of the arc
	 * @param b
	 *            : int - the tail of the arc
	 * @param weight
	 *            : int - the weight of the arc
	 */
	void addEdge(int a, int b, int weight) {
	    Edge e = new Edge(V[a], V[b], weight);
	    V[a].Adj.add(e);
	    V[b].Adj.add(e);
	}

	/**
	 * Method to create an instance of VertexIterator
	 */
	public Iterator<Vertex> iterator() {
	    return new VertexIterator<Vertex>(V, N);
	}

	/**
	 * A Custom Iterator Class for iterating through the vertices in a graph
	 * 
	 *
	 * @param <Vertex>
	 */
	private class VertexIterator<Vertex> implements Iterator<Vertex> {
	    private int nodeIndex = 0;
	    private Vertex[] iterV;// array of vertices to iterate through
	    private int iterN; // size of the array

	    /**
	     * Constructor for VertexIterator
	     * 
	     * @param v
	     *            : Array of vertices
	     * @param n
	     *            : int - Size of the graph
	     */
	    private VertexIterator(Vertex[] v, int n) {
		nodeIndex = 0;
		iterV = v;
		iterN = n;
	    }

	    /**
	     * Method to check if there is any vertex left in the iteration
	     * Overrides the default hasNext() method of Iterator Class
	     */
	    public boolean hasNext() {
		return nodeIndex != iterN;
	    }

	    /**
	     * Method to return the next Vertex object in the iteration
	     * Overrides the default next() method of Iterator Class
	     */
	    public Vertex next() {
		nodeIndex++;
		return iterV[nodeIndex];
	    }

	    /**
	     * Throws an error if a vertex is attempted to be removed
	     */
	    public void remove() {
		throw new UnsupportedOperationException();
	    }
	}

    }

    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;
	CheckBipartite x = new CheckBipartite();

	if (args.length > 0) {
	    File inputFile = new File(args[0]);
	    in = new Scanner(inputFile);
	} else {
	    in = new Scanner(System.in);
	}

	// read the graph related parameters
	int n = in.nextInt(); // number of vertices in the graph
	int m = in.nextInt(); // number of edges in the graph

	// create a graph instance
	Graph g = x.new Graph(n);
	for (int i = 0; i < m; i++) {
	    int u = in.nextInt();
	    int v = in.nextInt();
	    int w = in.nextInt();
	    g.addEdge(u, v, w);
	}
	in.close();
        try {
	    x.driver(g);
        } catch(Exception e) { System.out.println("Exception: " + e); }
    }

    /**
     * Driver method to check whether or not the graph is a bipartite and print
     * the graph
     * 
     * @param in
     */
    void driver(Graph g) throws Exception {
	boolean isBipartite = true;
	initialize(g);
	// Run BFS on every component
	for (Vertex src : g) {
	    if (!src.seen) {
		src.distance = 0;
		isBipartite &= checkBipartite(g, src);
	    }
	}

	// prints the detailed output if the graph is bipartite
	if (!isBipartite) {
	    System.out.println("Graph is not bipartite");
	} else {
	    System.out.println("Graph is bipartite");
	    printResult(g);
	}
    }

    /**
     * Method to initialize a graph
     *  1) Sets the parent of every vertex as null
     *  2) Sets the seen attribute of every vertex as false 
     *  3) Sets the distance of every vertex as infinite
     * 
     * @param g
     *            : Graph - The reference to the graph
     */
    void initialize(Graph g) {
	for (Vertex u : g) {
	    u.seen = false;
	    u.parent = null;
	    u.distance = Integer.MAX_VALUE;
	}
    }

    /**
     * Method to print the graph
     * 
     * @param g
     *            : Graph - The reference to the graph
     */
    void printResult(Graph g) {
	for (Vertex u : g) {
	    if (u.distance % 2 == 0) {
		System.out.println(u + " Outer");
	    } else {
		System.out.println(u + " Inner");
	    }
	}
    }

    /**
     * Method to perform BFS on the graph to check if the graph is bipartite or not
     * 
     * @param g
     *            : Graph - The reference to the graph
     * @param src
     *            : Vertex - The reference to the source vertex of the component
     * @return true if the graph is bipartite
     */
    boolean checkBipartite(Graph g, Vertex src) throws Exception {
	boolean isBipartite = true;
	Queue<Vertex> Q = new LinkedList<>();

	// add the source vertex to the queue
	Q.add(src);
	// mark the source as visited
	src.seen = true;

	// Perform BFS
	while (!Q.isEmpty()) {
	    // remove a vertex from the head of the queue
	    Vertex u = Q.remove();
	    // iterate through the u's adjacency list
	    for (Edge e : u.Adj) {
		Vertex v = e.otherEnd(u);
		/*
		 * if the vertex v is not visited then mark v as visited and
		 * update v's distance and parent and then add v to the queue
		 */
		if (!v.seen) {
		    v.seen = true;
		    v.parent = u;
		    v.distance = u.distance + 1;
		    Q.add(v);
		} else {
		    /*
		     * if the ends of edge (u,v), vertices u and v, are at the 
		     * same distance from the source, the graph is not bipartite
		     */
		    if (u.distance == v.distance)
			isBipartite = false;
		}
	    }
	}
	return isBipartite;
    }
}

/*
Sample input:
3 3
1 2 3
2 3 4
1 3 5

Output:
Graph is not bipartite

Another input:
5 6
1 2 1
2 3 1
3 4 1
4 1 1
2 5 1
4 5 1

Output:
Graph is bipartite
1 Outer
2 Inner
3 Outer
4 Inner
5 Outer

*/

