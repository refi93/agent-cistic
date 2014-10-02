import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public  class MyAgent extends Agent{	
	int[][] net;
	
	Position dest;
	AgentState goal = null; 
	Deque<String> actionStack;
	boolean firstInit;
		
	public MyAgent(int height, int width) {
		firstInit = true;
		dest = new Position(20,17);
		actionStack = new ArrayDeque<String>();
	}
	
	
	private AgentState BFS(int[][] arr, AgentState initState, Position destination){
		if (arr[initState.pos.r][initState.pos.c] == 2) return null;
		
		Queue open = new LinkedList<AgentState>();
		open.add(initState); // states to visit
		
		Set<AgentState> close = new HashSet<AgentState >(); // visited states
		
		
		while (!open.isEmpty()){
			AgentState curState = (AgentState) open.remove();
			if (curState.pos.equals(destination)) {
				return curState;
			}
			
			if (!close.contains(curState)){
				close.add(curState);
				AgentStateIterator it = new AgentStateIterator(curState);
				while(it.hasNext()){
					AgentState pom = it.next();
					if ((pom != null) && (!close.contains(pom))){
						open.add(pom);
					}
				}
			}
		}
		return null;
	}
	
	
	public void act(){			
		/* ZACIATOK MIESTA PRE VAS KOD */
		
		if (firstInit){
			net = percept();	// aktualny percept
						// zadefinovane su konstanty CLEAN=0, DIRTY=1, WALL=2)	
			goal = BFS(
						net, 
						new AgentState(
								new Position( // pos
										getPerceptSize(), 
										getPerceptSize()
									), 
								getOrientation(), // orientation
								net, // map
								null, // prevState
								null // prevAction
							), 
						dest
					);
			AgentState cur = goal;
			while(cur.prevAction != null){
				actionStack.push(cur.prevAction);
				cur = cur.prevState;
			}
			
			firstInit = false;
		}
		if(!actionStack.isEmpty()){
			String action = actionStack.pop();
			if (action == "rotate"){
				turnLEFT();
				return;
			}
			else if (action == "forward"){
				moveFW();
				return;
			}
		}
	}
	
}
