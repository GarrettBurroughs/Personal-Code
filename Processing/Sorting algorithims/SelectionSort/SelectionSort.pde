int[] items;
int scl;
int current;
boolean done = false;

void setup(){
  size(600, 400);
  items = new int[300];
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
  int max = current;
  background(0);
  for(int i = 1; i < items.length; i++){
    rect((i) * scl, height - items[i], scl, items[i]);
  }
  for(int i = 0; i < current; i++){
    if(items[i] > items[max]){
      max = i;
    }
  }
  swap(items, current, max);
  if(current > 1){
    current = current - 1;
  }else{
    done = true;
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
