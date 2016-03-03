/*
 * @author: gketkar08
 * 
 * Given an array of unsorted objects of some class (that implements hashCode and equals), 
 * move the distinct elements of the array to the front.
 * Find the k distinct elements of arr[], and move them to arr[0..k-1]. Return k. Use hashing to implement the algorithm in expected O(n) time. 
 * Signature: public static int findDistinct(T[] arr)
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class Distinct {	
	static Integer[] arr;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(arr);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distinct other = (Distinct) obj;
		if (!Arrays.equals(arr, other.arr))
			return false;
		return true;
	}
	/*
	 * Finds the distinct elements in the array arr and puts them at the front of the array.
	 */
	public static <T> int findDistinct(T[] arr) {
		HashSet<T> ourHashTable = new HashSet<>();
		
		// Add elements of input array to our hash set
		for(int i=0; i<arr.length; i++) {
			ourHashTable.add(arr[i]);
		}
		
		// Get an iterator over the HashSet.
		int i = 0;
		for(T s : ourHashTable)
		{
			arr[i++] = s;
			System.out.println("");
		}
//		
		int distinctCount = ourHashTable.size();
		return distinctCount;
	}// mostFrequent ends

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		arr = new Integer[n];
		int i = 0;
		while(n>0){
			arr[i] = Integer.parseInt(args[i+1]);
			n--;
			i++;
		}
		int resu = findDistinct(arr);
		for(int j=0; j<resu; j++){
			System.out.println(arr[j]);
		}
		System.out.println("There are " + resu + " distinct elements in the array.");
	}// Main ends
}// Class ends