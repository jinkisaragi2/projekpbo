package game;

import processing.core.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends PApplet {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static final int PLAYER_SPEED = 5;
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
    Player p;

    //player variables
    public PImage[] idle_1;
    public PImage[] walk_1;
    public PImage[] attack_1;
    public PImage[] attack_2;
    boolean running, idle, attacking_1, attacking_2;
    boolean left, right, up, down;

    // Game state variables
    boolean isPaused = false;
    private List<Wall> walls; // List of walls

    int c = 0;
    int indikator = 0;

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS);

        registerMethod("keyPressed", this);
        registerMethod("keyReleased", this);
        //coll
//        Rectangle wallBoundary = new Rectangle(wallX, wallY, wallWidth, wallHeight);

        rumah = loadImage("src/assets/interior/in3.png");
        p = new Player(800, 200);
        idle_1 = new PImage[4];
        walk_1 = new PImage[2];
        idle_1[0] = loadImage("src/assets/avatar_b/avatar_b_belakang_2.png");
        idle_1[1] = loadImage("src/assets/avatar_b/avatar_b_depan_2.png");
        idle_1[2] = loadImage("src/assets/avatar_b/avatar_b_kiri_2.png");
        idle_1[3] = loadImage("src/assets/avatar_b/avatar_b_kanan_2.png");
        p.setIdle(idle_1);
        p.setWalk(walk_1);

        //ini
        idle = true;
        running = false;
        attacking_1 = false;
        attacking_2 = false;

        //generate tembok
        walls = new ArrayList<>();
        walls.add(new Wall(0, 0, 250, 720));
        walls.add(new Wall(0, 0, 1280, 190));
        walls.add(new Wall(0, 540, 580, 400));
        walls.add(new Wall(695, 540, 580, 400));
        walls.add(new Wall(1030, 0, 250, 720));
    }
//ini

    public void movePlayer() {
        if (!isPaused) {

            int newPlayerX = p.getX();
            int newPlayerY = p.getY();

            if (up) {
                newPlayerY -= PLAYER_SPEED;
                indikator = 0;
                running = true;
                idle = false;
                for (int i = 0; i < 2; i++) {
                    walk_1[i] = loadImage("src/assets/walk/belakang_" + (i + 1) + ".png");
                }
                p.setWalk(walk_1);
            }
            if (down) {
                newPlayerY += PLAYER_SPEED;
                indikator = 1;
                running = true;
                idle = false;
                for (int i = 0; i < 2; i++) {
                    walk_1[i] = loadImage("src/assets/walk/depan_" + (i + 1) + ".png");
                }
                p.setWalk(walk_1);
            }
            if (left) {
                newPlayerX -= PLAYER_SPEED;
                indikator = 2;
                running = true;
                idle = false;
                for (int i = 0; i < 2; i++) {
                    walk_1[i] = loadImage("src/assets/walk/kiri_" + (i + 1) + ".png");
                }
                p.setWalk(walk_1);
            }
            if (right) {
                newPlayerX += PLAYER_SPEED;
                indikator = 3;
                running = true;
                idle = false;
                for (int i = 0; i < 2; i++) {
                    walk_1[i] = loadImage("src/assets/walk/kanan_" + (i + 1) + ".png");
                }
                p.setWalk(walk_1);
            }

            // Check for collision with walls
            Rectangle playerRect = new Rectangle(newPlayerX, newPlayerY, p.getWidth(), p.getHeight());

            for (Wall wall : walls) {
                Rectangle wallRect = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
                if (playerRect.intersects(wallRect)) {
                    // Collision detected, don't update player's position
                    System.out.println("natap mas");
                    return;
                }
            }
            // Update player's position if no collision
            p.setX(newPlayerX);
            p.setY(newPlayerY);
        }
    }

    public void keyPressed() {
        // Handle game controls when the game is not paused
        if (!isPaused) {

            int newPlayerX = p.getX();
            int newPlayerY = p.getY();

            if (key == 'w') {
                up = true;
            }
            if (key == 's') {
                down = true;
            }
            if (key == 'a') {
                left = true;
            }
            if (key == 'd') {
                right = true;
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
            movePlayer();
            // Update game logic and draw game objects only if the game is not paused
            background(rumah);
            //p.update(left, right, up, down);

            // Draw the player
            if (idle) {
                p.drawIdle(this, indikator);
            } else if (running) {
                p.drawWalk(this, c);
            }

            // Draw other game objects (enemies, obstacles, etc.)
            c++;

            //Invis rect
            fill(255, 0);

            //Draw Player
            rect(p.getX(), p.getY(), p.getWidth(), p.getHeight());

            //Draw Wall
            for (Wall wall : walls) {
                rect(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
            }
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
        return x1 < x2 + w2
                && x1 + w1 > x2
                && y1 < y2 + h2
                && y1 + h1 > y2;
    }

    public void change() {
        surface.setVisible(false);
    }

    public void pindahMap() {
        String[] args = {"pindahMap"};
        PApplet.runSketch(args, new Ladang());
        change();
    }

    public static void main(String[] args) {
        PApplet.main("game.Game");

//        PApplet.main("game.Game");
    }
}
