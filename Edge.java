package individualProject;

public class Edge {
    private Node node;
    private byte visited;

    public Edge(Node node) {
        this.node = node;
        visited = 0;
    }

    public Node getNode() {
        return node;
    }

    public byte getVisited() {
        return visited;
    }

    public void setVisited(byte visited) {
        this.visited = visited;
    }

    public void incrVisited() {
        this.visited++;
    }

    public void clearVisited() {
        visited = 0;
    }

    public String toString() {
        return "" + node.getNumber();
    }
}
