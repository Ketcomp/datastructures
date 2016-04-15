

import java.util.Comparator;

public class Item {

	long id;
	double price;
	long[] description;

	Item(long id, double price, long[] description, int size) {
		this.id = id;
		this.price = price;
		this.description = new long[size];
		for (int i = 0; i < size; i++)
			this.description[i] = description[i];
	}
	
	Item (double price)
	{
	this.price= price;
	}
}
