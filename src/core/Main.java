package src.core;
import java.util.ArrayList;
import src.utils.GraphVisualizer;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n[[ ------------ gr4ppl GDBMS ------------ ]]\n");
        Graph g = new Graph(false ,false);
        int hpNID = g.addNode("Harry Potter");
        int rwNID = g.addNode("Ron Weasley");
        int ddNID = g.addNode("Albus Dumbledore");
        int smNID = g.addNode("Seamus");
        int hgNID = g.addNode("Hermione Granger");
        int vdmNID = g.addNode("Voldemort");
        int frEID = g.addEdge(hpNID, rwNID,  "FRIENDS_WITH");
        int frEID2 = g.addEdge(rwNID, smNID,  "FRIENDS_WITH");
        int frEID3 = g.addEdge(hpNID, hgNID, "FRIENDS_WITH");
        int frEID4 = g.addEdge(hgNID, rwNID, "FRIENDS_WITH");
        g.addEdge(ddNID, vdmNID, "FORMER_MENTOR");
        g.addEdge(ddNID, hpNID, "MENTOR");
        g.printNodes();
        g.printEdges();

        System.out.println(g.bfs(ddNID, smNID));

        GraphVisualizer gviz = new GraphVisualizer();
        gviz.visualize(g.getNodes(), g.getEdges());
        System.out.println("\n[[ ------------ completed ------------ ]]\n");
    }
}