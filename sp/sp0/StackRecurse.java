/*
 * Authored by G10
 * 
 * A program to perform pre-order binary tree traversal without using recursion.
 * A stack is used instead to perform the same operation.
 * Structure of tree is
 *             1
 *       --------------
 *       2            5
 *    ------       -------
 *    3    4       6     7
 */
import java.util.Deque;
import java.util.LinkedList;

public class StackRecurse{
	static void noRecurseTraversal(BinTree x){
		//Add first element to the stack
		Deque<BinTree> noRecurseStack = new LinkedList<BinTree>();
		noRecurseStack.push(x);
		
		while(!noRecurseStack.isEmpty()){
			BinTree headOfStack;
			headOfStack = noRecurseStack.getFirst();
			if(headOfStack.readyToPop){
				noRecurseStack.pop();
			}
			else{
				System.out.println(noRecurseStack.getFirst().toString());
				noRecurseStack.getFirst().readyToPop = true;
				if(headOfStack.rightChild != null) {noRecurseStack.push(headOfStack.rightChild);}
				if(headOfStack.leftChild != null) {noRecurseStack.push(headOfStack.leftChild);}
			}
		}
	}
	
	public static void main(String[] args){
		BinTree a = new BinTree(1);
		BinTree b = new BinTree(2);
		BinTree c = new BinTree(3);
		BinTree d = new BinTree(4);
		BinTree e = new BinTree(5);
		BinTree f = new BinTree(6);
		BinTree g = new BinTree(7);
		
		a.setLeftChild(b);
		a.setRightChild(e);
		b.setLeftChild(c);
		b.setRightChild(d);
		e.setLeftChild(f);
		e.setRightChild(g);
		
		noRecurseTraversal(a);
	}//Main ends
}//Class ends