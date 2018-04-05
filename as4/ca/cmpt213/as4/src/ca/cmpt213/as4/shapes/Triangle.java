package ca.cmpt213.as4.shapes;

public class Triangle extends ShapeImpl {
    private int width;

    public Triangle(int x, int y, int width) {
        super(x, y, width, width);
        this.width = width;
    }


    @Override
    protected boolean isBorder(int xPos, int yPos) {
        int xIndex = xPos - getLocationX();
        int yIndex = yPos - getLocationY();

        return xIndex == yIndex || xPos == getLocationX() || yPos == (getLocationY() + this.width - 1);
    }

    @Override
    protected boolean isInside(int xPos, int yPos) {
        int xIndex = xPos - getLocationX();
        int yIndex = yPos - getLocationY();

        return xIndex <= yIndex;
    }
}
