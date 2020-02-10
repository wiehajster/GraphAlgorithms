package individualProject.maze;

import individualProject.maze.Maze;

import java.io.*;

public class MazeLoader {
    public Maze loadMaze(String fileName) throws IOException {

        BufferedReader counter = new BufferedReader(new FileReader(fileName + ".txt"));
        String tmp = counter.readLine();
        if (tmp == null) throw new IllegalArgumentException();
        int lines = 1;
        while (counter.readLine() != null) {
            ++lines;
        }
        counter.close();

        int rows = lines - 2;
        int cols = tmp.length() - 2;
        byte[][] array = new byte[rows][cols];

        Maze maze = new Maze(array);
        BufferedReader reader = new BufferedReader(new FileReader(fileName + ".txt"));
        String buffer = reader.readLine();
        int row = 0;
        int col;

        for (int i = 0; i < lines; i++) {
            if (i >= 2 && i <= rows)
                row++;
            col = 0;

            for (int j = 0; j < buffer.length(); j++) {
                char c = buffer.charAt(j);
                if (j >= 2 && j <= cols)
                    col++;

                if (c == '+' && i >= 1 && i <= rows && j >= 1 && j <= cols) array[row][col] = 1;
                else if (c == '0') array[row][col] = 0;
                else if (c == '#') maze.setEntry(row, col);
                else if (c == '*') maze.setExit(row, col);
            }
            buffer = reader.readLine();
        }
        reader.close();
        return maze;
    }
}
