/**
 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.*;

public class Vertex {
    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance; // distance to the vertex from the source vertex
    public List<Edge> Adj, revAdj; // adjacency list; Adj has outgoing edges, revAdj has incoming edges
    public PriorityQueue<Edge> incomingEdgesPQ;
    public Vertex previous;
	public int children = 1;
	public List<Vertex> outgoing0s = new ArrayList<Vertex>();

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
	revAdj = new ArrayList<Edge>();   /* only for directed graphs */
	incomingEdgesPQ = new PriorityQueue<Edge>();

    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
    	return Integer.toString(name);
    }
}