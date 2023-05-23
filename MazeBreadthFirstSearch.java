/*
Mahdeen Ahmed Khan Sameer
Project 7
MazeBreadthFirstSearch Class: Implements the Breadth-First search algorithm for solving mazes, employing a queue data structure to systematically explore all neighboring cells at the current depth before moving to cells at the next depth level.
*/


import java.util.LinkedList;
import java.util.Queue;

public class MazeBreadthFirstSearch extends AbstractMazeSearch {
    private Queue<Cell> queue;
    private int exploredCells; // Variable to keep track of the explored cells

    public MazeBreadthFirstSearch(Maze maze) {
        super(maze);
        this.queue = new LinkedList<>();
        this.exploredCells = 0;
    }

    @Override
    public Cell findNextCell() {
        return this.queue.poll();
    }

    @Override
    public void addCell(Cell next) {
        this.queue.offer(next);
        this.exploredCells++; // Increment the explored cells counter
    }

    @Override
    public int numRemainingCells() {
        return this.queue.size();
    }

    // Method to get the number of cells explored by the algorithm
    public int getExploredCells() {
        return this.exploredCells;
    }

    public static void main(String[] args) throws InterruptedException {
        // Use smaller dimensions for the maze
        Maze maze = new Maze(5, 5, 0.2); // Create a smaller maze
        Cell start = maze.get(0, 0); // Define the start cell
        Cell target = maze.get(maze.getRows()-1, maze.getCols()-1); // Define the target cell
        MazeBreadthFirstSearch searcher = new MazeBreadthFirstSearch(maze);
        
        // Reduce the delay to speed up the test
        LinkedList<Cell> path = searcher.search(start, target, true, 50);
        if (path == null) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path found. Length: " + path.size());
            System.out.println("Cells explored: " + searcher.getExploredCells());
        }
    }
    
}
