import java.util.LinkedList;

public class Exploration2_3 {
    public static void main(String[] args) {
        // Create a new Maze
        Maze maze = new Maze(7, 7, .2);

        // Set the start and target cells
        Cell start = maze.get(0, 0);
        Cell target = maze.get(6, 6);

        // Create the BFS searcher
        MazeBreadthFirstSearch bfs = new MazeBreadthFirstSearch(maze);

        // Perform the search and print the length of the path
        try {
            LinkedList<Cell> bfsPath = bfs.search(start, target, false, 0);
            System.out.println("BFS Path Length: " + (bfsPath == null ? "No path found" : bfsPath.size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

    
