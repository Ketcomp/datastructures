
/** @author rbk
 *  @author vmungara 
 *  Singly linked list: for instructional purposes only
 */

public class SinglyLinkedList<T> {
	public class Entry<T> {
		T element;
		Entry<T> next;

		Entry(T x, Entry<T> nxt) {
			element = x;
			next = nxt;
		}

		@Override
		public String toString() {
			return element.toString();
		}
	}

	Entry<T> header, tail;
	int size;

	SinglyLinkedList() {
		header = new Entry<>(null, null);
		tail = null;
		size = 0;
	}

	void add(T x) {
		if (tail == null) {
			header.next = new Entry<>(x, header.next);
			tail = header.next;
		} else {
			tail.next = new Entry<>(x, null);
			tail = tail.next;
		}
		size++;
	}

	void printList() {
		Entry<T> x = header.next;
		while (x != null) {
			System.out.print(x.element + " ");
			x = x.next;
		}
		System.out.println();
	}

	void unzip() {
		if (size < 3) { // Too few elements. No change.
			return;
		}

		Entry<T> tail0 = header.next;
		Entry<T> head1 = tail0.next;
		Entry<T> tail1 = head1;
		Entry<T> c = tail1.next;
		int state = 0;

		// Invariant: tail0 is the tail of the chain of elements with even
		// index.
		// tail1 is the tail of odd index chain.
		// c is current element to be processed.
		// state indicates the state of the finite state machine
		// state = i indicates that the current element is added after taili
		// (i=0,1).
		while (c != null) {
			if (state == 0) {
				tail0.next = c;
				tail0 = c;
				c = c.next;
			} else {
				tail1.next = c;
				tail1 = c;
				c = c.next;
			}
			state = 1 - state;
		}
		tail0.next = head1;
		tail1.next = null;
	}

	void multiUnzip(int k) {
		if (k >= size) {
			return;
		}

		Entry<T> tails[] = new Entry[k];
		Entry<T> headers[] = new Entry[k];
		tails[0] = header.next;
		for (int i = 1; i < k; i++) {
			headers[i] = tails[i - 1].next;
			tails[i] = headers[i];
		}

		Entry<T> c = headers[k - 1].next;
		int state = 0;
		// state s indicates the state of finite state machine
		// tails[i] is the tail of elements of chain in state i
		// headers[i] is the head of elements of chain in state i
		// (i = 0,1,2,...k)
		// loop runs through entire list using c as next element,
		// when c is added to state i, tails[0] points to new element
		// and state is changed to state + 1 or 0
		while (c != null) {
			tails[state].next = c;
			tails[state] = c;
			c = c.next;
			state = state < k - 1 ? ++state : 0;
		}

		for (int i = 0; i < k - 1; i++) {
			tails[i].next = headers[i + 1];
		}
		tails[k - 1].next = null;

	}

	public static void main(String[] args) {
		int n = 10;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		int k = 3;
		if (args.length > 1) {
			k = Integer.parseInt(args[1]);
		}

		SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
		for (int i = 1; i <= n; i++) {
			lst.add(new Integer(i));
		}
		lst.printList();
		// lst.unzip();
		lst.multiUnzip(k);
		lst.printList();
	}
}