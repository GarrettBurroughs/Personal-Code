class Cell{
  int x,y;
  boolean mine;
  boolean uncovered;
  float tileSize;
  boolean clicked = false;
  int value = 0;
  color c;
  boolean flagged = false;

  public Cell(int x, int y, boolean mine, boolean uncovered, float tileSize){
    this.x = x;
    this.y = y;
    this.mine = mine;
    this.uncovered = uncovered;
    this.tileSize = tileSize;
    c = color(255);
  }

  void render(){
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

  boolean click(){
    if(!flagged){
      clicked = true;
      if(mine){
        return true;
      }else{
        return false;
      }
    }else{
      return false;
    }
  }
  void showMine(){
    if(mine && flagged){
      fill(0);
      rect(x, y, tileSize, tileSize);
      fill(255, 0, 0);
      ellipse(x+tileSize/2,y + tileSize/2,tileSize - 4,tileSize - 4);
    }
    if(flagged && !mine){
      fill(0);
      rect(x, y, tileSize, tileSize);
    }
    if(mine && !flagged){
      fill(255);
      rect(x, y, tileSize, tileSize);
      fill(255, 0, 0);
      ellipse(x+tileSize/2,y + tileSize/2,tileSize - 4,tileSize - 4);
    }
  }
}
