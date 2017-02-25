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

public class Sort extends PApplet {

int[] items;
int scl;
int currentHi;
int currentLo;
boolean done = false;

public void setup(){
  
  items = new int[600];
  scl = width / items.length;
  for(int i = 1; i < items.length; i++){
    items[i] = items.length - i;
  }
  shuffle(items);
  currentHi = items.length - 1;
  currentLo = 0;
  frameRate(60);
}

public void draw(){
  noStroke();
  int max = currentHi;
  int min = currentLo;
  background(0);
  for(int i = 1; i < items.length; i++){
    rect((i) * scl, height - items[i], scl, items[i]);
  }
  for(int i = currentLo; i < currentHi; i++){
    if(items[i] > items[max]){
      max = i;
    }else if(items[i] < items[min]){
      min = i;
    }
  }
  swap(items, currentHi, max);
  swap(items, currentLo, min);
  if(currentHi > 1 && currentLo < items.length && currentHi > currentLo){
    currentHi = currentHi - 1;
    currentLo = currentLo + 1;
  }else{
    done = true;
    fill(0, 255, 0);
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
    String[] appletArgs = new String[] { "Sort" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
