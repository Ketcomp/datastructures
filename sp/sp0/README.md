
CS 6301.002.  Implementation of advanced data structures and algorithms
Short Project 0
Wed, Jan 13, 2016


Due: 1:00 PM, Thu, Jan 21.

This is an OPTIONAL project.  Since groups have not been formed yet,
you can do this individually.

1. Implement Merge Sort (say, from Cormen's book) in Java using generics.
   See http://www.utdallas.edu/~rbk/teach/2016s/notes/mergesort.pdf for pseudocode of merge sort.
   Compare its running time on n > 1 million elements with another
   O(nlogn) algorithm, say for example, sorting using a priority queue:

   Create a priority queue from an array list (which is a collection)
   containing the input elements using the constructor in the Java Library. 
   From https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html: 

   PriorityQueue(Collection<? extends E> c)
   Creates a PriorityQueue containing the elements in the specified collection.

   Remove the elements from the Priority Queue until it gets empty.

