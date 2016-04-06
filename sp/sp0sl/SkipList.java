
/** See  http://en.wikipedia.org/wiki/Skip_list
 */
/*
* @author: Gaurav
* Team: G10
* Gaurav Ketkar
* Madhuri Abnave
* Vijay Mungara
* Malav Shah
*/
import java.lang.Comparable;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class SkipList<T extends Comparable<? super T>> {
	int maxLevel;
	private SLE<T> head;
	private SLE<T> tail;

	// Class SkipList's constructor
	private SkipList(T negInfinity, T posInfinity, int max) {
		maxLevel = max;
		head = new SLE<T>(negInfinity, maxLevel);
		tail = new SLE<T>(posInfinity, 0);
		for (int i = 0; i < maxLevel; i++) {
			head.next[i] = tail;
		}
	}

	/*
	 * An object of class SkipListEntry (SLE) represents an element of the skip
	 * list
	 */
	public class SLE<T> {
		//SLE<T> prev;
		SLE<T>[] next;
		int lev;
		T element;

		// Constructor
		SLE(T x, int level) {
			next = new SLE[level];
			lev = level;
			element = x;
		}
		
		@Override
		public String toString() {
			return element+" ";
		}
	}

	/*
	 * Main method
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		// Initialize the list
		//int size = in.nextInt();
		int size = 1000;
		
		int maxLevel = (int) Math.log(size);
		SkipList<Integer> skList = new SkipList<Integer>(Integer.MIN_VALUE, Integer.MAX_VALUE, maxLevel);
		long start = System.currentTimeMillis();
		Integer element = null;
		for(int i = 0; i < size; i++) {
			element = (int)(Math.random()*100*size);
			skList.add(element);
		}
		System.out.println("Skiplist add operation for "+size+" elements: "+(System.currentTimeMillis() - start)+"ms");
		
		start = System.currentTimeMillis();
		TreeSet<Integer> treeset = new TreeSet<>();
		for(int i = 0; i < size; i++) {
			element = (int)(Math.random()*100*size);
			treeset.add(element);
		}
		System.out.println("TreeSet add operation for "+size+" elements: "+(System.currentTimeMillis() - start)+"ms");
		
		System.out.println();
		
		start = System.currentTimeMillis();
		for(int i = 0; i < size; i++) {
			element = (int)(Math.random()*100*size);
			skList.contains(element);
		}
		System.out.println("Skiplist contains operation for "+size+" elements: "+(System.currentTimeMillis() - start)+"ms");
		
		start = System.currentTimeMillis();
		for(int i = 0; i < size; i++) {
			element = (int)(Math.random()*100*size);
			treeset.contains(element);
		}
		System.out.println("TreeSet contains operation for "+size+" elements: "+(System.currentTimeMillis() - start)+"ms");
		
		System.out.println();	
		start = System.currentTimeMillis();
		for(int i = 0; i < size; i++) {
			element = (int)(Math.random()*100*size);
			skList.remove(element);
		}
		System.out.println("Skiplist remove operation for "+size+" elements: "+(System.currentTimeMillis() - start)+"ms");
		
		start = System.currentTimeMillis();
		for(int i = 0; i < size; i++) {
			element = (int)(Math.random()*100*size);
			treeset.remove(element);
		}
		System.out.println("TreeSet remove operation for "+size+" elements: "+(System.currentTimeMillis() - start)+"ms");
		
		in.close();
		
		
	}

	SLE<T>[] find(T x) {
		// int num = (Integer) x;
		SLE<T> prev[] = new SLE[maxLevel];
		SLE<T> p = head;
		for (int i = maxLevel - 1; i >= 0; i--) {
			while (p.next[i].element.compareTo(x) < 0) {
				p = p.next[i];
			}
			prev[i] = p;
		}
		
		return prev;
	}

	/*
	 * Add an element x to the list. Returns true if x was a new element.
	 */
	boolean add(T x) {
		SLE[] prev = find(x);

		if (prev[0].next[0].element.equals(x)) {
			prev[0].next[0].element = x;
			return false;
		} else {
			int lev = choice(maxLevel);
			SLE newEntry = new SLE<Object>(val, lev);
			for (int i = 0; i < lev; i++) {
				newEntry.next[i] = prev.next[i];
				prev.next[i] = newEntry;
			}
			return true;
		}
		
		int lev = choice(maxLevel);
		// (value, prev,next,maxLevel);
		SLE n = new SLE<T>(x, lev);
		//SLE[] nextList = new SLE[lev];
		for (int i = 0; i < lev; i++) {
			n.next[i] = prev[i].next[i];
			prev[i].next[i] = n;
		}
		return true;
	}

	/*
	 * Returns a random value between (1, maxLevel) for the height of next[]
	 */
	int choice(int maxLevel) {
		int i = 0;
		boolean b = false;
		Random rand = new Random();
		while (i < maxLevel) {
			b = rand.nextBoolean();
			if (b)
				i++;
			else
				break;
		}
		return i;
	}

	/*
	 * Is x in the list?
	 */
	boolean contains(T x) {
		SLE<T>[] prev = find(x);
		return (prev[0].next[0].element.equals(x));
	}

	/*
	 * Remove x from list; returns false if x was not in list
	 */
	boolean remove(T x) {
		int value = (Integer) x;
		SLE<T>[] prev = find(x);
		SLE n = prev[0].next[0];
		if (!n.element.equals(x)) {
			return false;
		} else {
			for (int i = 0; i < maxLevel; i++) {
				if (prev[i].next[i].equals(n)) {
					prev[i].next[i] = n.next[i];
				} else
					break;
			}
			return true;
		}
	}

	T ceiling(T x) { // Least element that is >= x, or null if no such element
		return null;
	}

	T findIndex(int index) { // Return the element at a given position (index)
		// in the list
		return null;
	}

	T first() { // Return the first element of the list
		return null;
	}

	T floor(T x) { // Greatest element that is <= x, or null if no such element
		return null;
	}

	boolean isEmpty() { // Is the list empty?
		return true;
	}

	Iterator<T> iterator() { // Returns an iterator for the list
		return null;
	}

	T last() { // Return the last element of the list
		return null;
	}

	void rebuild() { // Rebuild this list into a perfect skip list
	}

	int size() { // Number of elements in the list
		return 0;
	}
}
