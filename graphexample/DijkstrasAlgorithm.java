/*
 *  Name: Roman Joska
 *  Class: CS4720 
 *  Description: Dijkstra's Algorithm
 */

package graphexample;

public class DijkstrasAlgorithm {

	public static void main(String[] args) {
		/*
		int index;
        // Graph with 4 Vertices, 5 Edges created
		ClassGraph myGraph = new ClassGraph(4, 5);
        //Add Vertex 'A' and set it as Visited, length = 0
		index = myGraph.AddVertex('A');
        myGraph.GetVertex('A').setLength(0);
        myGraph.GetVertex('A').markVisited();
        //Add more Vertices      
        index = myGraph.AddVertex('B');
        index = myGraph.AddVertex('C');
        index = myGraph.AddVertex('D');
		//Add some edges
        index = myGraph.AddEdge('C', 'B', 5);
        index = myGraph.AddEdge('A', 'C', 1);
        index = myGraph.AddEdge('C', 'D', 2);
        index = myGraph.AddEdge('D', 'B', 3);
        index = myGraph.AddEdge('A', 'D', 2);
        //Print graph representation contents    
        //myGraph.printVertices();
        //myGraph.printEdges();
		*/
		
		Edgy[] eadge;
		eadge = new Edgy[5];
		
		eadge[0] = new Edgy('A', 'B', 4);
		eadge[1] = new Edgy('A', 'C', 1);
		eadge[2] = new Edgy('C', 'D', 1);
		eadge[3] = new Edgy('B', 'D', 2);
		eadge[4] = new Edgy('A', 'D', 3);
		
		
		ClassGraph myGraph = initGraph(4, 5, 'A', eadge);

        //dijkstra
        while(!allVisited(myGraph)) {
        	System.out.println("I am here");
        	int smallestEdge = 3000;
        	char edgeNam = 'A';
        	
        	//only check edges from seen to unseen
        	for(int i = 0; i < myGraph.edgeCount; i++) {
        			if(myGraph.GetVertex(myGraph.GetEdge(i).fromV).isVisited() && !myGraph.GetVertex(myGraph.GetEdge(i).toV).isVisited()) {
        				if((myGraph.GetEdge(i).weight + myGraph.GetVertex(myGraph.GetEdge(i).fromV).getLength()) < smallestEdge) {
        					System.out.printf("Checking on edge i = %d aka %c to %c\n", i, myGraph.GetEdge(i).fromV, myGraph.GetEdge(i).toV);
        					smallestEdge = myGraph.GetEdge(i).weight + myGraph.GetVertex(myGraph.GetEdge(i).fromV).getLength();
        					edgeNam = myGraph.GetEdge(i).toV;
        				}//end of checking if smallest
        			}//end of if visited
        	}//end of i
        	
        	//now that we have the smallest edge we can mark the to as seen and set its length
        	myGraph.GetVertex(edgeNam).setLength(smallestEdge);
            myGraph.GetVertex(edgeNam).markVisited();
        	
        }//end of while
        
        myGraph.printVertices();
        myGraph.printEdges();

	}
	
	public static boolean allVisited(ClassGraph myGraph) {
		System.out.println("I am in allVisited");
		for(int i = 0; i < myGraph.vertices.length; i++) {
			if(myGraph.GetVertex(i).isVisited() == false) {
				System.out.printf("Return false on %d aka %c\n", i, myGraph.GetVertex(i).getName());
				return false;
			}
		}
		
		return true;
	}

	
	public static ClassGraph initGraph(int numVert, int numEdge, char startChar, Edgy[] edges) {
		//init the graph
		int index;
		ClassGraph retGraph = new ClassGraph(numVert, numEdge);
		
		for(int i = 0; i < numVert; i++) {
			int moo = i + (int) 'A';
			char boo = (char) moo;
			index = retGraph.AddVertex(boo);
		}
		
		retGraph.GetVertex(startChar).setLength(0);
		retGraph.GetVertex(startChar).markVisited();
		
		for(int i = 0; i < numEdge; i++) {
			int weight = edges[i].getW();
			char too = edges[i].getT();
			char from = edges[i].getF();
			
			index = retGraph.AddEdge(from, too, weight);
		}
		
		return retGraph;
	}
	
}

class Edgy{
	private int weight;
	private char from;
	private char twoChar;
	
	public Edgy(char f, char t, int w) {
		weight = w;
		from = f;
		twoChar = t;
	}
	
	//getters
	public int getW() {
		return weight;
	}
	
	public char getF() {
		return from;
	}
	
	public char getT() {
		return twoChar;
	}
	
}


//the functions not displayed here were provided by Al, with some possible changes
