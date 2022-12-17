import java.util.ArrayList;

public class SalesRoute {
	ArrayList<Node> route;
	
	public SalesRoute() {
		route = new ArrayList<Node>();
	}
	//Add a city to the sales route
	public void add(Node city) {
		route.add(city);
	}
	//return to the city of origin
	public void returnFlight() {
		route.add(route.size(), route.get(0));
	}
	//cloning an arraylist of node
	public void copy(ArrayList<Node> routeList) {
		route = (ArrayList<Node>) routeList.clone(); 
	}
	//swap two cities
	//city 1 is the city you want to move and city 2 is the city you are moving to city 1's place.
	public void swap(Node city1, int city1Index, Node city2, int city2Index) {
		route.remove(city2Index);
		route.add(city2Index, city1);
		route.remove(city1Index);
		route.add(city1Index, city2);
	}
	//return the total distance of a sales route
	public int totalDistance() {
		int distance = 0;
		for(int i = 0; i < route.size() - 1; i++) {
			distance += travelDistance(route.get(i), route.get(i+1));
		}
		return distance;
	}
	//return the distance between distance
	public int travelDistance(Node cityFrom, Node cityTo) {
		for(Edge e : cityFrom.getOutgoingEdges()) {
			if(e.getHead().equals(cityTo)) {
				return e.getLabel();
			}
		}
		return 0;
	}
	
	public ArrayList<Node> getRoute(){
		return route;
	}
	
	public Node get(int index) {
		return route.get(index);
	}
	
	public int size() {
		return route.size();
	}
	
	public String toString() {
		String path = "";
		for(int i = 0; i < route.size(); i++) {
			path += route.get(i).getVal();
		}
		path += "  " + totalDistance();
		return path; 
	}
}
