import java.util.ArrayList;
// A list of tabus
public class TabuList {
	ArrayList<Tabu> tabuList;
	public TabuList() {
		tabuList = new ArrayList<Tabu>();
	}
	//create a tabu and add it to the list
	public Tabu add(Node startCity, SalesRoute sr) {
		Tabu t = new Tabu(startCity, sr);
		tabuList.add(t);
		return t;
	}
	//return true if the parameters matches a tabu
	public boolean contains(Node startCity, SalesRoute sr) {
		boolean found = false;
		if(originEquals(startCity) && routeEquals(sr)) {
			found = true;
		}
		return found;
	}
	
	public boolean originEquals(Node startCity) {
		for(Tabu t : tabuList) {
			if(t.getOrigin().equals(startCity)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean routeEquals(SalesRoute sr) {
		for(int i = 0; i < tabuList.size(); i++) {
			for(int j = 0 ; j < 3; j++) {
				if(tabuList.get(i).getRoute().get(j).equals(sr.get(j)) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void clear() {
		tabuList.clear();
	}
	
	public String toString() {
		String list = "";
		for(int i = 0; i < tabuList.size(); i++) {
			list += tabuList.get(i).toString() + "\n";
		}
		return list;
	}
}
