package game;

import processing.core.*;

import java.util.ArrayList;

public class Battle extends PApplet {


    String music = "src/assets/bgm/BattleMusic.wav";
    public BGMPlayer bgm;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int FPS = 60;

    //Assets
    public PImage battlebg;
    public Player p;

    public Enemy e;

    public PImage[] idle_1;
    public PImage[] walk_1;
    public PImage[] attack_1;
    public PImage[] attack_2;
    boolean running, idle, attacking_1, attacking_2;
    boolean left, right, up, down;

    boolean isPaused;

    boolean chooseMove = true;
    boolean isAttacking = false;
    boolean isDefending = false;
    boolean isFleeing = false;
    boolean isUsingItem = false;
    public PImage idle_2;

    int indikator = 0;

    public void setUp(){
        frameRate(FPS);

        registerMethod("keyPressed", this);
        registerMethod("keyReleased", this);
        //coll
//        Rectangle wallBoundary = new Rectangle(wallX, wallY, wallWidth, wallHeight);

        battlebg = loadImage("src/assets/background/bgbattle.png");
        p = new Player(300,500);
        e = new Enemy(800,500);
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

        //ini
        left = right = up = down = false;

    }

    public void settings(){
        size(WIDTH,HEIGHT);
    }

    int f = 0;
    GUIButton b;

    public boolean rectOverAttack = false;
    public boolean rectOverItems = false;
    public boolean rectOverDefend = false;
    public boolean rectOverFlee = false;

    boolean overRect(int x, int y, int width, int height)  {
        if (mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
            return true;
        } else {
            return false;
        }
    }

//    void update(int x, int y, GUIButton b) {
//
//        if ( overRect(b.x, b.y, b.width, b.height) ) {
//            rectOverAttack = true;
//        }
//        else{
//            rectOverAttack = false;
//        }
//        if(overRect((b.x+250), b.y, b.width, b.height)){
//            rectOverDefend = true;
//        }
//        else{
//            rectOverDefend = false;
//        }
//        if(overRect(b.x, (b.y+75), b.width, b.height)){
//            rectOverItems = true;
//        }
//        else{
//            rectOverItems = false;
//        }
//        if(overRect((b.x+250), (b.y+75), b.width, b.height)){
//            rectOverFlee = true;
//        }
//        else{
//            rectOverFlee = false;
//        }
//    }

    public void mousePressed(){
        if(rectOverAttack){
            isAttacking = true;
            chooseMove = false;
        }
        else if(rectOverDefend){
            isDefending = true;
            chooseMove = false;
        }
        else if(rectOverItems){
            isUsingItem = true;
            chooseMove = false;
        }
        else if(rectOverFlee){
            isFleeing = true;
            chooseMove = false;
        }
    }

    int c = 0;

    public void draw(){
        if (!isPaused) {
            // Update game logic and draw game objects only if the game is not paused
            background(battlebg);
            //p.update(left, right, up, down);

            // Draw the player
            if (idle) {
                p.drawIdle(this, indikator);
                e.drawIdle(this, c);
            } else if (running) {
                p.drawWalk(this, c);
                e.drawIdle(this, c);
            }

//            if(attacking_1){
//                boolean invoke_battle = true;
//                if(invoke_battle&&!inactive){
//                    change();
//                    invokeBattle();
//                    inactive = true;
//                }
//                invoke_battle = false;
//            }

            // Draw other game objects (enemies, obstacles, etc.)

            c++;

            //Invis rect
            fill(255, 0);
//            noStroke();


            //Draw Player
            rect(p.getX(), p.getY(), p.getWidth(), p.getHeight());

            //draw enemy
            rect(e.x, e.y, e.getWidth(), e.getHeight());

            //Draw Wall
//            for (Wall wall : walls) {
//                rect(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
//            }
//            for (Wall warp : warps) {
//                rect(warp.getX(), warp.getY(), warp.getWidth(), warp.getHeight());
//            }
        } else {
            // Display a pause message or screen when the game is paused
            background(0);  // Black background
            fill(255);  // White text color
            textAlign(CENTER, CENTER);
            textSize(48);
            text("Game Paused", width / 2, height / 2);
        }
    }

}
