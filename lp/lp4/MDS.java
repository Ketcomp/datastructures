

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class MDS {
	public enum updateOrInsert {
		update, insert;
	}

	class PriceCompare implements Comparator<Item> {
		public int compare(Item item1, Item item2) {
			if (item1.price <= item2.price)
				return -1;
			else
				return 1;
			else
				return 0;
		}
	}

	TreeMap<Long, Item> primary = new TreeMap<Long, Item>();
	TreeMap<Long, TreeSet<Item>> secondary = new TreeMap<Long, TreeSet<Item>>();
	TreeSet<Item> range = new TreeSet<Item>();
	HashMap<StringBuilder, Integer> same = new HashMap<StringBuilder, Integer>();

	/*
	 * A new Item needs to be inserted in all 4 of our data structures in order
	 * to maintain the consistency of the database.
	 */

	int insert(long id, double price, long[] description, int size) {
		int toReturn = 1; // 1 if Item is new 0 otherwise.
		Item newThing = new Item(id, price, description, size);

		// Does the item already exist?
		if (primary.get(id) != null) {
			toReturn = 0;
		}

		inPrimary(newThing, id, price, description, size, toReturn);
		inSecondary(newThing, id, price, description, size, toReturn);
		inRange(newThing, id, price, description, size, toReturn);
		inSame(newThing, id, price, description, size, toReturn);

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
	long delete(long id) {
		long[] description = primary.get(id).description;
		Item itemToDelete = primary.get(id);
		// Remove from primary
		primary.remove(id);
		// Remove from the secondary
		for (int i = 0; i < description.length; i++) {
			TreeSet<Item> removeFromThis = secondary.get(description[i]);
			removeFromThis.remove(itemToDelete);
		}
		return 0;
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

	double priceHike(long minid, long maxid, double rate) {
		return 0;
	}

	int range(double lowPrice, double highPrice) {
		Item item1 = new Item(lowPrice);
		Item item2 = new Item(highPrice);
		TreeSet<Item> subset = (TreeSet<Item>) range.subSet(item1, true, item2, true);
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

	/*
	 * Insertion functions for each of the 4 data structures.
	 */
	void inPrimary(Item newThing, long id, double price, long[] description, int size, int toReturn) {
		// Item already exists. Update fields.
		if (toReturn == 0) {
			// Update it in primary.
			Item alreadyThere = primary.get(id);
			alreadyThere.price = price;
			if (description.length != 0) {
				alreadyThere.description = description;
			}
		}
		// Item doesn't exist, it is new.
		else {
			// Insert into primary datastructure.
			primary.put(id, newThing);
		}
	}

	void inSecondary(Item newThing, long id, double price, long[] description, int size, int toReturn) {
		if (0 == toReturn) {
			// Update it in secondary.
			for (int i = 0; i < size; i++) {
				// Price
				secondary.get(description[i])you
			}
		} else {
			// Insert into secondary datastructure.
			for (int i = 0; i < size; i++) {
				// Find out if there is already a TreeSet for that description
				if (null == secondary.get(description[i])) {
					PriceCompare pc = new PriceCompare();
					TreeSet<Item> newSet = new TreeSet<Item>(pc);
					newSet.add(newThing);
					secondary.put(description[i], newSet);
				} else { // The tree set already exists.
					secondary.get(description[i]).add(newThing);
				}
			}
		}
	}
}