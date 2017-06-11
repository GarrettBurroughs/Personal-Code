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
int state = 0;
int mines = 100;
float startTime;
float endTime;

public void setup(){
  
  cells = new Cell[floor(height/cellSize)][floor(width/cellSize)] ;
  for(int i = 0; i < cells.length; i++){
    for(int j = 0; j < cells[i].length; j++){
      cells[i][j] = new Cell(i * cellSize, j * cellSize, false, false, cellSize);
    }
  }
}


public void startGame(){
  for(int i = 0; i < cells.length; i++){
    for(int j = 0; j < cells[i].length; j++){
      cells[i][j] = new Cell(i * cellSize, j * cellSize, false, false, cellSize);
    }
  }
  rectMode(CORNER);
  textAlign(LEFT,RIGHT);
  strokeWeight(1);
  for(int i = 0; i < mines; i++){
    int a = floor(random(floor(height/cellSize))), b = floor(random(floor(width/cellSize)));
    while(!cells[a][b].mine){
      if(!cells[a][b].mine){
        cells[a][b].mine = true;
      }else{
        a = floor(random(floor(height/cellSize)));
        b = floor(random(floor(width/cellSize)));
      }
    }
    startTime = millis()/1000;
  }

    for(int i = 0; i < cells.length; i++){
      for(int j = 0; j < cells[i].length; j++){
        setValue(i,j);
      }
    }
}

public void draw(){

  switch (state){
    case 0:
      background(255);
      fill(0);
      textSize(90);
      textAlign(CENTER,CENTER);
      text( "Minesweeper", width/2,  height/2- 100);
      textSize(30);
      text("Made By: Garrett Burroughs", width/2, height/2);
      text("start", width/2, height/2 + 60);
      rectMode(CENTER);
      noFill();
      rect(width/2, height/2 + 65, 70, 35);
      strokeWeight(2);
      stroke(0);
      if(mousePressed && mouseX > width/2 - 35 && mouseX < width/2 + 35 && mouseY > height/2 + 65 - 35/2 && mouseY < height/2 + 65 + 35/2){
        println("START");
        state = 1;
      }
      rectMode(CORNER);
      break;
    case 1:
      background(255);
      fill(0);
      textSize(20);
      textAlign(CENTER,CENTER);
      text("How many mines do you want \n (use the Arrow keys to adjust) \n (Up/Down -> +/- 10, Left/Right -> -/+ 1) \n MINES: " + str(mines), width/2, height/2);
      text("Ready!", width/2, height/2 + 100);
      rectMode(CENTER);
      noFill();
      rect(width/2, height/2 + 100, 70, 35);
      rectMode(CORNER);
      if(mousePressed && mouseX > width/2 - 35 && mouseX < width/2 + 35 && mouseY > height/2 + 100 - 35/2 && mouseY < height/2 + 100 + 35/2){
        startGame();
        delay(2000);
        state = 2;
      }
      break;
    case 2:
      for(Cell[] row : cells){
        for(Cell cell : row){
          cell.render();
        }
      }
      if(checkWin()){
        state = 4;
        endTime = millis()/1000;
      }
      break;
    case 3:
        background(0);
        fill(255);
        textAlign(CENTER,CENTER);
        textSize(70);
        text("GAME OVER", width/2, height/2 - 30);
        textSize(30);
        text("Press \'R\' to restart", width/2, height/2 + 30);
        break;
  case 4:
    background(255);
    fill(0, 255, 0);
    textAlign(CENTER,CENTER);
    text("Congratulations you beat Minesweeper in " + str(endTime - startTime) + " Seconds \n press \'R\' to restart", height/2, width/2, - 30);
    break;
  case 5:
    delay(3000);
    state = 3;
  }
}


public void mouseClicked(){
  int x = round(mouseX/cellSize), y = round(mouseY/cellSize);
  if(state == 2){
    if(mouseButton == LEFT){

      if(cells[x][y].value == 0){
        clearSurrounding(x,y);
      }
      if(cells[x][y].click()){
        gameOver();
      }
    }else if(mouseButton == RIGHT){
      cells[x][y].flagged = true;
    }
  }
}

public void gameOver(){
  showMines();
  state = 5;
}

public void keyPressed(){
  if(state == 1){
    switch(keyCode){
      case UP:
        mines += 10;
        break;
      case DOWN:
        mines -= 10;
        break;
      case RIGHT:
        mines += 1;
        break;
      case LEFT:
        mines -= 1;
        break;
    }
    if(mines < 0){
      mines = 0;
    }
  }
  if(state == 3 || state == 4){
    if(key == 'r'){
      state = 1;
    }
  }
}



public void clearSurrounding(int a, int b){
  /*
  * x-1, y-1, x, y-1, x+1, y-1;
  * x-1, y; x, y; x+1, y;
  * x-1, y+1; x, y+1; x+1, y+1;
  */
  if(!(a <= 0 || b <= 0)){
    if(!(cells[a-1][b-1].clicked == true)){
      cells[a-1][b-1].clicked = true;
      if(cells[a-1][b-1].value == 0){
        clearSurrounding(a-1, b-1);
      }
    }
  }

  if(!(a == 0)){
    if(!(cells[a-1][b].clicked == true)){
      cells[a-1][b].clicked = true;
      if(cells[a-1][b].value   == 0){
        clearSurrounding(a-1, b);
      }
    }
  }
  if(!((a == 0) || (b == floor(width/cellSize) - 1))){
    if(!(cells[a-1][b+1].clicked == true)){
      cells[a-1][b+1].clicked = true;
      if(cells[a-1][b+1].value == 0){
        clearSurrounding(a-1, b+1);
      }
    }
  }

  if(!(b == 0)){
    if(!(cells[a][b-1].clicked == true)){
      cells[a][b-1].clicked = true;
      if(cells[a][b-1].value   == 0){
        clearSurrounding(a, b-1);
      }
    }
  }

  if(cells[a][b].value     == 0  && !(cells[a][b].clicked == true)){
    cells[a][b].clicked = true;
    clearSurrounding(a, b);
  }

  if(!(b == floor(width/cellSize) - 1)){
    if(!(cells[a][b+1].clicked == true)){
      cells[a][b+1].clicked = true;
      if(cells[a][b+1].value   == 0 ){
        clearSurrounding(a, b+1);
      }
    }
  }

  if(!((b == 0) || (a == floor(height/cellSize) - 1))){
    if(!(cells[a+1][b-1].clicked == true)){
      cells[a+1][b-1].clicked = true;
      if(cells[a+1][b-1].value == 0){
        clearSurrounding(a+1, b-1);
      }
    }
  }

  if(!(a == floor(height/cellSize) - 1)){
    if(!(cells[a+1][b].clicked == true)){
      cells[a+1][b].clicked = true;
      if(cells[a+1][b].value   == 0){
        clearSurrounding(a+1, b);
      }
    }
  }

  if(!(a == floor(height/cellSize) - 1 || b == floor(width/cellSize) - 1)){
    if(!(cells[a+1][b+1].clicked == true)){
      cells[a+1][b+1].clicked = true;
      if(cells[a+1][b+1].value == 0){
        clearSurrounding(a+1, b+1);
      }
    }
  }
}


public void setValue(int a, int b){
  int value = 0;
  if(!(a <= 0 || b <= 0)){
    if(cells[a-1][b-1].mine){
      value++;
    }
  }

  if(!(a == 0)){
    if(cells[a-1][b].mine){
      value++;
    }
  }
  if(!((a == 0) || (b == floor(width/cellSize) - 1))){
    if(cells[a-1][b+1].mine){
      value++;
    }
  }

  if(!(b == 0)){
    if(cells[a][b-1].mine){
      value++;
    }
  }

  if(!(b == floor(width/cellSize) - 1)){
    if(cells[a][b+1].mine){
      value++;
    }
  }

  if(!((b == 0) || (a == floor(height/cellSize) - 1))){
    if(cells[a+1][b-1].mine){
      value++;
    }
  }

  if(!(a == floor(height/cellSize) - 1)){
    if(cells[a+1][b].mine){
      value++;
    }
  }

  if(!(a == floor(height/cellSize) - 1 || b == floor(width/cellSize) - 1)){
    if(cells[a+1][b+1].mine){
      value++;
    }
  }
  cells[a][b].value = value;
}


public boolean checkWin(){
  boolean won = true;
  for(Cell[] row : cells){
    for(Cell cell : row){
      if((cell.mine && !(cell.flagged))){
        won = false;
      }
    }
  }
  return won;
}

public void showMines(){
  for(Cell[] row : cells){
    for(Cell cell : row){
      cell.showMine();
    }
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
  boolean flagged = false;

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
    }else if(flagged){
      fill(255, 0, 0);
    }else{
      fill(170);
    }
    rect(x, y, tileSize, tileSize);
    if(clicked && !mine){
      textSize(tileSize);
      fill(0);
      if(value != 0){
        text(value, x, y + tileSize);
      }
    }else if(clicked){
      fill(0);
      ellipse(x+tileSize/2,y + tileSize/2,tileSize - 4,tileSize - 4);
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
  public void showMine(){
    if(mine){
      fill(255);
      rect(x, y, tileSize, tileSize);
      fill(255, 0, 0);
      ellipse(x+tileSize/2,y + tileSize/2,tileSize - 4,tileSize - 4);
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
