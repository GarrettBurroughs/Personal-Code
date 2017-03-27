
public class AutoModePrograms {
	public AutoStateMachine autoMode1 = new AutoStateMachine(new AutoDriveState(100, 2), new AutoDriveState(200, 1), new AutoDriveState(300, 50), new AutoDriveState(400, 25));
	public void main(String[] args) {
		autoMode1.autoRun();
	}
}
 