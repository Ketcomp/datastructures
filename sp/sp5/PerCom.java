import java.util.Scanner;
/*
* @author: Gaurav
* Team: G10
* Gaurav Ketkar
* Madhuri Abnave
* Vijay Mungara
* Malav Shah
*/

class PerCom {
	static boolean[] combiArr;
	static int[] permuArr;
	static int permuCount = 0;
	static int combiCount = 0;

	/*
	 * Visit every combination of k objects out of n
	 */
	public static void combination(int n, int k) {
		if (k > n) {
			return;
		} else if (k == 0) {
			visit();
		} else { // Choose A[n]?
					// Case1 : n is not selected.
			combination(n - 1, k);
			// Case2 : n is selected.
			combiArr[n - 1] = true;
			combination(n - 1, k - 1);
			combiArr[n - 1] = false;
		}
	}

	/*
	 * Output all n! permutations of A[1...n]
	 */
	public static void permute(int i) {
		if (i == 0) {
			visit(permuArr);
		} else {
			for (int j = 0; j < i; j++) {
				swap(i - 1, j);
				permute(i - 1);
				swap(i - 1, j); // Clean-up
			}
		}
	}

	/*
	 * Output all n! permutations of A[1...n] Use swap just once.
	 */
	public static void heaps(int n) {
		if (1 == n) {
			visit(permuArr);
		} else {
			for (int i = 0; i < n-1; i++) {
				heaps(n - 1);
				if (1 == n % 2) {
					swap(0, n - 1);
				} else {
					swap(i, n - 1);
				}
			}
			heaps(n - 1);
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		int k = in.nextInt();
		combiArr = new boolean[size];
		for (int i = 0; i < size; i++) {
			combiArr[i] = false;
		}
		permuArr = new int[size];
		for (int i = 0; i < size; i++) {
			permuArr[i] = i + 1;
		}
		System.out.println("Combinations");
		Timer time = new Timer();
		time.start();
		combination(size, k);
		System.out.println("# of combinations: " + combiCount);
		time.end();
		combiCount = 0;
		System.out.println(time.toString());

		System.out.println();
		System.out.println("Permutations");
		time.start();
		permute(k);
		System.out.print("# of permutations: " + permuCount);
		time.end();
		System.out.println();
		permuCount = 0;
		System.out.println("Take 2 took: " + time.toString());

		System.out.println();
		System.out.println("Heap's");
		time.start();
		heaps(k);
		time.end();
		System.out.print("# of Heap's permutations: " + permuCount);
		System.out.println();
		permuCount = 0;
		System.out.println("Heap took: " + time.toString());
	}

	/*
	 * Swap elements at i and j in permuArr
	 */
	public static void swap(int i, int j) {
		int temp;
		temp = permuArr[i];
		permuArr[i] = permuArr[j];
		permuArr[j] = temp;
	}

	/*
	 * Visit every of permu and do whatever you want..
	 */
	public static void visit(int[] permu) {
		int size = permu.length;
		permuCount++;
		for (int i = 0; i < size; i++) {
			// Do necessary work here
			int maxWork = Integer.MAX_VALUE;
		}
	}

	/*
	 * Visits all 'True' elements of combiArr and do whatever you want.
	 */
	public static void visit() {
		int size = combiArr.length;
		combiCount++;
		for (int i = 0; i < size; i++) {
			if (true == combiArr[i]) {
				// Do necessary work here
				int maxWork = Integer.MAX_VALUE;
			}
		}
	}
}