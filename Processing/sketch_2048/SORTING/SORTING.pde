int[] values;
int size = 20;
int scl;
void setup(){
  size(400, 400);
  scl = 
  values = new int[size];
  for(int i = 0; i < size; i++){
    values[i] = floor(random(20));
  }
}

void draw(){
  for(int i = 0; i< size; i++){
    rect(1, 1, 1, 1);
  }
}