/*
Mahdeen Ahmed Khan Sameer
Project 7
Cell Class: To represent individual cells within a maze.
*/

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    private int row, col;
    private Cell prev;
    private CellType type;
    public Cell(int r, int c, CellType type) {
        row = r;
        col = c;
        this.type = type;
    }
    public void setPrev(Cell prev) {
        this.prev = prev;
    }
    public Cell getPrev() {
        return prev;
    }
    public void reset() {
        setPrev(null);
    }
    public CellType getType() {
        return type;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Cell))
            return false;
        Cell c = (Cell) o;
        return row == c.row && col == c.col && type == c.type;
    }

    public String toString() {
        return "(" + row + ", " + col + ", " + type + ")";
    }

    public void drawType(Graphics g, int scale) {
        g.setColor(Color.BLACK);
        g.drawRect(getCol() * scale, getRow() * scale, scale, scale);
        switch (getType()) {
            case FREE:
                draw(g, scale, getPrev() != null ? Color.YELLOW : Color.GRAY);
                break;
            case OBSTACLE:
                draw(g, scale, Color.BLACK);
                break;
        }
    }
    public void drawAllPrevs(Maze maze, Graphics g, int scale, Color c) {
        g.setColor(c);
        for (Cell neighbor : maze.getNeighbors(this)) {
            if (neighbor.getPrev() == this) {
                g.drawLine(getCol() * scale + scale / 2, getRow() * scale + scale / 2,
                        neighbor.getCol() * scale + scale / 2, neighbor.getRow() * scale + scale / 2);
                neighbor.drawAllPrevs(maze, g, scale, c);
            }
        }
    }

    public void drawPrevPath(Graphics g, int scale, Color c) {
        g.setColor(c);
        if (getPrev() != null && getPrev() != this) {
            g.drawLine(getCol() * scale + scale / 2, getRow() * scale + scale / 2,
                    getPrev().getCol() * scale + scale / 2, getPrev().getRow() * scale + scale / 2);
            getPrev().drawPrevPath(g, scale, c);
        }
    }

    public void draw(Graphics g, int scale, Color c) {
        g.setColor(c);
        g.fillRect(getCol() * scale + 2, getRow() * scale + 2, scale - 4, scale - 3);
    }
}