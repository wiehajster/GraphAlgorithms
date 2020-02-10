package individualProject;

public class Graph {
    private Node[] graph;
    private Node startNode;
    private Node endNode;

    public Graph(Node[] graph, Node startNode, Node endNode) {
        this.graph = graph;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node : graph) {
            stringBuilder.append(node + "\n");
        }
        stringBuilder.append("[start] = " + startNode.getNumber() + " [end] = " + endNode.getNumber() + "\n");
        return stringBuilder.toString();
    }

    public Node[] getGraph() {
        return graph;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void clearVisited() {
        for (Node node : graph) {
            node.setVisited((byte) 0);
        }
    }

    public void clearEdges() {
        for (Node node : graph) {
            for (Edge edge : node.getNext()) {
                edge.clearVisited();
            }
        }
    }

}
