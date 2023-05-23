/*
Mahdeen Ahmed Khan Sameer
Project 7
MazeTest Class: To test Maze class.
*/


import javax.swing.*;
import java.awt.*;

public class MazeTest {
    private static final int SCALE = 50; // Display scale

    public static void main(String[] args) {
        // Creating a new maze
        Maze maze = new Maze(7, 7, 0.2);
        System.out.println(maze);

        // Creating a new JFrame to display the maze
        JFrame frame = new JFrame("Maze Test");
        frame.setSize(maze.getCols() * SCALE, maze.getRows() * SCALE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding a custom JPanel that paints the maze
        frame.add(new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                maze.draw(g, SCALE);
            }
        });

        frame.setVisible(true);
    }
}
