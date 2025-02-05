package storage;

import core.Graph;
import java.io.IOException;

public interface Storage {
    // Interface adapter for different storage classes to implement.
    void saveGraph(Graph g, String filePath) throws IOException;
    Graph loadGraph(String filePath) throws IOException;
}
