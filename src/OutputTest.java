import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


import org.junit.Test;


public class OutputTest {
	
	GraphSplit gs = new GraphSplit("input_group17.txt");
	int numNodes = gs.getNumNodes();
	ArrayList<Node> nodes = gs.getNodes();

	
	@Test
	public void test() {
		//read the output file
		Scanner in;
		try {
			FileReader reader = new FileReader("output.txt");
			in = new Scanner(reader);
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			return;
		}
		
		//set up vars to hold the partitions
		int numEdges = Integer.parseInt(in.nextLine());
		Set<Node> leftNodes = new HashSet<Node>();
		Set<Node> rightNodes = new HashSet<Node>();
		
		//read in the partitions
		String line = in.nextLine();
		String[] line_arr = line.split(" ");
		for (int i = 0; i<numNodes/2; i++) {
			int n = Integer.parseInt(line_arr[i]);
			leftNodes.add(nodes.get(n-1));
		}
		line = in.nextLine();
		line_arr = line.split(" ");
		for (int i = 0; i<numNodes/2; i++) {
			int n = Integer.parseInt(line_arr[i]);
			rightNodes.add(nodes.get(n-1));
		}
		
		//assert that they separated the nodes correctly
		assertTrue(leftNodes.size()==numNodes/2);
		assertTrue(rightNodes.size()==numNodes/2);
		
		//count the actual number of edges between the sets
		int numActualEdges=0;
		for (Node n: leftNodes) {
			Set<Node> neighbors = n.getNeighbors();
			for (Node n2: neighbors) {
				if (rightNodes.contains(n2)) numActualEdges+=1;
			}
		}
		
		//assert that the amount of actual edges is equal to amount of stated edges
		assertEquals(numActualEdges, numEdges);
		
		in.close();
	}

}
