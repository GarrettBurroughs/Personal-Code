class Bot{
  float x = 0;
  float y = 0;
  float ySpeed;
  float xSpeed = 1;
  void move(double acceleration){
    y = y + ySpeed;
    x = x + xSpeed;
    ySpeed += acceleration *.1;
  }

  void render(){
    ellipse(x,y,20, 20);
  }
}
