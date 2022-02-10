package org.academiadecodigo.bootcamp.gfx.simplegfx;

import org.academiadecodigo.bootcamp.grid.GridColor;
import org.academiadecodigo.bootcamp.grid.GridDirection;
import org.academiadecodigo.bootcamp.grid.position.AbstractGridPosition;
import org.academiadecodigo.bootcamp.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Simple graphics position
 */
public class SimpleGfxGridPosition extends AbstractGridPosition {

    private Rectangle rectangle;
    private SimpleGfxGrid simpleGfxGrid;
    private int distance;
    private GridDirection direction;

    /**
     * Simple graphics position constructor
     *
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(SimpleGfxGrid grid) {
        super((int) (Math.random() * grid.getCols()), (int) (Math.random() * grid.getRows()), grid);
        simpleGfxGrid = grid;
        int x = getCol() * grid.getCellSize() + SimpleGfxGrid.PADDING;
        int y = getRow() * grid.getCellSize() + SimpleGfxGrid.PADDING;
        rectangle = new Rectangle(x, y, SimpleGfxGrid.PADDING, SimpleGfxGrid.PADDING);

    }

    /**
     * Simple graphics position constructor
     *
     * @param col  position column
     * @param row  position row
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(int col, int row, SimpleGfxGrid grid) {
        super(col, row, grid);
        simpleGfxGrid = grid;
        int x = getCol() * grid.getCellSize() + SimpleGfxGrid.PADDING;
        int y = getRow() * grid.getCellSize() + SimpleGfxGrid.PADDING;
        rectangle = new Rectangle(x, y, SimpleGfxGrid.PADDING, SimpleGfxGrid.PADDING);
        rectangle.draw();
    }

    /**
     * @see GridPosition#show()
     */
    @Override
    public void show() {
        rectangle.fill();
    }

    /**
     * @see GridPosition#hide()
     */
    @Override
    public void hide() {
        rectangle.delete();
    }

    /**
     * @see GridPosition#moveInDirection(GridDirection, int)
     */
    @Override
    public void moveInDirection(GridDirection direction, int distance) {
        int desired;
        int poss;
        int move;
        switch (direction) {
            case RIGHT:
                desired = distance * simpleGfxGrid.getCellSize();
                poss = (simpleGfxGrid.getCols() - (getCol() +1)) * simpleGfxGrid.getCellSize();
                move = Math.min(desired, poss);
                rectangle.translate(move, 0);
                break;
            case LEFT:
                desired = -distance * simpleGfxGrid.getCellSize();
                poss = -(getCol()) * simpleGfxGrid.getCellSize();
                move = Math.max(desired, poss);
                rectangle.translate(move, 0);
                break;
            case UP:
                desired = -distance * simpleGfxGrid.getCellSize();
                poss = -(getRow()) * simpleGfxGrid.getCellSize();
                move = Math.max(desired, poss);
                rectangle.translate(0, move);
                break;
            case DOWN:
                desired = distance * simpleGfxGrid.getCellSize();
                poss = (simpleGfxGrid.getRows() - (getRow() + 1)) * simpleGfxGrid.getCellSize();
                move = Math.min(desired, poss);
                rectangle.translate(0, move);
                break;
        }
        super.moveInDirection(direction, distance);
    }

    /**
     * @see AbstractGridPosition#setColor(GridColor)
     */
    @Override
    public void setColor(GridColor color) {
        super.setColor(color);
        rectangle.setColor(SimpleGfxColorMapper.getColor(color));
    }
}
