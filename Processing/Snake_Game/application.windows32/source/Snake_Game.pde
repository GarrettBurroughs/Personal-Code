Snake snake = new Snake();
Food food = new Food();
PFont f;
String dir;
int hold = 0;
boolean dispMessage = false;

void setup(){
  size(1200, 800);
  snake.x = 1;
  snake.y = 1;
  frameRate(10);
  food.pickLocation();
  f = createFont("Arial",16,true);
}


void draw(){ 
  background(20, 43, 173);
  textFont(f,16);
  text("score: "+snake.len, width - 100, height - 10);
  snake.move(dir);
  snake.update();
  snake.collision();
  snake.grow();
  snake.isDead();
  food.isEaten();
  snake.show();
  food.show();
}


void keyPressed(){
    if (keyCode == UP && dir != "DOWN" && !snake.hasDir){
      dir = "UP";
      snake.hasDir = true;
    }
    if (keyCode == DOWN && dir != "UP" && !snake.hasDir){
      dir = "DOWN";
      snake.hasDir = true;
    }
    if (keyCode == RIGHT && dir != "LEFT" && !snake.hasDir){
      dir = "RIGHT";
      snake.hasDir = true;
    }
    if (keyCode == LEFT && dir != "RIGHT" && !snake.hasDir){
      dir = "LEFT";
      snake.hasDir = true;
    }
    if (key == 'r'){
      food.pickLocation();
    }
    if (key == 'z'){
      hold += 1;
    }
    if (key == 'z' && hold == 20){
      snake.len += 30;
      hold = 0;
    }
}