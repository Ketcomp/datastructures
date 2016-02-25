/**
* @author: Gaurav Ketkar
*/
public class MergeQuick extends SinglePivotQuickSort1 {
	
	static Integer limit = 10000000;
	
	public static <T extends Comparable<? super T>> void merge(T[]A, int p, int q, int r, T[] tmp) {
		int i = p; int j = q+1;
        for(int k=p; k<=r; k++) {
        	if (j > r || (i <= q && A[i].compareTo(A[j]) <= 0)) {
        		tmp[k] = A[i++];
        	} 
        	else {
        		tmp[k] = A[j++];
        	}
    }
        for(int k=p; k<=r; k++)
        		A[k] = tmp[k];
        return;
	}// merge ends
	
	
	// Mergesort
	public static <T extends Comparable<? super T>> void mergeSort(T[] A, int p, int r, T[] tmp) {
		if(p<r){
			int q = (p + r) / 2;
			mergeSort(A, p, q, tmp);
			mergeSort(A, q+1, r, tmp);
			merge(A, p, q, r, tmp);
		}
	}// Mergesort ends

	//First ten prints the first 10 or A, whichever minimum number of elements.
		public static <T> void firstTen(T[] A) {
			int n = Math.min(A.length, 10);
			for (int i = 0; i < n; i++) {
				System.out.print(A[i] + " ");
			}
			System.out.println();
		}

	
	// Call merge sort
	public static<T extends Comparable<? super T>> void callMergeSort(T[] A, T[] tmp) {
		//Call merge sort
		firstTen(A);
		long start = System.currentTimeMillis();
		mergeSort(A, 0, limit - 1, tmp);
		long time = System.currentTimeMillis() - start;
		System.out.println("Merge sort on " + limit + " numbers takes: " +time+ " mili-seconds");
		firstTen(A);
	}
	
	public static void main(String args[])
	{
		//Single Pivot quick sort
//		
		if (args.length > 0) {
			limit = Integer.parseInt(args[0]);
		}
		Integer[] A = new Integer[limit];
		Integer[] tmp = new Integer[limit];
		for (int i = 0; i < limit; i++) {
			A[i] = new Integer(i);
		}

		Shuffle.shuffle(A, 0, limit - 1);
		Shuffle.printArray(A, limit-50, limit - 1, "Before: ");

		Timer timer = new Timer();
		timer.start();
		quickSort(A,0,limit-1);
		System.out.println(timer.end());
		Shuffle.printArray(A, limit-50, limit - 1, "After: ");
		
		System.out.println("");
		
		
		// Merge Sort
		
		Integer[] AMerge = new Integer[limit];
		Integer[] tmpMerge = new Integer[limit];
		for (int i = 0; i < limit; i++) { AMerge[i] = new Integer(limit - i);}

		//Call mergeSort
		callMergeSort(AMerge, tmpMerge);
		
		
		
	}
}