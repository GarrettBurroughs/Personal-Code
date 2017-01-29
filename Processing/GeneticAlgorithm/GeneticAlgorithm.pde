Bot bot;

void setup(){
  size(640,480);
  bot = new Bot();

}

int x = 0;
void draw(){
  background(0);
  bot.show();
  bot.physics();
}

void mouseClicked(){
  bot.jump();
}
