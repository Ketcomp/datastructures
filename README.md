Practicing implementation of data structures and common algorithms - primarily in Java

This repo contains code in preparation of the course CS 6301.002 - Implementation of Algorithms and Data Structures taught by [Prof. Balaji Raghavchari](https://www.utdallas.edu/~rbk) in the Spring 2016.


### **Contents**

* [Course syllabus](https://www.utdallas.edu/~rbk/teach/2016s/syl-CS6301-2016s.pdf)
* [Short Project 0 (Jan 13th/21st Jan)](#shortproject0)


#### Short Project 0
Implement Merge Sort (say, from Cormen's book) in Java using generics.
* Compare its running time on n > 1 million elements with another O(nlogn) algorithm, say for example, sorting using a priority queue:
    * Create a priority queue from an array list (which is a collection) containing the input elements using the constructor in the Java Library.
    * From [official documents](https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html):
        * PriorityQueue(Collection<? extends E> c)
        Creates a PriorityQueue containing the elements in the specified collection.
    * Remove the elements from the Priority Queue until it gets empty.

