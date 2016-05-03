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
	
	public boolean contains(int node_id) {
		if(this.id == node_id) return true;
		
		boolean flag = false;
		for(Node n : neighbors) {
			if(n.id == node_id) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public String toString() {
		return ""+(id+1);
	}

	
	
}
