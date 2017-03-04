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

/*
* This Processing Sketch demonstrates adnd visualizes InsertionSort
* InsertionSort is a sorting algorithm that sorts by having a sorted section that will always be sorted and adding elements from the unsorted section to the sorted section
*
* Source: https://en.wikipedia.org/wiki/Insertion_sort
*
* Made by Garrett Burroughs 3/1/17
*/


//-----GLOBAL VARIABLES----//
int[] items; //The array in which all of the elements to be sorted are stored
int scl; //A dynamic variable that determines the vertical width of the elements in the visualization
boolean sorted = false; //Keeps tack of weather or not the list is sorted
int delay; //delay in ms for each item to be sorted
int fps = 60; //A variable to set the frames per second and set the delay for the sorting so that an element is swapped(sorted) every frame.
int size = 600; // this variable controls the number of elements to be sorted

//Code to be run one time at the begging of the sketch
public void setup(){
  
  items = new int[size];
  scl = width / items.length;
  for(int i = 1; i < items.length; i++){
    items[i] = items.length - i;
  }
  shuffle(items);
  delay = 1000/fps;
  frameRate(fps);
  thread("sort"); //starting the sorting algorithm

}


//Animation loop: code to be run repeatedly for animation
public void draw(){
  noStroke();
  background(0);
  if(isSorted(items)){
    fill(0, 255, 0);
  }else{
    fill(255);
  }
  for(int i = 1; i < items.length; i++){
    float itemHeight = map(items[i], 1, items.length, 1, height); //scales the height to the top of the screen
    rect((i) * scl, height - itemHeight, scl, itemHeight); // draws all of the elements
  }
}


public void sort(){
  boolean sorted = false;

  //Variables needed for the sorting algorithm
  int i = 1;


  while(!sorted){
    //Sorting Code Goes Here
    int j = i;
    while(j> 0 && items[j - 1] > items[j]){
      swap(items, j, j - 1);
      j = j - 1;
    }
    if(i < items.length - 1){
      i++;
    }

    sorted = isSorted(items);
    delay(delay);
  }
}

//Extra methods commonly used in sorting algorithms


//waits a certain number of milliseconds
public void delay(int delay){
  int mill = millis();
  while(millis() < mill + delay){};
}


//swaps elements a and b of an integer array
public void swap(int[] array, int a, int b){
  int temp = array[a];
  array[a] = array[b];
  array[b] = temp;
}


//retruns true if the array is sorted
public boolean isSorted(int[] array){
  for(int i = 0; i < items.length - 1; i++){
    if(items[i] > items[i + 1]){
      return false;
    }
  }
  return true;
}


//Randomly shuffles all elements of an array
public void shuffle(int[] array){
  for(int i = 0; i < array.length; i++){
    swap(array, i, floor(random(array.length - 1)));
  }
}
  public void settings() {  size(1200, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "InsertionSort" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
