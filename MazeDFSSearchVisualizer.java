import javax.swing.*;
import java.awt.*;

public class MazeDFSSearchVisualizer extends JFrame {
    private static final long serialVersionUID = 1L;

    private Maze maze;
    private MazeDepthFirstSearch dfs;
    private MazePanel panel;

    public MazeDFSSearchVisualizer(Maze maze, MazeDepthFirstSearch dfs, int delay) {
        this.maze = maze;
        this.dfs = dfs;
        this.panel = new MazePanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setVisible(true);

        this.runSearch(delay);
    }

    private void runSearch(int delay) {
        try {
            dfs.search(maze.get(0, 0), maze.get(maze.getRows() - 1, maze.getCols() - 1), true, delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class MazePanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private static final int CELL_SIZE = 20;

        public MazePanel() {
            this.setPreferredSize(new Dimension(maze.getCols() * CELL_SIZE, maze.getRows() * CELL_SIZE));
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            dfs.draw(g, CELL_SIZE);
        }
    }

    public static void main(String[] args) {
        Maze maze = new Maze(20, 20, 0.3);
        MazeDepthFirstSearch dfs = new MazeDepthFirstSearch(maze);
        new MazeDFSSearchVisualizer(maze, dfs, 100);
    }
}
