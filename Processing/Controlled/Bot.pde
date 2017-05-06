class Bot{
  float x = 0;
  float y = height/2;
  float angle = 0;
  void move(float direction){
    y = y + -sin(radians(direction));
    x = x + cos(radians(direction));
    println(x, y);
  }

  void render(){
    fill(0);
    ellipse(x,y,20, 20);
  }
}
