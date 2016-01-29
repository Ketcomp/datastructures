/**
 * 
 * @author vmungara
 * 
 * Write the Merge sort algorithm that works on linked lists. This will be
 * a member function of a linked list class, so that it can work with the
 * internal details of the class. The function should use only O(log n)
 * extra space (mainly for recursion), and not make copies of elements
 * unnecessarily. You can start from the SinglyLinkedList class provided or
 * create your own.
 * 
 *      static<T extends Comparable<? super T>> void mergeSort(SortableList
 *      <T> lst) { ... }
 * 
 */
public class SortableList<T extends Comparable<? super T>> extends SinglyLinkedList<T> {

	public void merge(SortableList<T> l2) {
		if (l2.size == 0)
			return;

		SinglyLinkedList<T>.Entry<T> c1 = this.header.next;
		SinglyLinkedList<T>.Entry<T> c2 = l2.header.next;
		SinglyLinkedList<T>.Entry<T> temp = null;
		SinglyLinkedList<T>.Entry<T> prevNode = this.header;

		// set header
		if (c1.element.compareTo(c2.element) == 1) {
			temp = c2.next;
			this.header.next = c2;
			c2.next = c1;
			prevNode = c2;
			c2 = temp;
		}

		while (c1 != null && c2 != null) {
			if (c1.element.compareTo(c2.element) == -1) {
				prevNode = c1;
				c1 = c1.next;
			} else {
				temp = c2.next;
				prevNode.next = c2;
				c2.next = c1;
				prevNode = c2;
				c2 = temp;
			}
		}

		if (c1 == null) {
			prevNode.next = c2;
		}

		// set sizes
		this.size += l2.size;
		l2.size = 0;
		l2.tail = null;
	}

	void mergeSort() {
		mergeSort(this);
	}

	public static <T extends Comparable<? super T>> void mergeSort(SortableList<T> lst) {
		if (lst.size > 1) {
			// split lst to 2 SortableLists
			SortableList<T> l2 = lst.splitSortableList();
			mergeSort(lst);
			mergeSort(l2);
			lst.merge(l2);
		}
	}

	/*
	 * split SortableList to 2 parts
	 */
	private SortableList<T> splitSortableList() {
		SortableList<T> l2 = new SortableList<>();

		// find the middle node
		int count = this.size / 2;
		SinglyLinkedList<T>.Entry<T> c = this.header;
		while (count != 0) {
			c = c.next;
			count--;
		}

		l2.header.next = c.next;
		l2.tail = this.tail;
		l2.size = this.size - this.size / 2;

		this.tail = c;
		c.next = null;
		this.size = this.size / 2;
		return l2;
	}

	public static void main(String[] args) {
		int n = 15;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}

		SortableList<Integer> lst = new SortableList<>();
		for (int i = 1; i <= n; i++) {
			// lst.add(new Integer(n-i+1));
			lst.add((int) (Math.random() * (n - 1)));
		}
		lst.printList();
		lst.mergeSort();

		lst.printList();

	}
}