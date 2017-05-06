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

public class Snake_Game extends PApplet {

Snake snake = new Snake();
Food food = new Food();
PFont f;
String dir;
int hold = 0;
boolean dispMessage = false;

public void setup(){
  
  snake.x = 1;
  snake.y = 1;
  frameRate(10);
  food.pickLocation();
  f = createFont("Arial",16,true);
}


public void draw(){
  background(20, 43, 173);
  textFont(f,16);
  text("score: "+snake.len, width - 100, height - 10);
  snake.move(dir);
  snake.update();
  snake.collision();
  snake.grow();
  snake.isDead();
  food.isEaten();
  snake.show();
  food.show();
}


public void keyPressed(){
    if (keyCode == UP && dir != "DOWN" && !snake.hasDir){
      dir = "UP";
      snake.hasDir = true;
    }
    if (keyCode == DOWN && dir != "UP" && !snake.hasDir){
      dir = "DOWN";
      snake.hasDir = true;
    }
    if (keyCode == RIGHT && dir != "LEFT" && !snake.hasDir){
      dir = "RIGHT";
      snake.hasDir = true;
    }
    if (keyCode == LEFT && dir != "RIGHT" && !snake.hasDir){
      dir = "LEFT";
      snake.hasDir = true;
    }
    if (key == 'r'){
      food.pickLocation();
    }
    if (key == 'z'){
      hold += 1;
    }
    if (key == 'z' && hold == 20){
      snake.len += 30;
      hold = 0;
    }
}
class Food{
  int x;
  int y;
  int scl = 20;
  
  
  public void show(){
    stroke(20, 43, 173);
    fill(255, 0, 102);
    rect(x, y, scl, scl);
  }
  
  
  public void pickLocation(){
    int valid = 0;
    while (valid == 0){
      valid = 1;
      int cols = floor(width/scl);
      int rows = floor(height/scl);
      int newX = floor(random(cols));
      int newY = floor(random(rows));
      x = newX * scl;
      y = newY * scl;
      for (int i = 0; i < snake.tail.size(); i++) {
        PVector pos = snake.tail.get(i);
        float d = dist(x, y, pos.x, pos.y);
        if (d < 1) {
          valid *= 0;
        }else{
          valid *=1;
        }
      } 
    }
  }
  
  
  public void isEaten(){
    if (x == snake.x - 1  && y == snake.y - 1){
      pickLocation();
      snake.len += 5;  ;snake.len++;
    }
  }
  
  
}
class Snake{
  int x;
  int y;
  int xSpeed = 0;
  int ySpeed = 0;
  int scl = 20;
  int len = 0;
  boolean hasDir = false;
  ArrayList<PVector> tail = new ArrayList<PVector>();
  boolean dead = false;
  
  
  public void show(){
    stroke(20, 43, 173);
    fill(255, 247, 22);
    rect(x,y, scl, scl);
    for (PVector v : tail) {
      rect(v.x, v.y, scl, scl);
    }
  }
  
  
  public void move(String dir){
    if (dir == "UP"){
      xSpeed = 0;
      ySpeed = -1;
      
    }
    else if (dir == "DOWN"){
      xSpeed = 0;
      ySpeed = 1;
    }
    else if (dir == "LEFT"){
      xSpeed = -1;
      ySpeed = 0;
    }
    else if (dir == "RIGHT"){
      xSpeed = 1;
      ySpeed = 0;
    }
  }
  
  
  public void update(){
    if (!dead){
      x = x + xSpeed * scl;
      y = y + ySpeed * scl;
      hasDir = false;
    }
    if (!(constrict(0, width, 0, height))){
      x = x - xSpeed * scl;
      y = y - ySpeed * scl;
      this.dead = true;
    }
  }
  
  
  public void grow(){
    if (len > 0) {
      if (len == tail.size() && !tail.isEmpty() && !dead) {
        tail.remove(0);
      }
      tail.add(new PVector(x, y));
    }
  }
  
  
  public boolean constrict(int minX, int maxX, int minY, int maxY){
    if (x <= minX){
      return false;
    }
    else if (x >= maxX){
      return false;
    }
    else if (y <= minY){
      return false;
    }
    else if (y >= maxY){
      return false;
    }
    else {
      return true;
    }
  }
  
  
  public void isDead(){
    if(dead && !(dir == null)){
      x = 1; y = 1;
      xSpeed = 0; ySpeed = 0;
      len = 0;
      tail.clear();
      dead = false;
    }
  }
  
  
  public void collision() {
    for (int i = 0; i < tail.size(); i++) {
      PVector pos = tail.get(i);
      float d = dist(x, y, pos.x, pos.y);
      if (d < 1) {
        this.dead = true;
        println("dead");
      }
    }
  }
}
  public void settings() {  size(1200, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Snake_Game" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
