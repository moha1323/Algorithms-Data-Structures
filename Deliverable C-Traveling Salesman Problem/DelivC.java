import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Class DelivC does the work for deliverable DelivC of the Prog340

public class DelivC {

	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	TabuList tl = new TabuList();
	ArrayList<SalesRoute> shortest = new ArrayList<SalesRoute>();
	int steps = 0;
	
	public DelivC( File in, Graph gr ) {
		inputFile = in;
		g = gr;
		
		// Get output file name.
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring( 0, inputFileName.length()-4 ); // Strip off ".txt"
		String outputFileName = baseFileName.concat( "_out.txt" );
		outputFile = new File( outputFileName );
		if ( outputFile.exists() ) {    // For retests
			outputFile.delete();
		}
		
		try {
			output = new PrintWriter(outputFile);			
		}
		catch (Exception x ) { 
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}
		//System.out.println( "DelivC:  To be implemented");
		//output.println( "DelivC:  To be implemented");
		print();
		output.flush();
	}
	
	private void print() {
		randomRestart();
		findShortest();
	}
	//return an object, SalesRoute, that is randomly chooses cities for a sales route
	private SalesRoute randomInitialization() {
		SalesRoute route = new SalesRoute();
		int n = 0;
		Random rand = new Random();
		int randomNum;
		ArrayList<Integer> randomNumList = new ArrayList<Integer>();
		while(n < g.getNodeList().size()) {
			randomNum = rand.nextInt(g.getNodeList().size());
			if(!randomNumList.contains(randomNum)) {
				randomNumList.add(randomNum);
				n++;
			} else {
				randomNum = rand.nextInt(g.getNodeList().size());
			}
		}
		for(int i = 0; i < randomNumList.size(); i++) {
			route.add(g.getNodeList().get(randomNumList.get(i)));
		}
		route.returnFlight();
		System.out.println(route.toString());
		return route;
	}
	//swaps the cities around 10 times to see if it will yield optimal solution
	private void localSearch(SalesRoute sr) {
		SalesRoute route = new SalesRoute();
		route.copy(sr.getRoute());
		int walkCount = 0;
		while(walkCount < 10) {
			SalesRoute[] routeArray = findLongestTravelDistanceTrio(route);
			Node x = findLongestTravelDistance(routeArray, sr, 0);
			if(!x.equals(sr.get(0))) {
				if(!tl.contains(route.get(0), routeArray[0])) {
					int index = route.getRoute().indexOf(x);
					if(index == 1) {
						route.swap(route.get(index), index, route.get(index+1), index+1);
					} else if(index == (route.size() - 2)) {
						route.swap(route.get(index-1), index-1, route.get(index), index);
					} else {
						route.swap(route.get(index-1), index-1, route.get(index+1), index+1);
					}
					output.println(route.toString() + " (swap)");
					steps++;
					if(route.totalDistance() > sr.totalDistance()) {
						Tabu forbid = tl.add(route.get(0), routeArray[0]);
						if(index == 1) {
							route.swap(route.get(index+1), index+1, route.get(index), index);
						} else if(index == (route.size() - 2)) {
							route.swap(route.get(index), index, route.get(index-1), index-1);
						} else {
							route.swap(route.get(index+1), index+1, route.get(index-1), index-1);
						}
					} else {
						System.out.println(route.toString());
						shortest.add(route);
					}
					sr.copy(route.getRoute());
				}
			} else {
				if(!tl.contains(route.get(0), routeArray[1])) {
					int index = route.getRoute().indexOf(x);
					if(index == 1) {
						route.swap(route.get(index), index, route.get(index+1), index+1);
					} else if(index == (route.size() - 2)) {
						route.swap(route.get(index-1), index-1, route.get(index), index);
					} else {
						route.swap(route.get(index-1), index-1, route.get(index+1), index+1);
					}
					output.println(route.toString() + " (swap)");
					steps++;
					if(route.totalDistance() > sr.totalDistance()) {
						Tabu forbid = tl.add(route.get(0), routeArray[1]);
						if(index == 1) {
							route.swap(route.get(index+1), index+1, route.get(index), index);
						} else if(index == (route.size() - 2)) {
							route.swap(route.get(index), index, route.get(index-1), index-1);
						} else {
							route.swap(route.get(index+1), index+1, route.get(index-1), index-1);
						}
					} else if (sr.totalDistance() < route.totalDistance()) {
						System.out.println(route.toString());
						shortest.add(route);
					}
					sr.copy(route.getRoute());
				}
			}
			walkCount++;
		}
	}
	//split the sales route into sales routes with three cities and find two of the sales route that have the highest total distance
	private SalesRoute[] findLongestTravelDistanceTrio(SalesRoute sr) {
		SalesRoute[] routeArray = new SalesRoute[sr.size() - 2];
		SalesRoute[] swapCandidates = new SalesRoute[2];
		int largestA = 0;
	    int largestB = 0;
	    
		for(int i = 0; i < sr.size() - 2; i++) {
			routeArray[i] = new SalesRoute();
			routeArray[i].add(sr.get(i));
			routeArray[i].add(sr.get(i+1));
			routeArray[i].add(sr.get(i+2));
		}
		for(int i = 0; i < routeArray.length; i++) {
			if(routeArray[i].totalDistance() > largestA) {
				largestB = largestA;
				largestA = routeArray[i].totalDistance();
			} else if(routeArray[i].totalDistance() > largestB) {
				largestB = routeArray[i].totalDistance();
			}
		}
		for(int i = 0; i < routeArray.length; i++) {
			if(routeArray[i].totalDistance() == largestA) {
				swapCandidates[0] = routeArray[i];
			} else if(routeArray[i].totalDistance() == largestB) {
				swapCandidates[1] = routeArray[i];
			}
		}
		return swapCandidates;
	} 
	//After finding the highest sales route of three cities sales route now find the highest distance between two cities. 
	private Node findLongestTravelDistance(SalesRoute[] sr, SalesRoute originalSR, int index) {
		SalesRoute route = sr[index];
		Node city = null;
		if(route.travelDistance(route.get(0), route.get(1)) < route.travelDistance(route.get(1), route.get(2))) {
			city = route.get(1);
		} else {
			city = route.get(0);
		}
		if(city.equals(originalSR.get(0))) {
			city = findLongestTravelDistance(sr, originalSR, index+1);
		}
		return city;
	} 
	//restart 30 times
	private void randomRestart() {
		for(int i = 0; i < 30; i++) {
			System.out.println("***" + (i + 1) + "***");
			SalesRoute route = randomInitialization();
			output.println(route.toString() + " (random restart)");
			localSearch(route);
			tl.clear();
		}
	}
	//find the shortest route
	private void findShortest() {
		Collections.sort(shortest, new SalesRouteComparator());//Sort the sales route by shortest total distance traveled 
		output.println("Shortest path found was " + shortest.get(0).toString() + " after " + (steps + 30));
		System.out.println("Shortest path found was " + shortest.get(0).toString() + " after " + (steps + 30));
	}
	
}

