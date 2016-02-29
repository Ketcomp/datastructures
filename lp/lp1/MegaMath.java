import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/*
 @author Group 10
 */

public class MegaMath {
	// Default base is 10
	static final int base = 10;
	List<Integer> number = new ArrayList<Integer>();
	boolean isNegative = false; // Is the number negative?

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
		// convert 'this' to decimal
		String s = "";
		Iterator<Integer> itr = this.number.iterator();

		while (itr.hasNext()) {
			Integer value = itr.next();
			s = s + value.toString();
		}
		// e.g. when s is 05200, result becomes 00250
		String result = new StringBuilder(s).reverse().toString();
		// e.g. when result is 00250, tempo becomes 250
		Integer tempo = Integer.parseInt(result);
		result = tempo.toString();
		// return the number in human readable order
		return result;
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
		padding(a,b);
		List<Integer> number = new ArrayList<>();
		List<Integer> num1 = a.number;
		List<Integer> num2 = b.number;
		Iterator<Integer> itr1 = num1.iterator();
		Iterator<Integer> itr2 = num2.iterator();
		MegaMath result = new MegaMath();
		int borrow = 0;
		int carry = 0;

		// if a < b return subtract(b,a) and make isNegative true
		MegaMath negativeNum = new MegaMath();
		if (compare(a, b) < 0){
			negativeNum = subtract(b, a);
			negativeNum.isNegative = true;
			return negativeNum;
		}

		// else subtract
		// Iterate through both numbers and subtract them digit by digit.
		while (itr1.hasNext() && itr2.hasNext()) {
			int one = (int) itr1.next();
			int two = (int) itr2.next();
			int temp = 0;
			
			if (one - borrow - two < 0) { // Carry is necessary
				temp = one + 10 + carry - borrow - two;
				borrow = 1;
			} else {
				temp = one + carry - borrow - two;
				borrow = 0;
			}
			number.add(temp % base);
			carry = temp / base;
		}
		// At this point, iter2 has run out of elements
		while (itr1.hasNext()) {
			int temp = (int) itr1.next() + carry - borrow;
			if (temp == 0)
				break; // Prevents leading zeros from appearing.
			number.add(temp % base);
			carry = temp / base;
		}
		result.number = number;
		return result;
	}

	// Product of two numbers.

	// Pad the two numbers so that they both have equal number of digits.
	public static void padding(MegaMath a, MegaMath b) {
		int count = 0;
		if (a.number.size() < b.number.size()) {
			count = b.number.size() - a.number.size();
			while (count > 0) {
				a.number.add(0);
				count--;
			}
		} else {
			count = a.number.size() - b.number.size();
			while (count > 0) {
				b.number.add(0);
				count--;
			}
		}
	}

	public static MegaMath product(MegaMath a, MegaMath b) {
		MegaMath answer = new MegaMath();
		int n = a.number.size();

		if (a.number.size() != b.number.size()) {
			padding(a, b);
		}
		if (n == 1) {
			int product = (a.number.get(0) * b.number.get(0));
			while (product > 0) {
				answer.number.add((product % base));
				product = product / base;
			}
			return answer;
		}
		MegaMath a_first = new MegaMath();
		MegaMath b_first = new MegaMath();
		MegaMath a_second = new MegaMath();
		MegaMath b_second = new MegaMath();
		int i = 0;
		while (i < (n / 2)) {
			a_second.number.add(a.number.get(i));
			b_second.number.add(b.number.get(i));
			i++;
		}
		i = n / 2;
		while (i < n) {
			a_first.number.add(a.number.get(i));
			b_first.number.add(b.number.get(i));
			i++;
		}
		MegaMath answer1 = product(a_first, b_first);
		MegaMath answer2 = product(a_second, b_second);
		MegaMath sum1 = add(a_first, a_second);
		MegaMath sum2 = add(b_first, b_second);
		MegaMath prod1 = product(sum1, sum2);
		MegaMath sub1 = subtract(prod1, answer1);
		MegaMath sub2 = subtract(sub1, answer2);
		i = 0;
		while (i < n) {
			answer1.number.add(0, 0);
			i++;
		}
		i = 0;
		while (i < (n / 2)) {
			sub2.number.add(0, 0);
			i++;
		}
		MegaMath finalsum1 = add(answer1, sub2);
		MegaMath finalsum2 = add(finalsum1, answer2);
		return finalsum2;
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

	private static MegaMath divideByTwo(MegaMath a) {

		List<Integer> number = new ArrayList<>();
		List<Integer> num1 = a.number;

		Iterator<Integer> itr1 = num1.iterator();
		Stack<Integer> stck = new Stack<Integer>();

		int quotient = 0;
		int divident = 0;
		int carry = 0;
		// Iterate through numbers and push it in stack.
		while (itr1.hasNext()) {
			stck.push(itr1.next());
		}
		while (!stck.isEmpty()) {
			if (carry != 0) {
				divident = carry * 10 + stck.pop();
			} else {
				divident = stck.pop();
			}

			while (divident < 2 && !stck.isEmpty()) {

				divident = (divident * 10) + stck.pop();
			}
			quotient = divident / 2;
			number.add(quotient);
			carry = divident % 2;

		}
		/*if(carry!=0)
		{
			number.add(0);
		}*/
		MegaMath divideByTwo = new MegaMath();
		divideByTwo.number = number;
		return divideByTwo;

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
		int loop = index; // Loop over 'index' number of times to cover 'a'
							// fully
		int i = 1;
		// This means a and b are the same size. So check digit by digit
		// from MSD.
		while (loop > 0) {
			int condition = a.number.get(index - i) - b.number.get(index - i);
			i++;
			loop--;
			if (condition == 0)
				continue;// In-decisive, need to check next digit
			return condition;
		}
		return 0; // All digits were same, they are equal!
	}

	public static void main(String[] args) {

		String a = "20";
		String b = "000";
		long c = 131314641314656316L;

		MegaMath x = new MegaMath(a);
		MegaMath y = new MegaMath(b);
		MegaMath z = new MegaMath(c);

		// // Test Addition
//		MegaMath addition = add(x, y);
//		System.out.println(addition.toString());
//		addition.printList();

//		 // Test Subtraction
//		 MegaMath subtraction = subtract(x, y);
//		 System.out.println(subtraction.toString() + "\nNumber is negative? "+ subtraction.isNegative);
		// MegaMath subtraction2 = subtract(x,y);
		// System.out.println(subtraction2.toString());

		// // Test printList.
		// x.printList();

		// // Test compare
		// System.out.println("Comparison result is " + compare(y, x));

		// Test product
		// MegaMath p = product(x, y);
		// System.out.println(x.toString() + " multiplied by ");
		// System.out.println(y.toString() + " gives, ");
		// System.out.println(p.toString());
		
//		// Test Divide
//		MegaMath div = divideByTwo(x);
////		div.printList();
//		System.out.println(div.toString());
	}
}
