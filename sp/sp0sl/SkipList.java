
/** See  http://en.wikipedia.org/wiki/Skip_list
 */

import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
public class SkipList<T extends Comparable<? super T>> {
	static int maxLevel = 0;
	static SLE head;
	static SLE tail;
	
	/*
	 * An object of class SkipListEntry (SLE) represents an element of the skip list
	 */
	public static class SLE<Comparable> {
		SLE prev;
		SLE[] next;
		int lev;
		int element;
		// Constructor
		SLE(int x, int level) {
			next = new SLE[level];
			lev = level;
			element = x;
		}
	}

	/*
	 * Main method
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		maxLevel = in.nextInt();
		head = new SLE(Integer.MIN_VALUE, maxLevel);
		tail = new SLE(Integer.MAX_VALUE, maxLevel);
		while(in.hasNext()){
			Integer element = in.nextInt();
			add(element);
		}

	}

	SLE find(T x) {
		int num =(Integer)x;
		SLE prev;
		SLE p = head;
		for(int i = maxLevel; i>0; i--){
			while(p.next[i].element < num){
				p = p.next[i];
			}
		}
		prev = p;
		return prev;
	}

	/*
	 * Add an element x to the list. Returns true if x was a
	 * new element.
	 */
	boolean add(T x) { 
		SLE prev = find(x);
		int val = (Integer)x;
		if(prev.next[0].element == (Integer)x){
			prev.next[0].element = val;
			return false;
		}
		else{
			int lev = choice(maxLevel);
			SLE newEntry = new SLE(val, lev);
			for(int i= 0; i<lev; i++){
				newEntry.next[i] = prev.next[i];
				prev.next[i] = newEntry;
			}
			return true;
		}
	}

	/*
	 * Returns a random value between (0, maxLevel) for the height of next[]
	 */
	int choice (int maxLevel){
		int i = 0;
		boolean b = false;
		Random rand = new Random();
		while (i<maxLevel){
			b = rand.nextBoolean();
			if(b) i++;
			else break;
		}
		return i;
	}
	
	/*
	 * Is x in the list?
	 */
	boolean contains(T x) {
		SLE prev = find(x);
		return (prev.next[0].element == (Integer)x);
	}

	/*
	 * Remove x from list; returns false if x was not in
	 * list
	 */
	boolean remove(T x) {
		int value = (Integer) x;
		SLE prev = find(x);
		SLE toRemove = prev.next[0];
		if(toRemove.element != (Integer)x){
			return false;
		}
		else{
			for(int i= 0; i< maxLevel; i++){
				if(prev.next[i] == toRemove){
					prev.next[i] = toRemove.next[i];
				}
				else break;
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
