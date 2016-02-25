import java.util.Scanner;

/**
 * 
 * @author vmungara
 *
 */
public class SelectionProblem {

	private static<T extends Comparable<? super T>> void klargest(T[] arr, int k) {
		int q = select(arr, 0, arr.length- 1, k);
		for(int i = k; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
	// Find the k largest elements of arr[p..r].  Returns index q.
	// The k largest elements are found in arr[q..r].
	public static<T extends Comparable<? super T>> int select(T[] arr, int p, int r, int k) {
		int q = partition(arr, p, r);
		// arr[p...q-1] are less than arr[q]
		// arr[q+1...r] are greater than or equal to arr[q]
		if(r - q >= k) {
			return select(arr, q + 1, r, k);
		} else if(r - q + 1 == k) {
			return q;
		}
		return select(arr, p, q - 1, k - (r - q + 1));
	}

	private static<T extends Comparable<? super T>> int partition(T[] arr, int p, int r) {
		int i = p + (int)(Math.random()*(r - p + 1));
		T x = arr[i];
		swap(arr, i, r);
		i = p - 1;
		// Invariant: for arr[..], 
		// a[p..i] < x;
		// a[i+1..j] > x;
		// a[j..r-1] are unprocessed 
		// a[r] = x;
		for(int j = p; j < r; j++) {
			if(arr[j].compareTo(x) <= 0) {
				i++;
				swap(arr, i, j);
			}
			
		}
		swap(arr, i+1, r);
		return i+1;
	}

	private static<T extends Comparable<? super T>> void swap(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = 10;
		int k = 4;
		Integer arr[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		klargest(arr, k);
		
	}

}
