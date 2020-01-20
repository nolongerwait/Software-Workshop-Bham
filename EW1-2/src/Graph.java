import java.io.*;
import java.util.Scanner;

/**
 *  The class implements the two methods in the interface. A
 *  (bi-directional) graph is represented by the numberOfNodes and the
 *  adjacency matrix.
 * 
 *  @version 2018-10-25
 *  @author Manfred Kerber
 */

public class Graph implements GraphInterface {
    private int numberOfNodes;
    private int[][] adjacencyMatrix;

    /**
     *  @param numberOfNodes is the number of nodes (or vertices) in the graph.
     *  @param adjacencyMatrix stores the information about whether
     *  two nodes are connected and if what their distance/weight
     *  is. -1 stands for not connected.
     */
    public Graph(int numberOfNodes, int[][] adjacencyMatrix) {
        this.numberOfNodes = numberOfNodes;
        this.adjacencyMatrix = adjacencyMatrix;
    }

    /**
     *  The method counts in an array of boolean how many elements are
     *  true.
     *  @param visited A one-dimensional array of boolean.
     *  @return True, if the array contains only the value true.
     */
    public static boolean allTrue(boolean[] visited) {
        for (boolean el : visited) {
            if (!el) {
                return false;
            }
        }
        return true;
    }

    /**
     *  The method is to determine whether the given graph is
     *  connected, that is, whether it is in principle possible to
     *  walk from any vertex to any other vertix using the connections
     *  by the graph.
     *  @return true if any two vertices can be reached from each
     *  other.
     */
    public boolean connected() {
        /* Starting with the node 0 we compute which nodes can be
         * visited from it either directly, or via other nodes. We
         * store in the array visited, which ones can be visited this
         * way. If all can be visited then the array is connected,
         * else not. 
         */
        boolean[] visited = new boolean[numberOfNodes];
        if (numberOfNodes == 0) {
            return true;
        } else {
            /* 
             * We use to variable changed to keep iterating until
             * nothing changes anymore.
             */
            boolean changed = true;
            //We start with node 0.
            visited[0] = true;
            /*
             *  We repeat the loops until no more new connections can
             *  be found.  This is in the current form not efficient
             *  and could be improved by using queues, however, we do
             *  not have these data structures available quite now.
             */
            while (changed) {
                changed = false;
                // Iterate over each node.
                for (int i = 0; i < numberOfNodes; i++) {
                    /*
                     * Find for i all nodes that can be reached from
                     * it and change the visited array accordingly if
                     * they have not been visited previously. In this
                     * case, also reset the changed variable to true.
                     */
                    for (int j = 0; j < numberOfNodes; j++) {
                        if (visited[i] && adjacencyMatrix[i][j] != -1
                            && !visited[j]) {
                            visited[j] = true;
                            changed = true;
                        }
                    }
                }
            }
        }
        /*
         *  If all elements in the visited array are true then this 
         */
        return allTrue(visited);
    }

    /**
     *  Constructor to read a graph from a file.
     *  <pre>
     *  6
     *  0  7 -1  6  3 18
     *  7  0 -1  2  9 -1
     * -1 -1  0 24 15  1
     *  6  2 24  0 -1 10
     *  3  9 15 -1  0 -1
     * 18 -1  1 10 -1  0
     * </pre>
     * 
     *
     * The first element represents the number of nodes in the graph,
     * which corresponds to the size of the adjacency matrix, that is,
     * 6x6 in this example. Then follows the adjacency matrix line by
     * line.
     *
     * The first int is set to the numberOfNodes.
     * The following are the lines of the adjacency matrix.
     *
     *  @param filename The name of a file that contains a graph.
     */
    public Graph(String filename) {
        // try since the file may not exist.
        try {
            // we read from the scanner s which is linked to the file filename.
            Scanner s = new Scanner(new File(filename));
    
            // We read the number of nodes in the graph
            numberOfNodes = s.nextInt();
            //We initialize and read in the adjacency matrix.
            adjacencyMatrix = new int[numberOfNodes][numberOfNodes];
            for (int i = 0; i < numberOfNodes; i++) {
                for (int j = 0; j < numberOfNodes; j++) {
                    adjacencyMatrix[i][j] = s.nextInt();
                }
            }
            // We finished reading and close the scanner s.
            s.close();
        }
        catch (IOException e){
            //If the file is not found, an error message is printed.
            System.out.println("File not found.");
            numberOfNodes = 0;
            adjacencyMatrix = new int[numberOfNodes][numberOfNodes];
        }
    }

    /**
     *  The method returns the total length represented by the graph,
     *  for instance, in the case of a road system the method would
     *  represent to total length of all the roads. Note that in the
     *  matrix every road is represented twice (e.g., as a road from
     *  vertex 0 to vertex 1 and as road from vertex 1 to vertex 0),
     *  but the length should be added once only. Also -1 is used to
     *  represent that a corresponding road does not exist, hence
     *  these values are not be included in the sum
     *  @return The sum of all the connections (excluding -1 and each
     *  counted only once).
     */
    public int totalLength(){
        int sum = 0;
        for (int[] row : adjacencyMatrix) {
            for (int col : row) {
                if (col != -1) {
                    sum += col;
                }
            }
        }
        return sum/2;
    }

    /**
     *  The method creates a randomly generated graph in the format
     *  described in the class and writes it to a file.
     *  @param filename The filename to which the test is to be written. 
     *  @param numberOfNodes The number of nodes of the graph.
     *  @param connectionProbability The probability that any two
     *  different nodes are connected.
     *  @param maxWeight The maximal weight in a particular
     *  connection. The actual weight will be randomly chosen between
     *  1 and maxWeight for any two connected nodes.
     */
    public static void createTest(String filename, int numberOfNodes,
                                  double connectionProbability, int maxWeight) {
        double random;
        int value;
        int[][] result = new int[numberOfNodes][numberOfNodes];
        // try since it may not be possible to write to filename.
        try {
            BufferedWriter out = 
                new BufferedWriter(new FileWriter(filename));

            // We write the numberOfNodes out.
            out.write(numberOfNodes + "\n");

            // For every row
            for (int i = 0; i < numberOfNodes; i++) {
                //For every column
                result[i][i] = 0;
                for (int j = i+1; j < numberOfNodes; j++) {
                    //create a random value
                    random = Math.random();
                    /* 
                     * If the random number is below the
                     * connectionProbability then the nodes are
                     * connected, a second random number between 1
                     * and maxWeight is created and added to result
                     */
                    if (random <= connectionProbability) {
                        random = Math.random();
                        value = 1 + (int) (random * maxWeight);
                        result[i][j] = value;
                        result[j][i] = value;
                        } else {
                        result[i][j] = -1;
                        result[j][i] = -1;
                        }
                }
            }
            //The matrix is written to the file.
            for (int i = 0; i < numberOfNodes; i++) {
                for (int j = 0; j < numberOfNodes; j++) {
                    out.write(result[i][j] + " ");
                }
                //At the end of a line a newline symbol is inserted.
                out.write("\n");
            }
            // We finished reading and close scanner s.
            out.close();
        }
        catch (IOException e){
            //Errors are caught.
            System.out.println("File not found.");
        }
    }

    /*
     *  Some examples in the main method.
     */
    public static void main(String[] args) {
        int[][] a = {{ 0, 7,-1, 6, 3,18},
                     { 7, 0,-1, 2, 9,-1},
                     {-1,-1, 0,24,15, 1},
                     { 6, 2,24, 0,-1,10},
                     { 3, 9,15,-1, 0,-1},
                     {18,-1, 1,10,-1, 0}};
        Graph g1 = new Graph(6, a);
        System.out.println(g1.connected());
        System.out.println(g1.totalLength());

        int[][] b = {{ 0, 7,-1, 6,-1,-1},
                     { 7, 0,-1, 2,-1,-1},
                     {-1,-1, 0,24,-1,-1},
                     { 6, 2,24, 0,-1,-1},
                     {-1,-1,-1,-1, 0, 3},
                     {-1,-1,-1,-1, 3, 0}};
        Graph g2 = new Graph(6, b);
        System.out.println(g2.connected());
        System.out.println(g2.totalLength());

        Graph g3 = new Graph("g3.graph");
        System.out.println(g3.connected());
        System.out.println(g3.totalLength());

        createTest("g4.graph", 5, 0.4, 9);
        Graph g4 = new Graph("g4.graph");
        System.out.println(g4.connected());
        System.out.println(g4.totalLength());

        createTest("g5.graph", 1000, 0.008, 100);
        Graph g5 = new Graph("g5.graph");
        System.out.println(g5.connected());
        System.out.println(g5.totalLength());
    }
}
