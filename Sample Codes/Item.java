/**  Java example
 *   Simple class that stores just a single int
 *   @author rbk
 */

public class Item implements Comparable<Item> {
    private int element;

    Item(int x) { element = x; }

    public int getItem() { return element; }

    public void setItem(int x) { element = x; }

    public int compareTo(Item another) { return this.element - another.element; }

    public String toString() { return Integer.toString(element); }

    public static void main(String[] args) {
	final int n = 10;
	Item[] A = new Item[n];
	for(int i=0; i<n; i++) {
	    A[i] = new Item(i+1);
	}

	Item x = new Item(8);

	System.out.println("Binary search for 8: " + Search.badlyDeclaredBinarySearch(A, 0, 9, x));
    }
}

/* Sample output:
Binary search for 8: true
*/
