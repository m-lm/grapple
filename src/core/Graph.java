package core;
import java.util.*;

// Graph object connects Nodes/Edges for Nodes/Edges/Graph operations.
public class Graph {
    private Map<Integer, Node> nodes;
    private Map<String, Node> labelLookup;
    private Map<Integer, Edge> edges;
    private boolean isWeighted;
    private boolean isDirected;

    public Graph(boolean isWeighted, boolean isDirected) {
        this.nodes = new HashMap<Integer, Node>();
        this.edges = new HashMap<Integer, Edge>();
        this.labelLookup = new HashMap<String, Node>();
        this.isWeighted = isWeighted;
        this.isDirected = isDirected;
    }

    // -------------------- Node/Edge Operations

    public int addNode(String label) {
        // Add a new Node to the Graph. Returns Node's ID, not object
        if (this.labelLookup.get(label) != null) {
            // Label/node already exists; enforce unique labels. Return existent node ID
            System.out.println("Error: Unique node label already exists. Returning label's node ID.");
            return this.labelLookup.get(label).getId();
        }
        Node newNode = new Node(label);
        this.nodes.put(newNode.getId(), newNode);
        this.labelLookup.put(label, newNode);
        return newNode.getId();
    }

    public void removeNode(int id) {
        // Remove Node from the Graph and labelLookup, after disconnecting edges from Graph map
        for (Integer edgeID : this.edges.keySet()) {
            Edge e = this.edges.get(edgeID);
            
            if (e.getSource() == this.nodes.get(id) || e.getTarget() == this.nodes.get(id)) {
                this.edges.remove(edgeID);
            }
            this.edges.remove(id);
        }
        this.nodes.remove(id);
        this.labelLookup.remove(this.nodes.get(id).getLabel());
    }

    public Node getNode(int id) {
        // Get a Node from the Graph
        return this.nodes.get(id);
    }

    public Node getNode(String label) {
        // Given a unique label, get a Node from the Graph
        return this.labelLookup.get(label);
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
                n.getNeighborLabels().toString());
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

    public Set<Integer> getNeighbors(int id) {
        // Get IDs of neighboring nodes
        Set<Integer> adjs = new HashSet<Integer>();
        Node node = this.nodes.get(id);
        for (Edge e : node.getEdges()) {
            if (node == e.getSource()) {
                adjs.add(e.getTarget().getId());
            }
            else {
                adjs.add(e.getSource().getId());
            }
        }
        return adjs;
    }

    public void printPath(Iterable<Integer> path) {
        // Takes an iterable and prints it as a graph path
        Iterator<Integer> iter = path.iterator();
        if (!iter.hasNext()) {
            System.out.println("No path");
            return;
        }
        System.out.println();
        System.out.print(this.nodes.get(iter.next()).getLabel());
        while (iter.hasNext()) {
            System.out.print(" -> " + this.nodes.get(iter.next()).getLabel());
        }
        System.out.println();
    }

    private ArrayList<Integer> constructPath(Map<Integer, Integer> predecessors, int start, int end) {
        // Helper method to reconstruct shortest path in BFS algorithm
        ArrayList<Integer> path = new ArrayList<Integer>();
        for (Integer backtrack = end; backtrack != null; backtrack = predecessors.get(backtrack)) {
            path.add(backtrack);
        }
        Collections.reverse(path);
        return path;
    }

    private ArrayList<Integer> bfs(int start, int end) {
        // Helper method implementing Breadth-First Search for computing shortest path on unweighted graphs
        if (start == end) {
            return new ArrayList<Integer>();
        }
        Queue<Integer> queue = new ArrayDeque<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        Map<Integer, Integer> predecessors = new HashMap<Integer, Integer>(); // Map nodes and their predecessor nodes to recreate shortest path

        // Begin with starting node
        predecessors.put(start, null); 
        queue.add(start);
        
        // Start searching neighbors and constructing paths
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            if (!visited.contains(currentNode)) {
                visited.add(currentNode);
                for (int neighbor : getNeighbors(currentNode)) {
                    if (neighbor == end) {
                        predecessors.put(neighbor, currentNode); // Neighbor's predecessor is currentNode
                        return constructPath(predecessors, start, end);
                    }
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        predecessors.put(neighbor, currentNode); // Neighbor's predecessor is currentNode
                    }
                }
            }
            // If the node is the target destination, reconstruct shortest path using predecessors
            if (currentNode == end) {
                return constructPath(predecessors, start, end);
            }
        }

        // Return empty ArrayList if no shortest path found
        return new ArrayList<Integer>();
    }

    private ArrayList<Integer> dijkstra(int start, int end) {
        // Helper method implementing Dijkstra's Algorithm for computing shortest path on weighted graphs
        return new ArrayList<Integer>();
    }

    public ArrayList<Integer> getShortestPath(int start, int end) {
        // Use A*/Dijkstra or BFS depending on whether graph is weighted or unweighted respectively
        if (this.isWeighted) {
            return dijkstra(start, end);
        }
        else {
            return bfs(start, end);
        }
    }
}
