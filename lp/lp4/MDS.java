import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class MDS {

	class PriceCompare implements Comparator<Item> {
		public int compare(Item item1, Item item2) {
			if (item1.price <= item2.price)
				return -1;
			else
				return 1;
		}
	}

	TreeMap<Long, Item> primary = new TreeMap<Long, Item>();
	TreeMap<Long, TreeSet<Item>> secondary = new TreeMap<Long, TreeSet<Item>>();
	TreeSet<Item> range; 
	HashMap<StringBuilder, Integer> same = new HashMap<StringBuilder, Integer>();

	/*
	 * A new Item needs to be inserted in all 4 of our data structures in order
	 * to maintain the consistency of the database.
	 */

	int insert(long id, double price, long[] description, int size) {
		int toReturn = 1; // 1 if Item is new 0 otherwise.
		Item newThing = new Item(id, price, description, size);

		// Does the item already exist?
		if (primary.get(id) != null) 
		{
			toReturn=0;
			delete(id);
		}
		
		// Inserting to primary data structure
		primary.put(id,  newThing);
		PriceCompare pc= new PriceCompare();
		
		// Inserting to secondary data structure
		for(int i=0;i<size;i++)
		{
			if(secondary.get(description[i])==null)
			{
				TreeSet<Item> newSet= new TreeSet<Item>(pc);
				newSet.add(newThing);
				secondary.put(description[i], newSet);
			}
			else
			{
				secondary.get(description[i]).add(newThing);
			}
		}
		
		// Adding to range data structure
		if(range== null)
			range = new TreeSet<Item>(pc);
		range.add(newThing);
		
		// Adding to same data structure
		if(size>=8)
		{
			Arrays.sort(description);
			StringBuilder key= new StringBuilder();
			for(int i=0;i<size;i++)
				key= key.append(description[i]);
			if(same.get(key)==null)
				same.put(key, 1);
			else
			{
				int count= same.get(key);
				count++;
				same.put(key, count);
			}
		}
		return toReturn;
	}

	/*
	 * If Item is in out records, return its price, else return 0
	 */
	double find(long id) {
		if (primary.get(id) != null) {
			return primary.get(id).price;
		} else {
			return 0;
		}
	}

	/*
	 * Delete Item from all of our datastructures.
	 */
	long delete(long id) 
	{
		long toReturn=0;
		if(primary.containsKey(id))
		{
			Item toRemove= primary.remove(id);
			for(int i=0;i<toRemove.length;i++)
				toReturn= toReturn + toRemove.description[i];
			for(int i=0;i<toRemove.length;i++)
			{
				TreeSet<Item> removeFromSet= secondary.get(toRemove.description[i]);
				removeFromSet.remove(toRemove);
			}
			
			range.remove(toRemove);
			if(toRemove.length>=8)
			{
				Arrays.sort(toRemove.description);
				StringBuilder key= new StringBuilder();
				for(int i=0;i<toRemove.length;i++)
					key= key.append(toRemove.description[i]);
				if(same.get(key)==1)
					same.remove(key);
				else
				{
					int count= same.get(key);
					count--;
					same.put(key, count);
				}
			}
		}
		return toReturn;
	}

	double findMinPrice(long des) {
		TreeSet<Item> temp = secondary.get(des);
		return (temp.first().price);
	}

	double findMaxPrice(long des) {
		TreeSet<Item> temp = secondary.get(des);
		return (temp.last().price);
	}

	int findPriceRange(long des, double lowPrice, double highPrice) {
		TreeSet<Item> temp = secondary.get(des);
		Iterator iter = temp.iterator();
		Item tempitem = null;
		int count = 0;
		while (iter.hasNext()) {
			tempitem = (Item) iter.next();
			if (tempitem.price > lowPrice && tempitem.price < highPrice)
				count++;
		}
		return count;
	}

	double priceHike(long minid, long maxid, double rate) 
	{
		NavigableMap<Long, Item>  subMap= new TreeMap<Long, Item>();
		subMap= primary.subMap(minid, true, maxid, true);
		double total_increase=0, increment=0;
		Double d;
		Iterator iter= subMap.entrySet().iterator();
		Map.Entry<Long, Item> pair;
		while(iter.hasNext())
		{
			pair= (Map.Entry<Long, Item>)iter.next();
			increment= pair.getValue().price * (rate/100);
			d= increment;
			increment= new BigDecimal(d).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
			total_increase= total_increase+ increment;
			pair.getValue().price += increment; 
		}

		return total_increase;
	}

	int range(double lowPrice, double highPrice) {
		Item item1 = new Item(lowPrice);
		Item item2 = new Item(highPrice);
		NavigableSet<Item> subset = new TreeSet<Item>(); 
		subset = range.subSet(item1, true, item2, true);
		return subset.size();
	}

	int samesame() {
		int count = 0;
		Iterator iter = same.entrySet().iterator();
		Map.Entry pair;
		while (iter.hasNext()) {
			pair = (Map.Entry) iter.next();
			if ((Integer) pair.getValue() > 1)
				count = count + (Integer) pair.getValue();
		}
		return count;
	}
}