/**
 *  The interface contains the head of two methods connected and
 *  totalLength.
 * 
 *  @version 2018-10-25
 *  @author Manfred Kerber
 */

public interface GraphInterface {

    /**
     *  The method is to determine whether the given graph is
     *  connected, that is, whether it is in principle possible to
     *  walk from any vertex to any other vertix using the connections
     *  by the graph.
     *  @return true if any two vertices can be reached from each
     *  other.
     */
    public boolean connected();

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
    public int totalLength();
}
