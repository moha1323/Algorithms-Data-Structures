import java.util.*;

// A node of a graph for the Spring 2018 ICS 340 program

public class Node {

	String name;
	String val;  // The value of the Node
	String abbrev;  // The abbreviation for the Node
	ArrayList<Edge> outgoingEdges;  
	ArrayList<Edge> incomingEdges;
	String color;
	int discoveryTime, finishingTime;
	ArrayList<Node> descendent;
	
	public Node( String theAbbrev ) {
		setAbbrev( theAbbrev );
		val = null;
		name = null;
		outgoingEdges = new ArrayList<Edge>();
		incomingEdges = new ArrayList<Edge>();
		discoveryTime = 0;
		finishingTime = 0;
	}
	
	public String getAbbrev() {
		return abbrev;
	}
	
	public String getName() {
		return name;
	}
	
	public String getVal() {
		return val;
	}
	
	public ArrayList<Edge> getOutgoingEdges() {
		return outgoingEdges;
	}
	
	public ArrayList<Edge> getIncomingEdges() {
		return incomingEdges;
	}
	
	public void setAbbrev( String theAbbrev ) {
		abbrev = theAbbrev;
	}
	
	public void setName( String theName ) {
		name = theName;
	}
	
	public void setVal( String theVal ) {
		val = theVal;
	}
	
	public void addOutgoingEdge( Edge e ) {
		outgoingEdges.add( e );
	}
	
	public void addIncomingEdge( Edge e ) {
		incomingEdges.add( e );
	}
	
	public String getColor() {
		return color;
	}
	
	public int getDiscoveryTime() {
		return discoveryTime;
	}
	
	public int getFinishingTime() {
		return finishingTime;
	}
	
	public void setColor(String theColor) {
		color = theColor;
	}
	
	public void setDiscoveryTime(int theDiscoveryTime) {
		discoveryTime = theDiscoveryTime;
	}
	
	public void setFinishingTime(int theFinishingTime) {
		finishingTime = theFinishingTime;
	}
	
}
