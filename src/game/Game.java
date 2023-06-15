
package game;

import processing.core.*;

import java.awt.*;


public class Game extends PApplet{

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    //FPS
    public static int FPS = 60;
//buat coll
Rectangle playerBoundary;
    int wallX = WIDTH;
    int wallY = HEIGHT;
    int wallWidth = wallX;
    int wallHeight = wallY;
    //asset bg
    PImage rumah;
    
    //player
    Player p ;
    
    //player variables
    public PImage[] idle_1;
    public PImage[] walk_1;
    public PImage[] attack_1;
    public PImage[] attack_2;
    boolean running, idle, attacking_1, attacking_2;
    boolean left, right, up, down;

    // Game state variables
    boolean isPaused = false;

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

        rumah = loadImage("src/assets/interior/in3.png");
        p = new Player(800,200);
        idle_1 = new PImage[4];
        walk_1 = new PImage[2];
        idle_1[0] = loadImage("src/assets/avatar_b/avatar_b_belakang_2.png");
        idle_1[1] = loadImage("src/assets/avatar_b/avatar_b_depan_2.png");
        idle_1[2] = loadImage("src/assets/avatar_b/avatar_b_kiri_2.png");
        idle_1[3] = loadImage("src/assets/avatar_b/avatar_b_kanan_2.png");
        p.setIdle(idle_1);
        keyPressed();
        p.setWalk(walk_1);

        //ini
        idle = true;
        running = false;
        attacking_1 = false;
        attacking_2 = false;

    }
//ini
    public void keyPressed() {
        // Handle game controls when the game is not paused
        if (!isPaused) {
            // Handle player movement controls


            if (key == 'w') {
                indikator = 0; up = true; running = true; idle = false;
                for (int i = 0; i < 2; i++) {
                    walk_1[i] = loadImage("src/assets/walk/belakang_" + (i+1) + ".png");
                }
                p.setWalk(walk_1);

                if (left && p.getX() <= 0) {
                    left = false;
                }
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

        // Handle other game controls
        if (key == 'p') {
            // Pause or unpause the game when 'p' key is pressed
            isPaused = !isPaused;
        }
        
        // Cek kolisi dengan batas peta
        if (left && p.getX() <= 0) {
            left = false;
        }
        if (right && p.getX()>= WIDTH) {
            right = false;
        }
        if (up && p.getY() <= 0) {
            up = false;
        }
        if (down && p.getY()>= HEIGHT) {
            down = false;
        }
    }
    public void keyReleased() {
        // Handle game controls when the game is not paused
        if (!isPaused) {
            // Handle player movement controls
            if (key == 'w') {
                up = false;
            } else if (key == 's') {
                down = false;
            } else if (key == 'a') {
                left = false;
            } else if (key == 'd') {
                right = false;
            }

            running = attacking_1 = false;
            idle = true;
            c = -1;

        }
    }

    public void draw() {
        if (!isPaused) {
            // Update game logic and draw game objects only if the game is not paused
            background(rumah);
            p.update(left, right, up, down);

            // Draw the player
            if (idle) {
                p.drawIdle(this, indikator);
            } else if (running) {
                p.drawWalk(this, c);
            }

            // Draw other game objects (enemies, obstacles, etc.)

            c++;
        } else {
            // Display a pause message or screen when the game is paused
            background(0);  // Black background
            fill(255);  // White text color
            textAlign(CENTER, CENTER);
            textSize(48);
            text("Game Paused", width / 2, height / 2);
        }
    }

    private boolean collideRectRect(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        return x1 < x2 + w2 &&
                x1 + w1 > x2 &&
                y1 < y2 + h2 &&
                y1 + h1 > y2;
    }

    public void change(){
        surface.setVisible(false);
    }

    public void pindahMap(){
        String[] args = {"pindahMap"};
        PApplet.runSketch(args, new Ladang());
        change();
    }
    public static void main(String[] args) {
        PApplet.main("game.Menu");

//        PApplet.main("game.Game");
    }
}
