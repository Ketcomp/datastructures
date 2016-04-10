
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class MDS {

	class PriceCompare implements Comparator<Item> {
		public int compare(Item item1, Item item2) {
			if (item1.price < item2.price)
				return -1;
			else if (item1.price > item2.price)
				return 1;
			else
				return 0;
		}
	}

	TreeMap<Long, Item> primary = new TreeMap<Long, Item>();
	TreeMap<Long, TreeSet<Item>> secondary = new TreeMap<Long, TreeSet<Item>>();

	/*
	 * A new Item needs to be inserted in all the data structures we have in order to 
	 * maintain the consistency of the database.
	 */
	int insert(long id, double price, long[] description, int size) {
		Item newThing = new Item(id, price, description, size);
		
		// Insert into primary datastructure.
		primary.put(id, newThing);
		
		// Insert into secondary datastructure.
		for(int i=0; i<size; i++){
			// Find out if there is already a TreeSet for that description
			if(secondary.get(description[i]) == null){
				PriceCompare pc = new PriceCompare();
				TreeSet<Item> newSet = new TreeSet<Item>(pc);
				newSet.add(newThing);
				
				secondary.put(description[i], newSet);
			}
			else{ // The tree set already exists.
				secondary.get(description[i]).add(newThing);
			}
		}
		
		return 0;
	}

	/*
	 * If Item is in out records, return its price, else return 0
	 */
	double find(long id) {
		if(primary.get(id) != null){
			return primary.get(id).price;
		}
		else{
			return 0;
		}
	}

	long delete(long id) {
		long[] description = primary.get(id).description;
		Item itemToDelete = primary.get(id);
		
		// Remove from primary
		primary.remove(id);
		
		// Remove from the secondary
		for(int i=0; i<description.length; i++){
			TreeSet<Item> removeFromThis = secondary.remove(description[i]);
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
		return 0;
	}

	int samesame() {
		return 0;
	}
}