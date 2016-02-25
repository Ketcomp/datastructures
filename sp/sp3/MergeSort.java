package sp.sp3;


import java.util.*;
import java.lang.Comparable;

public class MergeSort {
    Stack<Frame> s;
    MergeSort() {
	s = new Stack<>();
    }

    class Frame {
	int p, q, r, state;
	Frame(int p, int q, int r, int state) {
	    this.p = p;
	    this.q = q;
	    this.r = r;
	    this.state = state;
	}
    }

    <T extends Comparable<? super T>> void nonRecursiveMergeSort(T[] A, T[] tmp, int left, int right) {
	s.push(new Frame(left, 0, right, 0));
	while(!s.isEmpty()) {
	    Frame current = s.peek();
	    switch(current.state) {
	    case 0:								/* state 0 */
		if(current.p < current.r) {					// if (p < r) {
		    current.q = (current.p + current.r)/2;			//     int q = (p+r)/2;
		    s.push(new Frame(current.p, 0, current.q, 0));		//     mergeSort(A, tmp, p, q);
		    current.state = 1;
		} else {
		    s.pop();
		}
		break;
	    case 1:						     	        /* state 1 */
		s.push(new Frame(current.q+1, 0, current.r, 0));		//     mergeSort(A, tmp, q+1, r);
		current.state = 2;
		break;
	    case 2:								/* state 2 */
		merge(A, tmp, current.p, current.q, current.r);			//     merge(A, tmp, p, q, r);
		s.pop();
		break;
	    }
	}
    }

    static<T extends Comparable<? super T>> void nonRecursiveMergeSort(T[] A, T[] tmp) {
	MergeSort sort = new MergeSort();
	sort.nonRecursiveMergeSort(A, tmp, 0, A.length - 1);
    }

    static<T extends Comparable<? super T>> void merge(T[] A, T[] tmp, int p, int q, int r) {
        int i = p; int j = q+1;
        for(int k=p; k<=r; k++) {
	    if (j > r || (i <= q && A[i].compareTo(A[j]) <= 0)) {
		tmp[k] = A[i++];
	    } else {
		tmp[k] = A[j++];
	    }
	}
        for(int k=p; k<=r; k++) A[k] = tmp[k];
        return;
    }

    public static void main(String[] args) {
        int n = 10;
	if(args.length > 0) { n = Integer.parseInt(args[0]); }
        Integer[] A = new Integer[n];  Integer[] tmp = new Integer[n];
        for (int i = 0; i < n; i++) {
            A[i] = new Integer(i);
        }

	Shuffle.shuffle(A, 0, n-1);
	Shuffle.printArray(A, 0, n-1, "Before: ");

	Timer timer = new Timer();
	timer.start();

	//mergeSort(A, tmp, 0, n-1);
        nonRecursiveMergeSort(A, tmp);
	Shuffle.printArray(A, 0, n-1, "After: ");
	System.out.println(timer.end());
    }
}

/*
Sample output:
Before:  4 5 0 7 8 6 3 9 1 2
After:  0 1 2 3 4 5 6 7 8 9
Time: 1 msec.
Memory: 2 MB / 250 MB.
*/