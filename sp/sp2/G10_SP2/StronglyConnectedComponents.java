import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Madhuri Abnave
 *
 */
public class StronglyConnectedComponents {

	public static int DFS(List<Vertex> verts, Stack S, boolean reverse) {
		int component = 0;
		if (!reverse) {
			component = 0;
			for (Vertex u : verts) {
				if (u != null)
					if (!u.seen) {
						DFSVisit(u, ++component, S, reverse);
					}

			}
		} else {
			Vertex fromStack = null;

			component = 0;
			while (!S.isEmpty()) {
				fromStack = (Vertex) S.pop();
				fromStack.parent = null;
				fromStack.seen = false;
				if (!fromStack.seen) {
					DFSVisit(fromStack, ++component, S, reverse);
				}
			}

			

		}

		return component;

	}

	/**
	 * @param reverse
	 * @param u:-the
	 *            vertex currently being considered
	 * @param component:-the
	 *            component nos for current vertext being considered
	 */
	public static void DFSVisit(Vertex u, int component, Stack S, boolean reverse) {
		List<Edge> l;
		u.seen = true;

		u.component = component;
		if (!reverse) {
			l = u.Adj;
		} else {
			l = u.revAdj;
		}
		for (Edge e : l) {
			Vertex v = e.otherEnd(u);
			if (!v.seen) {
				v.parent = u;
				DFSVisit(v, component, S, reverse);

			}
		}
		if (!reverse) {
			S.push(u);
		} 
		else{
			S.remove(u);
		}
	
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Stack S = new Stack();
		Graph sample = Graph.readGraph(in, true);
		boolean reverse = false;
		int i = DFS(sample.verts, S, reverse);
		System.out.println("reversing the graph ");
		reverse = true;
		for (Vertex vertex : sample.verts) {
			if (vertex != null) {
				vertex.seen = false;
				vertex.parent = null;
			}
		}

		int j = DFS(sample.verts, S, reverse);
		System.out.println("The number of strongly connected components are " + j);

	}

}
