import java.util.*;

class AVLTree extends BST{
	class AVLEntry<T> extends Entry<T> {
	int height;
	AVLEntry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
	    super(x,l,r,p);
	    height = 0;
	}
	int j = size;
    }
	
}