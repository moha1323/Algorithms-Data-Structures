import java.util.Comparator;
//Used for sorting the sales route by smallest total distance traveled
public class SalesRouteComparator implements Comparator<SalesRoute>{

	@Override
	public int compare(SalesRoute sr1, SalesRoute sr2) {
		if (sr1.totalDistance() > sr2.totalDistance()) {
			return 1;
		} else if(sr1.totalDistance() < sr2.totalDistance()) {
			return -1;
		} else {
			return 0;
		}
	}
	

}
