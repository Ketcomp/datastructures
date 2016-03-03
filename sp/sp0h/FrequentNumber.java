/*
 * Author@Malav Shah
 * 
 * Team:
 * Gaurav Ketkar
 * Madhuri Abnave
 * Vijay Mungara
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/*
 * 'mostFrequent(int[] array)' takes as parameter an array of integers, and returns 
 * an integer that is most frequent in the array.
 */
public class FrequentNumber {
	
	public static int mostFrequent(int[] arr)
	{
		HashMap<Integer, Integer> frequency_map= new HashMap<Integer, Integer>();
		int length= arr.length;
		int count=0;
		//Storing the count of each integer in the hashmap
		for(int i=0;i<length;i++)
		{
			if(frequency_map.get(arr[i])==null)
			{
				frequency_map.put(arr[i], 1);
			}
			else
			{
				count= frequency_map.get(arr[i]);
				count++;
				frequency_map.put(arr[i], count);
			}
		}
		
		//Iterating over the hashmap to find the most frequent number.
		int max_count=0;
		int max_number=0;
		Map.Entry<Integer, Integer> pair;
		Iterator iter= frequency_map.entrySet().iterator();
		while(iter.hasNext())
		{
			pair= (Map.Entry<Integer, Integer>)iter.next();
			if(pair.getValue()>= max_count)
			{
				max_count= pair.getValue();
				max_number=pair.getKey();
			}
		}
		return max_number;
	}

	public static int frequencyBySort(int arr[])
	{
		//Sorting the list.
		Arrays.sort(arr);
		int current_count=1;
		int max_count=0;
		int max_number=0;
		int length= arr.length;
		
		//Iterating over the sorted list to find the most frequent number.
		for(int i=1;i<length;i++)
		{
			if(arr[i]==arr[i-1])
			{
				current_count++;
			}
			else
			{
				current_count=1;
			}
			if(current_count>=max_count)
			{
				max_count=current_count;
				max_number=arr[i-1];
			}
		}
		return max_number;
	}
	
	public static void main(String args[])
	{
		int n = 100000000;
		// User input if specified.
		if(args.length>0) { n = Integer.parseInt(args[0]); } 
		
		int[] arr= new int[n];
		for(int i=0;i<n;i++)
			arr[i]=(int)(Math.random()*101);
		
		// Finding most frequent element by HashMap
		double start= System.currentTimeMillis();
		int max_number= mostFrequent(arr);
		double end= System.currentTimeMillis();
		System.out.println("The most frequent number using HashMap is " + max_number);
		System.out.println("Time(Milliseconds): " + (end-start));
		
		// Finding most frequent element by Arrays.sort
		start= System.currentTimeMillis();
		int max_number2= frequencyBySort(arr);
		end= System.currentTimeMillis();
		System.out.println("The most frequent number by Arrays.sort is " + max_number2);
		System.out.println("Time(Milliseconds): " + (end-start));
	}
}