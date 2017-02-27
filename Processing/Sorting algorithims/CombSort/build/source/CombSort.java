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

public class CombSort extends PApplet {

int[] items;
int scl;
boolean done = false;
int gap;
float shrink = 1.5f;
boolean sorted = false;

public void setup(){
  
  items = new int[600];
  scl = width / items.length;
  for(int i = 1; i < items.length; i++){
    items[i] = items.length - i;
  }
  shuffle(items);
  gap = items.length;
  frameRate(60);
}

public void draw(){
  noStroke();
  background(0);
  for(int i = 1; i < items.length; i++){
    rect((i) * scl, height - items[i], scl, items[i]);
  }
  if(!sorted){
    gap = floor(gap/shrink);
    if(gap > 1){
      sorted = false;
    }else{
      gap = 1;
      sorted = true;
    }
    for(int i = 0; i + gap < items.length; i++){
      if(items[i] > items[i + gap]){
        swap(items, i, i + gap);
        sorted = false;
      }
    }
  }
}


public void swap(int[] array, int a, int b){
  int temp = array[a];
  array[a] = array[b];
  array[b] = temp;
}

public void shuffle(int[] array){
  for(int i = 0; i < array.length; i++){
    swap(array, i, floor(random(array.length - 1)));
  }
}
  public void settings() {  size(1200, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "CombSort" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
