import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
    private static final String CSV_FILE_PATH = "edgess.csv";

    private static Map<String, Map<String, Integer>> createGraphFromCSV() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            reader.readLine(); // Skip the header line

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                String source = data[0];
                Map<String, Integer> neighbors = new HashMap<>();

                for (int i = 1; i < data.length - 1; i += 2) {
                    String destination = data[i];
                    int cost = Integer.parseInt(data[i + 1]);
                    neighbors.put(destination, cost);
                }

                graph.put(source, neighbors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    private static List<String> findShortestPathWithBranchNodes(Map<String, Map<String, Integer>> graph, String source, String destination) {
        // Check if the source or destination is a branch node
        boolean sourceIsBranchNode = graph.get(source).size() > 2;
        boolean destinationIsBranchNode = graph.get(destination).size() > 2;

        if (!sourceIsBranchNode && !destinationIsBranchNode) {
            // If both source and destination are not branch nodes, find the shortest path using Dijkstra's algorithm
            return findShortestPath(graph, source, destination);
        }

        // Create a modified graph that only includes branch nodes and their direct neighbors
        Map<String, Map<String, Integer>> modifiedGraph = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
            String node = entry.getKey();
            Map<String, Integer> neighbors = entry.getValue();

            if (neighbors.size() > 2 || node.equals(source) || node.equals(destination)) {
                modifiedGraph.put(node, neighbors);
            }
        }

        // Find the shortest path in the modified graph
        return findShortestPath(modifiedGraph, source, destination);
    }

    private static List<String> findShortestPath(Map<String, Map<String, Integer>> graph, String source, String destination) {
        // Implement Dijkstra's algorithm to find the shortest path
        // (You can use the code you previously had for Dijkstra's algorithm here)
        // ...

        // Placeholder code to return a dummy path
        List<String> path = new ArrayList<>();
        path.add(source);
        path.add(destination);

        return path;
    }

    public static void main(String[] args) {
        // Create the graph from CSV
        Map<String, Map<String, Integer>> graph = createGraphFromCSV();

        // Find the shortest path between two nodes, considering branch nodes
        String source = "Mahatma Nagar";
        String destination = "CIDCO";
        List<String> shortestPath = findShortestPathWithBranchNodes(graph, source, destination);

        // Print the shortest path
        System.out.println("Shortest Path:");
        for (String node : shortestPath) {
            System.out.println(node);
        }
    }
}
