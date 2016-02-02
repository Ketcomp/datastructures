import java.util.Scanner;

public class TopoPrint<T>{
	

	//List<Vertex> toplogicalOrder1(Graph g) { 
      /* Algorithm 1. Remove vertices with no incoming edges, one at a
       time, along with their incident edges, and add them to a list.
       */
   //}



//Stack<Vertex> toplogicalOrder2(Graph g) {
      /* Algorithm 2. Run DFS on g and push nodes to a stack in the
       order in which they finish.  Write code without using global variables.
       */
  // }


	public static void main(String[] args){
		Graph myGraph;
		Scanner in = new Scanner(System.in);
		boolean isDirected = true;
		if(args.length!=0){
			if(args[2]=="false") isDirected = false;
		}
		myGraph.readGraph(in, isDirected);
	}
}//Class ends