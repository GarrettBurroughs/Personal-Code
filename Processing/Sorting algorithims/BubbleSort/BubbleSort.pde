int[] items;
int scl;
boolean done = false;

void setup(){
  size(600, 400);
  items = new int[300];
  scl = width / items.length;
  for(int i = 1; i < items.length; i++){
    items[i] = items.length - i;
  }
  shuffle(items);
}

void draw(){
  noStroke();
  boolean done = true;
  background(0);
  for(int i = 1; i < items.length; i++){
    rect((i) * scl, height - items[i], scl, items[i]);
  }
  for(int i = 0; i < items.length - 1; i++){
    if(items[i] > items[i + 1]){
      swap(items, i, i + 1);
      done = false;
    }
  }
  if(done){
    fill(0, 255, 0);
  }
}

void swap(int[] array, int a, int b){
  int temp = array[a];
  array[a] = array[b];
  array[b] = temp;
}

void shuffle(int[] array){
  for(int i = 0; i < array.length; i++){
    swap(array, i, floor(random(array.length - 1)));
  }
}
