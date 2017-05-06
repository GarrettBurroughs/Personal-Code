class PID{


  //weights
  float P, I, D;
  double h;

  private float position, target;
  double totalError, prevError, error;

  public PID(float P, float I, float D){
    this.P = P;
    this.I = I;
    this.D = D;
    h = .001;
  }

  void setTarget(float t){
    target = t;
  }

  void setPosition(float p){
    position = p;
  }

  double control(){
    error = target - position;
    double deriv = (error - prevError)/h;
    prevError = error;
    totalError += error;
    return (P * error + I * totalError + D * deriv);
  }




}
