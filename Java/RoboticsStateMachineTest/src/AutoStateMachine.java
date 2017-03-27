
public class AutoStateMachine {
	AutoState[] states;
	int state;
	public AutoStateMachine(AutoState ... states){
		this.states = states;
		state = 0;
	}
	
	public AutoStateMachine setState(int state){
		this.state = state;
		return this;
	}
	
	public AutoStateMachine runState(int state){
		states[state].start();
		return this;
	}
	
	public AutoStateMachine runCurrent(){
		states[state].start();
		return this;
	}
	public AutoStateMachine autoRun(){
		while (true){
			states[state].run();
			if(states[state].nextState()){
				if(state == states.length - 1){
					break;
				}
				state++;
			}
		}
		return this;
	}
}
