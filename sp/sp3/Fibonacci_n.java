//Author: Malav Shah

public class Fibonacci_n {

	public static long linearFibonacci(long n, long p)
	{
		long first=0;
		long second=1;
		long answer=0;
		for(long i=2;i<=n;i++)
		{
			answer= (first)+(second);
			first=second;
			second=answer%p;
		}
		return (answer%p);
	}
	
	public static long logFibonacci(long n, long p)
	{
		long A[][]={
					{1,1},
					{1,0}
					};
		long answer[][];
		answer= power(A, n-1, p);
		return answer[0][0];	
	}
	
	public static long[][] power(long A[][],long n, long p)
	{
		if(n==0)
		{
			long identity[][]= {{1,1},{1,1}};
			return identity;
		}
		if(n==1)
			return A;
		
		long half[][]=power(A,n/2,p);
		long result[][];
		result= matrixMultiplication(half,half,p);
		if(n%2==0)
			return result;
		else
		{
			result= matrixMultiplication(result, A, p);
			return result;
		}
	}
	
	public static long[][] matrixMultiplication(long A[][],long B[][],long p)
	{
		long answer[][]= new long[2][2];
		for(int i=0;i<2;i++)
		{
			for(int j=0;j<2;j++)
			{
				for(int k=0;k<2;k++)
				{
					answer[i][j]=(answer[i][j]+(A[i][k]*B[k][j]))%p;
				}
			}
		}
		return answer;
	}
	
	public static void main(String args[])
	{	long n = 999999;
		long start = System.currentTimeMillis();
		long number = 500000000;
		long answer = linearFibonacci(n, number);
		System.out.println("Time taken for linear fibo: "+(System.currentTimeMillis() - start)+" ms");
		System.out.println("Linear Fibo answer: "+answer);
		
		System.out.println(" ");
		
		start = System.currentTimeMillis();
		answer = logFibonacci(n, number);
		System.out.println("Time taken for log fibo: "+(System.currentTimeMillis() - start)+" ms");
		System.out.println("Log Fibo answer: "+answer);
		
	}
}
