/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Melvin
 */
public class Player {
    public PImage[] idle;
    
    public PImage[] walk;

    public int x, y;
    
    int t = 0;

    public Player(PImage[] idle, int x, int y, PImage[] walk) {
        this.idle = idle;
        this.x = x; 
        this.y = y;
        this.walk = walk;
    }
    
    public Player(int x, int y) {
        this.x = x; 
        this.y = y;
    }
    
    public void drawIdle(PApplet app, int f){
        int t = 0;
        if (f == -1){
            t = 0;
            f = 0;
        }
        if (f%10 == 0){
            t++;
        }
        if (t>4){
            t = 0;
        }
        app.image(idle[t], x, y);
    }
    
    public void drawWalk(PApplet app, int f){
        if (f == -1){
            t = 0;
            f = 0;
        }
        if (f%10 == 0){
            t++;
        }
        if (t>1){
            t = 0;
        }
        app.image(walk[t],x,y);
    }
    
    public int vy, vx;
    
    public void update(boolean left,boolean right,boolean up,boolean down){
        vx = vy = 0;
        if(left){
            vx = -5;
        }
        if(right){
            vx = 5;
        }
        if(up){
            vy = -5;
        }
        if(down){
            vy = 5;
        }
        if(up&&down){
            vy = 0;
        }
        if(left&&right){
            vx = 0;
        }
        x+=vx;
        y+=vy;
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
    
    
}
