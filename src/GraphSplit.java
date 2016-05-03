import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;


public class GraphSplit {
	
	ArrayList<Node> nodes;
	int numNodes, numEdges;
	
	public GraphSplit(String filename) {
		setUp(filename);
	}
	
	public void setUp(String filename) {
		//open the file to read
		Scanner in;
		try {
			FileReader reader = new FileReader(filename);
			in = new Scanner(reader);
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			return;
		}
		
		//get the number of edges and nodes
		String line = in.nextLine();
		String[] line_arr = line.split(" ");
		numNodes = Integer.parseInt(line_arr[0]);
		numEdges = Integer.parseInt(line_arr[1]);
		
		//initialize the array of nodes
		nodes = initializeNodes(numNodes);
		
		//create the graph
		for (int i = 0; i<numEdges; i++) {
			line = in.nextLine();
			line_arr = line.split(" ");
			int n1 = Integer.parseInt(line_arr[0]);
			int n2 = Integer.parseInt(line_arr[1]);
			nodes.get(n1-1).addNeighbor(nodes.get(n2-1));
			nodes.get(n2-1).addNeighbor(nodes.get(n1-1));
		}
		
		//close the scanner
		in.close();
	}
	
	//set up the array of nodes 
	public ArrayList<Node> initializeNodes(int numNodes) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i<numNodes; i++) {
			Node n = new Node(i);
			nodes.add(n);
		}
		return nodes;
	}
	
	//schedule nodes with most edges first
	public void algo1_do_werk() {
		PriorityQueue<Node> unscheduled_nodes = new PriorityQueue<Node>(nodes);
		Set<Node> left = new HashSet<Node>();
		Set<Node> right = new HashSet<Node>();
		int scheduled_nodes = 0;
		//schedule the nodes
		while(scheduled_nodes<numNodes) {
			//get node with most neighbors
			Node n = unscheduled_nodes.poll();
			Set<Node> current_set = right;
			if (n.isScheduled()) {
				if (left.contains(n)) current_set = left;
			}else {
				if (left.size()<right.size()) current_set = left;
				left.add(n);
			}
			//if number of neighbors == 0 pop from unscheduled_nodes
			if (n.getUnscheduledNeighbors() != 0) {
				unscheduled_nodes.add(n);
			}
			//
		}

	}
	
	public void algo_max_edges() {
		int num_crossing_edges = numNodes/2*numNodes/2;
		System.out.println(num_crossing_edges);
	}
	
	//start with all in one set move to next set if they have minimum edges in original set
	public void algo2_do_werk() {
		Set<Node> first_set = new HashSet<Node>(nodes);
		Set<Node> second_set = new HashSet<Node>();
		
		//schedule half of nodes to the other side
		for (int i = 0; i<numNodes/2; i++) {
			//find node with least connections to same side
			int minNeighbors = first_set.size();
			Node move_node = nodes.get(0);
			for (Node n: first_set) {
				if (n.getNeighbors2().size()<minNeighbors) {
					move_node = n;
					minNeighbors=n.getNeighbors2().size();
				}
			}
			//schedule the node on the other side
			second_set.add(move_node);
			first_set.remove(move_node);
			//loop over second set nodes and remove the node from their neighbors2;
			for (Node n: first_set) {
				n.removeFromNeighbors2(move_node);
			}
		}
		printStuff(first_set,second_set);
	}
	
	public void printStuff(Set<Node> leftNodes, Set<Node> rightNodes) {
		try {
			PrintWriter writer = new PrintWriter("output.txt");
			
			int numActualEdges=0;
			for (Node n: leftNodes) {
				Set<Node> neighbors = n.getNeighbors();
				for (Node n2: neighbors) {
					if (rightNodes.contains(n2)) {
						numActualEdges+=1;
					}
				}
			}
			System.out.println(numActualEdges);
			writer.println(numActualEdges);
			for (Node n: leftNodes) {
				System.out.print((n.getId()+1)+" ");
				writer.print((n.getId()+1)+" ");
			}
			System.out.println();
			writer.println();
			for (Node n: rightNodes) {
				System.out.print((n.getId()+1)+" ");
				writer.print((n.getId()+1)+" ");
			}
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	public int getNumNodes() {
		return numNodes;
	}
	
	public int getNumEdges() {
		return numEdges;
	}

	public static void main(String[] args) {
		GraphSplit gs = new GraphSplit("input.txt");
		gs.algo2_do_werk();
		Greedy greedy = new Greedy(gs.getNodes());

	}
}
