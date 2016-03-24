/** See  http://en.wikipedia.org/wiki/Skip_list
 */

import java.lang.Comparable;
import java.util.Iterator;

public class SkipList<T extends Comparable<? super T>> {

    boolean add(T x) {  // Add an element x to the list.  Returns true if x was a new element.
	return true;
    }

    T ceiling(T x) { // Least element that is >= x, or null if no such element
	return null;
    }

    boolean contains(T x) {  // Is x in the list?
	return false;
    }

    T findIndex(int index) {  // Return the element at a given position (index) in the list
	return null;
    }

    T first() {  // Return the first element of the list
	return null;
    }

    T floor(T x) {  // Greatest element that is <= x, or null if no such element
	return null;
    }

    boolean isEmpty() {  // Is the list empty?
	return true;
    }

    Iterator<T> iterator() {  // Returns an iterator for the list
	return null;
    }

    T last() {  // Return the last element of the list
	return null;
    }

    void rebuild() {  // Rebuild this list into a perfect skip list
    }

    boolean remove(T x) {  // Remove x from list; returns false if x was not in list
	return false;
    }
    int size() {  // Number of elements in the list
	return 0;
    }
}
