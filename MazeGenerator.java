package individualProject;

import java.util.Random;

public class MazeGenerator {
    Random r = new Random();

    public Maze generateMaze(int numberOfRows, int numberOfColumns) {
        byte[][] array = generateArray(numberOfRows, numberOfColumns);
        recursiveDivision(array, 0, numberOfRows * 2 - 2, 0, numberOfColumns * 2 - 2);
        Maze maze = new Maze(array);
        generateEntryAndExit(maze, numberOfRows, numberOfColumns);
        return maze;
    }

    private void generateEntryAndExit(Maze maze, int numberOfRows, int numberOfColumns) {
        int wallWithEntry = r.nextInt(4);
        switch (wallWithEntry) {
            case 0:
                maze.setEntry(0, getRandomInt(true, numberOfColumns * 2 - 1, 0));
                maze.setExit(numberOfRows * 2 - 2, getRandomInt(true, numberOfColumns * 2 - 1, 0));
                break;
            case 1:
                maze.setEntry(getRandomInt(true, numberOfRows * 2 - 1, 0), 0);
                maze.setExit(getRandomInt(true, numberOfRows * 2 - 1, 0), numberOfColumns * 2 - 2);
                break;
            case 2:
                maze.setEntry(numberOfRows * 2 - 2, getRandomInt(true, numberOfColumns * 2 - 1, 0));
                maze.setExit(0, getRandomInt(true, numberOfColumns * 2 - 1, 0));
                break;
            case 3:
                maze.setEntry(getRandomInt(true, numberOfRows * 2 - 1, 0), numberOfColumns * 2 - 2);
                maze.setExit(getRandomInt(true, numberOfRows * 2 - 1, 0), 0);
        }
    }


    private void recursiveDivision(byte[][] array, int rowStartId, int rowEndId, int colStartId, int colEndId) {
        int rows = rowEndId - rowStartId + 1;
        int cols = colEndId - colStartId + 1;

        if (rows > 1 && cols > 1) {
            int range;
            boolean dividingAlongRows;
            if (rows > cols) {
                range = rows;
                dividingAlongRows = true;
            } else if (rows < cols) {
                range = cols;
                dividingAlongRows = false;
            } else if (r.nextInt() % 2 == 0) {
                range = rows;
                dividingAlongRows = true;
            } else {
                range = cols;
                dividingAlongRows = false;
            }

            int wallId;

            if (dividingAlongRows) {
                wallId = getRandomInt(false, range, rowStartId);

                for (int i = colStartId; i <= colEndId; i++) {
                    array[wallId][i] = 1;
                }
                int numberOfPassages = r.nextInt(cols) + 1;
                for (int i = 0; i < numberOfPassages; i++) {
                    int passage = getRandomInt(true, cols, colStartId);
                    array[wallId][passage] = 0;
                }
            } else {
                wallId = getRandomInt(false, range, colStartId);

                for (int i = rowStartId; i <= rowEndId; i++) {
                    array[i][wallId] = 1;
                }
                int numberOfPassages = r.nextInt(rows) + 1;
                for (int i = 0; i < numberOfPassages; i++) {
                    int passage = getRandomInt(true, rows, rowStartId);
                    array[passage][wallId] = 0;
                }
            }
            if (dividingAlongRows) {
                recursiveDivision(array, rowStartId, wallId - 1, colStartId, colEndId);
                recursiveDivision(array, wallId + 1, rowEndId, colStartId, colEndId);
            } else {
                recursiveDivision(array, rowStartId, rowEndId, colStartId, wallId - 1);
                recursiveDivision(array, rowStartId, rowEndId, wallId + 1, colEndId);
            }
        }
    }

    private byte[][] generateArray(int numberOfRows, int numberOfColumns) {

        int rows = numberOfRows * 2 - 1;
        int cols = numberOfColumns * 2 - 1;
        byte[][] array = new byte[rows][cols];
        return array;
    }

    private int getRandomInt(boolean isEven, int bound, int translation) {
        int randomInt = r.nextInt(bound) + translation;
        int max = bound + translation - 1;
        if (isEven) {
            if (randomInt % 2 != 0) {
                if (randomInt + 1 <= max) randomInt += 1;
                else randomInt -= 1;
            }
        } else {
            if (randomInt % 2 == 0) {
                if (randomInt + 1 <= max) randomInt += 1;
                else randomInt -= 1;
            }
        }
        return randomInt;
    }

}
