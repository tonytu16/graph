package graph;
import java.util.ArrayList;

/* See restrictions in Graph.java. */

/** Represents an undirected graph.  Out edges and in edges are not
 *  distinguished.  Likewise for successors and predecessors.
 *
 *  @author Tony Tu
 */
public class UndirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public int inDegree(int v) {
        return outDegree(v);
    }

    @Override
    public Iteration<Integer> successors(int v) {
        ArrayList<ArrayList<Integer>> inclusive = new ArrayList<>();
        ArrayList<ArrayList<Integer>> inclusivecopy = inclusive;
        ArrayList<ArrayList<Integer>> inclusivecopy1 = inclusivecopy;
        ArrayList<ArrayList<Integer>> inclusivecopy2 = inclusivecopy1;
        ArrayList<ArrayList<Integer>> inclusivecopy3 = inclusivecopy2;
        inclusivecopy3.add(getVertices().get(0));
        for (int i = 1; i < getVertices().size(); i++) {
            inclusivecopy3.add(new ArrayList<>());
            for (int j = 0; j < getVertices().get(i).size(); j++) {
                int second = getVertices().get(i).get(j);
                inclusivecopy3.get(i).add(second);
                if (i != second) {
                    inclusivecopy3.get(second).add(i);
                }
            }
        }
        return Iteration.iteration(inclusivecopy3.get(v).iterator());
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        return successors(v);
    }

    @Override
    public int add(int u, int v) {
        checkMyVertex(v);
        checkMyVertex(u);
        int temp1 = Integer.max(u, v);
        int temp2 = Integer.min(u, v);
        u = temp1;
        v = temp2;
        if (!contains(u, v) && !contains(v, u)) {
            getVertices().get(u).add(v);
        }
        return (edgeId(u, v));
    }

    @Override
    public void remove(int u, int v) {
        if (getVertices().get(u).contains(v)) {
            getVertices().get(u).remove(Integer.valueOf(v));
        }

        if (getVertices().get(v).contains(u)) {
            getVertices().get(v).remove(Integer.valueOf(u));
        }
    }

    @Override
    public boolean contains(int u, int v) {
        return contains(u) && contains(v)
                && ((getVertices().get(u).contains(v)
                || getVertices().get(v).contains(u)));
    }

    @Override
    protected int edgeId(int u, int v) {
        int temp1 = Integer.max(u, v);
        int temp2 = Integer.min(u, v);
        u = temp1;
        v = temp2;
        return (((u + v) * (u + v + 1)) / 2) + v;
    }

}
