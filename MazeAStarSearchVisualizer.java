import java.util.LinkedList;


public class MazeAStarSearchVisualizer {

    public static void main(String[] args) {
        // Define parameters for the maze
        int rows = 20;
        int columns = 20;
        double density = 0.3;
        int delay = 100;

        // Create the maze
        Maze maze = new Maze(rows, columns, density);

        // Get start and target cells
        Cell startCell = maze.get(0, 0);
        Cell targetCell = maze.get(rows - 1, columns - 1);

        // Ensure start and target cells are free
        if (startCell.getType() == CellType.OBSTACLE) {
            startCell = new Cell(0, 0, CellType.FREE);
            maze.get(0, 0).setPrev(startCell);
        }
        if (targetCell.getType() == CellType.OBSTACLE) {
            targetCell = new Cell(rows - 1, columns - 1, CellType.FREE);
            maze.get(rows - 1, columns - 1).setPrev(targetCell);
        }

        // Create the A* search object
        MazeAStarSearch search = new MazeAStarSearch(maze, targetCell);

        // Start the search
        try {
            LinkedList<Cell> path = search.search(startCell, targetCell, true, delay);
            if (path != null) {
                System.out.println("Path found!");
                for (Cell cell : path) {
                    System.out.println(cell);
                }
            } else {
                System.out.println("No path found.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
