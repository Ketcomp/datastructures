
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class SkipList<T extends Comparable<? super T>> {
	int maxLevel;
	private SLE<T> head;
	private SLE<T> tail;
	
	// Class SkipList's constructor
	private SkipList(T negInfinity,T posInfinity,int max) {
		maxLevel = max;
		tail = new SLE<T>(negInfinity, null,null,maxLevel);
		head = new SLE<T>(posInfinity, null,null,maxLevel);
		tail.prev=head;
		for(int i=0;i<maxLevel;i++)
		head.next[i]=tail;
		
	}

	/*
	 * An object of class SkipListEntry (SLE) represents an element of the skip
	 * list
	 */
	public class SLE<T> {
		SLE<T> prev;
		SLE<T>[] next;
		int lev;
		T element;

		// Constructor
		SLE(T x,SLE<T> prv, SLE<T>[] nxt,int level) {
			next = nxt;
			prev=prv;
			lev = level;
			element = x;
		}
	}

	/*
	 * Main method
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		// Initialize the list
		int size= in.nextInt();
		int maxLevel = (int) Math.log(size); 
		SkipList<Integer> skList = new SkipList<Integer>(Integer.MIN_VALUE,Integer.MAX_VALUE,maxLevel);

		while (in.hasNext()) {
			Integer element = in.nextInt();
			skList.add(element);
		}
		in.close();
	}

	SLE<?> find(T x) {
		// int num = (Integer) x;
		SLE<T> prev;
		SLE<T> p = head;
		for (int i = maxLevel; i > 0; i--) {
			while (p.next[i].element.compareTo(x) < 0) {
				p = p.next[i];
			}
		}
		prev = p;
		return prev;
	}

	/*
	 * Add an element x to the list. Returns true if x was a new element.
	 */
	boolean add(T x) {
		SLE prev = find(x);
		int val = (Integer) x;
		if (prev.next[0].element == (Integer) x) {
			prev.next[0].element = val;
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
	}

	/*
	 * Returns a random value between (0, maxLevel) for the height of next[]
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
		SLE prev = find(x);
		return (prev.next[0].element == (Integer) x);
	}

	/*
	 * Remove x from list; returns false if x was not in list
	 */
	boolean remove(T x) {
		int value = (Integer) x;
		SLE prev = find(x);
		SLE toRemove = prev.next[0];
		if (toRemove.element != (Integer) x) {
			return false;
		} else {
			for (int i = 0; i < maxLevel; i++) {
				if (prev.next[i] == toRemove) {
					prev.next[i] = toRemove.next[i];
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
