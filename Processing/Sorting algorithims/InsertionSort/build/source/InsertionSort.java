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

public class InsertionSort extends PApplet {

int[] items;
int scl;
boolean done = false;
int i = 1;

public void setup(){
  
  items = new int[300];
  scl = width / items.length;
  for(int i = 1; i < items.length; i++){
    items[i] = items.length - i;
  }
  shuffle(items);
}

public void draw(){
  noStroke();
  background(0);
  for(int i = 1; i < items.length; i++){
    rect((i) * scl, height - items[i], scl, items[i]);
  }
  int j = i;
  while(j> 0 && items[j - 1] > items[j]){
    swap(items, j, j - 1);
    j = j - 1;
  }
  if(i < items.length - 1){
    i++;
  }else{
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
  public void settings() {  size(600, 400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "InsertionSort" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
