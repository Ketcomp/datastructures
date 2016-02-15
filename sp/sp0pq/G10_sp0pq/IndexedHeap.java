// Ver 1.0:  Wed, Feb 3.  Initial description.
// Ver 1.1:  Thu, Feb 11.  Simplified Index interface

import java.util.Comparator;
import java.util.Scanner;

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
    /** Build a priority queue with a given array q */
    IndexedHeap(T[] q, Comparator<T> comp) {
    	super(q, comp);
    }

    /** Create an empty priority queue of given maximum size */
    IndexedHeap(int n, Comparator<T> comp) {
    	super(n, comp);
    }

    /** restore heap order property after the priority of x has decreased */
    void decreaseKey(T x) {
    	percolateUp(x.getIndex());
    }
    
    void percolateDown(int i) { 
    	T x = pq[i];
    	while(2*i <= size) {
    		if(2*i == size) { //one child
    			if( c.compare(x, pq[size]) > 0) {
    				pq[i] = pq[size];
    				pq[i].putIndex(i);
    				i = size;
    			} else 
    				break;
    			
    		} else { // 2 children
    			int child = 0;
    			if(c.compare(pq[2*i], pq[2*i + 1]) <= 0)
    				child = 2*i;
    			else 
    				child = 2*i + 1;
    			
    			if(c.compare(x, pq[child]) > 0){
    				pq[i] = pq[child];
    				pq[i].putIndex(i);
    				i = child;
    			} else 
    				break;
    		}
    		pq[i] = x;
    		x.putIndex(i);
    	}
    }
    
    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) {
    	pq[0] = pq[i];
    	pq[0].putIndex(0);
    	while(c.compare(pq[i/2], pq[0]) > 0) {
    		pq[i] = pq[i/2];
    		pq[i].putIndex(i);
    		i /= 2;
    	}
    	pq[i] = pq[0];
    	pq[i].putIndex(i);
    }
    
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
        Graph g = Graph.readGraph(in, false);
        Vertex[] array = g.verts.toArray(new Vertex[g.verts.size()]);
        int a = g.verts.size();
    	IndexedHeap<Vertex> heap = new IndexedHeap<>(array, g.verts.get(1));
    	
    	for(int i= 0; i < 10; i++) {
    		heap.add(new Vertex(i));
    	}
    	
    	System.out.println(heap);
    	
	}
}
