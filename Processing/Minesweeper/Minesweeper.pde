Cell[][] cells;
int cellSize = 20;


void setup(){
  size(600, 600);
  cells = new Cell[floor(height/cellSize)][floor(width/cellSize)] ;
  for(int i = 0; i < cells.length; i++){
    for(int j = 0; j < cells[i].length; j++){
      cells[i][j] = new Cell(i * cellSize, j * cellSize, false, false, cellSize);
    }
  }
}


void draw(){
  for(Cell[] row : cells){
    for(Cell cell : row){
      cell.render();
    }
  }

}


void mouseClicked(){
  int x = round(mouseX/cellSize), y = round(mouseY/cellSize);
  if(cells[x][y].value == 0){
    clearSurrounding(x,y);
  }
  if(cells[x][y].click()){
    gameOver();
  }
}

void gameOver(){

}


void clearSurrounding(int a, int b){
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
