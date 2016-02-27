

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 @author Madhuri
 */
public class MegaMath {
	
	final int base=10;
	List<Integer> number = new ArrayList<Integer>();
	
	MegaMath()
	{
		
	}
	
	MegaMath(String num) {

		for (int i = num.length() - 1; i >= 0; i--) {
			int temp = Integer.parseInt(String.valueOf(num.charAt(i)));
			number.add(new Integer(temp));
		}

	}

	MegaMath(Long num) {

		this(num.toString());

	}

	@Override
	public String toString() {
		// convert this to decimal
		String s = "";
		Iterator itr = this.number.iterator();
		while (itr.hasNext()) {
			s = s + itr.next().toString();
		}
		return s;
	}

	public static MegaMath add(MegaMath a, MegaMath b) {
		List<Integer> number = new ArrayList<>();
		List<Integer> num1 = a.number;
		List<Integer> num2 = b.number;
		Iterator itr1 = num1.iterator();
		Iterator itr2 = num2.iterator();

		int carry = 0;

		while (itr1.hasNext() && itr2.hasNext()) {
			int sum = 0;
			int temp = (Integer) itr1.next() + (Integer) itr2.next();
			sum = temp + carry;
			number.add(sum % 10);
			carry = sum / 10;
		}

		while (itr1.hasNext()) {
			int sum = 0;
			int temp = (Integer) itr1.next();
			sum = temp + carry;
			number.add(sum % 10);
			carry = sum / 10;
		}
		while (itr2.hasNext()) {
			int sum = 0;
			int temp = (Integer) itr2.next();
			sum = temp + carry;
			number.add(sum % 10);
			carry = sum / 10;
		}
		MegaMath add = new MegaMath();
		add.number = number;

		return add;
	}

	public MegaMath subtract(MegaMath a, MegaMath b) {

		// if a < b
		return new MegaMath();
	}

	public MegaMath product(MegaMath a, MegaMath b) {
		return null;
	}

	public MegaMath power(MegaMath a, long n) {
		return null;
	}

	public void printList() {
		
		Iterator iter= this.number.iterator();
		Integer digit;
		System.out.print(base+" : ");
		while(iter.hasNext())
		{
			digit= (Integer)iter.next();
			System.out.print(digit+" ");
		}
	}

	// level 2 started
	// a and n are both MegaMath. Here a may be negative, but assume that n is
	// non-negative.
	public MegaMath power(MegaMath a, MegaMath n) {
		return null;
	}

	// Fractional part is discarded (take just the quotient). Both a and b may
	// be positive or negative. If b is 0, raise an exception.
	public MegaMath divide(MegaMath a, MegaMath b) {
		return null;
	}

	private MegaMath divideByTwo(MegaMath a) {
		return null;
	}

	// remainder you get when a is divided by b (a%b). Assume that a is
	// non-negative, and b > 0.
	public MegaMath mod(MegaMath a, MegaMath b) {
		return null;
	}

	// return the square root of a (truncated). Use binary search. Assume that a
	// is non-negative
	public MegaMath squareRoot(MegaMath a) {
		return null;
	}

	private int compare(MegaMath a, MegaMath b) {
		return 0;
	}

	public static void main(String[] args) {

		String a = "12366464";
		/*String b = "1246464484664646";
		long c = 131314641314656316L;
		long d = 466876164664L;
		MegaMath num1 = new MegaMath(a);
		System.out.println(num1);
		MegaMath num2 = new MegaMath(b);
		System.out.println(num2);
		MegaMath num3 = new MegaMath(c);
		MegaMath num4 = new MegaMath(d);
		MegaMath add = add(num1, num2);
		System.out.println(add.toString());
		System.out.println(add.number);*/
		MegaMath x= new MegaMath(a);
		x.printList();
		
	}
}