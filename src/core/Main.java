package src.core;
import java.util.*;
import src.utils.GraphVisualizer;

public class Main {
    public static void main(String[] args) {
        // Initialize graph, visualizer, and visualization thread
        Graph g = new Graph(false ,false);
        GraphVisualizer gviz = new GraphVisualizer();
        Thread vizThread = new Thread(() -> {
            gviz.visualize(g.getNodes(), g.getEdges());
        });
        vizThread.setDaemon(true);
        vizThread.start();
        System.err.flush();
        System.out.flush();
        System.out.print("\n".repeat(70));

        System.out.println("\n[[ ------------ gr4ppl GDBMS ------------ ]]\n");

        // Enter main user interaction loop
        Scanner userInput = new Scanner(System.in);
        while (true) {
                System.out.println("""
                    \nGr4ppl Database Menu:
                    \na. Add node
                    \nb. Add relationship
                    \nc. Import graph
                    \nd. Save graph
                    \ne. Shortest Path
                    \nf. Quit
                    """);
                System.out.print("> ");
                String choice = userInput.nextLine().toLowerCase();
                if (choice.equals("a")) {
                    // Add node
                    System.out.println("\n");
                    System.out.println("Input a new node.");
                    System.out.print("> ");
                    String newCharacter = userInput.nextLine();
                    g.addNode(newCharacter);
                }
                else if (choice.equals("b")) {
                    // Add edge
                    System.out.println("\n");
                    System.out.println("Input a new relationship.");
                    System.out.print("Source Node\n> ");
                    String srcLabel = userInput.nextLine();
                    System.out.print("Target Node\n> ");
                    String targetLabel = userInput.nextLine();
                    System.out.print("Relationship\n> ");
                    String newRelation = userInput.nextLine();
                    g.addEdge(g.getNode(srcLabel).getId(), g.getNode(targetLabel).getId(), newRelation);
                }
                else if (choice.equals("c")) {
                    // Import graph data
                    System.out.println("\n");
                    continue;
                }
                else if (choice.equals("d")) {
                    // Save graph data
                    System.out.println("\n");
                    continue;
                }
                else if (choice.equals("e")) {
                    // Compute shortest path between two nodes
                    System.out.println("\n");
                    System.out.println("Input two nodes to compute the shortest path.");
                    System.out.print("Source Node\n> ");
                    String srcLabel = userInput.nextLine();
                    System.out.print("Target Node\n> ");
                    String targetLabel = userInput.nextLine();

                    int srcId = g.getNode(srcLabel).getId();
                    int targetId = g.getNode(targetLabel).getId();
                    ArrayList<Integer> shortestPath = g.getShortestPath(srcId, targetId);
                    System.out.println(shortestPath);
                    // gviz.colorEdges(shortestPath);
                }
                else {
                    System.out.println("\n");
                    g.printNodes();
                    g.printEdges();
                    break;
                }
                // Refresh visualization after graph updates
                gviz.visualize(g.getNodes(), g.getEdges());
        }

        // Close input stream from CLI
        userInput.close();
    
        System.out.println("\n[[ ------------ exited ------------ ]]\n");
        System.exit(0); // force exit for Graphstream processes
    }
}