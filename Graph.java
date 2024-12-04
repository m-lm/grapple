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
        Node node1 = nodes.get(srcId);
        Node node2 = nodes.get(targId);
        Edge newEdge = new Edge(id, node1, node2, relation);
        node1.addEdge(newEdge);
        node2.addEdge(newEdge);
        edges.put(id, newEdge);
    }

    public void removeEdge(int id) {
        edges.remove(id);
    }

    public Edge getEdge(int id) {
        return edges.get(id);
    }

    // -------------------- Graph Operations

    public void printNodes() {
        for (Integer id : this.nodes.keySet()) {
            Node n = this.nodes.get(id);
            String template = """
                    Node ID: %d
                    Label: %s
                    """;
            String display = String.format(template, n.getId(), n.getLabel());
            System.out.println(display);
        }
    }

    public void printEdges() {
        for (Integer id : this.edges.keySet()) {
            Edge e = this.edges.get(id);
            String template = """
                    Edge ID: %d
                    Entity 1: %s
                    Relation: %s
                    Entity 2: %s
                    """;
            String display = String.format(template, e.getId(), e.getSource().getLabel(), e.getRelation(), e.getTarget().getLabel());
            System.out.println(display);
        }
    }
}
