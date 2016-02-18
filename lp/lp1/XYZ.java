/**
 * 
 */


import java.util.ArrayList;
import java.util.List;

/*
 @author Madhuri
 */
public class XYZ {
	List<Integer> number = new ArrayList<>();

	XYZ(String num) {

		for (int i = num.length() - 1; i >= 0; i--) {
			int temp = Integer.parseInt(String.valueOf(num.charAt(i)));
			number.add(new Integer(temp));
		}

	}

	XYZ(long num) {

		int remainder = 0;
		while (num > 0) {
			remainder = (int) (num % 10);
			num = num / 10;
			number.add(new Integer(remainder));
		}

	}

	@Override
	public String toString() {
		// convert this to decimal
		return null;
	}

	public XYZ add(XYZ a, XYZ b) {
		return null;
	}

	public XYZ subtract(XYZ a, XYZ b) {

		// if a < b
		return new XYZ(0);
	}

	public XYZ product(XYZ a, XYZ b) {
		return null;
	}

	public XYZ power(XYZ a, long n) {
		return null;
	}

	public void printList() {
		// print base : + list elements in lsd to msd order
	}

	// level 2 started
	// a and n are both XYZ. Here a may be negative, but assume that n is
	// non-negative.
	public XYZ power(XYZ a, XYZ n) {
		return null;
	}

	// Fractional part is discarded (take just the quotient). Both a and b may
	// be positive or negative. If b is 0, raise an exception.
	public XYZ divide(XYZ a, XYZ b) {
		return null;
	}

	private XYZ divideByTwo(XYZ a) {
		return null;
	}

	// remainder you get when a is divided by b (a%b). Assume that a is
	// non-negative, and b > 0.
	public XYZ mod(XYZ a, XYZ b) {
		return null;
	}

	// return the square root of a (truncated). Use binary search. Assume that a
	// is non-negative
	public XYZ squareRoot(XYZ a) {
		return null;
	}

	private int compare(XYZ a, XYZ b) {
		return 0;
	}

	public static void main(String[] args) {

		String a = "1236646494148144";
		String b = "1246464484648478";
		long c = 131314641314656316L;
		long d = 466876164664L;
		XYZ num1 = new XYZ(a);
		System.out.println(num1.number);
		XYZ num2 = new XYZ(b);
		System.out.println(num2.number);
		XYZ num3 = new XYZ(c);
		System.out.println(num3.number);
		XYZ num4 = new XYZ(d);
		System.out.println(num4.number);

	}
}
