
#####CS 6301.002.  Implementation of advanced data structures and algorithms. Spring 2016
#####Short Project 1 | Wed, Jan 20, 2016 | Due: 1:00 PM, Thu, Jan 28.
#####Topic: Lists, queues, stacks.
---
a. Given two linked lists implementing sorted sets, write functions for
   union, intersection, and set difference of the sets.

    public static<T extends Comparable<? super T>>
        void intersect(List<T> l1, List<T> l2, List<T> outList) {
sp   // Return elements common to l1 and l2, in sorted order.
sp   // outList is an empty list created by the calling
           // program and passed as a parameter.
sp   // Function should be efficient whether the List is
sp   // implemented using ArrayList or LinkedList.
sp   // Do not use HashSet/Map or TreeSet/Map or other complex
           // data structures.
sp}

    public static<T extends Comparable<? super T>>
        void union(List<T> l1, List<T> l2, List<T> outList) {
sp   // Return the union of l1 and l2, in sorted order.
sp   // Output is a set, so it should have no duplicates.
sp}

    public static<T extends Comparable<? super T>>
        void difference(List<T> l1, List<T> l2, List<T> outList) {
sp   // Return l1 - l2 (i.e, items in l1 that are not in l2), in sorted order.
sp   // Output is a set, so it should have no duplicates.
sp}


b. Suppose large numbers are stored in a list of integers.  Write
   functions for adding and subtracting large numbers.

   public static void add(List<Integer> x, List<Integer>  y,List<Integer> z, int b) {
   sp  // Return z = x + y.  Numbers are stored using base b.
sp  // The digits are stored in the list with the least
   sp  // significant digit first.  For example, if b = 10, then
sp  // the number 709 will be stored as 9 -> 0 -> 7.
sp  // Assume that b is small enough that you will not get any
sp  // overflow of numbers during the operation.
   }

   public static void subtract(List<Integer> x, List<Integer>  y,List<Integer> z, int b) {
   sp  // Return z = x - y.  Numbers are stored using base b.
sp  // Assume that x >= y.
   }


c. Write the Merge sort algorithm that works on linked lists.  This
   will be a member function of a linked list class, so that it can
   work with the internal details of the class.  The function should
   use only O(log n) extra space (mainly for recursion), and not make
   copies of elements unnecessarily.  You can start from the
   SinglyLinkedList class provided or create your own.

   static<T extends Comparable<? super T>> void mergeSort(SortableList<T> lst) { ... }

   Download a skeleton of SortableList.java from the class page.
   http://www.utdallas.edu/~rbk/teach/2016s/java/code/SortableList.java

d. Implement a recursive algorithm without recursion, by using a
   stack to simulate recursion.  You may work on any recursive
   algorithm that has multiple recursive calls such as Merge Sort,
   Binary tree traversals, Quick sort, or, Linear-time median.

e. Extend the unzip algorithm discussed in class on Thu, Jan 21 to
   multiUnzip on the SinglyLinkedList class:

   void multiUnzip(int k) {
   sp// Rearrange elements of a singly linked list by chaining
   sp// together elements that are k apart.  k=2 is the unzip
   sp// function discussed in class.  If the list has elements
sp// 1..10 in order, after multiUnzip(3), the elements will be
   sp// rearranged as: 1 4 7 10 2 5 8 3 6 9.  Instead if we call
sp// multiUnzip(4), the list 1..10 will become 1 5 9 2 6 10 3 7 4 8.
   }

f. Write recursive and nonrecursive functions for the following tasks:
   (i) reverse the order of elements of the SinglyLinkedList class
   (ii) print the elements of the SinglyLinkedList class, in reverse order.
   Write the code and annotate it with  proper loop invariants.
