class Bot{
  float x = 10.0f;
  float y = height - 10;
  int scl = 10;
  int[] brain = {0, 0, 1, 0, 0, 1};
  int step = 0;
  float xSpeed = 2.0f;
  float ySpeed = 0.0f;
  float velocity = 1.0f;
  float gravity = 0.2f;

  void jump(){
    velocity = 1;
  }
  

  void show(){
    rect(x, y, scl, scl);
  }
  void physics(){
    x = x + xSpeed;
    y = y - ySpeed;
    ySpeed = ySpeed + velocity;
    velocity = velocity - gravity;
    if (y > height - 10){
      y = height - 10;
      velocity = 0;
      ySpeed = 0;
    }
    if(y == height - 10){
      gravity = 0;
    }
    else{
      gravity = 0.2f;
    }
  }
}