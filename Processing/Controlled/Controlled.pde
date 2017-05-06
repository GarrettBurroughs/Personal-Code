Bot bot;
float direction;
void setup(){
  size(640, 480);
  background(255);
  bot = new Bot();
  direction = 0;
}

void draw(){
  bot.move(direction);
  bot.render();
}
void keyPressed(){
  if(keyCode == UP){
    direction += 1;
  }
  if(keyCode == DOWN){
    direction -= 1;
  }
}
