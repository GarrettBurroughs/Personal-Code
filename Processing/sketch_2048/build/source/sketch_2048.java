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

public class sketch_2048 extends PApplet {

int scl = 100;
ArrayList<Block> blocks = new ArrayList<Block>();
boolean pressed;
int dir;
public void setup(){
  background(0);
  
}

public void draw(){
  for (int i = 0; i < 4; i++){
    for(int j = 0; j < 4; j++){
      rect(i * scl, j * scl, scl, scl);
    }
  }
  if (pressed){
    update();
    pressed = false;
    Block block = new Block();
    block.x = floor(random(4)) * scl;
    block.y = floor(random(4)) * scl;
    block.val = floor(random(2) + 1);
    blocks.add(block);

  }
  for (int i = 0; i < blocks.size(); i++){
    Block dispBlock = blocks.get(i);
    dispBlock.show();
  }

}

public void update(){
  for (int i = 0; i < blocks.size(); i++){
    int pos = blocks.get(i).y/scl;
    switch(dir){
      case 1:
        for(int j = 0; j < blocks.size(); j++){
          if(blocks.get(j).x * scl == blocks.get(i).x * scl && blocks.get(j).y * scl < blocks.get(i).y * scl){
            pos--;
          }
        }
        blocks.get(i).y -= pos * scl;
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
    }
  }
}

public void keyPressed(){
  pressed = true;
  if (keyCode == UP){
    dir = 1;
  }
  if (keyCode == DOWN){
    dir = 2;
  }
  if (keyCode == RIGHT){
    dir = 3;
  }
  if (keyCode == LEFT){
    dir = 4;
  }
}
class Block{
  int x; 
  int y;
  float val;
  
  public void show(){
    float shade = val * 25.5f;
    fill(shade);
    rect(x, y, scl, scl);
    fill(255);
  }
  public void check(String dir){
    PVector[] sides;
    sides = new PVector[4];
    if (dir == "UP"){
      for (int i = 0; i < 4; i++){
        sides[i] = new PVector(i, 0);
      }
    }
    if (dir == "DOWN"){
      for (int i = 0; i < 4; i++){
        sides[i] = new PVector(i, 400);
      }
    }
    if (dir == "RIGHT"){
      for (int i = 0; i < 4; i++){
        sides[i] = new PVector(0, i);
      }
    }
    if (dir == "LEFT"){
      for (int i = 0; i < 4; i++){
        sides[i] = new PVector(400, i);
      }
    }
    for(int i = 0; i < blocks.size(); i++){
      
    }
  }
}
  public void settings() {  size(400, 400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_2048" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
