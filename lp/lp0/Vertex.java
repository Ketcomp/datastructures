/**
 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Vertex {
    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance; // distance to the vertex from the source vertex
    public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
    public Set<Edge> unseenEdges;  //for Hierholzer Algo
    public Iterator<Edge> itr = null;
    /**
     * Constructor for the vertex
     * 
     * @param n : int - name of the vertex
     */
    Vertex(int n) {
	name = n;
	seen = false;
	parent = null;
	Adj = new ArrayList<Edge>();
	revAdj = new ArrayList<Edge>();   /* only for directed graphs */
	unseenEdges = new LinkedHashSet<>();	  //for Hierholzer Algo
    }

    public Iterator<Edge> itr(){
    	if(itr == null) {
    		itr = Adj.iterator();
    	}
    	return itr; 
    }
    
    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
    }
    
    
}
