import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake extends GameEngine{
    // Game conditions
    boolean left, right, up, down;
    boolean gameOver;
    // Dot variables
    ArrayList dotList = new ArrayList();
    double dotX, dotY;
    double dotVX, dotVY;
    int dot;
    // Snake variables
    double snakeX,  snakeY;
    double snakeVX, snakeVY;
    // Apple location
    double appleX, appleY;
    // Score
    int score;


    public static void main(String[] args) {
        createGame(new Snake());
    }
    // Snake
    public void initSnake(){
        // Place snake in random area
        snakeX = round(rand(400));
        snakeY = round(rand(400));
        // Set velocity
        snakeVX = 100;
        snakeVY = 0;
    }
    public void updateSnake(double dt) {
        snakeX += snakeVX*dt;
        snakeY += snakeVY*dt;
        // If snake touches edge of screen then game over
        if (snakeX >= 499 || snakeY >= 499) {
            gameOver = true;
        }
        if (snakeX <= 0 || snakeY <= 0){
            gameOver = true;
        }
        if (left) {
            // Move snake left
            snakeX -= 100 * dt;
            dotX -= 100 * dt;
            snakeVY = 0;
            snakeVX = -100;
            dotVY = 0;
            dotVX = -100;

            // Left Boundary
            if (snakeX < 0) {
                snakeX = 0;
            }
        }
        // User holding right
        if (right) {
            // Move snake right
            snakeX += 100 * dt;
            dotX += 100 * dt;
            snakeVY = 0;
            snakeVX = 100;
            dotVY = 0;
            dotVX = 100;
            // Right Boundary
            if (snakeX >= 500) {
                snakeX = 499;
            }
        }
        // User holding up
        if (up) {
            // Move snake up
            snakeY -= 100 * dt;
            dotY -= 100 * dt;
            snakeVX = 0;
            snakeVY = -100;
            dotVY = -100;
            dotVX = 0;

            // Top Boundary
            if (snakeY < 0) {
                snakeY = 0;
            }
        }
        // User holding down
        if (down) {
            // Move snake down
            snakeY += 100 * dt;
            dotY += 100 * dt;
            snakeVX = 0;
            snakeVY = 100;
            dotVY = 100;
            dotVX = 0;
            // Bottom Boundary
            if (snakeY >= 500) {
                snakeY = 499;
            }
        }
    }
    public void drawSnake(){
        Image headPNG = loadImage("head.png");
        drawImage(headPNG,snakeX, snakeY);
    }
    // Dots
    public void initDot() {
        for (int i=0; i < 4; i++) {
            dotList.add(dot);
        }
        dotX = snakeX-10;
        dotY = snakeY;
        dotVX = 100;
        dotVY = 0;
    }
    public void updateDot(double dt) {
        if(distance(dotX, dotY, snakeX, snakeY) <= 5){
            gameOver = true;
        }
        if (dotX >= 499 || dotY >= 499) {
            gameOver = true;
        }
        if (dotX <= 0 || dotY <= 0){
            gameOver = true;
        }
        dotX += dotVX*dt;
        dotY += dotVY*dt;

        if (left){
            dotX = snakeX-10;
        }
        if (right){
            dotX = snakeX+10;
        }
        if (up){
            dotY = snakeY-10;
        }
        if (down){
            dotY = snakeY+10;
        }
    }
    public void drawDot() {
        Image dotPNG = loadImage("dot.png");
        for (int i = 0; i < dotList.size(); i++) {
            drawImage(dotPNG, dotX-i, dotY+i);
        }
    }
    // Apple
    public void initApple(){
        appleX = round(rand(400));
        appleY = round(rand(400));
    }
    public void updateApple(){
        // Apple and snake collision
        if(distance(appleX, appleY, snakeX, snakeY) <= 5){
            dotList.add(dot);
            score+=1;
            initApple();
        }
    }
    public void drawApple(){
        Image applePNG = loadImage("apple.png");
        drawImage(applePNG, appleX, appleY,10,10);
    }

    // initialise game
    public void init(){
        setupWindow(500, 500);
        gameOver = false;
        // init
        initSnake();
        initApple();
        initDot();
    }
    @Override
    public void update(double dt) {
        if(gameOver){return;}
        updateSnake(dt);
        updateApple();
        updateDot(dt);
    }

    @Override
    public void paintComponent() {
        changeBackgroundColor(black);
        clearBackground(500,500);
        drawSnake();
        drawApple();
        drawDot();
        if (gameOver){
            changeColor(red);
            drawText(40,40,"Score:" + score, Font.SANS_SERIF, 30);
            changeColor(red);
            drawText(20,150,"Game Over, Press R to restart.", Font.SANS_SERIF, 30);
        }
        if (!gameOver){
            changeColor(red);
            drawText(20,20,"Score: " + score + " Press R to restart.", Font.SANS_SERIF, 10);
        }
    }
    // KeyPressed Event Handler
    public void keyPressed(KeyEvent event) {
        // Up Arrow
        if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }

        // Down Arrow
        if (event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }

        // Left Arrow
        if (event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }

        // Right Arrow
        if (event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (event.getKeyCode() == KeyEvent.VK_R){
            init();
        }
    }
    // KeyReleased Event Handler
    public void keyReleased(KeyEvent event) {
        // Up Arrow
        if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }

        // Down Arrow
        if (event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }

        // Left Arrow
        if (event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }

        // Right Arrow
        if (event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
    }
}
