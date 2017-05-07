class Paddle{
  float x;
  float y = height/2;
  int max, min;

  public Paddle(float x, int min, int  max){
    this.x = x;
    this.max = max;
    this.min = min;
  }

  void move(float dist){
    y = y + dist;
    constrain();
  }

  void render(){
    rectMode(CENTER);
    rect(x, y, 20, 100);
    rectMode(CORNER);
  }

  void constrain(){
    if (y > max){
      y = max;
    }else if(y < min){
      y = min;
    }
  }
}
