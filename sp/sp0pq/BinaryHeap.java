// Ver 1.0:  Wec, Feb 3.  Initial description.

import java.util.Arrays;
import java.util.Comparator;

public class BinaryHeap<T> implements PQ<T> {
    T[] pq;
    protected int size;
    Comparator<T> c;
    /** Build a priority queue with a given array q */
    BinaryHeap(T[] q, Comparator<T> comp) {
		pq = q;
		size = q.length - 1;
		c = comp;
		buildHeap();
    }

    /** Create an empty priority queue of given maximum size */
    BinaryHeap(int n, Comparator<T> comp) { /* to be implemented */
  
    }

    public void insert(T x) {
    	add(x);
    }

    public T deleteMin() {
    	return remove();
    }

    public T min() { 
    	return peek();
    }

    public void add(T x) { /* to be implemented */
    	if(size == (pq.length - 1))
    		resize();
    	size++;
    	pq[size] = x;
    	percolateUp(size);
    }

    private void resize() {
    	pq = Arrays.copyOf(pq, 2*size);
	}

	public T remove() {
		if(size == 0)
			return null;
		T min = pq[1];
		pq[1] = pq[size--];
		percolateDown(1);
		return min;
    }

    public T peek() { 
    	if(size == 0)
    		return null;
    	
    	return pq[size];
    }

    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) {
    	pq[0] = pq[i];
    	while(c.compare(pq[i/2], pq[0]) > 0) {
    		pq[i] = pq[i/2];
    		i /= 2;
    	}
    	pq[i] = pq[0];
    }

    /** pq[i] may violate heap order with children */
    void percolateDown(int i) { 
    	T x = pq[i];
    	while(2*i <= size) {
    		if(2*i == size) { //one child
    			if( c.compare(x, pq[size]) > 0) {
    				pq[i] = pq[size];
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
    				i = child;
    			} else 
    				break;
    		}
    		pq[i] = x;
    		
    	}
    }

    /** Create a heap.  Precondition: none. */
    void buildHeap() {
    	for(int i = size / 2 ; i >= 1; i-- ) {
    		percolateDown(i);
    	}
    }
    
    public boolean isEmpty(){
    	return size <= 0;
    }
    
    /* sort array A[1..n].  A[0] is not used. 
       Sorted order depends on comparator used to buid heap.
       min heap ==> descending order
       max heap ==> ascending order
     */
    public static<T> void heapSort(T[] A, Comparator<T> comp) { /* to be implemented */
    	
    }
    
    public static void main(String[] args) {
    	int n = 8;
		Integer list[] = new Integer[n];
		for(int i = 0; i < n ; i++) {
			list[i] = n - i;
		}
		
		Comparator com = new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		};
		
		BinaryHeap heap = new BinaryHeap<>(list, com);
		System.out.println(heap.pq);
		
		
	}
}
