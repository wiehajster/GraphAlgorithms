package individualProject;

import individualProject.maze.MazeLoader;
import individualProject.maze.MazeSaver;
import individualProject.graph.Graph;
import individualProject.maze.GraphGenerator;
import individualProject.graph.graphSearchingAlgorithms.BFS;
import individualProject.graph.graphSearchingAlgorithms.Tremaux;
import individualProject.maze.Maze;
import individualProject.maze.MazeGenerator;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        Random r = new Random();
        MazeGenerator mazeGenerator = new MazeGenerator();
        MazeSaver mazeSaver = new MazeSaver();
        MazeLoader mazeLoader = new MazeLoader();
        GraphGenerator graphGenerator = new GraphGenerator();
        BFS bfs = new BFS();
        Tremaux tremaux = new Tremaux();
        ResultPrinter resultPrinter = new ResultPrinter();

        Maze mazeGenerated = mazeGenerator.generateMaze(4, 4);
        System.out.println(mazeGenerated);

        mazeSaver.saveMazeToFile("maze_generated", mazeGenerated);
        Maze mazeLoaded = mazeLoader.loadMaze("maze_generated");
        mazeSaver.saveMazeToFile("maze_loaded", mazeLoaded);
        System.out.println(mazeLoaded);

        Graph graph = graphGenerator.generateGraph(mazeGenerated);
        System.out.println(graph);

        LinkedList<Integer> bfsResult = bfs.searchForExit(graph);
        resultPrinter.printResult(BFS.class.getSimpleName(), bfsResult);

        LinkedList<Integer> tremauxResult = tremaux.searchForExit(graph);
        resultPrinter.printResult(Tremaux.class.getSimpleName(), tremauxResult);
    }
}