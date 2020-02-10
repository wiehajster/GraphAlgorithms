package individualProject;

import javafx.util.Pair;

public class Maze {
    private byte[][] maze;
    private Pair<Integer, Integer> entry;
    private Pair<Integer, Integer> exit;

    public Maze(byte[][] maze) {
        this.maze = maze;
    }

    public byte[][] getMaze() {
        return maze;
    }

    public Pair<Integer, Integer> getEntry() {
        return entry;
    }

    public Pair<Integer, Integer> getExit() {
        return exit;
    }


    public void setEntry(int row, int col) {
        this.entry = new Pair<Integer, Integer>(row, col);
    }

    public void setExit(int row, int col) {
        this.exit = new Pair<Integer, Integer>(row, col);
    }

    public int getRows() {
        if (maze == null) return 0;

        return maze.length;
    }

    public int getCols() {
        if (getRows() > 0) return maze[0].length;
        return 0;
    }

    public int getEntryRow() {
        if (entry == null) return -1;
        return entry.getKey();
    }

    public int getEntryCol() {
        if (entry == null) return -1;
        return entry.getValue();
    }

    public int getExitRow() {
        if (exit == null) return -1;
        return exit.getKey();
    }

    public int getExitCol() {
        if (exit == null) return -1;
        return exit.getValue();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                stringBuilder.append(maze[i][j]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
