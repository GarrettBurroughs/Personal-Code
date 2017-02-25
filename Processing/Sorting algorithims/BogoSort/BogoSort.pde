int[] items;
int scl;

void setup(){
  size(1200, 800);
  items = new int[6];
  scl = width / items.length;
  for(int i = 1; i < items.length; i++){
    items[i] = items.length - i;
  }
  shuffle(items);
}

void draw(){
  noStroke();
  background(0);
  for(int i = 1; i < items.length; i++){
    rect((i) * scl, height - items[i], scl, items[i]);
  }

  //ALGORITHM
  if(!(inOrder(items))){
    shuffle(items);
  }else{
    noLoop();
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
boolean inOrder(int[] array){
  boolean ordered = true;
  for(int i = 0; i < array.length - 1; i++){
    if(!(array[i] < array[i + 1])){
      ordered = false;
    }
  }
  return ordered;

}
