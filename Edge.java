import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Edge {
    private int id;
    private Node source;
    private Node target;
    private String relation;
    private HashMap<String, Object> properties;
    private boolean isDirected;
    private boolean isHyper;

    Edge(int id, Node source, Node target, String relation, boolean isDirected) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.relation = relation;
        this.isDirected = isDirected;
        this.isHyper = false;
        this.properties = new HashMap<String, Object>();
    }

    public int getId() {
        return this.id;
    } 

    public String getRelation() {
        return this.relation;
    }

    public Node getSource() {
        return this.source;
    }

    public Node getTarget() {
        return this.target;
    }

    public boolean isDirected() {
        return this.isDirected;
    }

    public boolean isHyper() {
        return this.isHyper;
    }

    public HashMap<String, Object> getProps() {
        return this.properties;
    }

    public Object getProp(String key) {
        return this.properties.get(key);
    }

    public void addProp(String key, Object val) {
        this.properties.put(key, val);
    }
}
