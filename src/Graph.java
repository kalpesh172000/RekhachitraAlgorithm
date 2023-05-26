import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
    private static class Node {
        String name;
        int distance;

        Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    private static final String CSV_FILE_PATH = "graph.csv";

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

   private static List<String> findShortestPathWithBranchNodes(String source, String destination, Map<String, Map<String, Integer>> graph) {
    Map<String, Integer> distances = new HashMap<>();
    Map<String, String> previousNodes = new HashMap<>();
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
    Set<String> visited = new HashSet<>();

    for (String node : graph.keySet()) {
        distances.put(node, Integer.MAX_VALUE);
        previousNodes.put(node, null);
    }

    distances.put(source, 0);
    priorityQueue.add(new Node(source, 0));

    while (!priorityQueue.isEmpty()) {
        Node currentNode = priorityQueue.poll();
        String current = currentNode.name;

        if (visited.contains(current)) {
            continue;
        }

        visited.add(current);

        if (current.equals(destination)) {
            break;
        }

        Map<String, Integer> neighbors = graph.get(current);
        if (neighbors == null) {
            continue;
        }

        for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
            String neighborName = neighbor.getKey();
            int distance = neighbor.getValue();

            int totalDistance = distances.get(current) + distance;
            if (totalDistance < distances.get(neighborName)) {
                distances.put(neighborName, totalDistance);
                previousNodes.put(neighborName, current);
                priorityQueue.add(new Node(neighborName, totalDistance));
            }
        }
    }

    List<String> path = new ArrayList<>();
    String current = destination;
    while (current != null) {
        path.add(0, current);
        current = previousNodes.get(current);
    }

    return path;
}


    private static List<String> findShortestPath(Map<String, Map<String, Integer>> graph, String source, String destination) {
    Map<String, Integer> distances = new HashMap<>();
    Map<String, String> previousNodes = new HashMap<>();
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
    Set<String> visited = new HashSet<>();

    for (String node : graph.keySet()) {
        distances.put(node, Integer.MAX_VALUE);
        previousNodes.put(node, null);
    }

    distances.put(source, 0);
    priorityQueue.add(new Node(source, 0));

    while (!priorityQueue.isEmpty()) {
        Node currentNode = priorityQueue.poll();
        String current = currentNode.name;

        if (visited.contains(current)) {
            continue;
        }

        visited.add(current);

        if (current.equals(destination)) {
            break;
        }

        Map<String, Integer> neighbors = graph.get(current);
        if (neighbors == null) {
            continue;
        }

        for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
            String neighborName = neighbor.getKey();
            int distance = neighbor.getValue();

            int totalDistance = distances.get(current) + distance;
            if (totalDistance < distances.get(neighborName)) {
                distances.put(neighborName, totalDistance);
                previousNodes.put(neighborName, current);
                priorityQueue.add(new Node(neighborName, totalDistance));
            }
        }
    }

    List<String> path = new ArrayList<>();
    String current = destination;
    while (current != null) {
        path.add(0, current);
        current = previousNodes.get(current);
    }

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
}