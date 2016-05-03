import java.util.HashSet;
import java.util.Set;

public class Node implements Comparable<Node> {
	
	Set<Node> neighbors;
	Set<Node> neighbors2;
	int id;
	int neighbors_unscheduled;
	int neighbors_scheduled;
	boolean scheduled;
	
	public Node(int id) {
		this.id = id;
		neighbors = new HashSet<Node>();
		neighbors2 = new HashSet<Node>();
		scheduled=false;
	}
	
	public void addNeighbor(Node n) {
		neighbors.add(n);
		neighbors2.add(n);
		neighbors_unscheduled+=1;
	}
	
	public Set<Node> getNeighbors() {
		return neighbors;
	}
	
	public Set<Node> getNeighbors2() {
		return neighbors2;
	}
	
	public void removeFromNeighbors2(Node n) {
		neighbors2.remove(n);
	}
	
	public int getUnscheduledNeighbors() {
		return neighbors_unscheduled;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isScheduled() {
		return scheduled;
	}

	@Override
	public int compareTo(Node n) {
		if (this.neighbors_unscheduled>n.neighbors_unscheduled) return -1;
		if (this.neighbors_unscheduled==n.neighbors_unscheduled) return 0;
		return 1;
	}
	
	

	
}
