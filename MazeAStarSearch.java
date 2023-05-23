/*
Mahdeen Ahmed Khan Sameer
Project 7
MazeAStarSearch Class: Implements the A* search algorithm for solving mazes, utilizing a priority queue and heuristic function to efficiently search the space by prioritizing paths that are estimated to be closer to the target.
*/


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
        // Use smaller dimensions for the maze
        Maze maze = new Maze(5, 5, 0.3); // Create a smaller maze
        Cell start = maze.get(0, 0); // Define the start cell
        Cell target = maze.get(maze.getRows()-1, maze.getCols()-1); // Define the target cell
        MazeAStarSearch searcher = new MazeAStarSearch(maze, target);
        
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
