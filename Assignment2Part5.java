package com.shpp.p2p.cs.ydanylenko.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/** Assignment2Part5 class allows drawing an optical illusion consisting of
 * small
 * black boxes matrix. The user uses constants to set the size of the matrix
 * grid - the square boxes in a row number and the rows number, as well as the
 * distance between the boxes. */
public class Assignment2Part5 extends WindowProgram {
    //The number of rows and columns in the grid, respectively.
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 6;
    //The width and height of each box.
    private static final double BOX_SIZE = 40;
    // The horizontal and vertical spacing between the boxes.
    private static final double BOX_SPACING = 10;

    public void run() {
        drawGrid();
    }

    /* Draw table consisting of same size rows. Method draws every row
     * from down to up. The first row is the most down while the last row is
     * the most upper.*/
    private void drawGrid() {
        for (int i = 1; i <= NUM_ROWS; i++) drawRow(i);
    }

    /** Draw the row of black boxes that stands between each other at
     * the same distance. The size of the boxes and the distance between them
     * are set by constants at the beginning of the class. The method finds out
     * the number of boxes in the row. Then it calculates the very edge of the
     * row on the right. And it starts drawing boxes from right to left. So
     * the first box is on the right, and the last one is on the left.
     * @param row_number - the maximal number of rows in the grid. */
    private void drawRow(int row_number) {
        //The x - coordinate of the grid center
        double grid_center_x = getWidth() / 2;
        //The y - coordinate of the grid center
        double grid_center_y = getHeight() / 2;
        //The height of the grid
        double grid_height = BOX_SIZE * NUM_ROWS + BOX_SPACING * (NUM_ROWS - 1);
        //The "lowest" coordinate from which the row numbers countdown starts.
        double grid_down_y_starting_coordinate =
                grid_center_y + grid_height / 2 + BOX_SPACING;
        //The y upper-left coordinate of the current row.
        double current_row_y_coordinate = row_number * (BOX_SPACING + BOX_SIZE);
        //The length of the row including spaces between boxes.
        double row_size = BOX_SIZE * NUM_COLS + BOX_SPACING * (NUM_COLS - 1);
        /*The upper-right y coordinate of the right row edge, same as
        upper-right y coordinate of the first box if counting from the right
        to the left. */
        double right_row_edge = grid_center_x + (row_size / 2);
        for (int j = 1; j <= NUM_COLS; j++) {
            double box_x_coordinate = (j * BOX_SIZE + (j - 1) * BOX_SPACING);
            GRect square =
                    new GRect((right_row_edge - box_x_coordinate),
                            (grid_down_y_starting_coordinate - current_row_y_coordinate),
                            BOX_SIZE, BOX_SIZE);
            square.setFilled(true);
            square.setColor(Color.BLACK);
            square.setFillColor(Color.BLACK);
            add(square);
        }
    }
}
