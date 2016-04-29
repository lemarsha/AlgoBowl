import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Greedy {
	private Set<Node> set1 = new HashSet<Node>();
	private Set<Node> set2 = new HashSet<Node>();
	
	private ArrayList<Node> nodes;
	private int set_size;

	public Greedy(ArrayList<Node> nodes) {
		super();
		this.nodes = nodes;
		this.set_size = nodes.size()/2;
		run();
	}
	
	private int set_deviation(Node in_node, Set<Node> node_set) {
		int total_encounters = 0;
		
		for(Node n : node_set) {
			if(n.contains(in_node.id)) {
				total_encounters++;
			}
			for(Node nn: in_node.getNeighbors()) {
				if(n.contains(nn.id)) {
					total_encounters++;
				}
			}
		}
		
		return total_encounters;
	}
	
	private int common_edge_count(Set<Node> set1, Set<Node> set2) {
		int total = 0;
		
		for(Node n : set1) {
			for(Node nn : n.neighbors) {
				if(set2.contains(nn)) {
					total++;
				}
			}
		}
		
		return total;
	}
	 
	private void run() {
		//placing first nodes into set1 and set2 for a starting place
		//two nodes chosen are nodes that have the least common edges with each other, ideally nothing
		Random rn = new Random();
		Node tmp = nodes.get(Math.abs(rn.nextInt())%nodes.size());
		set1.add(tmp);
		nodes.remove(tmp);
		
		for(Node n: nodes) {
			if(!n.contains(tmp.id)) {
				set2.add(n);
				nodes.remove(n);
				break;
			}
		}
		
		//Running through all the nodes to determine which set to place a node
		//starting at 1 because first node already placed into a set
		for(Node n: nodes) {
			 int deviation1=0, deviation2=0;
			 deviation1 = set_deviation(n, set1);
			 deviation2 = set_deviation(n, set2);
			 
			 
			 //Making sure there is room in both sets
			 if(set1.size() < set_size && set2.size() < set_size) {
				 
				 //Set2 is a better fit
				 if(deviation1 < deviation2) {
					 set2.add(n);
				 }
				 //Set1 is a better fit
				 else if(deviation1 > deviation2) {
					 set1.add(n);
				 } 
				 //They are the same
				 else {
					 if(set1.size() > set2.size()) {
						 set2.add(n);
					 } else {
						 set1.add(n);
					 }
				 }  
			 } 
			 //Room in set1
			 else if(set1.size() < set_size) {
				 set1.add(n);
			 } 
			 //Room in set2
			 else {
				 set2.add(n);
			 } 
		}
		
		//print common edges
		System.out.println(common_edge_count(set1, set2));
		
		
		//printing sets
		System.out.println(set1);
		System.out.println(set2);
	}
	
}
