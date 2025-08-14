package ASTC;

import java.util.*;

public class PathFinder {

    static class Node {
        String name;
        List<Edge> neighbors = new ArrayList<>();
        int distance = Integer.MAX_VALUE;
        boolean visited = false;
        List<Node> shortestPath = new ArrayList<>();

        public Node(String n) {
            name = n;
        }

        public void addNeighbor(Node neighbor, int weight) {
            neighbors.add(new Edge(neighbor, weight));
        }
    }

    static class Edge {
        Node neighbor;
        int weight;

        public Edge(Node n, int w) {
            this.neighbor = n;
            this.weight = w;
        }
    }

    public static String dijkstra(Node[] nodes, Node start, Node end) {
        start.distance = 0;
        start.shortestPath.add(start);

        while (!end.visited) {
            Node current = getMinimumDistance(nodes);

            if (current == null) {
                return "No path found to destination.";
            }

            current.visited = true;

            for (int j = 0; j < current.neighbors.size(); j++) {
                Edge edge = current.neighbors.get(j);
                Node neighbor = edge.neighbor;
                int edgeWeight = edge.weight;
                if (!neighbor.visited) {
                    int newDistance = current.distance + edgeWeight;
                    if (newDistance < neighbor.distance) {
                        neighbor.distance = newDistance;
                        neighbor.shortestPath = new ArrayList<>(current.shortestPath);
                        neighbor.shortestPath.add(neighbor);
                    }
                }
            }
        }

        Node destinationNode = findNode(nodes, end.name);

        if (destinationNode != null && destinationNode.distance != Integer.MAX_VALUE) {
            StringBuilder result = new StringBuilder();
            result.append("Shortest path from ").append(start.name).append(" to ").append(destinationNode.name).append(": ");
            for (int k = 0; k < destinationNode.shortestPath.size(); k++) {
                result.append(destinationNode.shortestPath.get(k).name).append("- ");
            }
            result.append("\nCost: ").append(destinationNode.distance).append("$");
            return result.toString();
        } else {
            return "Invalid destination node.";
        }
    }

    private static Node getMinimumDistance(Node[] nodes) {
        Node minNode = null;
        int MIN_DISTANCE = Integer.MAX_VALUE;
        for (int i = 0; i < nodes.length; i++) {
            if (!nodes[i].visited && nodes[i].distance <= MIN_DISTANCE) {
                minNode = nodes[i];
                MIN_DISTANCE = nodes[i].distance;
            }
        }
        return minNode;
    }

    public static Node findNode(Node[] nodes, String nodeName) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].name.equalsIgnoreCase(nodeName)) {
                return nodes[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Node[] nodes = createNodes();
        Node startNode = findNode(nodes, "Pakistan");
        Node endNode = findNode(nodes, "Russia");

        if (startNode != null && endNode != null) {
            String result = dijkstra(nodes, startNode, endNode);
            System.out.println(result);
        } else {
            System.out.println("Invalid start or end node.");
        }
    }

    public static Node[] createNodes() {
        Node[] nodes = {
                new Node("Pakistan"), new Node("India"),
                new Node("China"), new Node("Iran"),
                new Node("Afghanistan"), new Node("Russia")
        };

        nodes[0].addNeighbor(nodes[1], 5);
        nodes[0].addNeighbor(nodes[2], 8);

        nodes[1].addNeighbor(nodes[0], 5);
        nodes[1].addNeighbor(nodes[2], 6);
        nodes[1].addNeighbor(nodes[4], 4);

        nodes[2].addNeighbor(nodes[0], 8);
        nodes[2].addNeighbor(nodes[1], 6);
        nodes[2].addNeighbor(nodes[3], 3);
        nodes[2].addNeighbor(nodes[5], 10);

        nodes[3].addNeighbor(nodes[2], 3);
        nodes[3].addNeighbor(nodes[4], 7);

        nodes[4].addNeighbor(nodes[1], 4);
        nodes[4].addNeighbor(nodes[3], 7);
        nodes[4].addNeighbor(nodes[5], 5);

        nodes[5].addNeighbor(nodes[2], 10);
        nodes[5].addNeighbor(nodes[4], 5);

        return nodes;
    }
}
