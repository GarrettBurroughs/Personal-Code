
public class AutoDriveState implements AutoState{
	static int drivePos;
	int distance;
	int motorPower;
	
	public AutoDriveState(int distance, int motorPower){
		this.distance = distance;
		this.motorPower = motorPower;
	}
	@Override
	public void start() {
		motorPower = 2 * motorPower;
	}

	@Override
	public boolean nextState() {
		if(drivePos >= distance){
			return true;
		}
		return false;
	}

	@Override
	public void end() {
		motorPower = 0;
		
	}
	@Override
	public void run() {
		drivePos += motorPower;
		System.out.println(drivePos);
	}

}  
