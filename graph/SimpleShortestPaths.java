package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author Tony Tu
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        for (int i = 0; i <= G.maxVertex(); i++) {
            weights.add(-1.0);
            predecessors.add(0);
        }
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    @Override
    protected void setWeight(int v, double w) {
        weights.set(v, w);
    }

    @Override
    protected abstract double getWeight(int u, int v);

    @Override
    public int getPredecessor(int v) {
        if (!(v > predecessors.size())) {
            return predecessors.get(v);
        }
        return 0;
    }

    @Override
    public double getWeight(int v) {
        if (weights.size() <= v || weights.get(v) == -1.0) {
            return Double.POSITIVE_INFINITY;
        }
        return weights.get(v);
    }

    @Override
    protected void setPredecessor(int v, int u) {
        predecessors.set(v, u);
    }

    /** My list of weights. */
    private ArrayList<Double> weights = new ArrayList<>();
    /** My list of predecessors. */
    private ArrayList<Integer> predecessors = new ArrayList<>();

}
