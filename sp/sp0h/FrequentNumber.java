/*
* @author: Malav Shah
* Team: G10
* Gaurav Ketkar
* Madhuri Abnave
* Vijay Mungara
* Malav Shah
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/*
 * An O(nlogn) algorithm that sorts the array with Arrays.sort
 */
public class FrequentNumber {

	public static int frequencyBySort(int arr[]) {
		// Sorting the list.
		Arrays.sort(arr);

		int current_count = 1;
		int current_max = 0;
		int max_number = 0;
		
		// Iterating over the sorted list to find the most frequent number.
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[i - 1]) {
				current_count++;
			}else {
				current_count = 1; // Re-init current_count for the next number in the sorted array.
			}
			if (current_count >= current_max) {
				current_max = current_count;
				max_number = arr[i - 1];
			}
		}
		return max_number;
	}

	public static void main(String args[]) {
		int n = 200000000;
		// User input if specified.
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = (int) (Math.random() * 101);

		// Finding most frequent element by HashMap
		double start = System.currentTimeMillis();
		int max_number = mostFrequent(arr);
		double end = System.currentTimeMillis();

		System.out.println("The most frequent number using HashMap is " + max_number);
		System.out.println("Time: " + (end - start)/1000 + "s.\n");

		// Finding most frequent element by Arrays.sort
		start = System.currentTimeMillis();
		int max_number2 = frequencyBySort(arr);
		end = System.currentTimeMillis();
		
		System.out.println("The most frequent number by Arrays.sort is " + max_number2);
		System.out.println("Time: " + (end - start)/1000 + "s.");
	}
	
	/*
	 * 'mostFrequent(int[] array)' takes as parameter an array of integers, and returns 
	 * an integer that is most frequent in the array.
	 */
	public static int mostFrequent(int[] arr) {
		HashMap<Integer, Integer> freq_map = new HashMap<Integer, Integer>();
		int length = arr.length;
		int count = 0;
		// Storing the count of each integer in the hashmap
		for (int i = 0; i < length; i++) {
			if (freq_map.get(arr[i]) == null) {freq_map.put(arr[i], 1);}
			else {
				count = freq_map.get(arr[i]);
				count++; freq_map.put(arr[i], count);
			}
		}
		// Iterating over the hashmap to find the most frequent number.
		int current_max = 0;
		int max_number = 0;
		Map.Entry<Integer, Integer> pair;
		Iterator iter = freq_map.entrySet().iterator();
		
		while (iter.hasNext()) {
			pair = (Map.Entry<Integer, Integer>) iter.next();
			if (pair.getValue() >= current_max) {
				current_max = pair.getValue();
				max_number = pair.getKey();
			}
		}
		return max_number;
	}
}