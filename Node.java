import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

public class Node {
    private int id; 
    private String label;
    private Map<String, Object> properties; // String key to Object type (any) value
    private Set<Edge> edges;

    Node(int id, String label) {
        this.id = id;
        this.label = label;
        this.properties = new HashMap<String, Object>();
        this.edges = new HashSet<Edge>();
    }
    
    public int getId() {
        return this.id;
    }

    public String getLabel() {
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
        this.properties.put(key, val);
    }

    public Set<Edge> getEdges() {
        return this.edges;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
}