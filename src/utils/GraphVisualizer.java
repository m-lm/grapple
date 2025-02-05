package utils;
import java.util.*;
import core.Graph;
import core.Edge;
import core.Node;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

public class GraphVisualizer {
    private static int tracker = 0;
    private boolean isDirected;
    private SingleGraph graphDisplay;
    private Viewer viewer;
    private ArrayList<String> coloredIds;

    public GraphVisualizer() {
        this.graphDisplay = new SingleGraph(String.valueOf(tracker++));
        System.setProperty("org.graphstream.ui", "swing");
        this.graphDisplay.setStrict(false);
        this.graphDisplay.setAutoCreate(true);
        this.viewer = this.graphDisplay.display();
        this.coloredIds = new ArrayList<String>();
        this.isDirected = true;
    }

    public void visualize(Map<Integer, Node> nodes, Map<Integer, Edge> edges) {
        // Visualizes graph structure
        for (Node n : nodes.values()) {
            org.graphstream.graph.Node vizNode = this.graphDisplay.addNode(Integer.toString(n.getId()));
            vizNode.setAttribute("ui.label", n.getLabel(true));
        }

        for (Edge e : edges.values()) {
            String sourceId = Integer.toString(e.getSource().getId());
            String targetId = Integer.toString(e.getTarget().getId());
            org.graphstream.graph.Edge vizEdge = this.graphDisplay.addEdge(Integer.toString(e.getId()), sourceId, targetId, true);
            // Tentatively create bidirectional connection for FRIENDS_WITH edge relation
            if (e.getRelation().equals("FRIENDS_WITH")) {
                this.graphDisplay.addEdge(Integer.toString(e.getId()) + "_reverse", targetId, sourceId, this.isDirected);
            }
            vizEdge.setAttribute("ui.label", e.getRelation());
        }
    }

    /* 
    public void colorEdges(ArrayList<Integer> ids) {
        // Color edges for traversals. First clears styling then updates visualization.
        for (String coloredId: this.coloredIds) {
            org.graphstream.graph.Node coloredNode = this.graphDisplay.getNode(coloredId);
            coloredNode.removeAttribute("ui.style");
        }
        for (int i = 0; i < ids.size(); i++) {
            String currId = String.valueOf(ids.get(i));
            org.graphstream.graph.Node currNode = this.graphDisplay.getNode(currId);
            this.coloredIds.clear();
            this.coloredIds.add(currId);
            currNode.setAttribute("ui.style", "fill-color: green;");
        }
    }
        */

    public void close() {
        // Closes existing graph visualization
        this.viewer.close();
        this.graphDisplay.clear();
    }
}