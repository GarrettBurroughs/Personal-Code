class Cell{
  int x,y;
  boolean mine;
  boolean uncovered;
  float tileSize;
  boolean clicked = false;
  int value = 0;
  color c;

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


  boolean click(){
    clicked = true;
    if(mine){
      return true;
    }else{
      return false;
    }
  }
}
