import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    private int row, col;
    private Cell prev;
    private CellType type;
    private double speedMultiplier;
    private Color color; // Color for drawing the cell
    private double cost; // Cost or time associated with the cell

    public Cell(int r, int c, CellType type) {
        row = r;
        col = c;
        this.type = type;
        this.speedMultiplier = determineSpeedMultiplier();
        this.color = determineColor();
        this.cost = 0.0; // Initialize cost to 0
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    private double determineSpeedMultiplier() {
        switch (getType()) {
            case FREE:
                return 1.0;
            case OBSTACLE:
                return 0.0; // Impassable
            case MUD:
                return 0.5; // Half speed in mud
            case ICE:
                return 2.0; // Double speed on ice
            case TURN:
                return 0.75; // Speed reduced to simulate the cost of a turn
            default:
                return 1.0;
        }
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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
            case TURN:
                return Color.RED; // Color for turn cells
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
