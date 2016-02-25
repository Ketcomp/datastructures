

/**
 * 
 */


/*
 @author Madhuri
 */
public class SinglePivotQuickSort {
	private static void swap(Integer[] a,int i,int j)
	{
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
		
	}
	private static int partition(Integer[] a,int p,int r)
	{
		int pivot=a[r];
		int i=p;
		int j=p;
		int x1=a[r];
		if(p<r)
		{
			
			while(i<r)
			if(a[i]<=x1)
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
	
	private static void quickSort(Integer[] A,int p,int r)
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
		int n = 10000000;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		Integer[] A = new Integer[n];
		Integer[] tmp = new Integer[n];
		boolean duplicate=false;
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
