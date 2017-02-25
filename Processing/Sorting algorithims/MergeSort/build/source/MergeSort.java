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

public class MergeSort extends PApplet {

int[] items;
int scl;
boolean done = false;
int[] work;

public void setup(){
  
  items = new int[600];
  work = new int[600];
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
  mergeSort(items, work, 600);
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
public void mergeSort(int[] A, int[] B, int n){
  for(int arrayWidth = 1; arrayWidth < n; arrayWidth = 2 * arrayWidth){
    for (int i = 0; i < n; i = i + 2 * width){
      bottomUpMerge(A, i, min(i + arrayWidth, n), min(i+2*arrayWidth, n), B);
    }
    copyArray(B, A, n);
  }
}

public void bottomUpMerge(int A[], int iLeft, int iRight, int iEnd, int[] B){
  int i = iLeft;
  int j = iRight;
  for(int k =iLeft; k < iEnd; k++){
    if(i < iRight && (j >= iEnd || A[i] <= A[j])){
      B[k] = A[i];
      i = i + 1;
    }else{
      B[k] = A[i];
      j = j + 1;
    }
  }
}

public void copyArray(int[] A, int[] B, int n){
  for(int i = 0; i < n; i ++){
    B[i] = A[i];
  }
}
  public void settings() {  size(1200, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "MergeSort" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
