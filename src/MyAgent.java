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
	int[][] net;
	
	Position dest;
	AgentState goal = null; 
	Deque<String> actionStack;
	boolean firstInit;
	GlobalPerceptMap myWorld;
	AgentState myState;
	
		
	public MyAgent(int height, int width) {
		firstInit = true;
		dest = new Position(10,7);
		actionStack = new ArrayDeque<String>();
	}
	
	
	public static Comparator<AgentState> agentStateComparator = new Comparator<AgentState>(){
		
		@Override
		public int compare(AgentState s0, AgentState s1) {
			// TODO Auto-generated method stub
			return (int) (s0.compVal() - s1.compVal());
		}
    };
	
	/*
	private AgentState Astar(int[][] arr, AgentState initState, Position destination){
		if (arr[initState.pos.r][initState.pos.c] == 2) return null;
		
		Queue open = new PriorityQueue<AgentState>(1000, agentStateComparator);
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
						if (!open.contains(pom)){
							open.add(pom);
						}
						else{
							java.util.Iterator pqit = open.iterator(); 
							while(pqit.hasNext()){
								AgentState openElem = (AgentState)pqit.next();
								if (openElem.equals(pom) && (openElem.dist > pom.dist)){
									openElem.prevAction = pom.prevAction;
									openElem.prevState = pom.prevState;
									openElem.dist = pom.dist;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	*/
	
	public void act(){			
		/* ZACIATOK MIESTA PRE VAS KOD */
		
		if (firstInit){
			this.myWorld = new GlobalPerceptMap(getPerceptSize());
			this.myState = 
					new AgentState(
							new Position(40, 40), // current position (global)
							getOrientation(), // current orientation
							myWorld, // map of World
							null, // previous state
							null, // previous action
							0, // distance from start (in number of actions)
							new Position(getPerceptSize(), getPerceptSize()) // destination - we have none
					);
			firstInit = false;
		}
		
		myWorld.update(myState, percept());
		
		System.out.println(myWorld + "\n \n");
		
		Random generator = new Random();
		int rand = generator.nextInt(3);
		//System.out.println(rand);
		if (rand == 0){
			if (myState.getFW() != null){
				myState = myState.getFW();
				moveFW();
				System.out.println("AAA");
			}
			return;
		}
		else if (rand == 1){
			myState = myState.rotateLEFT();
			turnLEFT();
			return;
		}
		else if (rand == 2){
			myState = myState.rotateRIGHT();
			turnRIGHT();
			return;
		}
	}
	
}
