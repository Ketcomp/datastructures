import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author vmungara
 * 
 *
 */
public class TreeDiameter {

	static int diameter(Graph g) {

		resetGraph(g);
		Vertex z = BFS(g.verts.get(1));
		if(z == null) {
			System.out.println("graph has a cycle");
			return -1;
		}
		
		resetGraph(g);
		z = BFS(z);
		System.out.println(z.distance);
		return z.distance;
	}

	/*
	 * Run BFS from source vertex. each null pushed into queue represents new level. 
	 */
	private static Vertex BFS(Vertex source) {
		Queue<Vertex> queue = new LinkedList<>();
		
		queue.add(source);
		queue.add(null);
		source.seen = true;
		source.distance = 0;
		int height = 1;
		
		Vertex temp = null;
		while(!queue.isEmpty()) {
			Vertex u = queue.remove();
			if(u == null) {
				height++;
				if(!queue.isEmpty()) queue.add(null);
			} else {			
				temp = u;
				// invariant: 
				// forward edge - v.seen is false 
				// back edge - v.parent is u 
				// cross edge - v.distance = u.distance or loop 
				for(Edge e : u.Adj) {
					Vertex v = e.otherEnd(u);
					if(!v.seen) {
						v.parent = u;
						v.distance = height;
						queue.add(v);
						v.seen = true;
					} else if(u.parent == v) {
						continue;
					} else {
						return null;
					}
				}
			}
		}
		return temp;
	}

	private static void resetGraph(Graph g) {
		for(Vertex u: g.verts){
			if(u != null) {
				u.seen = false;
				u.distance = 0;
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean directed = false;
		Graph graph = Graph.readGraph(in, directed);
		
		int diameter = diameter(graph);
		System.out.println("diameter of the grap is "+diameter);
	}
	
}
