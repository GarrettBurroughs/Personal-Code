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
    obstacles[i] = new Obstacle(floor(random(width - width/10) + width/10), floor(random(height/4)));
  }

}

int x = 0;
void draw(){
  background(0);
  for (int i = 0; i < bots.length; i++){
    bots[i].show();
    bots[i].physics();
    bots[i].move();
    bots[i].fitness();
    for (int j = 0; j < obstacles.length; j++){
      if(bots[i].x + bots[i].scl > obstacles[j].x && bots[i].x < obstacles[j].x + 10 && bots[i].y + bots[i].scl > height - obstacles[j].tallness){
        bots[i].collided = true;
      }
    }
  }
  for (int i = 0; i < obstacles.length; i++){
    obstacles[i].show();
  }
  finished = true;
  for(int i = 0; i < bots.length; i++){
    if(!bots[i].collided){
      finished = false;
    }
  }
  if (finished){
    println("finished");
    for(int i = 0; i < bots.length; i++){
      println(bots[i].fitness);
    }
    frameRate(0);
  }
}
