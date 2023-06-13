
package game;

import processing.core.*;

import java.awt.*;


public class Game extends PApplet{

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    
    //FPS
    public static int FPS = 60;

    //asset bg
    PImage rumah;
    PImage ladang;
    
    //player
    Player p ;
    
    //player variables
    public PImage[] idle_1;
    public PImage[] walk_1;
    public PImage[] attack_1;
    public PImage[] attack_2;
    boolean running, idle, attacking_1, attacking_2;
    boolean left, right, up, down;
    
    int c = 0;
    int indikator = 0;

    public void settings(){
        size(WIDTH, HEIGHT);
    }
    
    public void setup(){
        frameRate(FPS);
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

        ladang = loadImage("src/assets/background/background_2.png");

        //ini
        idle = true;
        running = false;
        attacking_1 = false;
        attacking_2 = false;

    }
//ini
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

    public void draw() {
        background(rumah);
        p.update(left, right, up, down);

        // Check for collisions with enemies


        // Draw the player
        if (idle) {
            p.drawIdle(this, indikator);
        } else if (running) {
            p.drawWalk(this, c);
        }

        // Draw other game objects (enemies, obstacles, etc.)

        c++;
//            if (key == 'f') {
//                if (p.getX() > (WIDTH / 2) && p.getX() > 100 && p.getY() < 400) {
//                    // Perform the warp action
//                    p.setX(WIDTH - 1);  // Set the player's X position to the rightmost edge of the screen
//                    p.setY(HEIGHT / 2);  // Set the player's Y position to the middle of the screen
//
//                    // Switch to the ladang map
//                    background(ladang);  // Set the ladang map as the background
//
//                    // Draw the player on the ladang map
//                    p.drawIdle(this, c);  // Draw the player's idle state on the ladang map
//
//                }
//            }
    }


    
    public static void main(String[] args) {

        PApplet.main("game.Game");

    }
    
}
