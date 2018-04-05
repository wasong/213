package ca.cmpt213.as4.shapes;

import java.awt.Color;
import ca.cmpt213.as4.UI.Canvas;

public interface Shape {
    int getLocationX();
    int getLocationY();

    void setBorderChar(char c);
    char getBorderChar();

    void setColor(Color c);
    Color getColor();

    void draw(Canvas canvas);
}
