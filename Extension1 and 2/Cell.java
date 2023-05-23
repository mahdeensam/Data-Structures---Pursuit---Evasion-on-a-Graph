import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    private int row, col;
    private Cell prev;
    private CellType type;
    private double speedMultiplier;
    private Color color; // Color for drawing the cell

    public Cell(int r, int c, CellType type) {
        row = r;
        col = c;
        this.type = type;
        this.speedMultiplier = 1.0;
        this.color = determineColor();
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
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

    private Color determineColor() {
        switch (getType()) {
            case FREE:
                return Color.GRAY;
            case OBSTACLE:
                return Color.BLACK;
            case MUD:
                return Color.ORANGE; // Color for mud cells
            case ICE:
                return Color.CYAN; // Color for ice cells
            default:
                return Color.GRAY;
        }
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
        draw(g, scale, color); // Use the determined color for drawing the cell
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