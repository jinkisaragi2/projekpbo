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
    PImage exitButtonImg; // New button image
    // Button variables
    int buttonX;
    int buttonY;
    int buttonWidth;
    int buttonHeight;
    boolean buttonPressed;
    boolean exitButtonPressed; // New button state

    PImage loadButtonImg;
    int loadButtonX;
    int loadButtonY;
    boolean loadButtonPressed;

    public void settings(){
        size(WIDTH, HEIGHT);
    }

    public void setup(){
        backgroundImg = loadImage("src/assets/background/intro.png");
        playButtonImg = loadImage("src/assets/sb.png");
        exitButtonImg = loadImage("src/assets/e.png"); // Load the exit button image
        loadButtonImg = loadImage("src/assets/l.png");

        buttonWidth = 200;
        buttonHeight = 50;
        buttonX = (WIDTH - buttonWidth) / 2;
        buttonY = (HEIGHT - buttonHeight) / 2+100;
        // Set the position of the load button
        loadButtonX = (WIDTH - buttonWidth) / 2;
        loadButtonY = (HEIGHT - buttonHeight) / 2+50;

        buttonPressed = false;
        exitButtonPressed = false; // Initialize the button state

        loadButtonPressed = false;


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
        // Draw the Exit button
        if (exitButtonPressed) {
            tint(255, 0, 0);
        } else {
            noTint();
        }
        image(exitButtonImg, buttonX, buttonY + buttonHeight + 20, buttonWidth, buttonHeight);

        if (loadButtonPressed) {
            tint(255, 0, 0);
        } else {
            noTint();
        }
        image(loadButtonImg, loadButtonX, loadButtonY, buttonWidth, buttonHeight);

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
        }// Check if the mouse is clicked within the Exit button area
        if (mouseX >= buttonX && mouseX <= buttonX + buttonWidth && mouseY >= buttonY + buttonHeight + 20 && mouseY <= buttonY + 2 * buttonHeight + 20) {
            exitButtonPressed = true;
            // Perform exit action here
            if (exitButtonPressed) {
                System.exit(0); // Terminate the program
            }
        }if (mouseX >= loadButtonX && mouseX <= loadButtonX + buttonWidth && mouseY >= loadButtonY && mouseY <= loadButtonY + buttonHeight) {
            loadButtonPressed = true;
            // Load the game or perform any action here
            if (loadButtonPressed) {
                // Code to load the game
            }
        }
    }

    public void mouseReleased() {
        buttonPressed = false;
        exitButtonPressed = false;
    }

}