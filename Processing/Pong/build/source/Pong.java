import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Pong extends PApplet {

Ball ball;
Paddle player;
Paddle opponent;
int playerMovement = 0;
int opponentMovement = 0;

public void setup(){
  
  ball = new Ball(width/2, height/2, 5, 0, 20);
  player = new Paddle(15, 0 + 50, height - 50);
  opponent = new Paddle(width - 15, 0 + 50, height - 50);
}

public void draw(){

  background(0);
  player.move(playerMovement);
  player.render();
  opponent.render();
  ball.render();
  if(playerCollided()){
    ball.bounce((ball.y - player.y)/5);
  }
  if(opponentCollided()){
    ball.bounce((ball.y - opponent.y)/5);
  }
  ball.update();
  ball.edgeCollision();
  if(!keyPressed){
    playerMovement = 0;
  }
}

public void keyPressed() {
  if (keyCode == UP){
    playerMovement = -5;
  }else if (keyCode == DOWN){
    playerMovement = 5;
  }
}

public boolean playerCollided(){
  if(ball.x - ball.ballSize/2 < 25 && ball.y - ball.ballSize/2 < player.y + 50 && ball.y + ball.ballSize/2 > player.y - 50){
    return true;
  }else{
    return false;
  }
}

public boolean opponentCollided(){
   if(ball.x + ball.ballSize/2 > width - 25 && ball.y - ball.ballSize/2 < opponent.y + 50 && ball.y + ball.ballSize/2 > opponent.y - 50){
    return true;
  }else{
    return false;
  }
}
class Ball{
  float x;
  float y;
  float xSpeed;
  float ySpeed;
  float ballSize;

  public Ball(float x, float y, float xSpeed, float ySpeed, float ballSize){
    this.x = x;
    this.y = y;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.ballSize = ballSize;
  }

  public void render(){
    ellipse(x,y,ballSize, ballSize);
  }

  public void update(){
    x = x + xSpeed;
    y = y + ySpeed;
  }

  public void bounce(float dir){
    xSpeed = -xSpeed;
    ySpeed = dir;
  }
  public void edgeCollision(){
    if(y + ballSize/2 > height){
      ySpeed = -ySpeed;
    }else if(y - ballSize/2 < 0){
      ySpeed = -ySpeed;
    }
  }
}
class Paddle{
  float x;
  float y = height/2;
  int max, min;

  public Paddle(float x, int min, int  max){
    this.x = x;
    this.max = max;
    this.min = min;
  }

  public void move(float dist){
    y = y + dist;
    constrain();
  }

  public void render(){
    rectMode(CENTER);
    rect(x, y, 20, 100);
    rectMode(CORNER);
  }

  public void constrain(){
    if (y > max){
      y = max;
    }else if(y < min){
      y = min;
    }
  }
}
  public void settings() {  size(640, 480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Pong" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
