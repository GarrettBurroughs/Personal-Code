int[] items;
int scl;
int current;
boolean sorted = false; //Keeps tack of weather or not the list is sorted
int delay = 5; //delay in ms for each item to be sorted

void setup(){
  size(1200, 800);
  items = new int[600];
  scl = width / items.length;
  for(int i = 1; i < items.length; i++){
    items[i] = items.length - i;
  }
  shuffle(items);
  frameRate(60);
  thread("sort");
  current = items.length - 1;
}

void draw(){
  noStroke();
  background(0);
  if(isSorted(items)){
    fill(0, 255, 0);
  }else{
    fill(255);
  }
  for(int i = 1; i < items.length; i++){
    float itemHeight = map(items[i], 1, items.length, 1, height);
    rect((i) * scl, height - itemHeight, scl, itemHeight);
  }
}

void swap(int[] array, int a, int b){
  int temp = array[a];
  array[a] = array[b];
  array[b] = temp;
}

boolean isSorted(int[] array){
  for(int i = 0; i < items.length - 1; i++){
    if(items[i] > items[i + 1]){
      return false;
    }
  }
  return true;
}

void shuffle(int[] array){
  for(int i = 0; i < array.length; i++){
    swap(array, i, floor(random(array.length - 1)));
  }
}

void sort(){
  boolean sorted = false;
  while(!sorted){
    //Sorting Code Goes Here
    int max = current;
    for(int i = 0; i < current; i++){
      if(items[i] > items[max]){
        max = i;
      }
    }
    swap(items, current, max);
    if(current > 1){
      current = current - 1;
    }else{
      fill(0, 255, 0);
    }
    sorted = isSorted(items);
    delay(delay);
  }
}

void delay(int delay){
  int mills = millis();
  while(millis() < mills + delay){};
}
