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
boolean sorted = false; //Keeps tack of weather or not the list is sorted
int delay = 5; //delay in ms for each item to be sorted

public void setup(){
  
  items = new int[600];
  scl = width / items.length;
  for(int i = 1; i < items.length; i++){
    items[i] = items.length - i;
  }
  shuffle(items);
  frameRate(60);
  thread("sort");
  current = items.length - 1;
}

public void draw(){
  noStroke();
  background(0);
  if(isSorted(items)){
    fill(0, 255, 0);
  }else{
    fill(255);
  }
  for(int i = 1; i < items.length; i++){
    float itemHeight = map(items[i], 1, items.length, 1, height);
    rect((i) * scl, height - itemHeight, scl, itemHeight);
  }
}

public void swap(int[] array, int a, int b){
  int temp = array[a];
  array[a] = array[b];
  array[b] = temp;
}

public boolean isSorted(int[] array){
  for(int i = 0; i < items.length - 1; i++){
    if(items[i] > items[i + 1]){
      return false;
    }
  }
  return true;
}

public void shuffle(int[] array){
  for(int i = 0; i < array.length; i++){
    swap(array, i, floor(random(array.length - 1)));
  }
}

public void sort(){
  boolean sorted = false;
  while(!sorted){
    //Sorting Code Goes Here
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
    sorted = isSorted(items);
    delay(delay);
  }
}

public void delay(int delay){
  int mills = millis();
  while(millis() < mills + delay){};
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
