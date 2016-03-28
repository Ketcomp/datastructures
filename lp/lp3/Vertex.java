/**
 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.*;

public class Vertex implements Index, Comparator<Vertex>{
    public int name; 		// name of the vertex
    public boolean seen; 	// flag to check if the vertex has already been visited
    public Vertex parent; 	// parent of the vertex
    public int distance; 	// distance to the vertex from the source vertex
    public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
    public int inDegree; 	// Added to accommodate DAG's needs
    private int index = 0; 	// To implement Index
    public boolean active = false; //for topological order
    public int count = 0; //for BellmanFord
    public int color = 0; //for finding cycles

    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
	name = n;
	index = n;
    distance = n;
    seen = false;
	parent = null;
	Adj = new ArrayList<Edge>();
	revAdj = new ArrayList<Edge>();   /* only for directed graphs */
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
    	return Integer.toString(name);
    }
    @Override
    public void putIndex(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public int compare(Vertex o1, Vertex o2) {
        return o1.distance - o2.distance;
    }
    
}
