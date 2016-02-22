CS 6301.002.  Implementation of advanced data structures and algorithms
Spring 2016
Short Project 0pq (Priority Queues)
Sun, Feb 14, 2016

Group 10

1. Implemented BinaryHeap
2. Implemented IndexedHeap and Prim's MST algorithm (MST.java)
3. Compared the running times of Prim1 (priority queue of edges)
   and Prim2 (indexed priority queue of vertices).


submission details:

1. Testing BinaryHeap: 
	run : javac BinaryHeap.java
	      java BinaryHeap 

2. Testing IndexedHeap 
	run : javac IndexedHeap.java
	      java IndexedHeap

3. Testing Prim's algorithms 
	run : javac MST.java
	      java MST <input_file>


Report: 
-> Implemented binary heap as discussed in class. 
-> For Index heap, instead of declaring indexes in BinaryHeap class, we have overridden percolateUp and percolateDown methods to set index to vertex/ item when ever it is shifted in the heap. 
-> MST java has PrimMST1 and PrimMST2 methods with implementaion 1 and implementaion 2 as discussed in class. 

Observation: while running both Prims with multiple test inputs, we noticed that Prims1 is slower than Prims2 by around 4.3 sec

console output:
Running Prims 1
	mst weight: 9999
	time = 5139 ms
Running Prims 2
	mst weight: 9999
	time = 704 ms
