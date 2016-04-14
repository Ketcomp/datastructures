import java.util.Scanner;

class PerCom{
	static class permuObject{
		boolean selected = true;
		int value = 0;
	}
	static boolean[] combiArr;
	static permuObject[] permuArr;
	
	/*
	 * Visit every combination of k objects out of n
	 */
	public static void combination(int n, int k){
		if (k > n){
			return;
		}
		else if(k == 0){
			visit();
			System.out.println();
		}
		else{ // Choose A[n]?
			// Case1 : n is not selected.
			combination(n-1, k);
			// Case2 : n is selected.
			combiArr[n-1] = true;
			combination(n-1, k-1);
			combiArr[n-1] = false;
		}
	}
	
	/*
	 * Output all n! permutations of A[1...n]
	 */
	public static void permute(int i){
		if(i == 0){
			visit(permuArr);
			System.out.println();
		}
		else{
			for(int j = 0; j< i; j++){
				swap(i-1,j);
				permute(i-1);
				swap(i-1,j); // Clean-up	
			}
		}
	}
	
	/*
	 * Output all n! permutations of A[1...n]
	 * Use swap just once.
	 */
	public static void heaps(int n){
		if(1 == n){
			visit(permuArr);
			System.out.println();
		}
		else{
			for(int i = 0; i<n; i++){
				heaps(n-1);
				if(1 == n%2){
					swap(0, n-1);
				}
				else{
					swap(i, n-1);
				}
			}
			heaps(n-1);
		}
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		int k = in.nextInt();
		combiArr = new boolean[size];
		for(int i = 0; i<size; i++){
			combiArr[i] = false;
		}
		permuArr = new permuObject[size];
		for(int i = 0; i<size; i++){
			permuArr[i] = new permuObject();
			permuArr[i].value = i+1;
		}
		System.out.println("Combinations");
		combination(size, k);
		System.out.println("Permutations");
		permute(k);
		System.out.println("Heap's");
		heaps(k);
	}
	
	/*
	 * Swap elements at i and j in permuArr
	 */
	public static void swap(int i, int j){
		int temp;
		temp = permuArr[i].value;
		permuArr[i].value = permuArr[j].value;
		permuArr[j].value = temp;
	}

	/*
	 * Visit every 'True' element of permu and print it.
	 */
	public static void visit(permuObject[] permu){
		int size = permu.length;
		for(int i = 0; i<size; i++){
			if(true == permu[i].selected){
				System.out.print(permu[i].value);
			}
		}
	}
	
	/*
	 * Visits all 'True' elements of combiArr and prints them out.
	 */
	public static void visit(){
		int size = combiArr.length;
		for(int i = 0; i<size; i++){
			if(true == combiArr[i]){
				System.out.print(i+1);
			}
		}
	}

}