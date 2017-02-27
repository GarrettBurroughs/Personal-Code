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

public class QuickSort extends PApplet {

int[] items;
int scl;

public void setup(){

  items = new int[600];
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
  quicksort(items, 1, items.length);
  println("done");
}
public void quicksort(int[] array, int hi, int lo){
  if(lo < hi){
    int p = partition(array, hi, lo);
    quicksort(array, lo, p - 1);
    quicksort(array, p + 1, hi);
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

public int partition(int[] array, int lo, int hi){
  int pivot = array[hi];
  int i = lo - 1;
  for(int j = lo; j < hi - 1; j++){
    if (array[j] <= pivot){
      i = i + 1;
      swap(array, i, j);
    }
  }
  swap(array, i + 1, hi);
  return i + 1;
}
  public void settings() {  size(1200, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "QuickSort" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
