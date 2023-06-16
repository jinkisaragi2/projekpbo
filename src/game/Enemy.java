package game;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemy {
    public PImage idle;

    public int x, y;

    private int height  = 80;
    private int width  = 60;

    int t = 0;

    public Enemy(PImage idle, int x, int y) {
        this.idle = idle;
        this.x = x;
        this.y = y;
    }

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawIdle(PApplet app, int f){
        int t = 0;
        t = f;
        app.image(idle, x, y);
    }

    public PImage getIdle() {
        return idle;
    }

    public void setIdle(PImage idle) {
        this.idle = idle;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
