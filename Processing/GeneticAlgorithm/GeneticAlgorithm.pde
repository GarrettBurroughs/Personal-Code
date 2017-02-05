Bot bot;
Obstacle[] obstacles;


void setup(){
  size(1280,720);
  bot = new Bot();
  bot.newBot();
  obstacles = new Obstacle[10];
  for (int i = 0; i < obstacles.length; i++){
    obstacles[i] = new Obstacle(floor(random(width)), floor(random(height/4)));
  }
}

int x = 0;
void draw(){
  background(0);
  bot.show();
  bot.physics();
  bot.move();
  for (int i = 0; i < obstacles.length; i++){
    obstacles[i].show();
  }
}
