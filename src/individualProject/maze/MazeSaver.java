package individualProject.maze;

import individualProject.maze.Maze;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MazeSaver {

    public void saveMazeToFile(String fileName, Maze maze) throws IOException {

        if (maze == null || maze.getMaze().length == 0) throw new IllegalArgumentException();

        FileWriter fileWriter = new FileWriter(fileName + ".txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int entryRow = maze.getEntryRow();
        int entryCol = maze.getEntryCol();
        int exitRow = maze.getExitRow();
        int exitCol = maze.getExitCol();
        if (maze.getEntryRow() == 0 && maze.getExitRow() == maze.getRows() - 1) {
            --entryRow;
            ++exitRow;
        } else if (maze.getEntryRow() == maze.getRows() - 1 && maze.getExitRow() == 0) {
            ++entryRow;
            --exitRow;
        } else if (maze.getEntryCol() == 0 && maze.getExitCol() == maze.getCols() - 1) {
            --entryCol;
            ++exitCol;
        } else {
            ++entryCol;
            --exitCol;
        }

        for (int i = -1; i <= maze.getRows(); i++) {
            for (int j = -1; j <= maze.getCols(); j++) {
                String tmp;
                if (i == -1 || i == maze.getRows() || j == -1 || j == maze.getCols()) {
                    if (i == entryRow && j == entryCol) tmp = "#";
                    else if (i == exitRow && j == exitCol) tmp = "*";
                    else tmp = "+";
                } else {
                    if (maze.getMaze()[i][j] == 1) tmp = "+";
                    else tmp = "0";
                }
                printWriter.print(tmp);
                if (j == maze.getCols()) printWriter.println();
            }
        }
        printWriter.close();
    }
}
