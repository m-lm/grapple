package src.utils;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static AtomicInteger idCounter = new AtomicInteger(0);
    private static Set<Integer> usedIds = new HashSet<Integer>();

    public static int generate() {
        // Generate unique global ID for use in both Nodes and Edges
        int newId = idCounter.getAndIncrement();
        while (usedIds.contains(newId)) {
            // If generated ID happens to already be assigned, keep incrementing until one is unassigned
            newId = idCounter.getAndIncrement();
        }
        return newId;
    }
}
