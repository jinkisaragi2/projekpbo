package game;

import java.awt.*;

public class GUIButton {
    public int x,y;
    public int width,height;
    public Color bgcolor;

    public GUIButton(int x, int y, int width, int height, Color bgcolor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bgcolor = bgcolor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(Color bgcolor) {
        this.bgcolor = bgcolor;
    }
}
