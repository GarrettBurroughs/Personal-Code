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

public void setup(){
  
  bot = new Bot();

}

int x = 0;
public void draw(){
  background(0);
  bot.show();
  bot.physics();
}

public void mouseClicked(){
  bot.jump();
}
class Bot{
  float x = 10.0f;
  float y = height - 10;
  int scl = 10;
  int[] brain = {0, 0, 1, 0, 0, 1};
  int step = 0;
  float xSpeed = 1.0f;
  float ySpeed = 0.0f;
  float velocity = 1.0f;
  float gravity = 0.1f;

  public void jump(){
    velocity = 1;
  }
  

  public void show(){
    rect(x, y, scl, scl);
  }
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
      gravity = 0.1f;
    }
  }
}
  public void settings() {  size(640,480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "GeneticAlgorithm" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
