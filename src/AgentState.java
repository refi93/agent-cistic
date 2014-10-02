
public class AgentState {
	int orientation;
	Tuple<Integer, Integer> position;
	
	public AgentState(Tuple<Integer, Integer> position, int orientation){
		this.position = position;
		this.orientation = orientation;
	}
}
