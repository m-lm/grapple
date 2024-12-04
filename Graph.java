// Graph object connects Nodes/Edges for Nodes/Edges/Graph operations.

import java.util.*;

public class Graph {
    private Map<Integer, Node> nodes;
    private Map<Integer, Edge> edges;
    private boolean isDirected;
    private boolean isHyper;

    Graph(boolean isDirected) {
        this.nodes = new HashMap<Integer, Node>();
        this.edges = new HashMap<Integer, Edge>();
        this.isDirected = isDirected;
        this.isHyper = false;
    }

    // -------------------- Node/Edge Operations

    public void addNode(int id, String label) {
        Node newNode = new Node(id, label);
        nodes.put(id, newNode);
    }

    public void removeNode(int id) {
        nodes.remove(id);
    }

    public Node getNode(int id) {
        return nodes.get(id);
    }
    
    public void addEdge(int id, int srcId, int targId, String relation) {
        Edge newEdge = new Edge(id, nodes.get(srcId), nodes.get(targId), relation);
        edges.put(id, newEdge);
    }

    public void removeEdge(int id) {
        edges.remove(id);
    }

    public Edge getEdge(int id) {
        return edges.get(id);
    }

    // -------------------- Graph Operations

    public Map<Integer, Node> getNodes() {
        return this.nodes;
    }

    public Map<Integer, Edge> getEdges() {
        return this.edges;
    }

}
