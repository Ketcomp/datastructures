/*
 * Authored by G10
 * 
 * Defines the binary tree needed for the program StackRecurse.java 
 */
public class BinTree
{
	//Value of a node, its left and right children and its state - whether or not it is ready to be popped
	int nodeValue;
	BinTree leftChild;
	BinTree rightChild;
	boolean readyToPop;
	
	//Constructor for BinTree
	BinTree(int value){
		nodeValue = value;
		leftChild = null;
		rightChild = null;
		readyToPop = false;
	}
	//Get Values
	BinTree getLeftChild(){
		return leftChild;
	}
	BinTree getRightChild(){
		return rightChild;
	}
	int getValue(){
		return nodeValue;
	}
	boolean getStatus(){
		return readyToPop;
	}
	
	//Set values
	void setLeftChild(BinTree left){
		leftChild = left;
	}
	void setRightChild(BinTree right){
		rightChild = right;
	}
	void setPopStatus(boolean x){
		readyToPop = x;
	}
	public String toString(){
		return "Node value: "+nodeValue;
	}
	
}//Class ends