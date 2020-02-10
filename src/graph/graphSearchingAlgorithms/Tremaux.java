package graph.graphSearchingAlgorithms;

import graph.Edge;
import graph.Graph;
import graph.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Tremaux {
    public LinkedList<Integer> searchForExit(Graph graph) {
        Node node = graph.getStartNode();
        Node previousNode = node;
        while (node != graph.getEndNode()) {

            ArrayList<Edge> next = node.getNext();

            if (next.size() == 1) {
                Node nextNode = next.get(0).getNode();
                node.incrEdge(nextNode);
                nextNode.incrEdge(node);
                previousNode = node;
                node = nextNode;
            } else {
                boolean allNotMarked = true;
                for (Edge edge : next) {
                    if (edge.getNode() != previousNode && edge.getVisited() != 0) {
                        allNotMarked = false;
                        break;
                    }
                }

                if (allNotMarked) {
                    Node nextNode = getRandomNotMarkedNode(next);
                    node.incrEdge(nextNode);
                    nextNode.incrEdge(node);
                    previousNode = node;
                    node = nextNode;
                } else if (node.getEdge(previousNode) == 1) {
                    node.incrEdge(previousNode);
                    previousNode.incrEdge(node);
                    Node nextNode = previousNode;
                    previousNode = node;
                    node = nextNode;
                } else {
                    Node nextNode = getRandomNode(next);
                    node.incrEdge(nextNode);
                    nextNode.incrEdge(node);
                    previousNode = node;
                    node = nextNode;
                }
            }
        }
        LinkedList<Integer> result = new LinkedList<>();
        previousNode = null;
        result.add(node.getNumber());
        while (node != graph.getStartNode()) {
            for (Edge edge : node.getNext()) {
                if (edge.getVisited() == 1 && edge.getNode() != previousNode) {
                    result.add(edge.getNode().getNumber());
                    previousNode = node;
                    node = edge.getNode();
                    break;
                }
            }
        }
        return result;
    }

    public Node getRandomNotMarkedNode(ArrayList<Edge> next) {
        ArrayList<Edge> available = new ArrayList<>();
        for (Edge edge : next) {
            if (edge.getVisited() == 0)
                available.add(edge);
        }
        Random r = new Random();
        int nextEdgeId = r.nextInt(available.size());
        return available.get(nextEdgeId).getNode();
    }

    public Node getRandomNode(ArrayList<Edge> next) {
        ArrayList<Edge> available = new ArrayList<>();
        int min_visited = 1;
        for (Edge edge : next) {
            if (edge.getVisited() == min_visited)
                available.add(edge);
            else if (edge.getVisited() == 0) {
                available = new ArrayList<>();
                available.add(edge);
                min_visited = 0;
            }
        }
        Random r = new Random();
        int nextEdgeId = r.nextInt(available.size());
        return available.get(nextEdgeId).getNode();
    }


}
