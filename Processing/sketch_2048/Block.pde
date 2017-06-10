class Block{
  int x; 
  int y;
  float val;
  
  void show(){
    float shade = val * 25.5;
    fill(shade);
    rect(x, y, scl, scl);
    fill(255);
  }
  void check(String dir){
    PVector[] sides;
    sides = new PVector[4];
    if (dir == "UP"){
      for (int i = 0; i < 4; i++){
        sides[i] = new PVector(i, 0);
      }
    }
    if (dir == "DOWN"){
      for (int i = 0; i < 4; i++){
        sides[i] = new PVector(i, 400);
      }
    }
    if (dir == "RIGHT"){
      for (int i = 0; i < 4; i++){
        sides[i] = new PVector(0, i);
      }
    }
    if (dir == "LEFT"){
      for (int i = 0; i < 4; i++){
        sides[i] = new PVector(400, i);
      }
    }
    for(int i = 0; i < blocks.size(); i++){
      
    }
  }
}