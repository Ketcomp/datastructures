import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author vmungara 
 * 
 * Using List as Numbers with base b to do add and subract
 */
public class ListAddSubtract {

	public static Integer next(Iterator<Integer> itr) {
		if (itr.hasNext()) {
			return itr.next();
		}

		return null;
	}

	public static void add(List<Integer> x, List<Integer> y, List<Integer> z, int b) {
		// Return z = x + y. Numbers are stored using base b.
		// The "digits" are stored in the list with the least
		// significant digit first. For example, if b = 10, then
		// the number 709 will be stored as 9 -> 0 -> 7.
		// Assume that b is small enough that you will not get any
		// overflow of numbers during the operation.
		Iterator<Integer> itr1 = x.iterator();
		Iterator<Integer> itr2 = y.iterator();
		int pass = 0;
		Integer p = next(itr1), q = next(itr2);
		// invariant : sum is total sum with pass
		// sum is b*(sum/b) + sum % b 
		while (p != null && q != null) {
			int sum = p + q + pass;
			z.add(sum % b);
			pass = sum / b;
			p = next(itr1);
			q = next(itr2);
		}

		while (p != null) {
			int sum = p + pass;
			z.add(sum % b);
			pass = sum / b;
			p = next(itr1);
		}

		while (q != null) {
			int sum = q + pass;
			z.add(sum % b);
			pass = sum / b;
			q = next(itr1);
		}

		if (pass > 0) {
			z.add(0, pass);
		}
	}

	public static void print(List<Integer> list) {
		for (int i : list) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void subtract(List<Integer> x, List<Integer> y, List<Integer> z, int b) {
		// Return z = x - y. Numbers are stored using base b.
		// Assume that x >= y, if now we can check this before calling subtract
		Iterator<Integer> itr1 = x.iterator();
		Iterator<Integer> itr2 = y.iterator();
		int borrow = 0;
		int difference = 0;
		Integer p = next(itr1), q = next(itr2);
		// invariant : difference is p - q - borrow
		while (p != null && q != null) {
			difference = p - borrow - q;
			borrow = difference >= 0 ? 0 : 1;
			difference = difference >= 0 ? difference : b + difference;
			z.add(difference);
			p = next(itr1);
			q = next(itr2);
		}

		while (p != null) {
			difference = p - borrow;
			borrow = difference > 0 ? 0 : 1;
			difference = difference > 0 ? difference : b + difference;
			z.add(difference);
			p = next(itr1);
		}

	}

	public static void main(String[] args) {
		// length of the array, for the purpose of testing
		int n = 10;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		
		int b = 10;
		if (args.length > 1) {
			b = Integer.parseInt(args[1]);
		}
		
		List<Integer> x = new ArrayList<>();
		List<Integer> y = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			x.add(i, (int) (Math.random() * (b - 1)));
		}
		for (int i = 0; i < n - 3; i++) {
			y.add(i, (int) (Math.random() * (b - 1)));
		}
		print(x);
		print(y);
		List<Integer> addResult = new ArrayList<>();
		List<Integer> subtractResult = new ArrayList<>();
		add(x, y, addResult, b);
		System.out.println("sum output: ");
		print(addResult);
		
		subtract(x, y, subtractResult, b);
		System.out.println("subtract output: ");
		print(subtractResult);
	}
}
