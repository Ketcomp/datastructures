/**
 * 
 */


/*
 @author Madhuri
 */
public class SinglePivotQuickSort1 {
	private static <T extends Comparable<? super T>> void swap(T[] a,int i,int j)
	{
		T temp=a[i];
		a[i]=a[j];
		a[j]=temp;
		
	}
	private static <T extends Comparable<? super T>>  int partition(T[] a,int p,int r)
	{
		T pivot=a[r];
		int i=p;
		int j=p;
		T x1=a[r];
		if(p<r)
		{
			
			while(i<r)
			if(a[i].compareTo(x1) <= 0)
			{
				swap(a,j,i);
				j++;
				i++;
			}
			else{
				i++;
			}
			
		}
		swap(a,r,j);
		return j;
		
	}
	
	protected static <T extends Comparable<? super T>>  void quickSort(T[] A,int p,int r)
	{
	int q=partition(A,p,r); 
	if(p<q-1)
	{
		quickSort(A, p, q-1);
	}
	if(q+1<r)
	{
		quickSort(A, q+1, r);
	}
	}
	
	public static void main(String args[])
	{
		int n = 1000;
		boolean duplicate=false;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		Integer[] A = new Integer[n];
		Integer[] tmp = new Integer[n];
		if(duplicate)
		{
		for (int i = 0; i <(n/2); i++) {
			A[i] = new Integer(i);
		}
		for (int i = (n/2); i < n; i++) {
			A[i] = new Integer(i-(n/2));
		}
		}else{
			for (int i = 0; i <n; i++) {
				A[i] = new Integer(i);
			}
		}

		Shuffle.shuffle(A, 0, n - 1);
		Shuffle.printArray(A, n-50, n - 1, "Before: ");

		Timer timer = new Timer();
		timer.start();
		quickSort(A,0,n-1);
		System.out.println(timer.end());
		Shuffle.printArray(A, n-50, n - 1, "After: ");
	}

}
