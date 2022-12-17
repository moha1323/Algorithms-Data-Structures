//A tabu that will have origin of the route and the route we don't want to check again.
public class Tabu {
	Node origin;
	SalesRoute route;
	public Tabu(Node startCity, SalesRoute sr) {
		origin = startCity;
		route = sr;
	}
	
	public Node getOrigin() {
		return origin;
	}
	
	public SalesRoute getRoute() {
		return route;
	}
	
	public String toString() {
		String tabu = "";
		tabu += "Orgin " + origin.getAbbrev() + " Route " + route.toString();
		return tabu;
	}
}
