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

public class Minesweeper extends PApplet {

Cell[][] cells;
int cellSize = 20;


public void setup(){
  
  cells = new Cell[floor(height/cellSize)][floor(width/cellSize)] ;
  for(int i = 0; i < cells.length; i++){
    for(int j = 0; j < cells[i].length; j++){
      cells[i][j] = new Cell(i * cellSize, j * cellSize, false, false, cellSize);
    }
  }
}


public void draw(){
  for(Cell[] row : cells){
    for(Cell cell : row){
      cell.render();
    }
  }

}


public void mouseClicked(){
  int x = round(mouseX/cellSize), y = round(mouseY/cellSize);
  if(cells[x][y].value == 0){
    clearSurrounding(x,y);
  }
  if(cells[x][y].click()){
    gameOver();
  }
}

public void gameOver(){

}


public void clearSurrounding(int a, int b){
  /*
  * x-1, y-1, x, y-1, x+1, y-1;
  * x-1, y; x, y; x+1, y;
  * x-1, y+1; x, y+1; x+1, y+1;
  */
  if(cells[a-1][b-1].value == 0 && !(a <= 0 || b <= 0)){
    cells[a-1][b-1].clicked = true;
    clearSurrounding(a-1, b-1);
  }
  if(cells[a-1][b].value   == 0 && !(a == 0)){
    cells[a-1][b].clicked = true;
    clearSurrounding(a-1, b);
  }
  if(cells[a-1][b+1].value == 0 && !(a == 0) && (b == floor(width/cellSize))){
    cells[a-1][b+1].clicked = true;
    clearSurrounding(a-1, b+1);
  }
  if(cells[a][b-1].value   == 0 && !(b == 0)){
    cells[a][b-1].clicked = true;
    clearSurrounding(a, b-1);
  }
  if(cells[a][b].value     == 0){
    cells[a][b].clicked = true;
    clearSurrounding(a, b);
  }
  if(cells[a][b+1].value   == 0 && !(b == floor(width/cellSize))){
    cells[a][b+1].clicked = true;
    clearSurrounding(a, b+1);
  }
  if(cells[a+1][b-1].value == 0 && !(b == 0) && !(a == floor(height/cellSize))){
    cells[a+1][b-1].clicked = true;
    clearSurrounding(a+1, b-1);
  }
  if(cells[a+1][b].value   == 0 && !(a == floor(height/cellSize) || b == floor(width/cellSize))){
    cells[a+1][b].clicked = true;
    clearSurrounding(a+1, b);
  }
  if(cells[a+1][b+1].value == 0 && !(a == floor(height/cellSize) || b == floor(width/cellSize))){
    cells[a+1][b+1].clicked = true;
    clearSurrounding(a+1, b+1);
  }
}
class Cell{
  int x,y;
  boolean mine;
  boolean uncovered;
  float tileSize;
  boolean clicked = false;
  int value = 0;
  int c;

  public Cell(int x, int y, boolean mine, boolean uncovered, float tileSize){
    this.x = x;
    this.y = y;
    this.mine = mine;
    this.uncovered = uncovered;
    this.tileSize = tileSize;
    c = color(255);
  }

  public void render(){
    if(clicked){
      fill(255);
    }else{
      fill(170);
    }
    rect(x, y, tileSize, tileSize);
    if(clicked){
      textSize(tileSize);
      fill(0);
      text(value, x, y + tileSize);
    }

  }


  public boolean click(){
    clicked = true;
    if(mine){
      return true;
    }else{
      return false;
    }
  }
}
  public void settings() {  size(600, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Minesweeper" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
