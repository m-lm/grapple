public class Main {
    public static void main(String[] args) {
        System.out.println("gr4ppl GDBMS");
        Graph g = new Graph(false);
        g.addNode(1, "Harry Potter");
        g.addNode(23, "Ron Weasley");
        g.addEdge(1, 1, 23, "FRIENDS_WITH");
        g.addEdge(2, 1, 23, "SAME_HOUSE_AS");
        g.printNodes();
        g.printEdges();
        g.removeEdge(1);
        g.printEdges();
    }
}