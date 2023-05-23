/*
Mahdeen Ahmed Khan Sameer
Project 7
Cell Class: To test Cell Class

*/

import javax.swing.*;
import java.awt.*;


public class CellTest {

    public static void main(String[] args) {
        // Replace CellType.FREE with actual cell type if different
        Cell cell1 = new Cell(1, 1, CellType.FREE);
        Cell cell2 = new Cell(2, 2, CellType.FREE);

        assert cell1.getRow() == 1 : "Cell1 row should be 1";
        assert cell1.getCol() == 1 : "Cell1 column should be 1";
        assert cell1.getType() == CellType.FREE : "Cell1 type should be FREE";
        assert cell1.getPrev() == null : "Cell1 previous cell should be null initially";

        cell1.setPrev(cell2);
        assert cell1.getPrev() == cell2 : "Cell1 previous cell should be cell2 after setting";

        cell1.reset();
        assert cell1.getPrev() == null : "Cell1 previous cell should be null after resetting";

        assert !cell1.equals(cell2) : "Cell1 should not equal cell2";

        System.out.println("All non-visual tests passed!");

        // Create a JFrame (window) that will be visible on screen
        JFrame frame = new JFrame("Cell Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300); // set frame size

        // Create a JPanel (canvas) that you can draw on.
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int scale = 50; // set the scale for the cell
                cell1.drawType(g, scale); // draw cell1
                cell2.drawType(g, scale); // draw cell2
            }
        };

        frame.add(panel); // add the canvas to the window
        frame.setVisible(true); // make the window visible
    }
}
