package graph;
import java.util.ArrayList;
/* See restrictions in Graph.java. */

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Tony Tu
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    /**
    @Override
    public int outDegree(int v) {
        return 1;
    }
     */

    @Override
    public int inDegree(int v) {
        int count = 0;
        Iteration<Integer> pred = predecessors(v);
        for (int i : pred) {
            count++;
        }
        return count;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        ArrayList<Integer> pred = new ArrayList<>();
        for (int i = 1; i < getVertices().size(); i++) {
            for (int j: getVertices().get(i)) {
                if (j == v) {
                    pred.add(i);
                }
            }
        }
        return Iteration.iteration(pred.iterator());
    }
}
