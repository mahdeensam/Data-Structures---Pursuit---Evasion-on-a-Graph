/*
Mahdeen Ahmed Khan Sameer
Project 7
AbstractMazeSearch Class: To define a blueprint for maze searching algorithms, providing basic utilities for tracking and visualization of search paths.
*/

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public abstract class AbstractMazeSearch {
    protected Maze maze;
    protected Cell start;
    protected Cell target;
    protected Cell cur;
    protected int exploredCells = 0;


    public AbstractMazeSearch(Maze maze) {
        this.maze = maze;
        this.exploredCells = 0;
    }

    public int getExploredCells() {
        return exploredCells;
    }


    public abstract Cell findNextCell();

    public abstract void addCell(Cell next);

    public abstract int numRemainingCells();

    public Maze getMaze() {
        return this.maze;
    }

    public void setTarget(Cell target) {
        this.target = target;
    }

    public Cell getTarget() {
        return this.target;
    }

    public void setCur(Cell cur) {
        this.cur = cur;
    }

    public Cell getCur() {
        return this.cur;
    }

    public void setStart(Cell start) {
        this.start = start;
        start.setPrev(start);
    }

    public Cell getStart() {
        return this.start;
    }

    public void reset() {
        this.cur = this.start = this.target = null;
    }

    public LinkedList<Cell> traceback(Cell cell) {
        Cell curCell = cell;
        LinkedList<Cell> path = new LinkedList<>();

        while (curCell != null) {
            path.addFirst(curCell);

            if (curCell.equals(this.start)) {
                return path;
            }

            curCell = curCell.getPrev();
        }

        return null;
    }

    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay) throws InterruptedException {
        setStart(start);
        setTarget(target);
        setCur(start);
        addCell(start);
        MazeSearchDisplay displayWindow = null;
        if(display) {
            displayWindow = new MazeSearchDisplay(this, delay);
        }
    
        while (numRemainingCells() > 0) {
            Cell cur = findNextCell();
            exploredCells++;
    
            for (Cell neighbor : this.maze.getNeighbors(cur)) {
                if (neighbor.getPrev() == null && neighbor.getType() != CellType.OBSTACLE) {
                    neighbor.setPrev(cur);
                    addCell(neighbor);
    
                    if (display) {
                        Thread.sleep(delay);
                        displayWindow.repaint();
                    }
    
                    if (neighbor.equals(this.target)) {
                        return traceback(target);
                    }
                }
            }
        }
    
        return null;
    }    
    
    
    

    public void draw(Graphics g, int scale) {
        // Draws the base version of the maze
        getMaze().draw(g, scale);
        // Draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        // Draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        // Draws the target cell
        getTarget().draw(g, scale, Color.RED);
        // Draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);

        // If the target has been found, draws the path taken by the searcher to reach
        // the target sans backtracking.
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
    }
}
