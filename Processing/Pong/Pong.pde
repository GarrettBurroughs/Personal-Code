Ball ball;
Paddle player;
Paddle opponent;
int playerMovement = 0;
int opponentMovement = 0;

void setup(){
  size(640, 480);
  ball = new Ball(width/2, height/2, 5, 0, 20);
  player = new Paddle(15, 0 + 50, height - 50);
  opponent = new Paddle(width - 15, 0 + 50, height - 50);
}

void draw(){

  background(0);
  player.move(playerMovement);
  player.render();
  opponent.render();
  ball.render();
  if(playerCollided()){
    ball.bounce((ball.y - player.y)/5);
  }
  if(opponentCollided()){
    ball.bounce((ball.y - opponent.y)/5);
  }
  ball.update();
  ball.edgeCollision();
  if(!keyPressed){
    playerMovement = 0;
  }
}

void keyPressed() {
  if (keyCode == UP){
    playerMovement = -5;
  }else if (keyCode == DOWN){
    playerMovement = 5;
  }
}

boolean playerCollided(){
  if(ball.x - ball.ballSize/2 < 25 && ball.y - ball.ballSize/2 < player.y + 50 && ball.y + ball.ballSize/2 > player.y - 50){
    return true;
  }else{
    return false;
  }
}

boolean opponentCollided(){
   if(ball.x + ball.ballSize/2 > width - 25 && ball.y - ball.ballSize/2 < opponent.y + 50 && ball.y + ball.ballSize/2 > opponent.y - 50){
    return true;
  }else{
    return false;
  }
}
