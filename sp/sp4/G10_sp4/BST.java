/** @author rbk
 *  Binary search tree (nonrecursive version)
 **/

/*
* @author:
* Team: G10
* Gaurav Ketkar
* Madhuri Abnave
* Vijay Mungara
* Malav Shah
*/

import java.util.*;

public class BST<T extends Comparable<? super T>> {
    class Entry<T> {
        T element;
        Entry<T> left, right, parent;

        Entry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
            element = x;
	    left = l;
	    right = r;
	    parent = p;
        }
    }
    
    Entry<T> root;
    int size;

    BST() {
	root = null;
	size = 0;
    }

    // Constructor for a balanced BST
    BST(T[] arr) {
    	
    }
    // Find x in subtree rooted at node t.  Returns node where search ends.
    Entry<T> find(Entry<T> t, T x) {
	Entry<T> pre = t;
	while(t != null) {
	    pre = t;
	    int cmp = x.compareTo(t.element);
	    if(cmp == 0) {
		return t;
	    } else if(cmp < 0) {
		t = t.left;
	    } else {
		t = t.right;
	    }
	}
	return pre;
    }

    // Is x contained in tree?
    public boolean contains(T x) {
	Entry<T> node = find(root, x);
	return node == null ? false : x.equals(node.element);
    }

    // Add x to tree.  If tree contains a node with same key, replace element by x.
    // Returns true if x is a new element added to tree.
    public boolean add(T x) {
	if(size == 0) {
	    root = new Entry<>(x, null, null, null);
	} else {
	    Entry<T> node = find(root, x);
	    int cmp = x.compareTo(node.element);
	    if(cmp == 0) {
		node.element = x;
		return false;
	    }
	    Entry<T> newNode = new Entry<>(x, null, null, node);
	    if(cmp < 0) {
		node.left = newNode;
	    } else {
		node.right = newNode;
	    }
	}
	size++;
	return true;
    }

    // Remove x from tree.  Return x if found, otherwise return null
    public T remove(T x) {
	T rv = null;
	if(size > 0) {
	    Entry<T> node = find(root, x);
	    if(x.equals(node.element)) {
		rv = node.element;
		remove(node);
		size--;
	    }
	}
	return rv;
    }

    // Called when node has at most one child.  Returns that child.
    Entry<T> oneChild(Entry<T> node) {
	return node.left == null? node.right : node.left;
    }
    
    // Remove a node from tree
    void remove(Entry<T> node) {
	if(node.left != null && node.right != null) {
	    removeTwo(node);
	} else {
	    removeOne(node);
	}
    }

    // remove node that has at most one child
    void removeOne(Entry<T> node) {
	if(node == root) {
	    root = oneChild(root);
	} else {
	    Entry<T> p = node.parent;
	    if(p.left == node) {
		p.left = oneChild(node);
	    } else {
		p.right = oneChild(node);
	    }
	}
    }
    
    static int rightOrLeft = 0;
    // remove node that has two children, 
    // alternately from either right or left depending on int rightOrLeft
    void removeTwo(Entry<T> node) {
	if (0 == rightOrLeft){
    	Entry<T> minRight = node.right;
    	while(minRight.left != null) {
    		minRight = minRight.left;
    	}
    	node.element = minRight.element;
    	removeOne(minRight);
    	rightOrLeft = 1;
	}
	else if(1 == rightOrLeft)
	{
		Entry<T> maxLeft = node.left;
    	while(maxLeft.right != null) {
    		maxLeft = maxLeft.right;
    	}
    	node.element = maxLeft.element;
    	removeOne(maxLeft);
    	rightOrLeft = 1;
	}
    }

    public static void main(String[] args) {
	BST<Integer> t = new BST<>();
	Scanner in = new Scanner(System.in);
	while(in.hasNext()) {
	    int x = in.nextInt();
	    if(x > 0) {
		System.out.print("Add " + x + " : ");
		t.add(x);
		t.printTree();
	    } else if(x < 0) {
		System.out.print("Remove " + x + " : ");
		t.remove(-x);
		t.printTree();
	    } else {
	    Comparable[] arr = t.levelOrderTraversal();
		System.out.print("Final: ");
		for(int i=0; i<t.size; i++) {
		    System.out.print(arr[i] + " ");
		}
		System.out.println();
		return;
	    }		
	    
	}
    }

    // Return an array with the elements using a level order traversal of the tree
    public Comparable[] levelOrderTraversal(){
    	Comparable[] result = new Comparable[size];
    	Queue<Entry> nodeQ = new LinkedList<Entry>();
    	nodeQ.add(root);
    	int i = 0;
    	Entry<T> removedNode;
    	while(!nodeQ.isEmpty()){
    		removedNode = nodeQ.remove();
    		if(removedNode.left != null) nodeQ.add(removedNode.left);
    		if(removedNode.right != null) nodeQ.add(removedNode.right);
    		result[i++] = (Comparable) removedNode.element;
    	}
    	return result;
    }
    
    // Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
	Comparable[] arr = new Comparable[size];
	inOrder(root, arr, 0);
	return arr;
    }

    // Recursive in-order traversal of tree rooted at "node".
    // "index" is next element of array to be written.
    // Returns index of next entry of arr to be written.
    int inOrder(Entry<T> node, Comparable[] arr, int index) {
	if(node != null) {
	    index = inOrder(node.left, arr, index);
	    arr[index++] = node.element;
	    index = inOrder(node.right, arr, index);
	}
	return index;
    }

    public void printTree() {
	System.out.print("[" + size + "]");
	printTree(root);
	System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
	if(node != null) {
	    printTree(node.left);
	    System.out.print(" " + node.element);
	    printTree(node.right);
	}
    }
}
/*
Sample input:
1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0

Output:
Add 1 : [1] 1
Add 3 : [2] 1 3
Add 5 : [3] 1 3 5
Add 7 : [4] 1 3 5 7
Add 9 : [5] 1 3 5 7 9
Add 2 : [6] 1 2 3 5 7 9
Add 4 : [7] 1 2 3 4 5 7 9
Add 6 : [8] 1 2 3 4 5 6 7 9
Add 8 : [9] 1 2 3 4 5 6 7 8 9
Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
Remove -3 : [9] 1 2 4 5 6 7 8 9 10
Remove -6 : [8] 1 2 4 5 7 8 9 10
Remove -3 : [8] 1 2 4 5 7 8 9 10
Final: 1 2 4 5 7 8 9 10 

*/
