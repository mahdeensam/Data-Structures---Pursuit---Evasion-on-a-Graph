import java.util.*;

public class Extension2 {

    public static void main(String[] args) {
        Maze maze = new Maze(10, 10, 0.2); // Create a maze
        Cell start = maze.get(0, 0); // Define the start cell
        Cell target = maze.get(maze.getRows() - 1, maze.getCols() - 1); // Define the target cell

        // Calculate the number of steps required to reach each cell using Breadth-First Search
        int[][] steps = calculateSteps(maze, start);

        // Display the number of steps required to reach each cell
        displaySteps(steps, maze);

        // Calculate the total number of steps to reach the target cell
        int totalSteps = steps[target.getRow()][target.getCol()];
        System.out.println("Total steps to reach the target: " + totalSteps);
    }

    private static int[][] calculateSteps(Maze maze, Cell start) {
        int rows = maze.getRows();
        int cols = maze.getCols();
        int[][] steps = new int[rows][cols];

        // Initialize all steps to -1 (unreachable)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                steps[i][j] = -1;
            }
        }

        // Perform Breadth-First Search to calculate the steps to reach each cell
        Queue<Cell> queue = new LinkedList<>();
        queue.offer(start);
        steps[start.getRow()][start.getCol()] = 0;

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            int currentSteps = steps[current.getRow()][current.getCol()];

            for (Cell neighbor : maze.getNeighbors(current)) {
                if (steps[neighbor.getRow()][neighbor.getCol()] == -1) {
                    steps[neighbor.getRow()][neighbor.getCol()] = currentSteps + 1;
                    queue.offer(neighbor);
                }
            }
        }

        return steps;
    }

    private static void displaySteps(int[][] steps, Maze maze) {
        int rows = maze.getRows();
        int cols = maze.getCols();

        System.out.println("Number of steps to reach each cell:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%-3d ", steps[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
