// Class DelivB does the work for deliverable DelivB of the Prog340
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class DelivB {

	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	static int time;
	Stack<Node> visitedNode;
	Stack<Node> visitingU, visitingV;
	int count = 0;

	public DelivB(File in, Graph gr) {
		inputFile = in;
		g = gr;

		// Get output file name.
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring(0, inputFileName.length() - 4); // Strip off ".txt"
		String outputFileName = baseFileName.concat("_out.txt");
		outputFile = new File(outputFileName);
		if (outputFile.exists()) { // For retests
			outputFile.delete();
		}

		try {
			output = new PrintWriter(outputFile);
		} catch (Exception x) {
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}
		
		System.out.println("DelivB:  To be implemented");
		//output.println("DelivB:  To be implemented");
		print();
		output.flush();
	}
	
	private void print() {
		DFS(g);
		output.println("Node\tStart Time\tEnd Time");
		for(int i = visitedNode.size()-1; i >= 0; i--) {
			output.println(visitedNode.get(i).getName() + "\t\t" + visitedNode.get(i).getDiscoveryTime() 
					+ "\t\t\t" + visitedNode.get(i).getFinishingTime());
		}
		output.println("\nEdge\tType");
		for (int i = 0; i < g.getNodeList().size(); i++) {// This loop will traverse through all the nodes
			for (int j = 0; j < g.getNodeList().get(i).getOutgoingEdges().size(); j++) {// This loop will traverse through
																						// all the node's outgoing edges
				output.println(g.getNodeList().get(i).getOutgoingEdges().get(j).getTail().getAbbrev() 
						+ "-" + g.getNodeList().get(i).getOutgoingEdges().get(j).getHead().getAbbrev() + "\t\t"
						+ g.getNodeList().get(i).getOutgoingEdges().get(j).getType());
			}
		}
		
	}
	
	private void DFS(Graph g) {
		for(Node u : g.getNodeList()) {
			u.setColor("white");
		}
		time = 0;
		visitedNode = new Stack<Node>();
		visitingU = new Stack<Node>();
		visitingV = new Stack<Node>();
		for(Node u : g.getNodeList()) {
			if(u.getColor().equalsIgnoreCase("white")) {
				if(!g.getStartNode().getOutgoingEdges().isEmpty()) {
					DFSVisit(g,g.getStartNode());
					visitedNode.push(g.getStartNode());
				} else {
					DFSVisit(g,u);
					visitedNode.push(u);
				}
			}
		}
	}
	
	private void DFSVisit(Graph g, Node u) {
		time++;
		u.setDiscoveryTime(time);
		u.setColor("gray");
		for(Edge edge : u.getOutgoingEdges()){
			Node v = edge.getHead();
			if(v.getColor().equalsIgnoreCase("white")) {
				DFSVisit(g,v);
				visitedNode.push(v);
				edge.setType("T");
			} else if(v.getColor().equalsIgnoreCase("gray")) {
				edge.setType("B");
			} else if(v.getColor().equalsIgnoreCase("black")) {
				edge.setType("F");
			} else {
				edge.setType("C");
			}
		}
		u.setColor("black");
		time++;
		u.setFinishingTime(time);
	}


}
