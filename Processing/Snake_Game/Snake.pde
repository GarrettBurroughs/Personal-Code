class Snake{
  int x;
  int y;
  int xSpeed = 0;
  int ySpeed = 0;
  int scl = 20;
  int len = 0;
  boolean hasDir = false;
  ArrayList<PVector> tail = new ArrayList<PVector>();
  boolean dead = false;
  
  
  void show(){
    stroke(20, 43, 173);
    fill(255, 247, 22);
    rect(x,y, scl, scl);
    for (PVector v : tail) {
      rect(v.x, v.y, scl, scl);
    }
  }
  
  
  void move(String dir){
    if (dir == "UP"){
      xSpeed = 0;
      ySpeed = -1;
      
    }
    else if (dir == "DOWN"){
      xSpeed = 0;
      ySpeed = 1;
    }
    else if (dir == "LEFT"){
      xSpeed = -1;
      ySpeed = 0;
    }
    else if (dir == "RIGHT"){
      xSpeed = 1;
      ySpeed = 0;
    }
  }
  
  
  void update(){
    if (!dead){
      x = x + xSpeed * scl;
      y = y + ySpeed * scl;
      hasDir = false;
    }
    if (!(constrict(0, width, 0, height))){
      x = x - xSpeed * scl;
      y = y - ySpeed * scl;
      this.dead = true;
    }
  }
  
  
  void grow(){
    if (len > 0) {
      if (len == tail.size() && !tail.isEmpty() && !dead) {
        tail.remove(0);
      }
      tail.add(new PVector(x, y));
    }
  }
  
  
  boolean constrict(int minX, int maxX, int minY, int maxY){
    if (x <= minX){
      return false;
    }
    else if (x >= maxX){
      return false;
    }
    else if (y <= minY){
      return false;
    }
    else if (y >= maxY){
      return false;
    }
    else {
      return true;
    }
  }
  
  
  void isDead(){
    if(dead && !(dir == null)){
      x = 1; y = 1;
      xSpeed = 0; ySpeed = 0;
      len = 0;
      tail.clear();
      dead = false;
    }
  }
  
  
  void collision() {
    for (int i = 0; i < tail.size(); i++) {
      PVector pos = tail.get(i);
      float d = dist(x, y, pos.x, pos.y);
      if (d < 1) {
        this.dead = true;
        println("dead");
      }
    }
  }
}