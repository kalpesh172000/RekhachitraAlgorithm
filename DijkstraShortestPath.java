import java.util.*;

public class DijkstraShortestPath {
    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = createGraph();

        String sourceNode = "Mahatma Nagar";
        String destinationNode = "CIDCO";

        List<String> shortestPath = findShortestPath(graph, sourceNode, destinationNode);

        if (shortestPath != null) {
            System.out.println("Shortest path from " + sourceNode + " to " + destinationNode + ":");
            System.out.println(shortestPath);
        } else {
            System.out.println("No path found from " + sourceNode + " to " + destinationNode);
        }
    }

    private static Map<String, Map<String, Integer>> createGraph() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        
        // Add nodes and their neighbors with weights to the graph
        // Replace the values with your actual graph representation
        
        graph.put("Mahatma Nagar", new HashMap<>());
        graph.get("Mahatma Nagar").put("Gangapur Road", 2);
        graph.get("Mahatma Nagar").put("Panchavati", 5);
        graph.get("Mahatma Nagar").put("College Road", 8);
        
        graph.put("Gangapur Road", new HashMap<>());
        graph.get("Gangapur Road").put("Mahatma Nagar", 4);
        graph.get("Gangapur Road").put("Panchavati", 6);
        graph.get("Gangapur Road").put("CIDCO", 9);
        
        graph.put("Panchavati", new HashMap<>());
        graph.get("Panchavati").put("Gangapur Road", 5);
        graph.get("Panchavati").put("Mahatma Nagar", 4);
        graph.get("Panchavati").put("College Road", 3);
        
        graph.put("College Road", new HashMap<>());
        graph.get("College Road").put("Mahatma Nagar", 8);
        graph.get("College Road").put("Panchavati", 3);
        graph.get("College Road").put("CIDCO", 9);
        
        graph.put("CIDCO", new HashMap<>());
        graph.get("CIDCO").put("College Road", 6);
        graph.get("CIDCO").put("Panchavati", 7);
        graph.get("CIDCO").put("Gangapur Road", 9);

        return graph;
    }

    private static List<String> findShortestPath(Map<String, Map<String, Integer>> graph, String sourceNode, String destinationNode) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
        PriorityQueue<NodeCostPair> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));

        // Initialize distances with infinity for all nodes except the source node
        for (String node : graph.keySet()) {
            if (node.equals(sourceNode)) {
                distances.put(node, 0);
            } else {
                distances.put(node, Integer.MAX_VALUE);
            }
        }

        minHeap.offer(new NodeCostPair(sourceNode, 0));

        while (!minHeap.isEmpty()) {
            NodeCostPair currentPair = minHeap.poll();
            String currentNode = currentPair.node;

            if (currentNode.equals(destinationNode)) {
                // Destination node reached, construct the path
                return constructPath(previousNodes, currentNode);
            }

            if (distances.get(currentNode) < currentPair.cost) {
                // Skip this node if a shorter path to it has already been found
                continue;
            }

            Map<String, Integer> neighbors = graph.get(currentNode);

            for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                String nextNode = neighbor.getKey();
                int newCost = distances.get(currentNode) + neighbor.getValue();

                if (newCost < distances.get(nextNode)) {
                    distances.put(nextNode, newCost);
                    previousNodes.put(nextNode, currentNode);
                    minHeap.offer(new NodeCostPair(nextNode, newCost));
                }
            }
        }

        // No path found from source to destination
        return null;
    }

    private static List<String> constructPath(Map<String, String> previousNodes, String currentNode) {
        LinkedList<String> path = new LinkedList<>();
        path.addFirst(currentNode);

        while (previousNodes.containsKey(currentNode)) {
            currentNode = previousNodes.get(currentNode);
            path.addFirst(currentNode);
        }

        return path;
    }

    static class NodeCostPair {
        String node;
        int cost;

        NodeCostPair(String node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
