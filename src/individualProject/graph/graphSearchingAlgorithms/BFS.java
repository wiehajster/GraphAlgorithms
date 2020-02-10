package individualProject.graph.graphSearchingAlgorithms;

import individualProject.graph.Edge;
import individualProject.graph.Graph;
import individualProject.graph.Node;

import java.util.ArrayList;
import java.util.LinkedList;

public class BFS {
    public LinkedList<Integer> searchForExit(Graph graph) {

        LinkedList<Node> fifo = new LinkedList<>();
        int[] deletedNodes = new int[graph.getGraph().length];
        LinkedList<Integer> result = new LinkedList<>();
        fifo.add(graph.getStartNode());

        while (!fifo.isEmpty()) {
            Node node = fifo.poll();
            if (node.isVisited() == 1)
                continue;
            if (node == graph.getEndNode()) {
                int nodeNumber = graph.getEndNode().getNumber();
                while (nodeNumber != graph.getStartNode().getNumber()) {
                    result.add(nodeNumber);
                    nodeNumber = deletedNodes[nodeNumber];
                }
                result.add(nodeNumber);
            }
            ArrayList<Node> next = new ArrayList<>();
            for (Edge edge : node.getNext()) {
                if (edge.getNode().isVisited() == 0)
                    next.add(edge.getNode());
            }
            for (Node n : next) {
                fifo.add(n);
                deletedNodes[n.getNumber()] = node.getNumber();
            }
            node.incrVisited();
        }
        return result;
    }
}
