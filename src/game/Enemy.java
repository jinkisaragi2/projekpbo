package game;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemy {
    public PImage idle;

    public int x, y;

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
}
