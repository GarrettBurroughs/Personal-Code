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

public class Template extends PApplet {

int[] items;
int scl;
int current;
boolean sorted = false;
Object lock = new Object();
SortThread sort = new SortThread();
int delay = 1;

public void setup(){
  
  items = new int[600];
  scl = width / items.length;
  for(int i = 1; i < items.length; i++){
    items[i] = items.length - i;
  }
  shuffle(items);
  current = items.length - 1;
  frameRate(60);
  sort.start();
}

public void draw(){
  noStroke();
  background(0);
  synchronized(lock){
    for(int i = 1; i < items.length; i++){
      rect((i) * scl, height - items[i], scl, items[i]);
    }
  }
}

public void swap(int[] array, int a, int b){
  int temp = array[a];
  array[a] = array[b];
  array[b] = temp;
}

public boolean isSorted(int[] array){
  boolean sorted = true;
  for(int i = 0; i < items.length - 1; i++){
    if(items[i] > items[i + 1]){
      sorted = false;
    }
  }
  return sorted;
}

public void shuffle(int[] array){
  for(int i = 0; i < array.length; i++){
    swap(array, i, floor(random(array.length - 1)));
  }
}
class SortThread extends Thread{
  public void run(){
    boolean sorted = false;
    while(!sorted){
      synchronized(lock){
        // Sorting Code Goes Here
        int max = current;
        for(int i = 0; i < current; i++){
          if(items[i] > items[max]){
            max = i;
          }
        }
        swap(items, current, max);
        if(current > 1){
          current = current - 1;
        }else{
          fill(0, 255, 0);
        }
        int mil = millis();
        while(millis() < mil + delay){};
      }
      sorted = isSorted(items);
    }
  }
}
  public void settings() {  size(1200, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Template" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
