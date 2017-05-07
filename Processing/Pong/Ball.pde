class Ball{
  float x;
  float y;
  float xSpeed;
  float ySpeed;
  float ballSize;

  public Ball(float x, float y, float xSpeed, float ySpeed, float ballSize){
    this.x = x;
    this.y = y;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.ballSize = ballSize;
  }

  void render(){
    ellipse(x,y,ballSize, ballSize);
  }

  void update(){
    x = x + xSpeed;
    y = y + ySpeed;
  }

  void bounce(float dir){
    xSpeed = -xSpeed;
    ySpeed = dir;
  }
  void edgeCollision(){
    if(y + ballSize/2 > height){
      ySpeed = -ySpeed;
    }else if(y - ballSize/2 < 0){
      ySpeed = -ySpeed;
    }
  }
}
