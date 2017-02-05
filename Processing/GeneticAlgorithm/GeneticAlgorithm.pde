Bot bot;

void setup(){
  size(1280,720);
  bot = new Bot();
  bot.newBot();
  println(bot.DNA);
}

int x = 0;
void draw(){
  background(0);
  bot.show();
  bot.physics();
  bot.move();
}

void mouseClicked(){

}
