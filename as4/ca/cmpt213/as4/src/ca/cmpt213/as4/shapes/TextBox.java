package ca.cmpt213.as4.shapes;

import ca.cmpt213.as4.UI.Canvas;

import java.util.ArrayList;

public class TextBox extends Rectangle {
    private int width;
    private int height;
    private String message;

    public TextBox(int x, int y, int width, int height, String message) {
        super(x, y, width, height);
        this.width = width;
        this.height = height;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void draw(Canvas canvas) {
//        ArrayList<String> rows = new ArrayList<>();
//        int rowWidth = width - 2; // subtract 2 for borders
//        int maxRow = height - 2;
//
//        // split message into rows
//        String tempMessage = this.message;
//        System.out.println(tempMessage);
//        while (tempMessage.length() >= rowWidth) {
//            rows.add(tempMessage.substring(0, rowWidth));
//            tempMessage = tempMessage.substring(rowWidth, tempMessage.length());
//        }

        // draw rectangle
        super.draw(canvas);
    }
}
