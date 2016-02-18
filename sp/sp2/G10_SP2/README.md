CS 6301.002.  Implementation of advanced data structures and algorithms
Spring 2016
Short Project 2
Wed, Jan 27, 2016

Ver 1.0: Initial description (Jan 27, 9:00 AM).

Due: 1:00 PM, Thu, Feb 4.

Topic: Graphs

Problems and their .java files
------------------------------
a. Topological ordering of a DAG. - TopoPrint.java
b. Diameter of a tree. - TreeDiameter.java
c. Strongly connected components of a directed graph. - StronglyConnectedComponents.java
d. Finding an odd-length cycle in a non-bipartite graph. - OddLengthCycle.java 
e. Is a given graph Eulerian? - TestEulerian.java


a. Topological ordering of a DAG. - TopoPrint.java
To Run: No special requirements
Test input:
7 10
1 2 0
1 4 0
1 7 0
2 3 0
2 4 0
3 4 0
4 7 0
5 4 0
6 5 0
6 7 0

b. Diameter of a tree. - TreeDiameter.java
To Run: No special requirements

c. Strongly connected components of a directed graph. - StronglyConnectedComponents.java 
To Run: Vertex.java was updated to include the following:
public int component;	

	/**
	 * @return the component number
	 */
	public int getComponent() {
		return component;
	}

	/**
	 * @param component the component number to set to the vertex
	 */
	public void setComponent(int component) {
		this.component = component;
	}
Test input:
5 7
1 2 0
2 3 0
3 1 0
2 4 0
3 4 0
3 5 0
5 4 0

d. Finding an odd-length cycle in a non-bipartite graph. - OddLengthCycle.java
To Run: No special requirements
Test input:
Bipartite Graph
5 4
1 2 1
2 3 1
3 4 1
4 5 1
Non Bipartite Graphs
5 5
1 2 1
2 3 1
3 4 1
3 5 1
4 5 1

5 5
1 2 1
2 3 1
3 5 1
4 5 1
1 4 1

e. Is a given graph Eulerian? - TestEulerian.java
To Run: No special requirements
