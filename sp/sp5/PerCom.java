import java.util.Scanner;

class PerCom{
	static boolean[] combiArr;
	static int[] permuArr;
	
	/*
	 * Visit every combination of k objects out of n
	 */
	public static void combination(int n, int k){
		if (k < n){
			return;
		}
		else if(k == 0){
			visitCombi();
		}
		else{ // Choose A[n]?
			// Case1 : n is not selected.
			combination(n-1, k);
			// Case2 : n is selected.
			combiArr[n] = true;
			combination(n-1, k-1);
			combiArr[n] = false;
		}
	}
	
	public static void permutation(int i){
		if(i == 0){
			visitPermu();
		}
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		combiArr = new boolean[size];
		for(int i = 0; i<size; i++){
			combiArr[i] = false;
		}
		
	}
	
	public static void swap(int i, int j){
		int temp;
		temp = permuArr[i];
		permuArr[i] = permuArr[j];
		permuArr[j] = temp;
	}
	
	public static void visitPermu(){
		int size = permuArr.length;
		for(int i =0; i<size; i++){
			if()
		}
	}
	
	/*
	 * Visits all elements of combiArr and prints them out.
	 */
	public static void visitCombi(){
		int size = combiArr.length;
		for(int i = 0; i<size; i++){
			if(true == combiArr[i]){
				System.out.println(combiArr[i]);
			}
		}
	}

}