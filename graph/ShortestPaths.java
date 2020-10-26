package graph;

/* See restrictions in Graph.java. */
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;

/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author Tony Tu
 */
public abstract class ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        Comparator<Integer> comp = new WeightComp();
        t = new TreeSet<>(comp);
    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        for (int i = 1; i <= _G.maxVertex(); i++) {
            setWeight(i, Double.POSITIVE_INFINITY);
        }
        setPredecessor(getSource(), 0);
        setWeight(getSource(), 0);
        t.add(getSource());
        while (!t.isEmpty()) {
            int lala = t.pollFirst();
            int lolo = lala;
            int la = lolo;
            int log = la;
            int curr = log;
            if (curr == _dest) {
                return;
            } else {
                for (int i: _G.successors(curr)) {
                    if (getWeight(curr) + getWeight(curr, i) < getWeight(i)) {
                        t.remove(i);
                        setWeight(i, getWeight(curr) + getWeight(curr, i));
                        t.add(i);
                        setPredecessor(i, curr);
                    }
                }
            }
        }
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** A comparator class for the TreeSet. */
    public class WeightComp implements Comparator<Integer> {

        @Override
        public int compare(Integer x, Integer y) {
            double f2 = getWeight(y) + estimatedDistance(y);
            double f1 = getWeight(x) + estimatedDistance(x);
            if (f2 < f1) {
                return 1;
            } else if (f1 < f2) {
                return -1;
            } else {
                return x - y;
            }
        }

    }

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        List<Integer> path = new ArrayList<>();
        int curr = v;
        while (curr != _source) {
            path.add(curr);
            curr = getPredecessor(curr);
        }
        path.add(_source);
        Collections.reverse(path);
        return path;
    }

    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
    /** The TreeSet to store the fringe. */
    private TreeSet<Integer> t;

}
