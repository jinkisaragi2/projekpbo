/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package game;

import processing.core.*;

/**
 *
 * @author Melvin
 */
public class Game extends PApplet{

    public static final int WIDTH = 1280;
    public static final int HEIGTH = 720;
    
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
    
    
    public void settings(){
        size(WIDTH, HEIGTH);
    }
    
    public void setup(){
        frameRate(FPS);
        rumah = loadImage("src/assets/interior/in1.png");
        p = new Player(300,200);
        idle_1 = new PImage[5];
        walk_1 = new PImage[2];
        for (int i = 0; i < 5; i++) {
            idle_1[i] = loadImage("src/assets/avatar_b/avatar_b_depan_2.png");
        }
        p.setIdle(idle_1);
        for (int i = 0; i < 2; i++) {
            walk_1[i] = loadImage("src/assets/walk/" + (i+1) + ".png");
        }
        p.setWalk(walk_1);
    }
    
    
    public void keyPressed(){
        if(key=='w'){ up = true; running = true; idle = false;}
        if(key=='s'){ down = true; running = true; idle = false;}
        if(key=='a'){ left = true; running = true; idle = false;}
        if(key=='d'){ right = true; running = true; idle = false;}
    }
    
    public void keyReleased(){
        running = attacking_1 = false;
        idle = true;
        c = -1;
        if(key=='w'){ up = false;}
        if(key=='s'){ down = false;}
        if(key=='a'){ left = false;}
        if(key=='d'){ right = false;}
    }
    
    public void draw(){
        background(rumah);
        p.update(left, right, up, down);
        if (idle){
            p.drawIdle(this, c);
        }
        else if (running){
            p.drawWalk(this, c);
        }
        
        c++;
    }
    
    public static void main(String[] args) {
        PApplet.main("game.Game");
    }
    
}
