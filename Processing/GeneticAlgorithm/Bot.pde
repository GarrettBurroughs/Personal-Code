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
  int state = 0;
  float energy = 500;

  float[] DNA = new float[10];

  void newBot(float[] DNA){

  }
  void newBot(){
    for(int i = 0; i < DNA.length; i++){
      DNA[i] = random(3);
    }
  }

  void jump(float amount){
    if(energy >= amount){
      velocity = amount;
      energy = energy - (amount * 10);
    }
  }

  void move(){
    if(state < DNA.length - 1 && y == height - 10){
      jump(DNA[state]);
      state++;
    }
  }

  void show(){
    rect(x, y, scl, scl);
  }

  //Basic physics engine for allowing the bots to navigate and move a 2D space
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
