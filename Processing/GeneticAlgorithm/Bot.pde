class Bot{
  float x = 10.0f;
  float y = height - 10;
  int scl = 10;
  float xSpeed = 2.0f;
  float ySpeed = 0.0f;
  float velocity = 1.0f;
  float gravity = 0.2f;
  float energy = 200;
  int fitness;
  int state = 0;
  boolean collided = false;
  int wait;

  float[][] DNA = new float[100][2];

  void newBot(float[][] DNA){
    x = 10.0f;
    y = height - 10;
    xSpeed = 2.0f;
    ySpeed = 0.0f;
    energy = 200;
    collided = false;
    this.DNA = DNA;
  }

  void newBot(){
    for(int i = 0; i < DNA.length; i++){
      int val = floor(random(20)) + 1;
      DNA[i][0] = val/2;
      DNA[i][1] = floor(random(20));
    }
  }

  int fitness(){
    fitness = int(2 * x);
    return fitness;
  }
  void jump(float amount){
    if(energy >= amount * 10){
      velocity = amount;
      energy = energy - (amount * 10);
    }
  }

  void move(){
    if(state < DNA.length - 1 && y == height - 10 && !collided && wait <= 0){
      jump(DNA[state][0]);
      state++;
    }else{
      wait = wait - 1;
    }
  }

  void show(){
    rect(x, y, scl, scl);
  }

  //Basic physics engine for allowing the bots to navigate and move a 2D space
  void physics(){
    if(!collided){
      x = x + xSpeed;
    }
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
