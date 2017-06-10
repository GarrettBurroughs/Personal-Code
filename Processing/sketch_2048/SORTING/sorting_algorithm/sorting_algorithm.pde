int elements = 30;
int[] list;
int scl;
int counter;
int max;
int min;
void setup(){
  size(600, 600);
  list = new int[elements]; 
  for(int i = 0; i < elements; i++){
    list[i] = floor(random(1,elements));
  }
  scl = floor(width/elements);
  counter = 0;
}

void draw(){
  min = counter;
  max = elements - (counter + 1);
  fill(255);
  background(0);
  for(int i = counter; i < elements - (counter + 1); i++){
    rect(i*scl, height - scl*list[i], scl, scl*list[i]);
  }
  if (counter < elements/2){
    for(int i = 0; i < elements; i++){
      if(list[i] > list[max]){
        max = i;
      }
      else if(list[i] < list[min]){
        min = i;
      }
    }
    swap(min, counter);
    swap(max, elements - (counter + 1));
    counter++;
  }
}


void swap(int index1, int index2){
  int mem = list[index1];
  list[index1] = list[index2];
  list[index2] = mem;
}