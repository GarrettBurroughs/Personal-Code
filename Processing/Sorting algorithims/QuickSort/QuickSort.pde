int[] items;
int scl;

void setup(){
  size(1200, 800);
  items = new int[600];
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
  quicksort(items, 1, items.length);
  println("done");
}
void quicksort(int[] array, int hi, int lo){
  if(lo < hi){
    int p = partition(array, lo, hi);
    quicksort(array, lo, p - 1);
    quicksort(array, p + 1, hi);
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

int partition(int[] array, int lo, int hi){
  int pivot = array[hi];
  int i = lo - 1;
  for(int j = lo; j < hi - 1; j++){
    if (array[j] <= pivot){
      i = i + 1;
      swap(array, i, j);
    }
  }
  swap(array, i + 1, hi);
  return i + 1;
}
