import java.util.LinkedList;

public class Exploration2_2 {
    public static void main(String[] args) {
        // Create a new Maze
        Maze maze = new Maze(7, 7, .2);

        // Set the start and target cells
        Cell start = maze.get(0, 0);
        Cell target = maze.get(6, 6);

        // Create the DFS searcher
        MazeDepthFirstSearch dfs = new MazeDepthFirstSearch(maze);

        // Perform the search and print the length of the path
        try {
            LinkedList<Cell> dfsPath = dfs.search(start, target, false, 0);
            System.out.println("DFS Path Length: " + (dfsPath == null ? "No path found" : dfsPath.size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

