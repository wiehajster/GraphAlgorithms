package individualProject.graph;

import java.util.ArrayList;

public class Node {
    private byte visited;
    private int number;
    private ArrayList<Edge> next;

    public Node(int number, ArrayList<Edge> next) {
        this.number = number;
        this.next = next;
        visited = 0;
    }

    public Node(int number) {
        this.number = number;
        visited = 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[" + number + "] ");
        for (Edge edge : next) {
            stringBuilder.append(edge.getNode().getNumber() + " ");
        }
        return stringBuilder.toString();
    }

    public byte isVisited() {
        return visited;
    }

    public ArrayList<Edge> getNext() {
        return next;
    }

    public int getNumber() {
        return number;
    }

    public void setVisited(byte visited) {
        this.visited = visited;
    }

    public void incrVisited() {
        visited++;
    }

    public int getNextSize() {
        return next.size();
    }

    public int getElement(int id) {
        return next.get(id).getNode().getNumber();
    }

    public void setNext(ArrayList<Edge> next) {
        this.next = next;
    }

    public void incrEdge(Node node) {
        for (Edge edge : next) {
            if (edge.getNode() == node) {
                edge.incrVisited();
                break;
            }
        }
    }

    public byte getEdge(Node node) {
        for (Edge edge : next) {
            if (edge.getNode() == node)
                return edge.getVisited();
        }
        return -1;
    }

}
