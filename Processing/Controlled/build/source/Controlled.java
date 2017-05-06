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

public class Controlled extends PApplet {

Bot bot;
float direction;
public void setup(){
  
  background(255);
  bot = new Bot();
  direction = 0;
}

public void draw(){
  bot.move(direction);
  bot.render();
}
public void keyPressed(){
  if(keyCode == UP){
    direction += 1;
  }
  if(keyCode == DOWN){
    direction -= 1;
  }
}
class Bot{
  float x = 0;
  float y = height/2;
  float angle = 0;
  public void move(float direction){
    y = y + -sin(radians(direction));
    x = x + cos(radians(direction));
    println(x, y);
  }

  public void render(){
    fill(0);
    ellipse(x,y,20, 20);
  }
}
  public void settings() {  size(640, 480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Controlled" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
