import java.util.LinkedList;

    public class Exploration2 {
        public static void main(String[] args) {
            // Create a new Maze
            Maze maze = new Maze(7, 7, .2);
    
            // Set the start and target cells
            Cell start = maze.get(0, 0);
            Cell target = maze.get(6, 6);
    
            // Create the A* searcher
            MazeAStarSearch aStar = new MazeAStarSearch(maze, target);
    
            // Perform the search and print the length of the path
            try {
                LinkedList<Cell> aStarPath = aStar.search(start, target, false, 0);
                System.out.println("A* Path Length: " + (aStarPath == null ? "No path found" : aStarPath.size()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
