import java.util.HashSet;
import java.util.Set;

public class Node {
	
	Set<Node> neighbors;
	int id;
	int neighbors_unscheduled;
	int neighbors_scheduled;
	
	public Node(int id) {
		this.id = id;
		neighbors = new HashSet<Node>();
	}
	
	public void addNeighbor(Node n) {
		neighbors.add(n);
		neighbors_unscheduled+=1;
	}
	
	public Set<Node> getNeighbors() {
		return neighbors;
	}
	
	

	
}
