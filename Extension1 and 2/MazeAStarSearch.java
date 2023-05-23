import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MazeAStarSearch extends AbstractMazeSearch {
    private PriorityQueue<Cell> priorityQueue;
    private HashSet<Cell> explored;

    public MazeAStarSearch(Maze maze, Cell target) {
        super(maze);
        this.priorityQueue = new PriorityQueue<>(new Comparator<Cell>() {
            @Override
            public int compare(Cell cell1, Cell cell2) {
                int cell1Distance = traceback(cell1).size() + Math.abs(target.getRow() - cell1.getRow()) + Math.abs(target.getCol() - cell1.getCol());
                int cell2Distance = traceback(cell2).size() + Math.abs(target.getRow() - cell2.getRow()) + Math.abs(target.getCol() - cell2.getCol());
                return cell1Distance - cell2Distance;
            }
        });
        this.explored = new HashSet<>();
    }

    @Override
    public Cell findNextCell() {
        return this.priorityQueue.poll();
    }

    @Override
    public void addCell(Cell next) {
        this.priorityQueue.offer(next);
        this.explored.add(next);
    }

    @Override
    public int numRemainingCells() {
        return this.priorityQueue.size();
    }

    // Method to get the number of cells explored by the algorithm
    public int getExploredCells() {
        return this.explored.size();
    }

    public static void main(String[] args) throws InterruptedException {
        Maze maze = new Maze(10, 10, 0.3); // Create a maze
        Cell start = maze.get(0, 0); // Define the start cell
        Cell target = maze.get(maze.getRows()-1, maze.getCols()-1); // Define the target cell
        MazeAStarSearch searcher = new MazeAStarSearch(maze, target);
    
        // Set a smaller scale for the display. For example, 10.
        MazeSearchDisplay display = new MazeSearchDisplay(searcher, 4);
    
        LinkedList<Cell> path = searcher.search(start, target, true, 100);
        if (path == null) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path found. Length: " + path.size());
            System.out.println("Cells explored: " + searcher.getExploredCells());
        }
    
        // Repaint the display after the search is complete
        display.repaint();
    }
    

}
