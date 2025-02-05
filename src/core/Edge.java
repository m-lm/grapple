package core;
import java.util.*;
import utils.IdGenerator;

public class Edge {
    private int id;
    private Node source;
    private Node target;
    private String relation;
    private Map<String, Object> properties;
    private boolean isHyper;

    public Edge(Node source, Node target, String relation) {
        this.id = IdGenerator.generate();
        this.source = source;
        this.target = target;
        this.relation = relation;
        this.isHyper = false;
        this.properties = new HashMap<String, Object>();
    }

    public int getId() {
        // Get ID
        return this.id;
    } 

    public String getRelation() {
        // Get relation label describing connection
        return this.relation;
    }

    public String getRelation(boolean showId) {
        // Get relation label describing connection, along with ID
        if (showId) {
            return String.format("%s (%s)", this.relation, this.id);
        }
        return this.relation;
    }

    public Node getSource() {
        // Get source Node; in a directed graph, the source is the Node the Edge points from
        return this.source;
    }

    public Node getTarget() {
        // Get target Node; in a directed graph, the target is the Node the Edge points to
        return this.target;
    }

    public boolean isHyper() {
        // Check whether Edge is an hyperedge, i.e. connects to more than two nodes
        // In which case, there would not only be source and target Nodes to consider
        return this.isHyper;
    }

    public Map<String, Object> getProps() {
        // Get the whole map of properties
        return this.properties;
    }

    public Object getProp(String key) {
        // Get specific property if it exists
        return this.properties.get(key);
    }

    public void addProp(String key, Object val) {
        // Add a new property to this Edge
        this.properties.put(key, val);
    }
}
