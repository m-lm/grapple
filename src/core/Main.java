package core;
import java.util.*;
import utils.GraphVisualizer;

public class Main {
    public static void main(String[] args) {
        // Initialize graph, visualizer, and visualization thread
        Graph g = new Graph(false, false);
        GraphVisualizer gviz = new GraphVisualizer();
        Thread vizThread = new Thread(() -> {
            gviz.visualize(g.getNodes(), g.getEdges());
        });
        vizThread.setDaemon(true);
        vizThread.start();
        System.err.flush();
        System.out.flush();
        System.out.print("\n".repeat(70));

        System.out.println("\n[[ ------------ Grapple GDBMS ------------ ]]\n");

        // Enter main user interaction loop
        Scanner userInput = new Scanner(System.in);
        while (true) {
                System.out.println("""
                    \nGrapple Database Menu:
                    \na. Add node
                    \nb. Add relationship
                    \nc. Add tags
                    \nd. Import graph
                    \ne. Save graph
                    \nf. Shortest path
                    \ng. Quit
                    """);
                System.out.print("> ");
                String choice = userInput.nextLine().toLowerCase();
                if (choice.equals("a")) {
                    // Add node
                    System.out.println();
                    System.out.println("Input a new node.");
                    System.out.print("> ");
                    String newCharacter = userInput.nextLine();
                    g.addNode(newCharacter);
                }
                else if (choice.equals("b")) {
                    // Add edge
                    System.out.println();
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
                    // Add tags to node
                    System.out.println();
                    System.out.println("Input node to add tags to.");
                    System.out.print("Node\n> ");
                    String nodeLabel = userInput.nextLine();
                    System.out.println("Input new tags. Separate by commas for multiple tags.");
                    System.out.print("Tags\n> ");
                    String newTags = userInput.nextLine();
                    newTags = newTags.replaceAll("\\s", "");
                    Set<String> tagIterable = new HashSet<String>(Set.of(newTags.split(",")));
                    g.getNode(nodeLabel).addTags(tagIterable);
                    System.out.println("Node tags: " + g.getNode(nodeLabel).getTags());
                }
                else if (choice.equals("d")) {
                    // Import graph data
                    System.out.println();
                    continue;
                }
                else if (choice.equals("e")) {
                    // Save graph data
                    System.out.println();
                    continue;
                }
                else if (choice.equals("f")) {
                    // Compute shortest path between two nodes
                    System.out.println();
                    System.out.println("Input two nodes to compute the shortest path.");
                    System.out.print("Source Node\n> ");
                    String srcLabel = userInput.nextLine();
                    System.out.print("Target Node\n> ");
                    String targetLabel = userInput.nextLine();
                    int srcId = g.getNode(srcLabel).getId();
                    int targetId = g.getNode(targetLabel).getId();
                    ArrayList<Integer> shortestPath = g.getShortestPath(srcId, targetId);
                    g.printPath(shortestPath);
                    // gviz.colorEdges(shortestPath);
                }
                else {
                    // Exit program
                    System.out.println();
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
        System.exit(0); // Force exit for Graphstream processes
    }
}