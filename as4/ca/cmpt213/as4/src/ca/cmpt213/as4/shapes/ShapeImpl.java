package ca.cmpt213.as4.shapes;

import java.awt.Color;
import ca.cmpt213.as4.UI.Canvas;

public abstract class ShapeImpl implements Shape {
    private int x;
    private int y;
    private int width;
    private int height;
    private char borderChar = '*';
    private Color color = Color.YELLOW;

    public ShapeImpl(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getLocationX() {
        return this.x;
    }

    @Override
    public int getLocationY() {
        return this.y;
    }

    @Override
    public void setBorderChar(char borderChar) {
        this.borderChar = borderChar;
    }

    @Override
    public char getBorderChar() {
        return this.borderChar;
    }

    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void draw(Canvas canvas) {
        // Setting colour and text:
        for (int col = this.x; col < (this.x + this.width); col++) {
            for (int row = this.y; row < (this.y + this.height); row++) {
                if (isBorder(col, row)) {
                    canvas.setCellColor(col, row, this.color);
                    canvas.setCellText(col, row, this.borderChar);
                } else if (isInside(col, row)) {
                    canvas.setCellColor(col, row, this.color);
                }
            }
        }
    }

    protected int getWidth() {
        return this.width;
    }

    protected int getHeight() {
        return this.height;
    }

    protected abstract boolean isBorder(int xPos, int yPos);
    protected abstract boolean isInside(int xPos, int yPos);
}
