/*
Mahdeen Ahmed Khan Sameer
Project 7
For our Exploration!
DummyMazeSearch Class: To provide a dummy implementation of the AbstractMazeSearch class, which serves as a placeholder or a simple example of a maze search algorithm. It is not a specific search algorithm like A* Search, Breadth-First Search, or Depth-First Search. Instead, it uses a queue data structure to explore cells in a simplistic manner. This class is mainly used for testing or demonstration purposes, and it does not guarantee finding the optimal path in a maze.
*/


import java.util.LinkedList;
import java.util.Queue;

public class DummyMazeSearch extends AbstractMazeSearch {
    private Queue<Cell> cells;

    public DummyMazeSearch(Maze maze) {
        super(maze);
        cells = new LinkedList<>();
    }

    @Override
    public Cell findNextCell() {
        return cells.poll();  // Retrieve and remove the cell from the front of the queue
    }

    @Override
    public void addCell(Cell next) {
        cells.add(next);  // Add the cell to the end of the queue
    }

    @Override
    public int numRemainingCells() {
        return cells.size();
    }
}
