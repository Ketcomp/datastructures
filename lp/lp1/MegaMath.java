import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 @author Group 10
 */

public class MegaMath {
	// assume default base is 10
	static int base = 10;
	List<Integer> number = new ArrayList<Integer>();

	// What does this constructor do?
	MegaMath() {

	}

	// Constructor for MegaMath class; takes a string s as parameter, that
	// stores a number in decimal,
	// and creates the MegaMath object representing that number.
	// The string can have arbitrary length.
	MegaMath(String num) {
		for (int i = num.length() - 1; i >= 0; i--) {
			number.add(Integer.parseInt(String.valueOf(num.charAt(i))));
		}
	}

	MegaMath(Long num) {
		this(num.toString());
	}

	// Convert the MegaMath class object into its equivalent string (in
	// decimal).
	// There are no leading zeroes in the string.
	@Override
	public String toString() {
		// convert this to decimal
		String s = "";
		Iterator<Integer> itr = this.number.iterator();
		while (itr.hasNext()) {
			s = s + itr.next().toString();
		}
		// return the number in human readable order
		return new StringBuilder(s).reverse().toString();
	}

	// Sum of two numbers stored as MegaMath.
	public static MegaMath add(MegaMath a, MegaMath b) {
		List<Integer> number = new ArrayList<>();
		List<Integer> num1 = a.number;
		List<Integer> num2 = b.number;
		Iterator<Integer> itr1 = num1.iterator();
		Iterator<Integer> itr2 = num2.iterator();
		int carry = 0;
		// Iterate through both numbers and add them digit by digit.
		while (itr1.hasNext() && itr2.hasNext()) {
			int sum = 0;
			int temp = (int) itr1.next() + (int) itr2.next();
			sum = temp + carry;
			number.add(sum % base);
			carry = sum / base;
		}
		// At this point either iter1 or iter2 has run out of elements
		while (itr1.hasNext()) {
			int sum = 0;
			int temp = (int) itr1.next();
			sum = temp + carry;
			number.add(sum % base);
			carry = sum / base;
		}
		while (itr2.hasNext()) {
			int sum = 0;
			int temp = (int) itr2.next();
			sum = temp + carry;
			number.add(sum % base);
			carry = sum / base;
		}
		MegaMath additionResult = new MegaMath();
		additionResult.number = number;
		return additionResult;
	}

	// Difference of two MegaMath numbers returned if positive.
	public static MegaMath subtract(MegaMath a, MegaMath b) {
		List<Integer> number = new ArrayList<>();
		List<Integer> num1 = a.number;
		List<Integer> num2 = b.number;
		Iterator<Integer> itr1 = num1.iterator();
		Iterator<Integer> itr2 = num2.iterator();
		MegaMath result = new MegaMath();
		int borrow = 0;
		int carry = 0;
		
		// if a < b return 0
		if( compare(a,b)<0 ) return new MegaMath("0");
		
		// else subtract
		// Iterate through both numbers and subtract them digit by digit.
		while (itr1.hasNext() && itr2.hasNext()) {
			int one = (int) itr1.next();
			int two = (int) itr2.next();
			int temp= 0;
			if( one-borrow-two < 0 ){
				temp = one+10+carry - borrow  - two;
				borrow = 1;
			}
			else {
				temp = one+carry - borrow - two;
				borrow = 0;
			}
			number.add(temp % base);
			carry = temp / base;
		}
		// At this point, iter2 has run out of elements
		while (itr1.hasNext()) {
			int temp = (int) itr1.next()+carry - borrow;
			if (temp == 0) break; // Prevents leading zeros from appearing.
			number.add(temp % base);
			carry = temp / base;
		}
		result.number = number;
		return result;
	}

	// Product of two numbers.
	public MegaMath product(MegaMath a, MegaMath b) {
		return null;
	}

	// Number 'a' raised to power 'n'
	public MegaMath power(MegaMath a, long n) {
		return null;
	}

	// Print the base + ":" + elements of the list, separated by spaces.
	public void printList() {
		Iterator<Integer> iter = this.number.iterator();
		System.out.print(base + " : ");
		while (iter.hasNext()) {
			// Number is printed in reverse - LSD first.
			System.out.print((Integer) iter.next() + " ");
		}
	}

	// level 2 started
	// 'a' and 'n' are both MegaMath. Here 'a' may be negative, but assume that
	// 'n' is
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