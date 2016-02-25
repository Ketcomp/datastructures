/**
 * 
 */
package sp.sp3;

import java.util.Random;

/*
 @author Madhuri
 */
public class DualPivotQuickSort1 {

	static <T extends Comparable<? super T>> void  quickSort(T[] A, int p, int r) {
		if (p < r) {
			Integer[] q = dualPartition(A, p, r);
			if (p < q[0] - 1)
				quickSort(A, p, q[0] - 1);
			if (A[q[0]] != A[q[1]]) {
				quickSort(A, q[0] + 1, q[1] - 1);

			}
			if (q[1] + 1 < r)
				quickSort(A, q[1] + 1, r);
		}

	}

	/**
	 * @param <T>
	 * @param <T>
	 * @param a
	 * @param p
	 * @param r
	 * @return
	 * @return
	 */
	private static <T extends Comparable<? super T>> Integer[] dualPartition(T[] A, int p, int r) {
		/*
		 * Random rand = new Random();
		 * 
		 * int pivot1 = rand.nextInt(r - p + 1); int pivot2 = rand.nextInt(r - p
		 * + 1);
		 * 
		 * swap(A, p, pivot1); swap(A, r, pivot2);
		 */

		if (A[r].compareTo(A[p]) <= 0) {
			swap(A, p, r);
		}
		Integer[] pivots = new Integer[2];
		int l = p + 1;
		int i = p + 1, j = r - 1;
		T x1 = A[p];
		T x2 = A[r];
		while (i <= j) {
			if (A[i].compareTo(x1) <= 0) {
				swap(A, l, i);
				l++;
				i++;

			} else if (x2.compareTo(A[j]) <= 0) {
				j--;

			} else if (x1.compareTo(A[i]) <= 0 && A[i].compareTo(x2) <= 0) {
				i++;

			} else if (x2.compareTo(A[i]) <= 0 && A[j].compareTo(x1) <= 0) {
				swap(A, i, j);
				swap(A, i, l);
				l++;
				i++;
				j--;

			} else if (x2.compareTo(A[i]) <= 0 && x1.compareTo(A[j]) <= 0 && A[j].compareTo(x2) <= 0) {
				swap(A, i, j);
				i++;
				j--;
				continue;
			}

		}

		swap(A, p, l - 1);
		swap(A, j + 1, r);
		pivots[0] = l - 1;
		pivots[1] = j + 1;
		return pivots;

	}

	static <T> void swap(T[] A, int x, int y) {
		T tmp = A[x];
		A[x] = A[y];
		A[y] = tmp;
	}

	public static void main(String[] args) {
		int n = 10000000;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		Integer[] A = new Integer[n];
		Integer[] tmp = new Integer[n];
		for (int i = 0; i < n; i++) {
			A[i] = new Integer(i);
		}

		Shuffle.shuffle(A, 0, n - 1);
		Shuffle.printArray(A, n - 50, n - 1, "Before: ");

		Timer timer = new Timer();
		timer.start();

		quickSort(A, 0, n - 1);
		System.out.println(timer.end());
		Shuffle.printArray(A, n - 50, n - 1, "After: ");

	}

}
