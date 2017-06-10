int scl = 100;
ArrayList<Block> blocks = new ArrayList<Block>();
boolean pressed;
int dir;
void setup(){
  background(0);
  size(400, 400);
}

void draw(){
  for (int i = 0; i < 4; i++){
    for(int j = 0; j < 4; j++){
      rect(i * scl, j * scl, scl, scl);
    }
  }
  if (pressed){
    update();
    pressed = false;
    Block block = new Block();
    block.x = floor(random(4)) * scl;
    block.y = floor(random(4)) * scl;
    block.val = floor(random(2) + 1);
    blocks.add(block);

  }
  for (int i = 0; i < blocks.size(); i++){
    Block dispBlock = blocks.get(i);
    dispBlock.show();
  }

}

void update(){
  for (int i = 0; i < blocks.size(); i++){
    int pos = blocks.get(i).y/scl;
    switch(dir){
      case 1:
        for(int j = 0; j < blocks.size(); j++){
          if(blocks.get(j).x * scl == blocks.get(i).x * scl && blocks.get(j).y * scl < blocks.get(i).y * scl){
            pos--;
          }
        }
        blocks.get(i).y -= pos * scl;
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
    }
  }
}

void keyPressed(){
  pressed = true;
  if (keyCode == UP){
    dir = 1;
  }
  if (keyCode == DOWN){
    dir = 2;
  }
  if (keyCode == RIGHT){
    dir = 3;
  }
  if (keyCode == LEFT){
    dir = 4;
  }
}
