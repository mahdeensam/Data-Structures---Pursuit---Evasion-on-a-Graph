import java.awt.Graphics;
import java.util.Iterator;
import java.util.Random;
import java.util.LinkedList;

public class Maze implements Iterable<Cell> {
    public Iterator<Cell> iterator() {
        return new Iterator<Cell>() {
            int r, c;

            public boolean hasNext() {
                return r < getRows();
            }

            public Cell next() {
                Cell next = get(r, c);
                c++;
                if (c == getCols()) {
                    r++;
                    c = 0;
                }
                return next;
            }
        };
    }
    private int rows, cols;
    private double density;
    private Cell[][] landscape;
    public Maze(int rows, int columns, double density) {
        this.rows = rows;
        this.cols = columns;
        this.density = density;
        landscape = new Cell[rows][columns];
        reinitialize();
    }
    public void reinitialize() {
        Random rand = new Random();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                double roll = rand.nextDouble();
                CellType type;
                if (roll < density) type = CellType.OBSTACLE;
                else if (roll < 2*density) type = CellType.MUD;
                else if (roll < 3*density) type = CellType.ICE;
                else if (roll < 4*density) type = CellType.TURN;
                else type = CellType.FREE;
                landscape[r][c] = new Cell(r, c, type);
            }
        }
    }
    public void reset() {
        for (Cell cell : this)
            cell.reset();
    }
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
    public Cell get(int row, int col) {
        return landscape[row][col];
    }

    public LinkedList<Cell> getNeighbors(Cell c) {
        LinkedList<Cell> cells = new LinkedList<Cell>();
        int[][] steps = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        for (int[] step : steps) {
            int nextRow = c.getRow() + step[0];
            int nextCol = c.getCol() + step[1];
            if (nextRow >= 0 && nextRow < getRows() && nextCol >= 0 && nextCol < getCols()
                    && get(nextRow, nextCol).getType() != CellType.OBSTACLE)
                cells.addLast(get(nextRow, nextCol));
        }
        return cells;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("-".repeat(cols + 3) + "\n");
        for (Cell[] cells : landscape) {
            output.append("| ");
            for (Cell cell : cells) {
                switch (cell.getType()) {
                    case OBSTACLE:
                        output.append('X');
                        break;
                    case MUD:
                        output.append('M');
                        break;
                    case ICE:
                        output.append('I');
                        break;
                    case TURN:
                        output.append('T');
                        break;
                    default:
                        output.append(' ');
                        break;
                }
            }
            output.append("|\n");
        }
        return output.append("-".repeat(cols + 3)).toString();
    }

    public void draw(Graphics g, int scale) {
        for (Cell cell : this)
            cell.drawType(g, scale);
    }

    public static void main(String[] args) {
        Maze ls = new Maze(7, 7, .2);
        System.out.println(ls);
    }
}