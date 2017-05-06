import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class PIDController extends PApplet {

PID pid;
Bot bot;

public void setup(){
  
  pid = new PID(.01f, 0, 0);
  bot = new Bot();
  pid.setTarget(height/2);
  background(0);
}

public void draw(){
  //background(0);
  pid.setPosition(bot.y);
  bot.render();
  bot.move(pid.control());

}

public double sigmoid(double n){
  return 1/(1 + exp((float)n));
}
class Bot{
  float x = 0;
  float y = 0;
  float ySpeed;
  float xSpeed = 1;
  public void move(double acceleration){
    y = y + ySpeed;
    x = x + xSpeed;
    ySpeed += acceleration *.1f;
  }

  public void render(){
    ellipse(x,y,20, 20);
  }
}
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
    h = .001f;
  }

  public void setTarget(float t){
    target = t;
  }

  public void setPosition(float p){
    position = p;
  }

  public double control(){
    error = target - position;
    double deriv = (error - prevError)/h;
    prevError = error;
    totalError += error;
    return (P * error + I * totalError + D * deriv);
  }




}
  public void settings() {  size(640, 480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "PIDController" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
