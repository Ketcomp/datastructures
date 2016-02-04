import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class OddLengthCycle {

	List<Vertex> oddLengthCycle(Graph g) {
		Queue<Vertex> Q = new LinkedList<>();

		for (Vertex u : g) {
		    u.seen = false;
		    u.parent = null;
		    u.distance = Integer.MAX_VALUE;
		}

		// Run BFS on every component
		for (Vertex src : g) {
		    if (!src.seen) {
			src.distance = 0;
			Q.add(src);
			src.seen = true;

			while (!Q.isEmpty()) {
			    Vertex u = Q.remove();
			    for (Edge e : u.Adj) {
				Vertex v = e.otherEnd(u);
				if (!v.seen) {
				    v.seen = true;
				    v.parent = u;
				    v.distance = u.distance + 1;
				    Q.add(v);
				} else {
				    if (u.distance == v.distance) {
				    	List<Vertex> cycle= new LinkedList<Vertex>();
				    	int index=0;
				    	Vertex u_parent=u.parent;
				    	Vertex v_parent=v.parent;
				    	cycle.add(u);
				    	cycle.add(v);
				    	index++;
				    	while(u_parent!=v_parent)
				    	{
				    		cycle.add(index,u_parent);
					    	cycle.add(index+1,v_parent);
					    	index++;
				    		u_parent=u_parent.parent;
				    		v_parent=v_parent.parent;
				    	}
				    	cycle.add(index,u_parent);
				    	return cycle;
				    }
				}
			    }
			}
		    }
		}
		return null;
	}
	
	public static void print(List<Vertex> cycle)
	{
		if(cycle==null)
		{
			System.out.println("This is a bipartite graph");
			return;
		}
		for(Vertex x: cycle)
		{
			System.out.print(x.name+"-");
		}
	}
	
	public static void main(String args[])
	{
		Scanner in= new Scanner(System.in);
		Graph g = Graph.readGraph(in, false);
		OddLengthCycle example= new OddLengthCycle();
		LinkedList<Vertex> cycle=(LinkedList<Vertex>)example.oddLengthCycle(g);
		print(cycle);
	}
}
