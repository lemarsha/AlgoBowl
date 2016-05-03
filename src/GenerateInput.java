import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class GenerateInput {
	
	private int numNodes, numEdges;
	private String filename;
	
	public GenerateInput(int numNodes, String filename){
		this.numNodes = numNodes;
		this.filename=filename;
		runMaxEdges();
	}
	
	public GenerateInput(int numNodes, int numEdges, String filename) {
		this.numNodes = numNodes;
		this.numEdges = numEdges;
		this.filename=filename;
		run();
	}
	
	public void runMaxEdges() {
		//create file to write to
		PrintWriter writer;
		try {
			writer = new PrintWriter(filename,"UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return;
		}
		
		//write to file
		writer.println(numNodes+" "+numNodes*(numNodes-1)/2);
		for (int i = 1; i<numNodes+1; i++) {
			for (int j = (i+1); j<numNodes+1; j++) {
				writer.println(i+" "+j);
			}
		}
		
		//close writer
		writer.close();
	}
	
	public void run() {
		//create file to write to
		PrintWriter writer;
		try {
			writer = new PrintWriter(filename,"UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return;
		}
		
		//write to file
		writer.println(numNodes+" "+numEdges);
		Set<String> existing_edges = new HashSet<String>();
		Random rand = new Random();
		for (int i = 0; i<numEdges; i++) {
			boolean valid = false;
			int n1=0, n2=0;
			while (!valid) {
				n1 = rand .nextInt(numNodes)+1;
				n2 = rand.nextInt(numNodes)+1;
				if (n1==n2) continue;
				String n = n1+" "+n2;
				if (existing_edges.contains(n)) continue;
				existing_edges.add(n);
				valid=true;
			}
			writer.println(n1+" "+n2);
		}
		
		//close writer
		writer.close();
	}
	
	public static void main(String[] args) {
		GenerateInput gi = new GenerateInput(446, "input_2.txt");
		GenerateInput gi2 = new GenerateInput(324, 555, "input_3.txt");
	}
	

}
