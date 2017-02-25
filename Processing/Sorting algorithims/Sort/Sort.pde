int[] items;
int scl;
int currentHi;
int currentLo;
boolean done = false;

void setup(){
  size(1200, 800);
  items = new int[600];
  scl = width / items.length;
  for(int i = 1; i < items.length; i++){
    items[i] = items.length - i;
  }
  shuffle(items);
  currentHi = items.length - 1;
  currentLo = 0;
  frameRate(60);
}

void draw(){
  noStroke();
  int max = currentHi;
  int min = currentLo;
  background(0);
  for(int i = 1; i < items.length; i++){
    rect((i) * scl, height - items[i], scl, items[i]);
  }
  for(int i = currentLo; i < currentHi; i++){
    if(items[i] > items[max]){
      max = i;
    }else if(items[i] < items[min]){
      min = i;
    }
  }
  swap(items, currentHi, max);
  swap(items, currentLo, min);
  if(currentHi > 1 && currentLo < items.length && currentHi > currentLo){
    currentHi = currentHi - 1;
    currentLo = currentLo + 1;
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
