package game;

import processing.core.*;

public class Menu extends PApplet {

    String music = "src/assets/bgm/MenuMusic.wav";
    public BGMPlayer bgm;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    // Asset bg
    PImage backgroundImg;
    PImage playButtonImg;
    // Button variables
    int buttonX;
    int buttonY;
    int buttonWidth;
    int buttonHeight;
    boolean buttonPressed;

    public void settings(){
        size(WIDTH, HEIGHT);
    }

    public void setup(){
        backgroundImg = loadImage("src/assets/background/intro.png");
        playButtonImg = loadImage("src/assets/sb.png");
        buttonWidth = 200;
        buttonHeight = 50;
        buttonX = (WIDTH - buttonWidth) / 2;
        buttonY = (HEIGHT - buttonHeight) / 2+100;

        buttonPressed = false;

        bgm = new BGMPlayer(music);
        bgm.BGMPlayer();
    }

    public void draw() {
        background(backgroundImg);

        // Draw button
        if (buttonPressed) {
            // Adjust the button appearance when pressed
            tint(255, 0, 0); // Apply a red tint to the button image when pressed
        } else {
            noTint();
        }
        image(playButtonImg, buttonX, buttonY, buttonWidth, buttonHeight);
    }

    public void mousePressed() {
        // Check if the mouse is clicked within the button area
        if (mouseX >= buttonX && mouseX <= buttonX + buttonWidth && mouseY >= buttonY && mouseY <= buttonY + buttonHeight) {
            buttonPressed = true;
            // Start the game or perform any action here
            if (buttonPressed) {
                String[] args = {"game.Game"};
                PApplet.runSketch(args, new Game());
                surface.setVisible(false);
                this.bgm.stop();
            }
        }
    }

    public void mouseReleased() {
        buttonPressed = false;
    }

}