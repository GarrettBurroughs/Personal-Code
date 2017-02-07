class Obstacle{
  int x;
  int tallness;

  void show(){
    rect(x, height - tallness, 10, tallness);
  }

  public Obstacle(int x, int tallness){
    this.x = x;
    this.tallness = tallness;
  }
}
