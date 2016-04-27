import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


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
	
	public ArrayList<Node> initializeNodes(int numNodes) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i<numNodes; i++) {
			Node n = new Node(i);
			nodes.add(n);
		}
		return nodes;
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
	}
}
