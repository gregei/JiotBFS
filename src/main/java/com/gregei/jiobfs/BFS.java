package com.gregei.jiobfs;

import java.util.*;

/**
 * Class that represents the BFS algorithm, which is an algorithm to find a path through a maze.
 */
public class BFS {
    // Queue holding the current point which we check which point to travel to next, then repeats with the next point pushed to the queue.
    private LinkedList<Node> queue;
    // Array holding points the algorithm visits.
    public ArrayList<Point> points = new ArrayList<>();

    // Multiple coordinates to check to the left, right, up, and down from a point.
    private int rowNum[] = {-1, 0, 0, 1};
    private int colNum[] = {0, -1, 1, 0};

    // Holds an array of visited positions so we dont cross the same path twice.
    private boolean[][] visited;

    // Class level variable to hold the rows and colums of a passed grid.
    private int ROWS, COLS;

    /**
     *
     * @param grid
     * @param ROWS
     * @param COLS
     * @param source
     * @param destination
     * @return The distance it takes to reach the destination.
     */
    public int findPath(int[][] grid, int ROWS, int COLS, Point source, Point destination) {
        this.ROWS = ROWS;
        this.COLS = COLS;

        visited = new boolean[grid.length][grid[0].length];

        // Clear visited array
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                visited[i][j] = false;
            }
        }

        // Checks the source point as visited.
        visited[source.x][source.y] = true;

        queue = new LinkedList<>();
        queue.add(new Node(source, 0));

        // A loop pushing points we can move to a queue and then queuing points on a path until we reach our destination or it reaches the end
        // of that path. If it reaches the end then it backtracks and tries another path until the destination is reached.
        while (!queue.isEmpty()) {
            Node current = queue.peek();
            Point point = current.point;

            // Exit if we reach the destination.
            if (point.x == destination.x && point.y == destination.y) {
                return current.distance;
            }

            queue.pop();

            // Checks all found corners of a point and travels to each one.
            for (int i = 0; i < 4; ++i) {
                int row = point.x + rowNum[i];
                int col = point.y + colNum[i];

                if (isValid(row, col) && (grid[row][col] == 1) && !visited[row][col]) {
                    visited[row][col] = true;
                    Node ajd = new Node(new Point(row, col), current.distance + 1);
                    queue.add(ajd);
                    points.add(new Point(row, col));
                }
            }
        }

        return 0;
    }

    /**
     * Checks the bounds of the next move to make sure it stays on the grid
     * @param row
     * @param col
     * @return is this space valid to move to
     */
    private boolean isValid(int row, int col) {
        return (row >= 0) && (row < ROWS) && (col >= 0) && (col < COLS);
    }
}