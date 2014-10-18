import java.util.ArrayDeque;

import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.*;

import javax.swing.text.html.HTMLDocument.Iterator;

public  class MyAgent extends Agent{	
	Deque<String> actionStack;
	boolean firstInit;
	GlobalPerceptMap myWorld;
	AgentState myState;
	
		
	public MyAgent(int height, int width) {
		firstInit = true;
		actionStack = new ArrayDeque<String>();
	}
	
	
	public static Comparator<AgentState> agentStateComparator = new Comparator<AgentState>(){
		
		@Override
		public int compare(AgentState s0, AgentState s1) {
			// TODO Auto-generated method stub
			return (int) (s0.compVal() - s1.compVal());
		}
    };
    
	
    // find nearest dirty / unknown using BFS
	private AgentState BFS(GlobalPerceptMap myWorld, AgentState initState){
		initState.dist = 0;
		if (myWorld.getMap()[initState.pos.r][initState.pos.c] == World.WALL) return null;
		
		
		
		Queue open = new LinkedList<AgentState>();
		open.add(initState); // states to visit
		
		Set<AgentState> close = new HashSet<AgentState>(); // visited states
		
		int counter = 0;
		while (!open.isEmpty()){
			AgentState curState = (AgentState) open.remove();
			int curTile = myWorld.getMap()[curState.pos.r][curState.pos.c];
			if (curState.pos.equals(curState.dest)) {
				return curState;
			}
			else if (curState.dest.equals(new Position(-1, -1))){ // so we don't have specific destination
				if (curTile == World.DIRTY){
					return curState;
				}
				if (curTile == Constants.UNKNOWN){ // if we are on not visited position, return path to it
					return curState.prevState; // aby sme nahodou chudaka neposlali do steny
				}
			}
			
			
			// nechceme rozvijat unknown policka dalej
			if ((curTile != Constants.UNKNOWN) && !close.contains(curState)){
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
		
		int[][] net = percept();
		
		
		if (firstInit){
			this.myWorld = new GlobalPerceptMap(getPerceptSize());
			
			this.myState = 
					new AgentState(
							new Position(Constants.WORLD_MAX_SIZE, Constants.WORLD_MAX_SIZE), // current position (global)
							getOrientation(), // current orientation
							myWorld, // map of World
							null, // previous state
							null, // previous action
							0, // distance from start (in number of actions)
							new Position(-1, -1) // destination - we have none
					);
			firstInit = false;
		}
		
		//update world with the current perception
		myWorld.update(myState, net);
		
		if (myWorld.getMap()[myState.pos.r][myState.pos.c] == World.DIRTY){
			suck();
			return;
		}
		else if (actionStack.size() == 0){
			// reset myState previous actions
			myState.prevAction = null;
			myState.prevState = null;
			
			
			
			AgentState goal = null;
			
			goal = BFS(
				myWorld, 
				myState
			);
			
			
			AgentState cur = goal;
			
			
			if (cur == null){
				halt(); // we have no position to visit
				return;
			}
			
			while(cur.prevAction != null){
				actionStack.push(cur.prevAction);
				cur = cur.prevState;
			}
		}
		else{
			String action = actionStack.pop();
			if (action == "rotateLEFT"){
				turnLEFT();
				myState = myState.rotateLEFT();
				return;
			}
			else if (action == "rotateRIGHT"){
				turnRIGHT();
				myState = myState.rotateRIGHT();
				return;
			}
			else if (action == "forward"){
				moveFW();
				myState = myState.getFW();
				return;
			}
		}
		
		
	}
	
}
