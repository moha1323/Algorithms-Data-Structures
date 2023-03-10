//import java.util.*;

// Edge between two nodes
public class Edge {
	
	int label; //switched from String to int
	int dist;
	Node tail;
	Node head;
	String type;
	
	public Edge( Node tailNode, Node headNode, String theLabel ) {
		setLabel( theLabel );
		setTail( tailNode );
		setHead( headNode );
	}
	
	public int getLabel() {
		return label;
	}
	
	public Node getTail() {
		return tail;
	}
	
	public Node getHead() {
		return head;
	}
	
	public int getDist() {
		return dist;
	}
	
	public void setLabel( String s ) {
		label = Integer.parseInt(s);
	}
	
	public void setTail( Node n ) {
		tail = n;
	}
	
	public void setHead( Node n ) {
		head = n;
	}
	
	public void setDist( String s ) {
		try {
			dist = Integer.parseInt( s );
		}
		catch ( NumberFormatException nfe ) {
			dist = Integer.MAX_VALUE;
		}
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String edgeType) {
		type = edgeType;
	}
}
