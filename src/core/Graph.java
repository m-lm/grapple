package src.core;
import java.util.*;

// Graph object connects Nodes/Edges for Nodes/Edges/Graph operations.
public class Graph {
    private Map<Integer, Node> nodes;
    private Map<Integer, Edge> edges;
    private boolean isDirected;
    private boolean isHyper;

    public Graph(boolean isDirected) {
        this.nodes = new HashMap<Integer, Node>();
        this.edges = new HashMap<Integer, Edge>();
        this.isDirected = isDirected;
        this.isHyper = false;
    }

    // -------------------- Node/Edge Operations

    public int addNode(String label) {
        // Add a new Node to the Graph. Returns Node's ID, not object
        Node newNode = new Node(label);
        this.nodes.put(newNode.getId(), newNode);
        return newNode.getId();
    }

    public void removeNode(int id) {
        // Remove Node from the Graph, after disconnecting edges from Graph map
        for (Integer edgeID : this.edges.keySet()) {
            Edge e = this.edges.get(edgeID);
            if (e.getSource() == this.nodes.get(id) || e.getTarget() == this.nodes.get(id)) {
                // NOTE: this doesn't exactly work if Graph is a Hypergraph
                this.edges.remove(edgeID);
            }
            this.edges.remove(id);
        }
        this.nodes.remove(id);
    }

    public Node getNode(int id) {
        // Get a Node from the Graph
        return this.nodes.get(id);
    }
    
    public int addEdge(int srcId, int targId, String relation) {
        // Add an Edge to the Graph, and connect it. Returns Edge's ID, not object
        // NOTE: Need to rework this to handle directed Graph cases
        Node node1 = this.nodes.get(srcId);
        Node node2 = this.nodes.get(targId);
        Edge newEdge = new Edge(node1, node2, relation);
        node1.addEdge(newEdge);
        node2.addEdge(newEdge);
        this.edges.put(newEdge.getId(), newEdge);
        return newEdge.getId();
    }

    public void removeEdge(int id) {
        // Remove an Edge from the Graph, after disconnecting it
        this.edges.get(id).getSource().removeEdge(this.edges.get(id));
        this.edges.get(id).getTarget().removeEdge(this.edges.get(id));
        this.edges.remove(id);
    }

    public Edge getEdge(int id) {
        // Get an Edge from the Graph
        return this.edges.get(id);
    }

    // -------------------- Graph Operations

    public Map<Integer, Node> getNodes() {
        return Map.copyOf(nodes);
    }

    public Map<Integer, Edge> getEdges() {
        return Map.copyOf(edges);
    }

    public void printNodes() {
        // Display Node details of Graph
        for (Integer id : this.nodes.keySet()) {
            Node n = this.nodes.get(id);
            String template = """
                    Node ID: %d (%s)
                    Label: %s
                    Adjacent: %s
                    """;
            String display = String.format(
                template, 
                n.getId(), 
                n, 
                n.getLabel(true), 
                n.getAdjacent().toString());
            System.out.println(display);
        }
    }

    public void printEdges() {
        // Display Edge details of Graph
        for (Integer id : this.edges.keySet()) {
            Edge e = this.edges.get(id);
            String template = """
                    Edge ID: %d (%s)
                    Entity 1: %s
                    Relation: %s
                    Entity 2: %s
                    """;
            String display = String.format(
                template, 
                e.getId(), 
                e, 
                e.getSource().getLabel(true), 
                e.getRelation(), 
                e.getTarget().getLabel(true));
            System.out.println(display);
        }
    }
}
