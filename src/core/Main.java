package src.core;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

import src.utils.GraphVisualizer;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n[[ ------------ gr4ppl GDBMS ------------ ]]\n");
        Graph g = new Graph(false);
        int hpNID = g.addNode("Harry Potter");
        int rwNID = g.addNode("Ron Weasley");
        int frEID = g.addEdge(hpNID, rwNID,  "FRIENDS_WITH");
        int shEID = g.addEdge(hpNID, rwNID, "SAME_HOUSE_AS");
        g.printNodes();
        g.printEdges();
        g.removeEdge(frEID);
        g.printEdges();

        GraphVisualizer gviz = new GraphVisualizer();
        gviz.visualize(g.getNodes(), g.getEdges());
        System.out.println("\n[[ ------------ completed ------------ ]]\n");
    }
}