public class SinglyLinkedListReversal<T> {
	public class Entry<T> {
		T element;
		Entry<T> next;

		Entry(T x, Entry<T> nxt) {
			element = x;
			next = nxt;
		}
	}

	Entry<T> header, tail;
	int size;

	SinglyLinkedListReversal() {
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

	// reversal without recursion
	// Invariant: header is the head of the chain of elements.
	// header.next is the first element in the chain.
	// current is current element to be processed.
	// prev is the element which is before the current element.
	Entry<T> noRecReverse() {
		Entry<T> prev = null;
		Entry<T> current = header.next;
		Entry<T> next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		header.next = prev;
		return header.next;
	}

	// reversal with recursion
	// Invariant: header is the head of the chain of elements.
	// header.next is the first element in the chain.
	// current is current element to be processed.
	// prev is the element which is before the current element.
	//next1 is the remaining part of the list 
	Entry<T> reverse(Entry<T> current, Entry<T> prev) {

		if (current.next == null) {
			header.next = current;
			current.next = prev;
			return null;
		}
		Entry<T> next1 = current.next;
		current.next = prev;
		reverse(next1, current);
		return header.next;
	}

	public static void main(String[] args) {
		int n = 10;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}

		SinglyLinkedListReversal<Integer> lst = new SinglyLinkedListReversal<>();
		for (int i = 1; i <= n; i++) {
			lst.add(new Integer(i));
		}
		lst.printList();
		lst.noRecReverse();
		lst.printList();
		lst.reverse(lst.header.next, null);
		lst.printList();
	}
}
