CS 6301.002.  Implementation of advanced data structures and algorithms
Spring 2016
Short Project 3
Tue, Feb 16, 2016

Due: 1:00 PM, Thu, Feb 25.

a. Fibonacci numbers - Fibonacci_n.java
Input: 500000000
Output:
        Time taken for linear fibo: 9 ms
        Linear Fibo answer: 184390626
         
        Time taken for log fibo: 1 ms
        Log Fibo answer: 184390626


b. Multipivot Quicksort
Single Pivot Quick Sort:
Before:  428 999 770 66 469 502 974 192 ..... 646 366 768 281 984
Time: 6 msec.
Memory: 1 MB / 82 MB.
After:  950 951 952 953 954 955 956 957 ..... 995 996 997 998 999

Dual Pivot Quick sort:

Before:  37 42 56 31 67 4 65 60 72 87 30 98 ..... 39 95 74 46 1
Time: 1 msec.
Memory: 1 MB / 82 MB.
After:  50 51 52 53 54 55 56 57 58 59 60 61...... 95 96 97 98 99

c. Merge sort vs Quick sort

Output: 
Before:  6015546 9877011 442952 6002286 2831848 5698576 .....
Quick sort on 10000000 elements takes: 5888 milli-seconds.
Memory: 200 MB / 268 MB.
After:  9999950 9999951 9999952 9999953 9999954 9999955 ......... 9999996 9999997 9999998 9999999

Before: 10000000 9999999 9999998 9999997 9999996 9999995 9999994 9999993 9999992 9999991 
Merge sort on 10000000 numbers takes: 2732 mili-seconds
After: 1 2 3 4 5 6 7 8 9 10 

d. Selection problem
Input: 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 K=4
Output: 5 6 7 8 9 10