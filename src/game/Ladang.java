package game;

import processing.core.*;

import java.awt.*;


public class Ladang extends PApplet {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static final int FPS = 60;

    Rectangle playerBoundary;
    int wallX = WIDTH;
    int wallY = HEIGHT;
    int wallWidth = wallX;
    int wallHeight = wallY;

    //bg
    PImage ladang;

    //player
    Player p;

    //enemy
    Enemy e;

    //player variables
    public PImage[] idle_1;
    public PImage[] walk_1;
    public PImage[] attack_1;
    public PImage[] attack_2;
    boolean running, idle, attacking_1, attacking_2;
    boolean left, right, up, down;

    //enemy variables
    public PImage idle_2;

    int c = 0;
    int indikator = 0;

    public void settings(){
        size(WIDTH, HEIGHT);
    }

    public void setup(){
        frameRate(FPS);

        registerMethod("keyPressed", this);
        registerMethod("keyReleased", this);
        //coll
        Rectangle wallBoundary = new Rectangle(wallX, wallY, wallWidth, wallHeight);

        ladang = loadImage("src/assets/background/background_2.png");
        p = new Player(425,30);
        e = new Enemy(800,200);
        //player
        idle_1 = new PImage[4];
        walk_1 = new PImage[2];
        idle_1[0] = loadImage("src/assets/avatar_b/avatar_b_belakang_2.png");
        idle_1[1] = loadImage("src/assets/avatar_b/avatar_b_depan_2.png");
        idle_1[2] = loadImage("src/assets/avatar_b/avatar_b_kiri_2.png");
        idle_1[3] = loadImage("src/assets/avatar_b/avatar_b_kanan_2.png");
        p.setIdle(idle_1);
        keyPressed();
        p.setWalk(walk_1);
        //enemy
        idle_2 = loadImage("src/assets/tumbuhan/row-10.png");
        e.setIdle(idle_2);

        ladang = loadImage("src/assets/background/background_2.png");

        //ini
        idle = true;
        running = false;
        attacking_1 = false;
        attacking_2 = false;

    }

    public void keyPressed() {
        if (key == 'w') {
            indikator = 0; up = true; running = true; idle = false;
            for (int i = 0; i < 2; i++) {
                walk_1[i] = loadImage("src/assets/walk/belakang_" + (i+1) + ".png");
            }
            p.setWalk(walk_1);
        }
        if (key == 's') {
            indikator = 1; down = true; running = true; idle = false;
            for (int i = 0; i < 2; i++) {
                walk_1[i] = loadImage("src/assets/walk/depan_" + (i+1) + ".png");
            }
            p.setWalk(walk_1);
        }
        if (key == 'a') {
            indikator = 2; left = true; running = true; idle = false;
            for (int i = 0; i < 2; i++) {
                walk_1[i] = loadImage("src/assets/walk/kiri_" + (i+1) + ".png");
            }
            p.setWalk(walk_1);
        }
        if (key == 'd') {
            indikator = 3; right = true; running = true; idle = false;
            for (int i = 0; i < 2; i++) {
                walk_1[i] = loadImage("src/assets/walk/kanan_" + (i+1) + ".png");
            }
            p.setWalk(walk_1);
        }
        if (key == 'f') {
            pindahMap();
        }
    }

    public void keyReleased() {
        running = attacking_1 = false;
        idle = true;
        c = -1;

        if (key == 'w') {
            up = false;
        } else if (key == 's') {
            down = false;
        } else if (key == 'a') {
            left = false;
        } else if (key == 'd') {
            right = false;
        }
    }

    public void draw(){
        background(ladang);
        p.update(left, right, up, down);

        // Draw the player
        if (idle) {
            p.drawIdle(this, indikator);
        } else if (running) {
            p.drawWalk(this, c);
        }

        //draw enemy
        if (idle) {
            e.drawIdle(this, indikator);
        }
    }

    public void change(){
        surface.setVisible(false);
    }

    public void pindahMap(){
        String[] args = {"pindahMap"};
        PApplet.runSketch(args, new Game());
        change();
    }

    private boolean collideRectRect(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        return x1 < x2 + w2 &&
                x1 + w1 > x2 &&
                y1 < y2 + h2 &&
                y1 + h1 > y2;
    }



}

