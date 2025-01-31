package src.core;
import java.util.*;
import src.utils.IdGenerator;

public class Node {
    private int id; // Unique numeric identifier
    private String label; // Unique plaintext identifier
    private Set<String> tags; // Categories that describe the node entity
    private Map<String, Object> properties; // String key to Object type (any) value for attribute data
    private Set<Edge> edges; // Relationships

    public Node(String label) {
        this.id = IdGenerator.generate();
        this.label = label;
        this.tags = new HashSet<String>();
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

    public String getLabel(boolean showId) {
        // Get label of Node, i.e. entity represented by Node, along with ID
        if (showId) {
            return String.format("%s (%s)", this.label, this.id);
        }
        return this.label;
    }

    public Set<String> getTags() {
        return this.tags;
    }

    public void addTag(String newTag) {
        this.tags.add(newTag);
    }

    public void addTags(Iterable<String> newTags) {
        for (String t : newTags) {
            this.tags.add(t);
        }
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

    public Set<String> getNeighborLabels() {
        // Get adjacent Nodes connected to this Node
        Set<String> adjs = new HashSet<String>();
        for (Edge e : this.edges) {
            if (this == e.getSource()) {
                adjs.add(e.getTarget().getLabel(true));
            }
            else {
                adjs.add(e.getSource().getLabel(true));
            }
        }
        return adjs;
    }
}