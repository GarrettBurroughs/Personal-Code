Bot[] bots;
Obstacle[] obstacles;
boolean finished;

void setup(){
  size(1280,720);
  finished = false;
  bots = new Bot[10];
  obstacles = new Obstacle[10];

  for (int i = 0; i < bots.length; i++){
    bots[i] = new Bot();
    bots[i].newBot();
  }

  for (int i = 0; i < obstacles.length; i++){
    obstacles[i] = new Obstacle(floor(random(width)), floor(random(height/4)));
  }

}

int x = 0;
void draw(){
  background(0);
  for (int i = 0; i < bots.length; i++){
    bots[i].show();
    bots[i].physics();
    bots[i].move();
    for (int j = 0; j < obstacles.length; j++){
      if(bots[i].x + bots[i].scl > obstacles[j].x && bots[i].x < obstacles[j].x + 10 && bots[i].y > height - obstacles[j].tallness){
        bots[i].collided = true;
      }
    }
  }
  for (int i = 0; i < obstacles.length; i++){
    obstacles[i].show();
  }
}
