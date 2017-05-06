PID pid;
Bot bot;

void setup(){
  size(640, 480);
  pid = new PID(.01, 0, 0);
  bot = new Bot();
  pid.setTarget(height/2);
  background(0);
}

void draw(){
  //background(0);
  pid.setPosition(bot.y);
  bot.render();
  bot.move(pid.control());

}

double sigmoid(double n){
  return 1/(1 + exp((float)n));
}
