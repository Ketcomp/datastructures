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

	// Remainder you get when a is divided by b (a%b). Assume that a is
	// non-negative, and b > 0.
	public MegaMath mod(MegaMath a, MegaMath b) {
		return null;
	}

	// Return the square root of a (truncated). Use binary search. Assume that a
	// is non-negative
	public MegaMath squareRoot(MegaMath a) {
		return null;
	}

	// Compares two MegaMath numbers and returns 1,0 or -1 appropriately.
	private static int compare(MegaMath a, MegaMath b) {
		int index = a.number.size();
		int loop = index; // Loop over 'index' number of times to cover 'a' fully
		int i = 1;
		// return -1 if a is smaller
		if(a.number.size() < b.number.size())
			return -1;
		// return 1 if a is larger
		else if(a.number.size() > b.number.size())
			return 1;
		else 
		// This means a and b are the same size. So check digit by digit from MSD.
			while(loop > 0){
				int condition = a.number.get(index - i) 
								- b.number.get(index -i);
				i++;
				loop--;
				if(condition == 0) continue;// In-decisive, need to check next digit
				return condition;
			}
		return 0; // All digits were same, they are equal!
	}

	public static void main(String[] args) {
		if (args.length > 0) {
			base = Integer.parseInt(args[0]);
		}
		String a = "12366464";
		String b = "68864";
		long c = 131314641314656316L;

		MegaMath x = new MegaMath(a);
		MegaMath y = new MegaMath(b);
		MegaMath z = new MegaMath(c);

//		// Test Addition
//		MegaMath addition = add(x,y);
//		System.out.println(addition.toString());

		//Test Subtraction
//		MegaMath subtraction = subtract(y, x);
//		System.out.println(subtraction.toString());
		MegaMath subtraction2 = subtract(x,y);
		System.out.println(subtraction2.toString());

//		// Test printList.
//		x.printList();

//		// Test compare
//		System.out.println("Comparison result is " + compare(y, x));

	}
}