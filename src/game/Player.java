package game;

import processing.core.*;

import java.util.ArrayList;

public class Player implements Interface{

    public PImage[] idle;
    public PImage[] walk;
    public PImage[] attack1;
    public PImage[] attack2;

    public int x, y;

    private int height  = 80;
    private int width  = 60;

    int t = 0;

    public Player(PImage[] idle, int x, int y, PImage[] walk, PImage[] attack1, PImage[] attack2) {
        this.idle = idle;
        this.x = x;
        this.y = y;
        this.walk = walk;
        this.attack1 = attack1;
        this.attack2 = attack2;
    }

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void drawIdle(PApplet app, int f) {
        int t = 0;
        t = f;
        app.image(idle[t], x, y);
    }

//    public void drawIdle(PApplet app, int f) {
//        int t = 0;
//        t = f;
//        app.image(idle[t], x, y);
//    }

    public void drawWalk(PApplet app, int f) {
        if (f == -1) {
            t = 0;
            f = 0;
        }
        if (f % 10 == 0) {
            t++;
        }
        if (t > 1) {
            t = 0;
        }
        app.image(walk[t], x, y);
    }

    boolean isCollision(Enemy e){

        if (this.x < e.x + 50 &&
                this.x + 50 > e.x &&
                this.y < e.y + 50 &&
                50 + this.y > e.y) {

            System.out.println("collision");
            return true;
        } else {
            System.out.println(e.x+50);
            System.out.println(e.y+50);
            System.out.println(this.x);
            System.out.println(this.y);
            System.out.println("Ndak collide");
            return false;
        }
    }

    public boolean drawAttack1(PApplet app, int f, ArrayList<Enemy> e){
        if(f==-1) {t=0; f = 0;}
        if(f%10==0) t++;
        if(t>5) {
            t=0;
            boolean collided = false;
            for(Enemy enemy : e){
                collided = isCollision(enemy);

            }
            if(collided){
                System.out.println("Commence Battle");
                return true;
            }
        }
        app.image(attack1[t],x,y);
        return false;
    }

    public void drawAttack2(PApplet app, int f){
        if(f==-1) {t=0; f = 0;}
        if(f%10==0) t++;
        if(t==8) t=0;
        app.image(walk[t],x,y);
    }

    public int vy, vx;

    public void update(boolean left, boolean right, boolean up, boolean down) {
        vx = vy = 0;
        if (left) {
            vx = -5;
        }
        if (right) {
            vx = 5;
        }
        if (up) {
            vy = -5;
        }
        if (down) {
            vy = 5;
        }
        if (up && down) {
            vy = 0;
        }
        if (left && right) {
            vx = 0;
        }
        x += vx;
        y += vy;
    }

    public PImage[] getIdle() {
        return idle;
    }

    public void setIdle(PImage[] idle) {
        this.idle = idle;
    }

    public PImage[] getWalk() {
        return walk;
    }

    public void setWalk(PImage[] walk) {
        this.walk = walk;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}