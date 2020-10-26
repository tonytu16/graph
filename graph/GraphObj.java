package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Collections;

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Tony Tu
 */
abstract class GraphObj extends Graph {

    /** An ArrayList of ArrayLists that contain edges
     * from and to other integer vertices. */
    private ArrayList<ArrayList<Integer>> vertices;

    /** A new, empty Graph. */
    GraphObj() {
        vertices = new ArrayList<>();
        vertices.add(new ArrayList<>());
    }

    /** Return my ArrayList<ArrayList<Integer>> of vertices. */
    public ArrayList<ArrayList<Integer>> getVertices() {
        return vertices;
    }

    @Override
    public int vertexSize() {
        return vertices.get(0).size();
    }

    @Override
    public int maxVertex() {
        return Collections.max(vertices.get(0));
    }

    @Override
    public int edgeSize() {
        int e = 0;
        for (int i = 1; i < vertices.size(); i++) {
            for (int x: vertices.get(i)) {
                e++;
            }
        }
        return e;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        int count = 0;
        for (int i: successors(v)) {
            count++;
        }
        return count;
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        return (vertices.get(0).contains(u));
    }

    @Override
    public boolean contains(int u, int v) {
        return contains(u) && contains(v) && (vertices.get(u).contains(v));
    }

    @Override
    public int add() {
        for (int i = 1; i <= vertices.get(0).size(); i++) {
            if (!vertices.get(0).contains(i)) {
                vertices.get(0).add(i);
                return i;
            }
        }
        vertices.get(0).add(vertices.get(0).size() + 1);
        vertices.add(new ArrayList<>());
        return vertices.size() - 1;
    }

    @Override
    public int add(int u, int v) {
        checkMyVertex(v);
        checkMyVertex(u);
        if (!contains(u, v)) {
            vertices.get(u).add(v);
        }
        return (edgeId(u, v));
    }

    @Override
    public void remove(int v) {
        vertices.get(0).remove(Integer.valueOf(v));
        ArrayList<Integer> store = new ArrayList<>();
        for (int i: predecessors(v)) {
            store.add(i);
        }
        for (int i: store) {
            remove(i, v);
        }
        vertices.set(v, new ArrayList<>());
    }

    @Override
    public void remove(int u, int v) {
        vertices.get(u).remove(Integer.valueOf(v));
    }

    @Override
    public Iteration<Integer> vertices() {
        return Iteration.iteration(vertices.get(0).iterator());
    }

    @Override
    public Iteration<Integer> successors(int v) {
        return Iteration.iteration(vertices.get(v).iterator());
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        ArrayList<int[]> edges = new ArrayList<>();
        for (int i = 1; i < vertices.size(); i++) {
            for (int j: vertices.get(i)) {
                int[] edge = {i, j};
                edges.add(edge);
            }
        }
        return Iteration.iteration(edges.iterator());
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!vertices.get(0).contains(v)) {
            throw new NoSuchElementException("no such vertex");
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        return (((u + v) * (u + v + 1)) / 2) + v;
    }
}
