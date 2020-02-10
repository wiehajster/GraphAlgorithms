package maze;

import graph.Edge;
import graph.Graph;
import graph.Node;

import java.util.ArrayList;

public class GraphGenerator {

    public Graph generateGraph(Maze maze) {
        int rows = (maze.getRows() + 1) / 2;
        int cols = (maze.getCols() + 1) / 2;
        int numberOfNodes = rows * cols;

        Node[] nodes = new Node[numberOfNodes];

        int number = 0;
        int startNode = -1;
        int endNode = -1;

        int maxRows = maze.getRows() - 1;
        int maxCols = maze.getCols() - 1;

        for (int i = 0; i <= maxRows; i += 2) {
            for (int j = 0; j <= maxCols; j += 2) {
                ArrayList<Edge> next = new ArrayList<>();

                if (j > 0 && maze.getMaze()[i][j - 1] == 0) {
                    Node node = createNode(number - 1, nodes);
                    Edge edge = new Edge(node);
                    next.add(edge);
                }

                if (i < maxRows && maze.getMaze()[i + 1][j] == 0) {
                    Node node = createNode(number + cols, nodes);
                    Edge edge = new Edge(node);
                    next.add(edge);
                }


                if (j < maxCols && maze.getMaze()[i][j + 1] == 0) {
                    Node node = createNode(number + 1, nodes);
                    Edge edge = new Edge(node);
                    next.add(edge);
                }


                if (i > 0 && maze.getMaze()[i - 1][j] == 0) {
                    Node node = createNode(number - cols, nodes);
                    Edge edge = new Edge(node);
                    next.add(edge);
                }

                if (startNode == -1 && i == maze.getEntryRow() && j == maze.getEntryCol())
                    startNode = number;

                if (endNode == -1 && i == maze.getExitRow() && j == maze.getExitCol())
                    endNode = number;

                if (nodes[number] == null)
                    nodes[number] = new Node(number, next);
                else {
                    nodes[number].setNext(next);
                }
                number++;
            }
        }

        return new Graph(nodes, nodes[startNode], nodes[endNode]);
    }

    public Node createNode(int number, Node[] nodes) {
        Node node;
        if (nodes[number] == null) {
            node = new Node(number);
            nodes[number] = node;
        } else node = nodes[number];
        return node;
    }
}
