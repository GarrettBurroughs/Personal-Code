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

Bot[] bots;
Obstacle[] obstacles;
boolean finished;

public void setup(){
  
  finished = false;
  bots = new Bot[10];
  obstacles = new Obstacle[10];

  for (int i = 0; i < bots.length; i++){
    bots[i] = new Bot();
    bots[i].newBot();
  }

  for (int i = 0; i < obstacles.length; i++){
    obstacles[i] = new Obstacle(floor(random(width - width/10) + width/10), floor(random(height/4)));
  }

}

int x = 0;
public void draw(){
  background(0);
  for (int i = 0; i < bots.length; i++){
    bots[i].show();
    bots[i].physics();
    bots[i].move();
    bots[i].fitness();
    for (int j = 0; j < obstacles.length; j++){
      if(bots[i].x + bots[i].scl > obstacles[j].x && bots[i].x < obstacles[j].x + 10 && bots[i].y + bots[i].scl > height - obstacles[j].tallness){
        bots[i].collided = true;
      }
    }
  }
  for (int i = 0; i < obstacles.length; i++){
    obstacles[i].show();
  }
  finished = true;
  for(int i = 0; i < bots.length; i++){
    if(!bots[i].collided){
      finished = false;
    }
  }
  if (finished){
    println("finished");
    for(int i = 0; i < bots.length; i++){
      println(bots[i].fitness);
    }
    frameRate(0);
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
  float energy = 200;
  int fitness;
  boolean collided = false;

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

  public int fitness(){
    fitness = PApplet.parseInt(10 * x * x + x * energy);
    return fitness;
  }
  public void jump(float amount){
    if(energy >= amount * 10){
      velocity = amount;
      energy = energy - (amount * 10);
    }
  }

  public void move(){
    if(state < DNA.length - 1 && y == height - 10 && !collided){
      jump(DNA[state]);
      state++;
    }
  }

  public void show(){
    rect(x, y, scl, scl);
  }

  //Basic physics engine for allowing the bots to navigate and move a 2D space
  public void physics(){
    if(!collided){
      x = x + xSpeed;
    }
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
