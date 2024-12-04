package src.core;
import java.util.*;
import src.utils.IdGenerator;

public class Node {
    private int id; 
    private String label;
    private Map<String, Object> properties; // String key to Object type (any) value
    private Set<Edge> edges;

    public Node(String label) {
        this.id = IdGenerator.generate();
        this.label = label;
        this.properties = new HashMap<String, Object>();
        this.edges = new HashSet<Edge>();
    }
    
    public int getId() {
        // Get ID of Node
        return this.id;
    }

    public String getLabel() {
        // Get label of Node, i.e. entity represented by Node
        return this.label;
    }

    public Map<String, Object> getProps() {
        // Get the whole map of properties
        return this.properties;
    }

    public Object getProp(String key) {
        // Get the value of the property if it exists, else null
        return this.properties.get(key);
    }

    public void addProp(String key, Object val) {
        // Add a property to Node
        this.properties.put(key, val);
    }

    public Set<Edge> getEdges() {
        // Get the Edge objects connected to this Node
        return this.edges;
    }

    public void addEdge(Edge edge) {
        // Add an Edge object to list of Edges connected to this Node
        this.edges.add(edge);
    }

    public void removeEdge(Edge edge) {
        // Remove an Edge object connected to this Node
        this.edges.remove(edge);
    }

    public Set<String> getAdjacent() {
        // Get adjacent Nodes connected to this Node
        Set<String> adjs = new HashSet<String>();
        for (Edge e : this.edges) {
            if (this == e.getSource()) {
                adjs.add(e.getTarget().getLabel() + " (" + e.getTarget().getId() + ")");
            }
            else {
                adjs.add(e.getSource().getLabel() + " (" + e.getSource().getId() + ")");
            }
        }
        return adjs;
    }
}