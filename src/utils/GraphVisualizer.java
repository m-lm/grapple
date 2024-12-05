package src.utils;
import java.util.*;
import src.core.Graph;
import src.core.Edge;
import src.core.Node;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

public class GraphVisualizer {
    private static int tracker = 0;
    private SingleGraph graphDisplay;

    public GraphVisualizer() {
        this.graphDisplay = new SingleGraph("gr4ppl Visualizer - " + tracker++);
        System.setProperty("org.graphstream.ui", "swing");
        this.graphDisplay.setStrict(false);
        this.graphDisplay.setAutoCreate(true);
    }

    public void visualize(Map<Integer, Node> nodes, Map<Integer, Edge> edges) {
        for (Node n : nodes.values()) {
            org.graphstream.graph.Node vizNode = this.graphDisplay.addNode(Integer.toString(n.getId()));
            vizNode.setAttribute("ui.label", n.getLabel());
        }

        for (Edge e : edges.values()) {
            org.graphstream.graph.Edge vizEdge = this.graphDisplay.addEdge(Integer.toString(e.getId()), Integer.toString(e.getSource().getId()), Integer.toString(e.getTarget().getId()));
        }

        this.graphDisplay.display();
    }
}