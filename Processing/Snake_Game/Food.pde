class Food{
  int x;
  int y;
  int scl = 20;
  
  
  void show(){
    stroke(20, 43, 173);
    fill(255, 0, 102);
    rect(x, y, scl, scl);
  }
  
  
  void pickLocation(){
    int valid = 0;
    while (valid == 0){
      valid = 1;
      int cols = floor(width/scl);
      int rows = floor(height/scl);
      int newX = floor(random(cols));
      int newY = floor(random(rows));
      x = newX * scl;
      y = newY * scl;
      for (int i = 0; i < snake.tail.size(); i++) {
        PVector pos = snake.tail.get(i);
        float d = dist(x, y, pos.x, pos.y);
        if (d < 1) {
          valid *= 0;
        }else{
          valid *=1;
        }
      } 
    }
  }
  
  
  void isEaten(){
    if (x == snake.x - 1  && y == snake.y - 1){
      pickLocation();
      snake.len += 5;  ;snake.len++;
    }
  }
  
  
}