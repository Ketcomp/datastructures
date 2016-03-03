import java.util.Arrays;
import java.util.HashSet;

/*
* @author: gketkar08
* Team: G10
* Gaurav Ketkar
* Madhuri Abnave
* Vijay Mungara
* Malav Shah
*/

/*
 * Given an array of unsorted objects of class Distinct, 
 * move the distinct elements of the array to the front. 
 *
 */

public class Distinct {
	static Integer[] arr;

	/*
	 * Finds the distinct elements in the array arr and puts them at the front
	 * of the array.
	 */
	public static <T> int findDistinct(T[] arr) {
		HashSet<T> ourHashTable = new HashSet<>();

		// Add elements of input array to our hash set
		for (int i = 0; i < arr.length; i++) {
			ourHashTable.add(arr[i]);
		}

		// Iterate over the HashSet and put all the unique elements at the beginning.
		int i = 0;
		for (T s : ourHashTable) {
			arr[i++] = s;
		}
		
		int distinctCount = ourHashTable.size();
		return distinctCount;
	}// findDistinct ends

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		arr = new Integer[n];
		int i = 0;
		while (n > 0) {
			arr[i] = Integer.parseInt(args[i++ + 1]);
			n--;
		}
		int result = findDistinct(arr);
		for (int j = 0; j < result; j++) {
			System.out.println(arr[j]);
		}
		System.out.println("There are " +result+ " distinct elements in the array.");
	}// Main ends

	/*
	 * (non-Javadoc)
	 * 
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
		if (!Arrays.equals(arr, Distinct.arr))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(arr);
		return result;
	}
}// Class ends