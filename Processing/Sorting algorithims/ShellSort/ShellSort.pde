int[] items;
int scl;
int current;
int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};

void setup(){
  size(1200, 800);
  items = new int[600];
  scl = width / items.length;
  for(int i = 1; i < items.length; i++){
    items[i] = items.length - i;
  }
  shuffle(items);
  current = items.length - 1;
  frameRate(60);
}

void draw(){
  noStroke();
  background(0);
  for(int i = 1; i < items.length; i++){
    rect((i) * scl, height - items[i], scl, items[i]);
  }
  for(int i = 1; i < gaps; i++){
    for(int j = gaps[i], i < items.lenght)
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
