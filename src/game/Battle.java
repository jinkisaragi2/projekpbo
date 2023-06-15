package game;

import processing.core.*;

import java.util.ArrayList;

public class Battle extends PApplet {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;


    public static final int FPS = 60;

    //Assets
    public  PImage battlebg;

    public Player p;

    public Enemy e;

    public PImage[] idle_1;
    public PImage[] walk_1;
    public PImage[] attack_1;
    public PImage[] attack_2;
    boolean running, idle, attacking_1, attacking_2;
    boolean left, right, up, down;

    boolean chooseMove = true;
    boolean isAttacking = false;
    boolean isDefending = false;
    boolean isFleeing = false;
    boolean isUsingItem = false;
    public PImage idle_2;

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

    void update(int x, int y, GUIButton b) {

        if ( overRect(b.x, b.y, b.width, b.height) ) {
            rectOverAttack = true;
        }
        else{
            rectOverAttack = false;
        }
        if(overRect((b.x+250), b.y, b.width, b.height)){
            rectOverDefend = true;
        }
        else{
            rectOverDefend = false;
        }
        if(overRect(b.x, (b.y+75), b.width, b.height)){
            rectOverItems = true;
        }
        else{
            rectOverItems = false;
        }
        if(overRect((b.x+250), (b.y+75), b.width, b.height)){
            rectOverFlee = true;
        }
        else{
            rectOverFlee = false;
        }
    }

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
        update(mouseX, mouseY, b);
        background(battlebg);

        if(chooseMove){
            p.drawIdle(this, f);
            e.drawIdle(this, f);
        }
        else if(isAttacking){
            textSize(30);
            fill(248);
            text("The Player Attacks..", 120, 450);
            if(c>85){
                f = -1;
                c = 0;
                e.x = 200;
                isAttacking = false;
                chooseMove = true;
                System.out.println(f+",");
            }
//            else if(c>70){
//                p.drawAttack1(this, f, );
//                f= -1;
//                System.out.println(c+"");
//                c++;
//            }
            else{
                p.x+=10;
                p.drawWalk(this, f);
                c++;
                f= -1;
            }
        }

        if(chooseMove){
            //Draw Attack button
            fill(255,245,248);
            stroke(255, 245, 248);
            rect(b.x,b.y,b.width,b.height);

            //Draw Defend Button
            fill(255,245,248);
            stroke(255, 245, 248);
            rect((b.x+250),b.y,b.width,b.height);

            //Draw Items Button
            fill(255,245,248);
            stroke(255, 245, 248);
            rect(b.x,(b.y+75),b.width,b.height);

            //Draw Flee Button
            fill(255,245,248);
            stroke(255, 245, 248);
            rect((b.x+250),(b.y+75),b.width,b.height);
        }

        //Draw Enemy
        e.drawIdle(this, f);
        f++;
    }

}
