package graph;
import java.util.LinkedList;
/* See restrictions in Graph.java. */

/** Implements a breadth-first traversal of a graph.  Generally, the
 *  client will extend this class, overriding the visit method as desired
 *  (by default, it does nothing).
 *  @author Tony Tu
 */
public class BreadthFirstTraversal extends Traversal {

    /** A breadth-first Traversal of G. */
    protected BreadthFirstTraversal(Graph G) {
        super(G, new LinkedList<Integer>());
    }

    @Override
    protected boolean visit(int v) {
        return super.visit(v);
    }

}
