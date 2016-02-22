import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Comparable;

public class MST {
    static final int Infinity = Integer.MAX_VALUE;

    /*
     * Code for Prim's algorithm using priority queue of edges
     */
    static int PrimMST1(Graph g)
    {
    	//reset graph
    	for(Vertex v : g.verts) {
    		if(v != null) {
    			v.seen = false; v.parent = null;
    		}
    	}
    	
        int wmst = 0; // weight of mst
        Vertex src = g.verts.get(1);
        src.seen = true;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for(Edge e: src.Adj) queue.add(e);
        
        Edge e = null;
        Vertex u = null; // vertex of edge e and is a part of set(MST)
        Vertex v = null; // other end of edge e and is not part of set(MST)
        Vertex w = null;  // adjacent vertex to v
        
        // invariant: when we remove edge e from pq with ends u, v. 
        // where u is part of MST and v is other end of e.
        // if v is not part of MST? add v to MST and edge weight to wmst, else continue
        while(!queue.isEmpty()) {
        	e = queue.remove();
        	if(e.From.seen && e.To.seen) continue;
        	u = e.From.seen ? e.From : e.To ;
        	v = e.otherEnd(u);
        	//v.parent = u;
        	wmst += e.Weight;
        	v.seen = true;
        	for(Edge f : v.Adj) {
        		w = f.otherEnd(v);
        		if(!w.seen) queue.add(f);
        	}
        }
        return wmst;
    }
    
    /*
     *  Code for Prim's algorithm using priority queue of vertices
     */
    static int PrimMST2(Graph g)
    {
    	//reset graph
    	for(Vertex v : g.verts) {
    		if(v != null) {
    			v.seen = false; v.parent = null; v.distance = Infinity;
    		}
    	}
    	
        int wmst = 0; // weight of mst
        Vertex src = g.verts.get(1);
        src.seen = true; src.distance = 0; // initial condition
        
        Vertex[] array = g.verts.toArray(new Vertex[g.verts.size()]);
        IndexedHeap<Vertex> heap = new IndexedHeap<>(array, src);
        Vertex u = null; // vertex of edge e and is a part of set(MST)
        Vertex v = null; // other end of edge e and is not part of set(MST)
        // invariant: initially add all vertices to heap with distance infinity 
        // get next item u from heap (the vertex with least distance from source)
        // for each adjacent vertex v, if v.distance is more than edge weight 
        // set u as parent of v and change v.distnace to edge weight
        while(!heap.isEmpty()) {
        	u = heap.remove(); //getMin()
        	u.seen = true;
        	wmst += u.distance;
        	
        	for(Edge e: u.Adj) {
        		v = e.otherEnd(u);
        		if(!v.seen && e.Weight < v.distance) {
        			v.distance = e.Weight; v.parent = u;
        			heap.percolateUp(v.getIndex());
        		}
        	}
        }
        return wmst;
    }

    public static void main(String[] args) throws FileNotFoundException 
    {	Scanner in = null;
		if(args.length == 1){
			File input = new File("sp0pq-big.txt");
			in = new Scanner(input);
		} else {
			in = new Scanner(System.in);
		}
		
        Graph g = Graph.readGraph(in, false);
        System.out.println("Running Prims 1");
        long start = System.currentTimeMillis();
        System.out.println("mst weight: "+PrimMST1(g));
        System.out.println("time = "+ (System.currentTimeMillis() - start) + " ms");

        System.out.println("Running Prims 2");
        start = System.currentTimeMillis();
        System.out.println("mst weight: "+PrimMST2(g));
        System.out.println("time = "+ (System.currentTimeMillis() - start) + " ms");

    }
}
