import java.util.Stack;
import java.util.LinkedList;

public class MazeDepthFirstSearch extends AbstractMazeSearch {
    private Stack<Cell> stack;
    private int exploredCells; // Variable to keep track of the explored cells

    public MazeDepthFirstSearch(Maze maze) {
        super(maze);
        this.stack = new Stack<>();
        this.exploredCells = 0;
    }

    @Override
    public Cell findNextCell() {
        // The next cell is the one on top of the stack
        return this.stack.pop();
    }

    @Override
    public void addCell(Cell next) {
        this.stack.push(next);
        this.exploredCells++; // Increment the explored cells counter
    }

    @Override
    public int numRemainingCells() {
        return this.stack.size();
    }

    // Method to get the number of cells explored by the algorithm
    public int getExploredCells() {
        return this.exploredCells;
    }

    public static void main(String[] args) throws InterruptedException {
        Maze maze = new Maze(10, 10, 0.2); // Create a maze
        Cell start = maze.get(0, 0); // Define the start cell
        Cell target = maze.get(maze.getRows()-1, maze.getCols()-1); // Define the target cell
        MazeDepthFirstSearch searcher = new MazeDepthFirstSearch(maze);
        LinkedList<Cell> path = searcher.search(start, target, true, 100);
        if (path == null) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path found. Length: " + path.size());
            System.out.println("Cells explored: " + searcher.getExploredCells());
        }
    }
}
