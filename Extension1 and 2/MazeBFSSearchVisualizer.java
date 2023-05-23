import java.util.LinkedList;

public class MazeBFSSearchVisualizer {

    public static void main(String[] args) {
        // Define parameters for the maze
        int rows = 20;
        int columns = 20;
        double density = 0.3;
        int scale = 10;
        int delay = 100;

        // Create the maze
        Maze maze = null;
        Cell startCell = null;
        Cell targetCell = null;
        do {
            maze = new Maze(rows, columns, density);

            // Get start and target cells
            startCell = maze.get(0, 0);
            targetCell = maze.get(rows - 1, columns - 1);
        } while(startCell.getType() == CellType.OBSTACLE || targetCell.getType() == CellType.OBSTACLE);

        // Create the BFS search object
        MazeBreadthFirstSearch search = new MazeBreadthFirstSearch(maze);

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
