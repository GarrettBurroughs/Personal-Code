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
ArrayList<Bot> matingPool = new ArrayList<Bot>();

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
  if(finished){
    for (int i = 0; i < bots.length; i++){
      for (int j = 0; j < bots[i].fitness; j++){
        matingPool.add(bots[i]);
      }
    }
    float[][] newDna = new float[100][2];
    Bot parent1 = matingPool.get(floor(random(matingPool.size())));
    Bot parent2 = matingPool.get(floor(random(matingPool.size())));
    newDna = parent1.DNA;
    int splitpoint = floor(random(100));
    for(int i = 0; i < splitpoint; i++){
      newDna[i] = parent2.DNA[i];
    }
    for(int i = 0; i < bots.length; i++){
      bots[i].newBot(newDna);
      println(bots[i].collided);
    }
    matingPool.clear();
    finished = false;
    println("Restarting");
    println(finished);
  }
}
class Bot{
  float x = 10.0f;
  float y = height - 10;
  int scl = 10;
  float xSpeed = 2.0f;
  float ySpeed = 0.0f;
  float velocity = 1.0f;
  float gravity = 0.2f;
  float energy = 200;
  int fitness;
  int state = 0;
  boolean collided = false;
  int wait;

  float[][] DNA = new float[100][2];

  public void newBot(float[][] DNA){
    x = 10.0f;
    y = height - 10;
    xSpeed = 2.0f;
    ySpeed = 0.0f;
    energy = 200;
    collided = false;
    this.DNA = DNA;
  }

  public void newBot(){
    for(int i = 0; i < DNA.length; i++){
      int val = floor(random(20)) + 1;
      DNA[i][0] = val/2;
      DNA[i][1] = floor(random(20));
    }
  }

  public int fitness(){
    fitness = PApplet.parseInt(2 * x);
    return fitness;
  }
  public void jump(float amount){
    if(energy >= amount * 10){
      velocity = amount;
      energy = energy - (amount * 10);
    }
  }

  public void move(){
    if(state < DNA.length - 1 && y == height - 10 && !collided && wait <= 0){
      jump(DNA[state][0]);
      state++;
    }else{
      wait = wait - 1;
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
