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

public class GeneticAlgorithm extends PApplet {

Bot bot;
Obstacle[] obstacles;


public void setup(){
  
  bot = new Bot();
  bot.newBot();
  obstacles = new Obstacle[10];
  for (int i = 0; i < obstacles.length; i++){
    obstacles[i] = new Obstacle(floor(random(width)), floor(random(height/4)));
  }
}

int x = 0;
public void draw(){
  background(0);
  println(bot.DNA);
  bot.show();
  bot.physics();
  bot.move();
  for (int i = 0; i < obstacles.length; i++){
    obstacles[i].show();
  }
}
class Bot{
  float x = 10.0f;
  float y = height - 10;
  int scl = 10;
  int[] brain = {0, 0, 1, 0, 0, 1};
  int step = 0;
  float xSpeed = 2.0f;
  float ySpeed = 0.0f;
  float velocity = 1.0f;
  float gravity = 0.2f;
  int state = 0;
  float energy = 1000;

  float[] DNA = new float[100];

  public void newBot(float[] DNA){

  }
  public void newBot(){
    for(int i = 0; i < DNA.length; i++){
      int val = floor(random(200));
      if(val > 20){
        val = 0;
      }
      DNA[i] = val/2;
    }
  }

  public void jump(float amount){
    if(energy >= amount){
      velocity = amount;
      energy = energy - (amount * 10);
    }
  }

  public void move(){
    if(state < DNA.length - 1 && y == height - 10){
      jump(DNA[state]);
      state++;
    }
  }

  public void show(){
    rect(x, y, scl, scl);
  }

  //Basic physics engine for allowing the bots to navigate and move a 2D space
  public void physics(){
    x = x + xSpeed;
    y = y - ySpeed;
    ySpeed = ySpeed + velocity;
    velocity = velocity - gravity;
    if (y > height - 10){
      y = height - 10;
      velocity = 0;
      ySpeed = 0;
    }
    if(y == height - 10){
      gravity = 0;
    }
    else{
      gravity = 0.2f;
    }
  }
}
class Obstacle{
  int x;
  int tallness;

  public void show(){
    rect(x, height - tallness, 10, tallness);
  }

  public Obstacle(int x, int tallness){
    this.x = x;
    this.tallness = tallness;
  }
}
  public void settings() {  size(1280,720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "GeneticAlgorithm" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
