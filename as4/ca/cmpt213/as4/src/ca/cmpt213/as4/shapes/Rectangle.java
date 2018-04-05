package ca.cmpt213.as4.shapes;

public class Rectangle extends ShapeImpl {
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.width = width;
        this.height = height;
    }


    @Override
    protected boolean isBorder(int xPos, int yPos) {
        return yPos == getLocationY() ||
            xPos == getLocationX() ||
            yPos == (getLocationY() + this.height - 1) ||
            xPos == (getLocationX() + this.width - 1);
    }

    @Override
    protected boolean isInside(int xPos, int yPos) {
        return true;
    }
}
