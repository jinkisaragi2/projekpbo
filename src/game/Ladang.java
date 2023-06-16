package game;

import processing.core.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Ladang extends PApplet {

    String music = "src/assets/bgm/GameMusic.wav";
    public BGMPlayer bgm;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static final int PLAYER_SPEED = 5;

    public static final int FPS = 60;

    Rectangle playerBoundary;
    int wallX = WIDTH;
    int wallY = HEIGHT;
    int wallWidth = wallX;
    int wallHeight = wallY;

    //bg
    PImage ladang;

    boolean isPaused = false;

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

    boolean pindah;
    private List<Wall> warps;

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
//        Rectangle wallBoundary = new Rectangle(wallX, wallY, wallWidth, wallHeight);

        ladang = loadImage("src/assets/background/background_2.png");
        p = new Player(425,100);
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
        idle_2 = loadImage("src/assets/enemy/Pokemans_044_1.png");
        e.setIdle(idle_2);

        ladang = loadImage("src/assets/background/background_2.png");

        //ini
        idle = true;
        running = false;
        attacking_1 = false;
        attacking_2 = false;

        warps = new ArrayList<>();
        warps.add(new Wall(425,30,100,50));

        //music
        bgm = new BGMPlayer(music);
        bgm.BGMPlayer();
    }

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


            for (Wall warp : warps){
                Rectangle warpRect = new Rectangle(warp.getX(), warp.getY(), warp.getWidth(), warp.getHeight());
                if (pindah == false){
                    if (playerRect.intersects(warpRect)){
                        pindahMap();
                        pindah = true;
                        System.out.println("tunjungan plaza");
                        return;
                    }
                }
            }
            // Update player's position if no collision
            p.setX(newPlayerX);
            p.setY(newPlayerY);
        }
    }

    public void keyPressed() {
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
            if(key==',') {
                attacking_1 = true;
            }
            if(key=='.') {
                attacking_2 = true;
            }
        }

        // Handle other game controls
        if (key == 'p') {
            // Pause or unpause the game when 'p' key is pressed
            isPaused = !isPaused;
        }
    }

    public void keyReleased() {
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

    public int f = 0;

    boolean inactive = false;

    public void draw(){
        if (!isPaused) {
            movePlayer();
            // Update game logic and draw game objects only if the game is not paused
            background(ladang);
            //p.update(left, right, up, down);

            // Draw the player
            if (idle) {
                p.drawIdle(this, indikator);
                e.drawIdle(this, c);
            } else if (running) {
                p.drawWalk(this, c);
                e.drawIdle(this, c);
            }

            if(attacking_1){
                boolean invoke_battle = true;
                if(invoke_battle&&!inactive){
                    change();
                    invokeBattle();
                    inactive = true;
                }
                invoke_battle = false;
            }

            // Draw other game objects (enemies, obstacles, etc.)

            c++;

            //Invis rect
            fill(255, 0);
            noStroke();


            //Draw Player
            rect(p.getX(), p.getY(), p.getWidth(), p.getHeight());

            //Draw Wall
//            for (Wall wall : walls) {
//                rect(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
//            }
            for (Wall warp : warps) {
                rect(warp.getX(), warp.getY(), warp.getWidth(), warp.getHeight());
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

    public void invokeBattle(){
        String[] args = {"InvokedBattle"};
        PApplet.runSketch(args, new Battle());
    }

    public void change(){
        surface.setVisible(false);
        this.bgm.stop();
    }

    public void pindahMap(){
        String[] args = {"pindahMap"};
        PApplet.runSketch(args, new Game());
        change();
    }





}

